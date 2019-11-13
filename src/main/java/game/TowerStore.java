package game;
/**
 * Area to buy different types of tower from. Manage all towerItem objects.
 */

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class TowerStore{
    private List<TowerStoreItem> storeItems = new ArrayList<>();
    private HBox store = new HBox();

    public TowerStore() {
        store.setLayoutX(150);
        store.setLayoutY(480);
        store.setPrefSize(650, 120);
        store.setPadding(new Insets(10, 12, 10,30));
        store.setSpacing(30);
        store.setStyle("-fx-background-color: #336699");
        this.addAllItems();
    }

    public void addAllItems() {
        this.addItem(new TowerStoreItem(Settings.NORMAL_TOWER_IMAGE));
        this.addItem(new TowerStoreItem(Settings.SNIPER_TOWER_IMAGE));
    }

    public void addItem(TowerStoreItem newItem) {
        int size = storeItems.size();
        storeItems.add(newItem);
        newItem.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("drag detected");
                Dragboard db = newItem.startDragAndDrop(TransferMode.ANY);

                /* put a string and image on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putImage(newItem.getImage());
                content.putString(Integer.toString(size));
                db.setContent(content);
                mouseEvent.consume();
            }
        });
        store.getChildren().add(newItem);
    }

    public HBox getStore() {
        return store;
    }

    public List<TowerStoreItem> getStoreItems() {
        return storeItems;
    }
}
