package com.sheensoft.persistance.service;

import com.sheensoft.domain.Rate;
import com.sheensoft.persistance.repository.RateRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class RateServiceTest {

    @Mock
    RateRepository rateRepository;

    @InjectMocks
    RateService rateService;

    @Test
    public void getNewRate() {
        Rate rate = rateService.getUpdatedRate("USD","EUR");
        assertNotEquals("0",rate);
    }
}