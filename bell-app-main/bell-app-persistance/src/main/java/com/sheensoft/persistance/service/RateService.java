package com.sheensoft.persistance.service;

import com.sheensoft.domain.Rate;
import com.sheensoft.domain.RateId;
import com.sheensoft.persistance.repository.RateRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class RateService {
    @Autowired
    RateRepository rateRepository;
    public Rate getUpdatedRate(String from, String to){
        List<Rate> rates = rateRepository.findByIdNew(from,to);
        //Checking if we have a currency and if is updated;
        Rate rate = new Rate();

        if (rates != null && rates.size() > 1){
            rate = rates.get(0);
        }

        Date now = Calendar.getInstance().getTime();
        Date rateDate = rate.getDate();

        if (rateDate == null || (now.getTime() - rateDate.getTime() >= 10*60*1000)) { //10 mins
            //getting new Date
            String factor = getNewDate(from, to);
            rate.setDate(Calendar.getInstance().getTime());
            rate.setRate(new BigDecimal(factor));
            RateId rateId = rate.getRateId();
            if (rateId == null){
                rateId = new RateId(from,to);
                rate.setRateId(rateId);
            }
            rateRepository.save(rate);
        }



        return rate;
    }

    public String getNewDate(String from, String to) {
        String apiKey = "9cc42d1b9d1af12a84e7225c77e67d4f";
        String urlString = "http://www.apilayer.net/api/live?access_key=9cc42d1b9d1af12a84e7225c77e67d4f&format=1";
        //urlString = urlString.replace(":FROM", from).replace(":TO", to);
        String factor = "0";
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                return factor;
            }
            InputStream response = conn.getInputStream();
            JSONObject responseJSON = new JSONObject(convertStreamToString(response));
            JSONObject rates = responseJSON.getJSONObject("quotes");
            factor = (rates != null) ? (rates.get(from + to)).toString() : "0";


        } catch (Exception e) {
            e.printStackTrace();
        }

        return factor;
    }

    private static String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
