package pj3;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;

public class Main extends Application{
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
/*准备场景*/
        Group root = new Group();
        Scene scene = new Scene(root,1300,750);
        primaryStage.setResizable(false);

        //设计背景图
        Image img = new Image(getClass().getResourceAsStream("background.png"));
        ImageView imv = new ImageView();
        imv.setImage(img);
        imv.setFitWidth(1300);
        imv.setFitHeight(750);
        root.getChildren().add(imv);

/*添加Node，设置布局*/
        //起点、终点选择
        ToggleGroup choose = new ToggleGroup();
        RadioButton start = new RadioButton("起点：");
        start.setSelected(true);
        start.setToggleGroup(choose);
        TextField starts = new TextField("闵行文化公园");
        starts.setEditable(false);
        RadioButton end = new RadioButton("终点：");
        TextField ends = new TextField("闵行文化公园");
        ends.setEditable(false);
        end.setToggleGroup(choose);
        start.setLayoutX(50);
        start.setLayoutY(50);
        starts.setLayoutX(110);
        starts.setLayoutY(45);
        starts.setMinSize(190,30);
        end.setLayoutX(50);
        end.setLayoutY(100);
        ends.setLayoutX(110);
        ends.setLayoutY(95);
        ends.setMinSize(190,30);
        //导航
        Button walk = new Button("步行导航");
        walk.setLayoutX(200);
        walk.setLayoutY(150);
        walk.setMinSize(100,30);
        //结果显示
        TextArea result = new TextArea();
        result.setEditable(false);
        result.setLayoutX(50);
        result.setLayoutY(200);
        result.setPrefHeight(500);
        result.setPrefWidth(250);
        //地图
        Image image =new Image(getClass().getResourceAsStream("map.png"));
        ImageView imageview = new ImageView();
        imageview.setImage(image);
        imageview.setFitHeight(650);
        imageview.setFitWidth(900);
        imageview.setX(350);
        imageview.setY(50);
        root.getChildren().addAll(walk,start,starts,end,ends,result,imageview);

        String ver[] = {"闵行文化公园","大上海国际花园","宝龙城","星宝购物中心","勇卫商务大厦",
                "莱茵风尚","七宝教寺","阳光乾城苑","茂盛花园","上海市七宝中学",
                "欧风花都","古美小区","强劲大厦","莲花国际广场","万源城御溪",
                "麦德龙","东方花园","闵行体育公园","上海文来中学","新时代花园",
                "万源城尚郡","双拥公园","福克斯商务大厦","上海绿瘦酒店","赢嘉广场"};

        Button n1 = new Button("1");Button n2 = new Button("2");Button n3 = new Button("3");Button n4 = new Button("4");Button n5 = new Button("5");
        Button n6 = new Button("6");Button n7 = new Button("7");Button n8 = new Button("8");Button n9 = new Button("9");Button n10 = new Button("10");
        Button n11 = new Button("11");Button n12 = new Button("12");Button n13 = new Button("13");Button n14 = new Button("14");Button n15 = new Button("15");
        Button n16 = new Button("16");Button n17 = new Button("17");Button n18 = new Button("18");Button n19 = new Button("19");Button n20 = new Button("20");
        Button n21 = new Button("21");Button n22 = new Button("22");Button n23 = new Button("23");Button n24 = new Button("24");Button n25 = new Button("25");

        n1.setStyle("-fx-background-color: white;");n2.setStyle("-fx-background-color: white;");n3.setStyle("-fx-background-color: white;");n4.setStyle("-fx-background-color: white;");n5.setStyle("-fx-background-color: white;");
        n6.setStyle("-fx-background-color: white;");n7.setStyle("-fx-background-color: white;");n8.setStyle("-fx-background-color: white;");n9.setStyle("-fx-background-color: white;");n10.setStyle("-fx-background-color: white;");
        n11.setStyle("-fx-background-color: white;");n12.setStyle("-fx-background-color: white;");n13.setStyle("-fx-background-color: white;");n14.setStyle("-fx-background-color: white;");n15.setStyle("-fx-background-color: white;");
        n16.setStyle("-fx-background-color: white;");n17.setStyle("-fx-background-color: white;");n18.setStyle("-fx-background-color: white;");n19.setStyle("-fx-background-color: white;");n20.setStyle("-fx-background-color: white;");
        n21.setStyle("-fx-background-color: white;");n22.setStyle("-fx-background-color: white;");n23.setStyle("-fx-background-color: white;");n24.setStyle("-fx-background-color: white;");n25.setStyle("-fx-background-color: white;");

