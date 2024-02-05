package com.paipeng.cptesseractocrapp.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class TesseractUtilTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void decode() {
        String imageFileName = Objects.requireNonNull(TesseractUtilTest.class.getClassLoader().getResource("images/test.png")).getFile();
        String output = TesseractUtil.decode(imageFileName);
        System.out.println("output: " + output);
    }
}