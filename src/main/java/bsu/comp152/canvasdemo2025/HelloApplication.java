package bsu.comp152.canvasdemo2025;

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
    private int xLoc;
    private int yLoc;
    private int deltaX;

    @Override
    public void start(Stage primaryStage) {
        var path = getClass().getResource("galleon.png");
        ship = new Image(path.toString());
        Canvas drawingArea = new Canvas(800,800);
        GraphicsContext drawer = drawingArea.getGraphicsContext2D();
        var backgroundColor = Paint.valueOf("lightblue");
        drawer.setFill(backgroundColor);
        drawer.fillRect(0,0,800,800);
        xLoc = 200;
        yLoc = 100;
        drawer.drawImage(ship, xLoc,yLoc);
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
        primaryStage.setScene(windowContents);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}