        n1.setLayoutX(600);n1.setLayoutY(90);n2.setLayoutX(828);n2.setLayoutY(50);n3.setLayoutX(505);n3.setLayoutY(140);n4.setLayoutX(825);n4.setLayoutY(173);n5.setLayoutX(1018);n5.setLayoutY(110);
        n6.setLayoutX(408);n6.setLayoutY(242);n7.setLayoutX(553);n7.setLayoutY(205);n8.setLayoutX(725);n8.setLayoutY(198);n9.setLayoutX(620);n9.setLayoutY(252);n10.setLayoutX(678);n10.setLayoutY(275);
        n11.setLayoutX(920);n11.setLayoutY(268);n12.setLayoutX(1100);n12.setLayoutY(200);n13.setLayoutX(675);n13.setLayoutY(655);n14.setLayoutX(1145);n14.setLayoutY(568);n15.setLayoutX(1010);n15.setLayoutY(360);
        n16.setLayoutX(1208);n16.setLayoutY(318);n17.setLayoutX(565);n17.setLayoutY(412);n18.setLayoutX(720);n18.setLayoutY(380);n19.setLayoutX(875);n19.setLayoutY(455);n20.setLayoutX(962);n20.setLayoutY(442);
        n21.setLayoutX(1078);n21.setLayoutY(390);n22.setLayoutX(947);n22.setLayoutY(557);n23.setLayoutX(415);n23.setLayoutY(525);n24.setLayoutX(588);n24.setLayoutY(500);n25.setLayoutX(485);n25.setLayoutY(662);

        n1.setMaxSize(40,40);n2.setMaxSize(40,40);n3.setMaxSize(40,40);n4.setMaxSize(40,40);n5.setMaxSize(40,40);
        n6.setMaxSize(40,40);n7.setMaxSize(40,40);n8.setMaxSize(40,40);n9.setMaxSize(40,40);n10.setMaxSize(40,40);
        n11.setMaxSize(40,40);n12.setMaxSize(40,40);n13.setMaxSize(40,40);n14.setMaxSize(40,40);n15.setMaxSize(40,40);
        n16.setMaxSize(40,40);n17.setMaxSize(40,40);n18.setMaxSize(40,40);n19.setMaxSize(40,40);n20.setMaxSize(40,40);
        n21.setMaxSize(40,40);n22.setMaxSize(40,40);n23.setMaxSize(40,40);n24.setMaxSize(40,40);n25.setMaxSize(40,40);

