package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DataChartGUI {

	public StackPane dataChart() {
		// will be replaced with a working chart later
	    Image dataChartTemp = new Image(getClass().getResourceAsStream("/application/resources/DataChartImage.png"));
	    ImageView dataChartImage = new ImageView(dataChartTemp);
	    dataChartImage.setFitWidth(350);
	    dataChartImage.setFitHeight(300);
	    
	    // data: blue background
	    Rectangle dataBackground = new Rectangle(400, 300);
	    dataBackground.setArcHeight(40.0);
	    dataBackground.setArcWidth(40.0);
	    dataBackground.setFill(Color.web("#DEE7EC",1));    
	    
	    // data: layout
	    StackPane data = new StackPane();
	    data.getChildren().addAll(dataBackground, dataChartImage);
	    return data; 
	}
}
