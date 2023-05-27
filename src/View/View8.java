package View;

import Controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class View8 {
	Controller c;
	Stage stage;
	private Label test = new Label();
	
	public View8(Stage stage) {
	this.stage = stage;
	ScrollPane sp = new ScrollPane();
	sp.setContent(test);
	sp.setPadding(new Insets(10));
	Scene testScene = new Scene(sp, 500, 500);
	stage.setTitle("Test");
	stage.setScene(testScene);
	}
	
	
	public void openView8() {
		test.setText(c.getLastTestString());
		stage.show();
		stage.setAlwaysOnTop(true);
	}

	public void setC(Controller c) {
		this.c = c;
	}
}
