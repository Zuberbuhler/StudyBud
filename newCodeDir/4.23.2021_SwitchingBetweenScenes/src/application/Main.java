package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {
	
	Stage window;
	Scene loginScene, homepageScene;
	@Override
	public void start(Stage primaryStage) {
		try {
			window = primaryStage;
			
			Button sucessfullLoginBtn = new Button("Login Successfully");
			sucessfullLoginBtn.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> 
			{
				window.setScene(homepageScene);
				primaryStage.setTitle("Homepage");
			});

			
			Button failedLoginBtn = new Button("Login Failure");
			failedLoginBtn.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> 
			{
				window.setScene(loginScene);
				primaryStage.setTitle("LoginFailure");
				});
			
			VBox loginButtons = new VBox();
			loginButtons.setAlignment(Pos.CENTER);
			loginButtons.setSpacing(20);
			loginButtons.setPadding(new Insets(20,20,20,20));
			loginButtons.getChildren().addAll(sucessfullLoginBtn, failedLoginBtn);
			
			loginScene = new Scene(loginButtons,400,400);

			
			Button logoutBtn = new Button("Logout");
			logoutBtn.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> 
			{
				window.setScene(loginScene);
				primaryStage.setTitle("Login Page");

			});
			VBox homepageLogoutVBox = new VBox();
			homepageLogoutVBox.setAlignment(Pos.CENTER);
			homepageLogoutVBox.setPadding(new Insets(20,20,20,20));
			homepageLogoutVBox.getChildren().add(logoutBtn);
			
			homepageScene = new Scene(homepageLogoutVBox, 800, 800);
			
			
			//Scene sceneHomePage = new Scene();
			
			//loginScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(loginScene);
			primaryStage.setTitle("Login Page");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
