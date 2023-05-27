package View;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import Controller.Controller;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class View11 {
	Controller c;
	Stage stage;
	private Label b = new Label("The tests are: ");
	private Label l3 = new Label("");
	

	public View11(Stage stage) {
		this.stage = stage;
		ScrollPane sp = new ScrollPane();
		VBox vb = new VBox(b,l3);
		vb.setPadding(new Insets(10));
		sp.setContent(vb);
		sp.setFitToWidth(true);
		Scene newCollection = new Scene(sp, 600, 600);
		stage.setTitle("New Collection");
		stage.setScene(newCollection);
		vb.setAlignment(Pos.CENTER);
		vb.setSpacing(15);
		
	}

	public void openView11() {
		l3.setText(c.getNewAllTestsString());
		stage.show();
		stage.setAlwaysOnTop(true);
		
	}
	
	

	public void setC(Controller c) {
		this.c = c;
	}
}