        n1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (start.isSelected()){
                    starts.setText(ver[0]);
                }else {
                    ends.setText(ver[0]);
                }
            }
        });
        n2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (start.isSelected()){
                    starts.setText(ver[1]);
                }else {
                    ends.setText(ver[1]);
                }
            }
        });
        n3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (start.isSelected()){
                    starts.setText(ver[2]);
                }else {
                    ends.setText(ver[2]);
                }
            }
        });
        n4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (start.isSelected()){
                    starts.setText(ver[3]);
                }else {
                    ends.setText(ver[3]);
                }
            }
        });
        n5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (start.isSelected()){
                    starts.setText(ver[4]);
                }else {
                    ends.setText(ver[4]);
                }
            }
        });
        n6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (start.isSelected()){
                    starts.setText(ver[5]);
                }else {
                    ends.setText(ver[5]);
                }
            }
        });
        n7.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (start.isSelected()){
                    starts.setText(ver[6]);
                }else {
                    ends.setText(ver[6]);
                }
            }
        });
        n8.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (start.isSelected()){
                    starts.setText(ver[7]);
                }else {
                    ends.setText(ver[7]);
                }
            }
        });
        n9.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (start.isSelected()){
                    starts.setText(ver[8]);
                }else {
                    ends.setText(ver[8]);
                }
            }
        });
        n10.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (start.isSelected()){
                    starts.setText(ver[9]);
                }else {
                    ends.setText(ver[9]);
                }
            }
        });
        n11.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (start.isSelected()){
                    starts.setText(ver[10]);
                }else {
                    ends.setText(ver[10]);
                }
            }
        });
        n12.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (start.isSelected()){
                    starts.setText(ver[11]);
                }else {
                    ends.setText(ver[11]);
                }
            }
        });
        n13.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (start.isSelected()){
                    starts.setText(ver[12]);
                }else {
                    ends.setText(ver[12]);
                }
            }
        });
        n14.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (start.isSelected()){
                    starts.setText(ver[13]);
                }else {
                    ends.setText(ver[13]);
                }
            }
        });
        n15.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (start.isSelected()){
                    starts.setText(ver[14]);
                }else {
                    ends.setText(ver[14]);
                }
            }
        });
        n16.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (start.isSelected()){
                    starts.setText(ver[15]);
                }else {
                    ends.setText(ver[15]);
                }
            }
        });
        n17.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (start.isSelected()){
                    starts.setText(ver[16]);
                }else {
                    ends.setText(ver[16]);
                }
            }
        });
        n18.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (start.isSelected()){
                    starts.setText(ver[17]);
                }else {
                    ends.setText(ver[17]);
                }
            }
        });
        n19.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (start.isSelected()){
                    starts.setText(ver[18]);
                }else {
                    ends.setText(ver[18]);
                }
            }
        });
        n20.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (start.isSelected()){
                    starts.setText(ver[19]);
                }else {
                    ends.setText(ver[19]);
                }
            }
        });
        n21.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (start.isSelected()){
                    starts.setText(ver[20]);
                }else {
                    ends.setText(ver[20]);
                }
            }
        });
        n22.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (start.isSelected()){
                    starts.setText(ver[21]);
                }else {
                    ends.setText(ver[21]);
                }
            }
        });
        n23.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (start.isSelected()){
                    starts.setText(ver[22]);
                }else {
                    ends.setText(ver[22]);
                }
            }
        });
        n24.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (start.isSelected()){
                    starts.setText(ver[23]);
                }else {
                    ends.setText(ver[23]);
                }
            }
        });
        n25.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (start.isSelected()){
                    starts.setText(ver[24]);
                }else {
                    ends.setText(ver[24]);
                }
            }
        });
        root.getChildren().addAll(n1,n2,n3,n4,n5,n6,n7,n8,n9,n10,n11,n12,n13,n14,n15,n16,n17,n18,n19,n20,n21,n22,n23,n24,n25);

        Graph graph = new Graph(ver.length);
        for (int i=0;i<ver.length;i++){
            graph.insertVertex(ver[i]);
        }

        Path[][] paths = new Path[ver.length][ver.length];
        for (int i=0;i < ver.length;i++){
            for (int j=0;j<ver.length;j++){
                paths[i][j] = new Path();
            }
        }

        graph.insertEdge(0,2,"漕宝路",800);//1-3
        paths[0][2].getElements().add(new MoveTo(n1.getLayoutX()+10, n1.getLayoutY()+25));
        paths[0][2].getElements().add(new LineTo(n1.getLayoutX()+10, n1.getLayoutY()+80));
        paths[0][2].getElements().add(new LineTo(n3.getLayoutX()+20, n3.getLayoutY()+40));
        paths[0][2].getElements().add(new LineTo(n3.getLayoutX()+15, n3.getLayoutY()+25));
        graph.insertEdge(0,7,"漕宝路",1100);//1-8
        paths[0][7].getElements().add(new MoveTo(n1.getLayoutX()+15, n1.getLayoutY()+25));
        paths[0][7].getElements().add(new LineTo(n1.getLayoutX()+15, n1.getLayoutY()+80));
        paths[0][7].getElements().add(new LineTo(n8.getLayoutX(), n8.getLayoutY()-35));
        paths[0][7].getElements().add(new LineTo(n8.getLayoutX(), n8.getLayoutY()));
        graph.insertEdge(1,3,"虹莘路",870);//2-4
        paths[1][3].getElements().add(new MoveTo(n2.getLayoutX(), n2.getLayoutY()+12));
        paths[1][3].getElements().add(new LineTo(n2.getLayoutX()-40, n2.getLayoutY()+20));
        paths[1][3].getElements().add(new LineTo(n4.getLayoutX(), n4.getLayoutY()));
        graph.insertEdge(2,5,"漕宝路",1300);//3-6
        paths[2][5].getElements().add(new MoveTo(n3.getLayoutX()+8, n3.getLayoutY()+25));
        paths[2][5].getElements().add(new LineTo(n3.getLayoutX()+10, n3.getLayoutY()+45));
        paths[2][5].getElements().add(new LineTo(n3.getLayoutX()-35, n3.getLayoutY()+52));
        paths[2][5].getElements().add(new LineTo(n6.getLayoutX()-5, n6.getLayoutY()-5));
        paths[2][5].getElements().add(new LineTo(n6.getLayoutX(), n6.getLayoutY()));
        graph.insertEdge(2,6,"新镇路",660);//3-7
        paths[2][6].getElements().add(new MoveTo(n3.getLayoutX()+23, n3.getLayoutY()+15));
        paths[2][6].getElements().add(new LineTo(n3.getLayoutX()+47, n3.getLayoutY()+15));
        paths[2][6].getElements().add(new LineTo(n7.getLayoutX()+20, n7.getLayoutY()));
        graph.insertEdge(3,7,"漕宝路",940);//4-8
        paths[3][7].getElements().add(new MoveTo(n4.getLayoutX(), n4.getLayoutY()));
        paths[3][7].getElements().add(new LineTo(n8.getLayoutX()+55, n8.getLayoutY()-36));
        paths[3][7].getElements().add(new LineTo(n8.getLayoutX()+5, n8.getLayoutY()-35));
        paths[3][7].getElements().add(new LineTo(n8.getLayoutX()+5, n8.getLayoutY()));
        graph.insertEdge(3,4,"漕宝路",1400);//4-5
        paths[3][4].getElements().add(new MoveTo(n4.getLayoutX()+23, n4.getLayoutY()));
        paths[3][4].getElements().add(new LineTo(n4.getLayoutX()+130, n4.getLayoutY()+10));
        paths[3][4].getElements().add(new LineTo(n5.getLayoutX()+12, n5.getLayoutY()+40));
        paths[3][4].getElements().add(new LineTo(n5.getLayoutX()+12, n5.getLayoutY()+25));
        graph.insertEdge(3,18,"虹莘路",1900);//4-19
        paths[3][18].getElements().add(new MoveTo(n4.getLayoutX()+10, n4.getLayoutY()+23));
        paths[3][18].getElements().add(new LineTo(n4.getLayoutX()+18, n4.getLayoutY()+50));
        paths[3][18].getElements().add(new LineTo(n4.getLayoutX()+40, n4.getLayoutY()+165));
        paths[3][18].getElements().add(new LineTo(n19.getLayoutX(), n19.getLayoutY()));
        graph.insertEdge(4,11,"莲花路",830);//5-12
        paths[4][11].getElements().add(new MoveTo(n5.getLayoutX()+23, n5.getLayoutY()+12));
        paths[4][11].getElements().add(new LineTo(n5.getLayoutX()+35, n5.getLayoutY()+12));
        paths[4][11].getElements().add(new LineTo(n12.getLayoutX()-23, n12.getLayoutY()+8));
        paths[4][11].getElements().add(new LineTo(n12.getLayoutX(), n12.getLayoutY()));
        graph.insertEdge(5,16,"七莘路",2100);//6-17
        paths[5][16].getElements().add(new MoveTo(n6.getLayoutX()+23, n6.getLayoutY()));
        paths[5][16].getElements().add(new LineTo(n6.getLayoutX()+40, n6.getLayoutY()-3));
        paths[5][16].getElements().add(new LineTo(n17.getLayoutX(), n17.getLayoutY()));
        graph.insertEdge(6,8,"新镇路",590);//7-9
        paths[6][8].getElements().add(new MoveTo(n7.getLayoutX()+23, n7.getLayoutY()+3));
        paths[6][8].getElements().add(new LineTo(n9.getLayoutX()-8, n9.getLayoutY()+26));
        paths[6][8].getElements().add(new LineTo(n9.getLayoutX(), n9.getLayoutY()+23));
        graph.insertEdge(7,9,"环东一路",440);//8-10
        paths[7][9].getElements().add(new MoveTo(n8.getLayoutX(), n8.getLayoutY()+12));
        paths[7][9].getElements().add(new LineTo(n8.getLayoutX()-23, n8.getLayoutY()+22));
        paths[7][9].getElements().add(new LineTo(n10.getLayoutX()+40, n10.getLayoutY()-8));
        paths[7][9].getElements().add(new LineTo(n10.getLayoutX()+23, n10.getLayoutY()));
        graph.insertEdge(8,9,"宝盛路",610);//9-10
        paths[8][9].getElements().add(new MoveTo(n9.getLayoutX()+23, n9.getLayoutY()+12));
        paths[8][9].getElements().add(new LineTo(n9.getLayoutX()+35, n9.getLayoutY()+10));
        paths[8][9].getElements().add(new LineTo(n10.getLayoutX(), n10.getLayoutY()+23));
        graph.insertEdge(9,17,"农南路",130);//10-18
        paths[9][17].getElements().add(new MoveTo(n10.getLayoutX()+23, n10.getLayoutY()+23));
        paths[9][17].getElements().add(new LineTo(n10.getLayoutX()+28, n10.getLayoutY()+33));
        paths[9][17].getElements().add(new LineTo(n10.getLayoutX()+8, n10.getLayoutY()+45));
        paths[9][17].getElements().add(new LineTo(n18.getLayoutX(), n18.getLayoutY()));
        graph.insertEdge(10,11,"平南路",1300);//11-12
        paths[10][11].getElements().add(new MoveTo(n11.getLayoutX()+20, n11.getLayoutY()+23));
        paths[10][11].getElements().add(new LineTo(n11.getLayoutX()+28, n11.getLayoutY()+40));
        paths[10][11].getElements().add(new LineTo(n12.getLayoutX()+18, n12.getLayoutY()+43));
        paths[10][11].getElements().add(new LineTo(n12.getLayoutX()+12, n12.getLayoutY()+23));
        graph.insertEdge(10,14,"合川路",940);//11-15
        paths[10][14].getElements().add(new MoveTo(n11.getLayoutX()+12, n11.getLayoutY()+23));
        paths[10][14].getElements().add(new LineTo(n11.getLayoutX()+22, n11.getLayoutY()+46));
        paths[10][14].getElements().add(new LineTo(n11.getLayoutX()+62, n11.getLayoutY()+30));
        paths[10][14].getElements().add(new LineTo(n15.getLayoutX()-15, n15.getLayoutY()+15));
        paths[10][14].getElements().add(new LineTo(n15.getLayoutX(), n15.getLayoutY()+10));
        graph.insertEdge(11,20,"莲花路",1400);//12-21
        paths[11][20].getElements().add(new MoveTo(n12.getLayoutX(), n12.getLayoutY()+12));
        paths[11][20].getElements().add(new LineTo(n12.getLayoutX()-20, n12.getLayoutY()+20));
        paths[11][20].getElements().add(new LineTo(n21.getLayoutX()+45, n21.getLayoutY()-8));
        paths[11][20].getElements().add(new LineTo(n21.getLayoutX()+28, n21.getLayoutY()));
        graph.insertEdge(12,23,"七莘路",1300);//13-24
        paths[12][23].getElements().add(new MoveTo(n13.getLayoutX(), n13.getLayoutY()));
        paths[12][23].getElements().add(new LineTo(n24.getLayoutX()+28, n24.getLayoutY()+25));
        graph.insertEdge(13,20,"莲花路",1600);//14-21
        paths[13][20].getElements().add(new MoveTo(n14.getLayoutX()+28, n14.getLayoutY()));
        paths[13][20].getElements().add(new LineTo(n14.getLayoutX()+35, n14.getLayoutY()-5));
        paths[13][20].getElements().add(new LineTo(n21.getLayoutX()+48, n21.getLayoutY()+5));
        paths[13][20].getElements().add(new LineTo(n21.getLayoutX()+29, n21.getLayoutY()+10));
        graph.insertEdge(13,21,"古美西路",1800);//14-22
        paths[13][21].getElements().add(new MoveTo(n14.getLayoutX(), n14.getLayoutY()));
        paths[13][21].getElements().add(new LineTo(n14.getLayoutX()-5, n14.getLayoutY()-12));
        paths[13][21].getElements().add(new LineTo(n22.getLayoutX()+23, n22.getLayoutY()+58));
        paths[13][21].getElements().add(new LineTo(n22.getLayoutX()+15, n22.getLayoutY()+23));
        graph.insertEdge(14,19,"合川路",200);//15-20
        paths[14][19].getElements().add(new MoveTo(n15.getLayoutX(), n15.getLayoutY()+15));
        paths[14][19].getElements().add(new LineTo(n15.getLayoutX()-13, n15.getLayoutY()+20));
        paths[14][19].getElements().add(new LineTo(n20.getLayoutX()+47, n20.getLayoutY()-5));
        paths[14][19].getElements().add(new LineTo(n20.getLayoutX()+28, n20.getLayoutY()));
        graph.insertEdge(15,20,"顾戴路",870);//16-21
        paths[15][20].getElements().add(new MoveTo(n16.getLayoutX()+23, n16.getLayoutY()+23));
        paths[15][20].getElements().add(new LineTo(n16.getLayoutX()+28, n16.getLayoutY()+38));
        paths[15][20].getElements().add(new LineTo(n21.getLayoutX()+92, n21.getLayoutY()-8));
        paths[15][20].getElements().add(new LineTo(n21.getLayoutX()+28, n21.getLayoutY()+33));
        paths[15][20].getElements().add(new LineTo(n21.getLayoutX()+23, n21.getLayoutY()+23));
        graph.insertEdge(16,17,"中谊路",600);//17-18
        paths[16][17].getElements().add(new MoveTo(n17.getLayoutX()+28, n17.getLayoutY()+23));
        paths[16][17].getElements().add(new LineTo(n17.getLayoutX()+110, n17.getLayoutY()-15));
        paths[16][17].getElements().add(new LineTo(n17.getLayoutX()+120, n17.getLayoutY()));
        paths[16][17].getElements().add(new LineTo(n18.getLayoutX(), n18.getLayoutY()+12));
        graph.insertEdge(16,22,"中谊路",1800);//17-23
        paths[16][22].getElements().add(new MoveTo(n17.getLayoutX()+10, n17.getLayoutY()+25));
        paths[16][22].getElements().add(new LineTo(n17.getLayoutX()+13, n17.getLayoutY()+33));
        paths[16][22].getElements().add(new LineTo(n23.getLayoutX()+12, n23.getLayoutY()));
        graph.insertEdge(16,23,"七莘路",700);//17-24
        paths[16][23].getElements().add(new MoveTo(n17.getLayoutX()+13, n17.getLayoutY()+25));
        paths[16][23].getElements().add(new LineTo(n24.getLayoutX()+22, n24.getLayoutY()));
        graph.insertEdge(18,21,"虹莘路",960);//19-22
        paths[18][21].getElements().add(new MoveTo(n19.getLayoutX(), n19.getLayoutY()+23));
        paths[18][21].getElements().add(new LineTo(n19.getLayoutX()+8, n19.getLayoutY()+113));
        paths[18][21].getElements().add(new LineTo(n22.getLayoutX(), n22.getLayoutY()));
        graph.insertEdge(19,20,"顾戴路",870);//20-21
        paths[19][20].getElements().add(new MoveTo(n20.getLayoutX()+23, n20.getLayoutY()+23));
        paths[19][20].getElements().add(new LineTo(n20.getLayoutX()+28, n20.getLayoutY()+35));
        paths[19][20].getElements().add(new LineTo(n21.getLayoutX(), n21.getLayoutY()+55));
        paths[19][20].getElements().add(new LineTo(n21.getLayoutX()+18, n21.getLayoutY()+43));
        paths[19][20].getElements().add(new LineTo(n21.getLayoutX()+12, n21.getLayoutY()+23));
        graph.insertEdge(22,24,"中春路",1100);//23-25
        paths[22][24].getElements().add(new MoveTo(n23.getLayoutX()+23, n23.getLayoutY()+23));
        paths[22][24].getElements().add(new LineTo(n25.getLayoutX()+28, n25.getLayoutY()));

        for (int i=0;i < ver.length;i++){
            for (int j=0;j<ver.length;j++){
                root.getChildren().addAll(paths[i][j]);
            }
        }

        Floyd.floyd(graph);
        int[][] pathmatrix = Floyd.GetPathmatrix();
        int[][] shortpath = Floyd.GetShortpath();

        walk.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String startpoint = starts.getText();
                String endpoint = ends.getText();
                //获取起点、终点的索引
                int a=0,b=0;
                for (int i=0;i < graph.vertexnum;i++){
                    if (graph.vertexes[i].equals(startpoint)){
                        a = i;
                    }
                    if (graph.vertexes[i].equals(endpoint)){
                        b = i;
                    }
                }
                for (int i=0;i < ver.length;i++){
                    for (int j=0;j<ver.length;j++){
                        paths[i][j].setStroke(Color.BLACK);
                    }
                }

                if (shortpath[a][b] == graph.INF){
                    result.setText(graph.vertexes[a]+"和"+graph.vertexes[b]+"间不可达");
                }else if (a == b){
                    result.setText("起点与终点不能相同");
                } else {
                    String output = "最短路径距离约为：" + shortpath[a][b]+"米\n";
                    output = output + "所需时间约为："+String.format("%.2f",((shortpath[a][b] / 1.5)/60))+"min\n";//按每秒1.5m算
                    output = output + graph.vertexes[a]+"\n"+"    |"+"\n";
                    int k = a;//中转顶点
                    output = output + "("+graph.edgeName(k,pathmatrix[k][b])+","+graph.matrix[k][pathmatrix[k][b]]+"m,"+String.format("%.2f",((graph.matrix[k][pathmatrix[k][b]] / 1.5)/60))+"min)\n"+"    |\n";
                    paths[k][pathmatrix[k][b]].setStroke(Color.RED);
                    paths[pathmatrix[k][b]][k].setStroke(Color.RED);
                    while (pathmatrix[k][b] != b) {
                        output = output + graph.vertexes[pathmatrix[k][b]]+"\n    |\n";
                        k = pathmatrix[k][b];
                        output = output+"("+graph.edgeName(k,pathmatrix[k][b])+","+graph.matrix[k][pathmatrix[k][b]]+"m,"+String.format("%.2f",((graph.matrix[k][pathmatrix[k][b]] / 1.5)/60))+"min)\n    |\n";
                        paths[k][pathmatrix[k][b]].setStroke(Color.RED);
                        paths[pathmatrix[k][b]][k].setStroke(Color.RED);
                    }
                    output = output+graph.vertexes[b];
                    result.setText(output);
                }
            }
        });

        primaryStage.setTitle("地图导航");
        primaryStage.setScene(scene);
        root.requestFocus();
        primaryStage.show();
    }
}
