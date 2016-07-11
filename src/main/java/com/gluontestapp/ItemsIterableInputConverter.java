//<editor-fold defaultstate="collapsed" desc="Copyright">
/*
 *   File:       ItemsIterableInputConverter.java
 *   Created:    11.07.2016, 13:07:59
 *   Author:     Sven Kalweit
 *   mailto:    sk@tunnelsoft.com
 *   Copyright Babendererde Engineers GmbH
 *   Markt 2
 *   D-23611 Bad Schwartau
 *   Germany
 * 
 *   The copyright to the computer program(s) herein
 *   is the property of Babendererde Ingenieure GmbH, Germany.
 *   The program(s) may be used and/or copied  only with
 *   the written permission of  Babendererde Engineers GmbH
 *   or in accordance with the terms and conditions
 *   stipulated  in the agreement/contract under which
 *   the  program(s) have been supplied.
 * 
 *   Copyright 2008 Version 1.1
 */
//</editor-fold>
package com.gluontestapp;

import com.gluonhq.connect.converter.InputStreamIterableInputConverter;
import com.gluonhq.connect.converter.JsonConverter;
import com.gluontestapp.model.Item;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 *
 * @author   <a href="mailto:sk@tunnelsoft.com">Sven Kalweit</a>
 * @param <E>
 * @date 11.07.2016, 13:07:59
 * @version 0.1
 */
public class ItemsIterableInputConverter extends InputStreamIterableInputConverter<Item> implements Iterator<Item> {

    private JsonArray jsonArray;
    private int index;
//    private final JsonConverter<Item> converter;
    private final DateTimeFormatter dateParser;

    /**
     * Creates a new instance of ItemsIterableInputConverter
     * @param targetClass
     */
    public ItemsIterableInputConverter() {
//        converter = new JsonConverter<>(targetClass);
        dateParser = DateTimeFormatter.ISO_DATE_TIME;    
    }

    @Override
    public boolean hasNext() {
        return index < jsonArray.size();
    }

    @Override
    public Item next() {
        JsonObject jsonObject = jsonArray.getJsonObject(index++);
        int id = jsonObject.getInt("Id");
        String value = jsonObject.getString("Value");
        String dateStr = jsonObject.getString("Date");
        LocalDateTime date = LocalDateTime.parse(dateStr, dateParser);
        return new Item(id, value, date);
    }

    @Override
    public Iterator<Item> iterator() {
        index = 0;

        try (JsonReader reader = Json.createReader(getInputStream())) {
            jsonArray = reader.readArray();
        }

        return this;
    }

}
