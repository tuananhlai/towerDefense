/**
 * Item box to buy towers from.
 */
package game;

import game.screen.PlayScreen;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;


public class TowerStoreItem extends ImageView {
    private int towerPrice;
    private int towerType;
    private int towerRange;
    private double towerFireRate;
    private int damage;
    private Rectangle2D boundingBox;

    public TowerStoreItem(Image icon, int towerPrice, int damage, int towerRange, double towerFireRate) {
        setPreserveRatio(true);
        setFitHeight(80);
        setImage(icon);
        this.towerPrice = towerPrice;
        this.damage = damage;
        this.towerRange = towerRange;
        this.towerFireRate = towerFireRate;
    }

    public void setHandleEvents(int towerCode) {
        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                TowerInfoPanel.showTowerInfo(towerPrice, damage, towerRange, towerFireRate);

                if (PlayScreen.money < towerPrice) {
                    GameStage.stage.getScene().setCursor(Cursor.CROSSHAIR);
                }
                else {
                    GameStage.stage.getScene().setCursor(Cursor.HAND);
                }
            }
        });
        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                GameStage.stage.getScene().setCursor(Cursor.DEFAULT);
            }
        });
        setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (PlayScreen.money < towerPrice) {
                    return;
                }
                Dragboard db = startDragAndDrop(TransferMode.ANY);
                /* put a string and image on dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putImage(getImage());
                content.putString(Integer.toString(towerCode));
                db.setContent(content);
                mouseEvent.consume();
            }
        });
    }


}
