package View;

import Controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class View13 {
	Controller c;
	Stage stage;
	private Label l2 = new Label("The tests on the Linked hash set: ");
	private Label l3 = new Label("The tests on the MyArrayList: ");
	private Label l4 = new Label("");
	private Label l5 = new Label("");

	public View13(Stage stage) {
		this.stage = stage;
		ScrollPane sp = new ScrollPane();
		BorderPane bp = new BorderPane();
		VBox vbR = new VBox(l3,l5);
		VBox vbL = new VBox(l2,l4);
		vbR.setPadding(new Insets(10));
		vbL.setPadding(new Insets(10));
		bp.setLeft(vbL);
		bp.setRight(vbR);
		sp.setContent(bp);
		sp.setFitToWidth(true);
		Scene newCollection = new Scene(sp, 800, 800);
		stage.setTitle("New Collection");
		stage.setScene(newCollection);
		vbR.setAlignment(Pos.CENTER);
		vbR.setSpacing(15);
		vbL.setAlignment(Pos.CENTER);
		vbL.setSpacing(15);
		
	}

	public void openView13() {
		l4.setText(c.getNewAllTestsStringOnHash());
		l5.setText(c.getMyArrayList());
		stage.show();
		stage.setAlwaysOnTop(true);
	}
	

	public void setC(Controller c) {
		this.c = c;
	}
}
