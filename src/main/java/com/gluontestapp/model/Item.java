//<editor-fold defaultstate="collapsed" desc="Copyright">
/*
 *   File:       Item.java
 *   Created:    11.07.2016, 11:00:17
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
package com.gluontestapp.model;

import javafx.beans.property.*;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author   <a href="mailto:sk@tunnelsoft.com">Sven Kalweit</a>
 * @date 11.07.2016, 11:00:17
 * @version 0.1
 */
public class Item {

    private IntegerProperty id;

    private StringProperty value;

    private StringProperty date;

    public final void setId(Integer value) {
        id.set(value);
    }

    @XmlElement(name = "Id")
    public final Integer getId() {
        return id.get();
    }

    public final IntegerProperty idProperty() {
        return id;
    }

    public final void setValue(String value) {
        this.value.set(value);
    }

    @XmlElement(name = "Value")
    public final String getValue() {
        return value.get();
    }

    public final StringProperty valueProperty() {
        return value;
    }

    public final void setDate(String value) {
        date.set(value);
    }

    @XmlElement(name = "Date")
    public final String getDate() {
        return date.get();
    }

    public final StringProperty dateProperty() {
        return date;
    }

    /**
     * Creates a new instance of Item
     *
     * @param id
     * @param value
     * @param date
     */
    public Item(int id, String value, String date) {
        this();
        this.id.set(id);
        this.value.set(value);
        this.date.set(date);
    }

    public Item() {
        id = new SimpleIntegerProperty();
        value = new SimpleStringProperty();
        date = new SimpleStringProperty();
    }

    @Override
    public String toString() {
        return id.get() + " " + value.get();
    }

}
