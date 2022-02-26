package pj1;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.application.Application;

public class test extends Application{
    public static void main(String[] args){
        launch(args);
    }

    public void start(Stage stage){
        Group root = new Group();
        Scene scene = new Scene(root,600,400);
        stage.setResizable(false);

        Text text = new Text("You should choose a BTS to use the dictionary.");
        text.setLayoutX(150);
        text.setLayoutY(100);
        Button RBtree = new Button("RB-tree");
        RBtree.setMinSize(100,50);
        RBtree.setLayoutX(175);
        RBtree.setLayoutY(200);
        Button Btree = new Button("B-tree");
        Btree.setMinSize(100,50);
        Btree.setLayoutX(325);
        Btree.setLayoutY(200);

        RBtree.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });

        root.getChildren().addAll(text,RBtree,Btree);

        stage.setTitle("Welcome to the English-Chinese dictionary");
        stage.setScene(scene);
        stage.show();
    }
}
