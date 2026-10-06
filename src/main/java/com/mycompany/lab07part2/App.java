package com.mycompany.lab07part2;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage mainStage) {
        //Pane for layout
        BorderPane root = new BorderPane();
        Pane pane = new Pane();
        pane.setPrefSize(400, 400);
        HBox buttonsBox = new HBox();
        
        //Buttons
        Button start = new Button("start");
        Button reset = new Button("reset");
        Button exit = new Button("exit");
        
        //Shapes
        Circle objectA = new Circle(20, 20, 20);
        
        //Paths
        Path paths = new Path();
        paths.getElements().addAll(new MoveTo(20, 20), new LineTo(380, 20), new LineTo(380, 380), new LineTo(20, 380), new LineTo(20, 20));
        
        //A PathTransition and its setup
        PathTransition pt = new PathTransition();
        pt.setNode(objectA);
        pt.setPath(paths);
        pt.setDuration(new Duration(3000));
        
        //Add the shape to the root
        pane.getChildren().add(objectA);
        buttonsBox.getChildren().addAll(start, reset, exit);
        root.setCenter(pane);
        root.setBottom(buttonsBox);
        
        //Scene and stage setup
        Scene scene = new Scene(root, 400, 400);
        mainStage.setScene(scene);
        mainStage.show();
        
        //When start button is pressed, start animation
        start.setOnAction(event -> pt.play());
        
        //When reset button is pressed, reset it to duration zero and stop the animation
        reset.setOnAction(event -> {
            pt.jumpTo(Duration.ZERO);
            pt.stop();
        });
        
        //Exit the program
        exit.setOnAction(event -> System.exit(0));
    }

    public static void main(String[] args) {
        launch();
    }

}