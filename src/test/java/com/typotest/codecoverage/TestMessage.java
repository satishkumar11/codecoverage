package com.typotest.codecoverage;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class TestMessage {

    @Test
    public void testName() {
        Message obj = new Message();
        Assertions.assertEquals("Hello Ji", obj.getMessage("Ji"));
    }
}
