package com.prasad.roostack.mapper.converter;

import org.junit.Test;

import java.util.Date;

/**
 * Created by lgunti on 002, Dec 02.
 */
public class StringToDateConverterTest {

    @Test
    public void testStringToDateConverter() {
        StringToDateConverter stringToDateConverter = new StringToDateConverter("dd-MM-yyyy");
        //Type<Date> type = new Type<Date>();
        Date date = stringToDateConverter.convertTo("01-12-2014", null );
        date.toString();
    }
}
