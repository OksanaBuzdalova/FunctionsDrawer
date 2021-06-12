/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafunctions;

import java.util.function.DoubleUnaryOperator;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class JavaFunctions extends Application {

    @Override
    public void start(Stage primaryStage){
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 1000, 600, Color.LIGHTCYAN);
        
        primaryStage.setTitle("График функции");
        primaryStage.setScene(scene);
        
        VBox taskbar = new VBox(10);
        taskbar.setPadding(new Insets(10, 30, 50, 30));
        taskbar.setPrefWidth(50);
        taskbar.setAlignment(Pos.CENTER_LEFT);
        root.setTop(taskbar);
        
        HBox taskbar0 = new HBox(10);
        taskbar0.setPrefHeight(10);
        taskbar0.setAlignment(Pos.TOP_LEFT);
        taskbar.getChildren().add(taskbar0);
        
        HBox taskbar1 = new HBox(10);
        taskbar1.setPrefHeight(10);
        taskbar1.setAlignment(Pos.TOP_LEFT);
        taskbar.getChildren().add(taskbar1);
        
        HBox taskbar2 = new HBox(10);
        taskbar2.setPrefHeight(10);
        taskbar2.setAlignment(Pos.TOP_LEFT);
        taskbar.getChildren().add(taskbar2);
        
        HBox taskbar3 = new HBox(10);
        taskbar3.setPrefHeight(10);
        taskbar3.setAlignment(Pos.TOP_LEFT);
        taskbar.getChildren().add(taskbar3);
        
        TextField lowX = createTextField(String.valueOf(-2*Math.PI));
        TextField highX = createTextField(String.valueOf(2*Math.PI));
        
        ComboBox f1 = createComboBox();
        TextField a1 = createTextField(String.valueOf(1));
        TextField k1 = createTextField(String.valueOf(1));
        ComboBox f2 = createComboBox();
        TextField a2 = createTextField(String.valueOf(1));
        TextField k2 = createTextField(String.valueOf(1));
        ComboBox f3 = createComboBox();
        TextField a3 = createTextField(String.valueOf(1));
        TextField k3 = createTextField(String.valueOf(1));
        
        taskbar0.getChildren().add(createLabel("Нижняя граница"));
        taskbar0.getChildren().add(lowX);
        taskbar0.getChildren().add(createLabel("Верхняя граница"));
        taskbar0.getChildren().add(highX);
        
        taskbar1.getChildren().add(createLabel("Функция 1"));
        taskbar1.getChildren().add(a1);
        taskbar1.getChildren().add(createLabel("*"));
        taskbar1.getChildren().add(f1);
        taskbar1.getChildren().add(createLabel("^"));
        taskbar1.getChildren().add(k1);
        
        taskbar2.getChildren().add(createLabel("Функция 2"));
        taskbar2.getChildren().add(a2);
        taskbar2.getChildren().add(createLabel("*"));
        taskbar2.getChildren().add(f2);
        taskbar2.getChildren().add(createLabel("^"));
        taskbar2.getChildren().add(k2);
        
        taskbar3.getChildren().add(createLabel("Функция 3"));
        taskbar3.getChildren().add(a3);
        taskbar3.getChildren().add(createLabel("*"));
        taskbar3.getChildren().add(f3);
        taskbar3.getChildren().add(createLabel("^"));
        taskbar3.getChildren().add(k3);
        
        
        taskbar3.getChildren().add(createButton("Кнопка.png", new Runnable(){
            @Override
            public void run(){
                NumberAxis xAxis = new NumberAxis();
                NumberAxis yAxis = new NumberAxis();
                LineChart<Number,Number> chart = new LineChart<>(xAxis, yAxis);
                chart.setTitle("Line Chart");
                xAxis.setLabel("OX");
                yAxis.setLabel("OY");
                
                LowX = lowX.getText();
                HighX = highX.getText();
                
                func1 = cmbFunc[f1.getSelectionModel().getSelectedIndex()];
                func2 = cmbFunc[f2.getSelectionModel().getSelectedIndex()];
                func3 = cmbFunc[f3.getSelectionModel().getSelectedIndex()];
                
                funcname1 = cmbfuncname[f1.getSelectionModel().getSelectedIndex()];
                funcname2 = cmbfuncname[f2.getSelectionModel().getSelectedIndex()];
                funcname3 = cmbfuncname[f3.getSelectionModel().getSelectedIndex()];
                
                String rank1 = k1.getText();
                int r1 = Integer.valueOf(rank1);
                String mult1 = a1.getText();
                Double m1 = Double.valueOf(mult1);
                
                String rank2 = k2.getText();
                int r2 = Integer.valueOf(rank2);
                String mult2 = a2.getText();
                Double m2 = Double.valueOf(mult2);
                
                String rank3 = k3.getText();
                int r3 = Integer.valueOf(rank3);
                String mult3 = a3.getText();
                Double m3 = Double.valueOf(mult3);
                
                funcnameAll = String.valueOf(m1)+"*("+funcname1+")^"+String.valueOf(r1)+" + "+String.valueOf(m2)+"*("+funcname2+")^"+String.valueOf(r2)+" + "+String.valueOf(m3)+"*("+funcname3+")^"+String.valueOf(r3);
                
                XYChart.Series<Number, Number> series = new XYChart.Series<>();
                series.setName(funcnameAll);
                double lowX = Double.valueOf(LowX);
                double highX = Double.valueOf(HighX);
                
                int steps = 150;
                double dx = (highX - lowX)/(steps - 1);
                for (int i = 0; i < steps; i++){
                    double x = lowX + i*dx;
                    double y = Math.pow(func1.applyAsDouble(x), r1)*m1 + Math.pow(func2.applyAsDouble(x), r2)*m2 + Math.pow(func3.applyAsDouble(x), r3)*m3;
                    series.getData().add(new XYChart.Data<>(x, y));
                }
                chart.getData().add(series);
                
                changeView(chart);
            }
        }));
        
        view = new StackPane();
        root.setCenter(view);
        view.getChildren().add(new Text("Используем JavaFx"));
        
        primaryStage.show();
    }
     
    /**
     * @param args the command line arguements
     */
    
    public static void main(String[] args) {
        launch(args);
    }
    
    private Node createButton(String iconName, final Runnable action){
        final ImageView node = new ImageView(new Image("file:"+iconName));
        node.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event){
                action.run();
            }
        });
        return node;
    }
    
    private Node createLabel (String lblName){
        Label lbl = new Label(lblName);
        return lbl;
    }
    private TextField createTextField(String value){
        TextField tf = new TextField(value);
        return tf;
    }
    
    private void changeView(Node node){
        view.getChildren().clear();
        view.getChildren().add(node);
    }
    
    private ComboBox createComboBox() {
        ComboBox<String> cb = new ComboBox<>();
    
        cmbfuncname = new String[4];
        cmbfuncname[0]= "sin x";
        cmbfuncname[1]= "cos x";
        cmbfuncname[2]= "x^2";
        cmbfuncname[3]= "x^3";
        funcname = cmbfuncname[0];
        
        for(String elem: cmbfuncname){
        cb.getItems().add(elem);
        }
        cb.getSelectionModel().selectFirst();
        
        cmbFunc = new DoubleUnaryOperator[4];
        cmbFunc[0]= Math::sin;
        cmbFunc[1]= Math::cos;
        cmbFunc[2]= x -> x*x;
        cmbFunc[3]= x -> x*x*x;
        func = cmbFunc[0];
        
        cb.setOnAction(new EventHandler<ActionEvent>(){
            
            @Override
            public void handle (ActionEvent event){
                func = cmbFunc[cb.getSelectionModel().getSelectedIndex()];
                funcname = cmbfuncname[cb.getSelectionModel().getSelectedIndex()];
            }
        }); 
        
        return cb;
    }
    
    private StackPane view;
    DoubleUnaryOperator[] cmbFunc;
    DoubleUnaryOperator func;
    String funcname;
    String[] cmbfuncname;
    String LowX;
    String HighX;
    DoubleUnaryOperator[] cmbFuncAll;
    DoubleUnaryOperator funcAll;
    DoubleUnaryOperator func1;
    DoubleUnaryOperator func2;
    DoubleUnaryOperator func3;
    String funcname1;
    String funcname2;
    String funcname3;
    String funcnameAll;
    
    
}
