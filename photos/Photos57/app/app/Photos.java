package app;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import controllers.AppController;
import controllers.loginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.User;

public class Photos extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/views/loginScreen.fxml"));
		AnchorPane root = (AnchorPane)loader.load();
		loginController loginControl = loader.getController();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Photo Gallery");
		primaryStage.show();
		
	}
	
	public static User readUser(String username) throws IOException, ClassNotFoundException {
		ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("data" + File.separator + username + ".dat"));
		User u = (User)inputStream.readObject();
		inputStream.close();
		return u;
	}

	public static void main(String[] args) {
		launch(args);

	}

}
