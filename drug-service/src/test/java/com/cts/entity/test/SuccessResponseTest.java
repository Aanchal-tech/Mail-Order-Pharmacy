package com.cts.entity.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cts.entity.SuccessResponse;

class SuccessResponseTest
{
    @BeforeEach
    void setUp() throws Exception {
    }
    
    @AfterEach
    void tearDown() throws Exception {
    }
    
    @Test
    void test() {
        final SuccessResponse successResponseOne = new SuccessResponse();
        successResponseOne.setResponseMessage("Success");
        Assertions.assertEquals((Object)"Success", (Object)successResponseOne.getResponseMessage());
    }
    
    @Test
    void testAllArgs() {
        final SuccessResponse successResponseOne = new SuccessResponse("Failure");
        Assertions.assertEquals((Object)"Failure", (Object)successResponseOne.getResponseMessage());
    }
}