package com.gluontestapp;

import com.gluontestapp.views.WelcomeView;
import com.gluontestapp.views.ListView;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.Avatar;
import com.gluonhq.charm.glisten.control.NavigationDrawer;
import com.gluonhq.charm.glisten.control.NavigationDrawer.Item;
import com.gluonhq.charm.glisten.layout.layer.SidePopupView;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.gluonhq.charm.glisten.visual.Swatch;
import com.gluonhq.charm.glisten.visual.SwatchElement;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GluonTestApp extends MobileApplication {

    public static final String PRIMARY_VIEW = HOME_VIEW;
    public static final String SECONDARY_VIEW = "List View";
    public static final String MENU_LAYER = "Side Menu";
    
    @Override
    public void init() {
        addViewFactory(PRIMARY_VIEW, () -> new WelcomeView(PRIMARY_VIEW).getView());
        addViewFactory(SECONDARY_VIEW, () -> new ListView(SECONDARY_VIEW).getView());
        
        NavigationDrawer drawer = new NavigationDrawer();
        
        NavigationDrawer.Header header = new NavigationDrawer.Header("TPC",
                "Testapp",
                new Avatar(21, new Image(GluonTestApp.class.getResourceAsStream("/icon.png"))));
        drawer.setHeader(header);
        
        final Item primaryItem = new Item("Welcome", MaterialDesignIcon.HOME.graphic());
        final Item secondaryItem = new Item("List", MaterialDesignIcon.VIEW_LIST.graphic());
        drawer.getItems().addAll(primaryItem, secondaryItem);
        
        drawer.selectedItemProperty().addListener((obs, oldItem, newItem) -> {
            hideLayer(MENU_LAYER);
            switchView(newItem.equals(primaryItem) ? PRIMARY_VIEW : SECONDARY_VIEW);
        });
        
        addLayerFactory(MENU_LAYER, () -> new SidePopupView(drawer));
    }

    @Override
    public void postInit(Scene scene) {
        Swatch.GREEN.assignTo(scene);

        scene.getStylesheets().add(GluonTestApp.class.getResource("style.css").toExternalForm());
        ((Stage) scene.getWindow()).getIcons().add(new Image(GluonTestApp.class.getResourceAsStream("/icon.png")));
    }
}
