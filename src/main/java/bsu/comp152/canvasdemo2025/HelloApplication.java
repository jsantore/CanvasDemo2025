package bsu.comp152.canvasdemo2025;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;


import java.io.IOException;

import static javafx.scene.input.KeyCode.LEFT;
import static javafx.scene.input.KeyCode.RIGHT;

public class HelloApplication extends Application {
    private Image ship;
    private TreasureChest goal;
    private int xLoc;
    private int yLoc;
    private int deltaX;

    private class TreasureChest{
        private int xLoc;
        private int yLoc;
        private Image pict;
        private int move;
        public TreasureChest(int xLoc, int yLoc){
            this.xLoc = xLoc;
            this.yLoc = yLoc;
            move = 2;
            var treasurePath = getClass().getResource("Chest2.png");
            this.pict = new Image(treasurePath.toString());
        }
    }
    @Override
    public void start(Stage primaryStage) {
        var path = getClass().getResource("galleon.png");
        ship = new Image(path.toString());
        goal = new TreasureChest(400, 400);
        Canvas drawingArea = new Canvas(800,800);
        GraphicsContext drawer = drawingArea.getGraphicsContext2D();
        var backgroundColor = Paint.valueOf("lightblue");
        drawer.setFill(backgroundColor);
        drawer.fillRect(0,0,800,800);
        xLoc = 200;
        yLoc = 100;
        drawer.drawImage(ship, xLoc,yLoc);
        drawer.drawImage(goal.pict, goal.xLoc, goal.yLoc);
        VBox organizer = new VBox();
        Button Quit = new Button("Quit");
        EventHandler<ActionEvent> buttonResponder = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                System.exit(0);
            }
        };
        Quit.setOnAction(buttonResponder);
        organizer.getChildren().add(Quit);
        organizer.getChildren().add(drawingArea);
        Scene windowContents = new Scene(organizer);
        organizer.setFocusTraversable(true);
        windowContents.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                System.out.println(event.getCode().toString());
                if (event.getCode() == LEFT || event.getCode() == KeyCode.NUMPAD4) {
                    System.out.println("LEFT!!");
                    deltaX = -2;
                }
                else if (event.getCode() == RIGHT || event.getCode() == KeyCode.NUMPAD6) {
                    System.out.println("RIGHT!!!");
                    deltaX = 2;
                }
                event.consume();
            }
        });
        windowContents.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode() == LEFT || keyEvent.getCode() == KeyCode.NUMPAD4 ||
                keyEvent.getCode() == RIGHT || keyEvent.getCode() == KeyCode.NUMPAD6) {
                    deltaX =0;
                }
            }
        });
        var animator = new AnimationTimer() {
            @Override
            public void handle(long currentTime) {
                var backgroundColor = Paint.valueOf("lightblue");
                drawer.setFill(backgroundColor);
                drawer.fillRect(0,0,800,800);
                xLoc += deltaX;
                GraphicsContext drawer = drawingArea.getGraphicsContext2D();
                drawer.drawImage(ship, xLoc,yLoc);
                if(goal.xLoc > 800){
                    goal.move = -goal.move;
                }
                goal.xLoc += goal.move;
                drawer.drawImage(goal.pict, goal.xLoc,goal.yLoc);

            }

        };
        animator.start();
        primaryStage.setScene(windowContents);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}