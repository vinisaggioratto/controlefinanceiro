package com.vs.cf.Utils;

import java.sql.Timestamp;
import java.time.Instant;

public class CurrentDate {

    public static Timestamp getCurrentTimestamp() {
        Instant instant = Instant.now();
        return Timestamp.from(instant);
    }
}
