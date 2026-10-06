package com.mycompany.lab07part2;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Label;


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
        
        //Label and its fade transition setup
        Label terminateProgram = new Label("Terminating the program...");
        FadeTransition labelFT = new FadeTransition();
        labelFT.setNode(terminateProgram);
        
        //Shapes
        Circle objectA = new Circle(20, 20, 20);
        Ellipse objectB = new Ellipse(200, 200, 15, 30);
        objectB.setFill(Color.BLUE);
        
        //Paths for Object A
        Path paths = new Path();
        paths.getElements().addAll(new MoveTo(20, 20), new LineTo(380, 20), new LineTo(380, 380), new LineTo(20, 380), new LineTo(20, 20));
        
        //A PathTransition and its setup for object A only
        PathTransition pt = new PathTransition();
        pt.setNode(objectA);
        pt.setPath(paths);
        pt.setDuration(new Duration(8000));
        pt.play();
        
        //Animations for objectB
        FadeTransition ft = new FadeTransition(new Duration(2000), objectB);
        ft.setFromValue(0);
        ft.setToValue(1);
        
        ScaleTransition st = new ScaleTransition(new Duration(2000), objectB);
        st.setToX(2);
        st.setToY(2);
        
        RotateTransition rt = new RotateTransition(new Duration(2000), objectB);
        rt.setFromAngle(0);
        rt.setToAngle(270);
        
        TranslateTransition tt = new TranslateTransition(new Duration(2000), objectB);
        tt.setToY(-100);
        
        //To make the termination visible
        labelFT.setDuration(new Duration(3000));
        labelFT.setFromValue(0);
        labelFT.setToValue(1);
        
        //All of objectB's animations in order
        SequentialTransition seq = new SequentialTransition(ft, st, rt, tt, labelFT);
        seq.play();
        
        //Add nodes to layout
        pane.getChildren().addAll(objectA, objectB);
        root.setCenter(pane);
        root.setBottom(terminateProgram);
        
        //Scene and stage setup
        Scene scene = new Scene(root, 400, 400);
        mainStage.setScene(scene);
        mainStage.show();
        
        seq.setOnFinished(event -> System.exit(0));
    }

    public static void main(String[] args) {
        launch();
    }
}