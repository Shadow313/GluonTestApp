//<editor-fold defaultstate="collapsed" desc="Copyright">
/*
 *   File:       ItemCell.java
 *   Created:    11.07.2016, 11:09:23
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
package com.gluontestapp.views;

import com.gluonhq.charm.glisten.control.CharmListCell;
import com.gluonhq.charm.glisten.control.ListTile;
import com.gluontestapp.model.Item;

/**
 *
 * @author   <a href="mailto:sk@tunnelsoft.com">Sven Kalweit</a>
 * @date 11.07.2016, 11:09:23
 * @version 0.1
 */
public class ItemCell extends CharmListCell<Item> {

    private final ListTile tile;

    /**
     * Creates a new instance of ItemCell
     */
    public ItemCell() {
        super();
        tile = new ListTile();
        setText(null);
    }

    @Override
    public void updateItem(Item item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null && !empty) {
            tile.textProperty().setAll(item.getValue(), item.getDate());
        }
        setGraphic(tile);
    }

}
