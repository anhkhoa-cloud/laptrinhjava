package com.pickellbal.model;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
public class StringToLocalTimeConverter implements Converter<String, LocalTime> {
    
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    
    @Override
    public LocalTime convert(String source) {
        if (source == null || source.trim().isEmpty()) {
            return null;
        }
        
        try {
            // Thử parse với format HH:mm
            return LocalTime.parse(source, TIME_FORMATTER);
        } catch (Exception e) {
            try {
                // Nếu không được, thử parse với format mặc định HH:mm:ss
                return LocalTime.parse(source);
            } catch (Exception ex) {
                throw new IllegalArgumentException("Invalid time format: " + source, ex);
            }
        }
    }
}