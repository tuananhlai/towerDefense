package game;
/**
 * Area to buy different types of tower from. Manage all towerItem objects.
 */

import game.tower.NormalTower;
import game.tower.SniperTower;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.*;
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

    /**
     * Add more tower options here.
     */
    public void addAllItems() {
        this.addItem(new TowerStoreItem(Settings.NORMAL_TOWER_IMAGE,
                Settings.NORMAL_TOWER_PRICE,
                Settings.NORMAL_TOWER_DAMAGE,
                Settings.NORMAL_TOWER_RANGE,
                Settings.NORMAL_TOWER_FIRE_RATE));
        this.addItem(new TowerStoreItem(Settings.SNIPER_TOWER_IMAGE,
                Settings.SNIPER_TOWER_PRICE,
                Settings.SNIPER_TOWER_DAMAGE,
                Settings.SNIPER_TOWER_RANGE,
                Settings.SNIPER_TOWER_FIRE_RATE));
    }

    public void addItem(TowerStoreItem newItem) {
        int towerCode = storeItems.size();
        storeItems.add(newItem);
        newItem.setHandleEvents(towerCode);
        store.getChildren().add(newItem);
    }

    /**
     * Set event listener for game field so that it knows when player want to plant tower
     * @param gameField game field
     */
    public void setBuyEvent(Canvas gameField) {
        gameField.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                if (dragEvent.getGestureSource() != gameField && dragEvent.getDragboard().hasString() && dragEvent.getDragboard().hasImage()) {
                    /* allow for both copying and moving, whatever user chooses */
                    dragEvent.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }

                dragEvent.consume();
            }
        });
        gameField.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                System.out.println("Drag dropped at " + dragEvent.getX() + " " + dragEvent.getY());

                Dragboard db = dragEvent.getDragboard();
                boolean isSuccess = false;
                if (db.hasImage() && db.hasString()) {
                    isSuccess = true;
                    plantTower(db.getString(), dragEvent.getX(), dragEvent.getY());
                }
                dragEvent.setDropCompleted(isSuccess);
                dragEvent.consume();
            }
        });
    }

    private void plantTower(String towerCode, double mouseX, double mouseY) {
        int rowIndex = (int) mouseY / Settings.TILE_HEIGHT;
        int colIndex = (int) mouseX / Settings.TILE_WIDTH;
        Vector2D plantPos = new Vector2D(colIndex * Settings.TILE_WIDTH, rowIndex * Settings.TILE_HEIGHT);
        if (GameField.unusablePositions.contains(plantPos)) {
            return;
        }
        switch (towerCode) {
            case Settings.NORMAL_TOWER_ITEM: {
                new NormalTower(colIndex * Settings.TILE_WIDTH, rowIndex * Settings.TILE_HEIGHT);
                break;
            }
            case Settings.SNIPER_TOWER_ITEM: {
                new SniperTower(colIndex * Settings.TILE_WIDTH, rowIndex * Settings.TILE_HEIGHT);
                break;
            }
        }

    }

    public HBox getStore() {
        return store;
    }

    public List<TowerStoreItem> getStoreItems() {
        return storeItems;
    }
}
