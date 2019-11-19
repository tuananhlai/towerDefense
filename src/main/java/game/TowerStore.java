package game;
/**
 * Area to buy different types of tower from. Manage all towerItem objects.
 */

import game.screen.PlayScreen;
import game.tower.*;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

import static game.screen.PlayScreen.graphicsContextPro;

public class TowerStore{
    private List<TowerStoreItem> storeItems = new ArrayList<>();
    private HBox store = new HBox();
    public static boolean isDrag = false;

    public TowerStore() {
        store.setLayoutX(150);
        store.setLayoutY(480);
        store.setPrefSize(650, 120);
        store.setPadding(new Insets(20, 12, 10,30));
        store.setSpacing(30);
        store.setStyle("-fx-background-color: #efefef");
        this.addAllItems();
    }

    /**
     * Add more tower options here.
     */
    private void addAllItems() {
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
        this.addItem(new TowerStoreItem(Settings.MACHINE_GUN_TOWER_IMAGE,
                Settings.MACHINE_GUN_TOWER_PRICE,
                Settings.MACHINE_GUN_TOWER_DAMAGE,
                Settings.MACHINE_GUN_TOWER_RANGE,
                Settings.MACHINE_GUN_TOWER_FIRE_RATE));
        this.addItem(new TowerStoreItem(Settings.SPREAD_TOWER_IMAGE,
                Settings.SPREAD_TOWER_PRICE,
                Settings.SPREAD_TOWER_DAMAGE,
                Settings.SPREAD_TOWER_RANGE,
                Settings.SPREAD_TOWER_FIRE_RATE));
    }

    private void addItem(TowerStoreItem newItem) {
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
                if (dragEvent.getGestureSource() != gameField && dragEvent.getDragboard().hasString() && dragEvent.getDragboard().hasImage() && !PlayScreen.isPause) {
                    dragEvent.acceptTransferModes(TransferMode.COPY_OR_MOVE);
//                    isDrag = true;
                }else{
//                    isDrag = false;
                }
                dragEvent.consume();
            }
        });
        gameField.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                Dragboard db = dragEvent.getDragboard();
                boolean isSuccess = false;
                if (db.hasImage() && db.hasString()) {
                    isSuccess = true;
                    plantTower(db.getString(), dragEvent.getX(), dragEvent.getY());
                }
                dragEvent.setDropCompleted(isSuccess);
                dragEvent.consume();
//                isDrag = false;
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
        int price = 0;
        Tower tower = null;
        switch (towerCode) {
            case Settings.NORMAL_TOWER_ITEM: {
                tower = new NormalTower(colIndex * Settings.TILE_WIDTH, rowIndex * Settings.TILE_HEIGHT);
                price = Settings.NORMAL_TOWER_PRICE;
                break;
            }
            case Settings.SNIPER_TOWER_ITEM: {
                tower = new SniperTower(colIndex * Settings.TILE_WIDTH, rowIndex * Settings.TILE_HEIGHT);
                price = Settings.SNIPER_TOWER_PRICE;
                break;
            }
            case Settings.MACHINE_GUN_TOWER_ITEM: {
                tower = new MachineGunTower(colIndex * Settings.TILE_WIDTH, rowIndex * Settings.TILE_HEIGHT);
                price = Settings.MACHINE_GUN_TOWER_PRICE;
                break;
            }
            case Settings.SPREAD_TOWER_ITEM: {
                tower = new SpreadTower(colIndex * Settings.TILE_WIDTH, rowIndex * Settings.TILE_HEIGHT);
                price = Settings.SPREAD_TOWER_PRICE;
                break;
            }
        }
        PlayScreen.spendMoney(price);
        tower.addClickArea(colIndex * Settings.TILE_WIDTH, rowIndex * Settings.TILE_HEIGHT, price);
    }



    public HBox getStore() {
        return store;
    }

    public List<TowerStoreItem> getStoreItems() {
        return storeItems;
    }
}
