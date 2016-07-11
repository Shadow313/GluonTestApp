package com.gluontestapp.views;

import com.gluonhq.charm.glisten.animation.BounceInRightTransition;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.gluonhq.connect.GluonObservableList;
import com.gluonhq.connect.converter.InputStreamIterableInputConverter;
import com.gluonhq.connect.provider.DataProvider;
import com.gluonhq.connect.provider.RestClient;
import com.gluontestapp.GluonTestApp;
import com.gluontestapp.ItemsIterableInputConverter;
import com.gluontestapp.model.Item;
import java.time.LocalDateTime;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

public class ListPresenter {

    @FXML
    private View list;

    public void initialize() {
        list.setShowTransitionFactory(BounceInRightTransition::new);

        list.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = MobileApplication.getInstance().getAppBar();
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e
                        -> MobileApplication.getInstance().showLayer(GluonTestApp.MENU_LAYER)));
                appBar.setTitleText("List");
            }
        });
        // create a RestClient to the specific URL
        RestClient restClient = RestClient.create()
                .method("GET")
                .host("http://192.168.0.70:8080")
                .path("/mobileTest");

        // create a custom Converter that is able to parse the response into a list of objects
        InputStreamIterableInputConverter<Item> converter = new ItemsIterableInputConverter<>(Item.class);
        // retrieve a list from the DataProvider
        GluonObservableList<Item> items = DataProvider.retrieveList(restClient.createListDataReader(converter));
        ListView<Item> lvItems = new ListView<>(items);
        lvItems.setCellFactory(lv -> new ListCell<Item>() {
            @Override
            protected void updateItem(Item item, boolean empty) {
                super.updateItem(item, empty);
                
                if(!empty) {
                    setText(item.getValue()+"\n"+item.getDate());
                } else {
                    setText(null);
                }
            }
        });
        list.setCenter(lvItems);
    }
}
