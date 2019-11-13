/**
 * Item box to buy towers from.
 */
package game;

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
    private Rectangle2D boundingBox;

    public TowerStoreItem(Image icon) {
        setPreserveRatio(true);
        setFitHeight(80);
        setImage(icon);
        setHandleEvents();
    }

    public void setHandleEvents() {
        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                GameStage.stage.getScene().setCursor(Cursor.HAND);
            }
        });
        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                GameStage.stage.getScene().setCursor(Cursor.DEFAULT);
            }
        });
    }


}
