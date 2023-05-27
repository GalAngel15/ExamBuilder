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


public class View12 {
	Controller c;
	Stage stage;
	private Label l2 = new Label("the tests are: ");
	private Label l3 = new Label("");

	public View12(Stage stage) {
		this.stage = stage;
		ScrollPane sp = new ScrollPane();
		VBox vb = new VBox(l2,l3);
		vb.setPadding(new Insets(10));
		sp.setContent(vb);
		sp.setFitToWidth(true);
		Scene newCollection = new Scene(sp, 600, 600);
		stage.setTitle("New Collection");
		stage.setScene(newCollection);
		vb.setAlignment(Pos.CENTER);
		vb.setSpacing(15);
		
	}

	public void openView12() {
		l3.setText(c.getNewAllTestsStringOnHash());
		stage.show();
		stage.setAlwaysOnTop(true);
	}
	

	public void setC(Controller c) {
		this.c = c;
	}
}