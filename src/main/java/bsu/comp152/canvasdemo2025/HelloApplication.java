package bsu.comp152.canvasdemo2025;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.IOException;

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
        xLoc = 200;
        yLoc = 300;
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
        primaryStage.setScene(windowContents);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}