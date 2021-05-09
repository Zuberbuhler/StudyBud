package application;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DataChartGUI {
	
	private  LineChart<String,Number> lineChart;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void createGraph() {
		 
		//defining the axes
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
       
        xAxis.setLabel("Days You Have studied ");
        yAxis.setLabel("Hours Spent Studying");
        
       //creating the chart
      lineChart = new LineChart<String,Number>(xAxis,yAxis);
               
       lineChart.setTitle("Weekly Study Hour Overview");
       //defining a series
       XYChart.Series series = new XYChart.Series();
       series.setName("Hours Spent Studying");
       
       //populating the series with data
       series.getData().add(new XYChart.Data("Monday", 1));
       series.getData().add(new XYChart.Data("Tuesday",2));
       series.getData().add(new XYChart.Data("Wednesday",3));
       series.getData().add(new XYChart.Data("Thursday", 4));
       series.getData().add(new XYChart.Data("Friday", 5));
       series.getData().add(new XYChart.Data("Saturday",6));
       series.getData().add(new XYChart.Data("Sunday", 5.5));
       
       lineChart.getData().add(series);
		
	}

	public StackPane dataChart() {
        
		createGraph();
		
	    // data: blue background
	    Rectangle dataBackground = new Rectangle(600,300);
	    dataBackground.setArcHeight(40.0);
	    dataBackground.setArcWidth(40.0);
	    dataBackground.setFill(Color.web("#DEE7EC",1));    
	    
	    // data: layout
	    StackPane data = new StackPane();
	    data.getChildren().addAll(dataBackground,lineChart);
	    return data; 
	}
}
