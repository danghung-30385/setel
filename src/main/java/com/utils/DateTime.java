package com.utils;

import java.sql.Timestamp;

public class DateTime {
    public String createCurrentTimeStamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return  timestamp.toString();
    }

}
