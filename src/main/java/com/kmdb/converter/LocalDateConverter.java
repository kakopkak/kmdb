package com.kmdb.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Long> {
    @Override
    public Long convertToDatabaseColumn(LocalDate attribute) {
        return attribute == null ? null :
                attribute.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    @Override
    public LocalDate convertToEntityAttribute(Long dbData) {
        return dbData == null ? null :
                Instant.ofEpochMilli(dbData).atZone(ZoneId.systemDefault()).toLocalDate();
    }
}