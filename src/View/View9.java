package View;

import Controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class View9 implements Command{
	Controller c;
	Stage stage;
	private Label label1 = new Label("Choose a test:");
	private Label label2 = new Label("");
	private Label label3 = new Label("Test copied!");
	private Label error1 = new Label("ERROR: CloneNotSupportedException");
	private ComboBox<Integer> comboBox = new ComboBox<>();
	private Button button = new Button("Create copy");
	
	public View9(Stage stage) {
		this.stage = stage;
		ScrollPane sp = new ScrollPane();
		VBox vb = new VBox(30, label1, comboBox, label2, button,label3, error1);
		sp.setContent(vb);
		vb.setPadding(new Insets(20));
		vb.setAlignment(Pos.CENTER);
		sp.setFitToWidth(true);
		label3.setVisible(false);
		error1.setVisible(false);
		Scene scene = new Scene(sp, 500, 700);
		stage.setTitle("Update answer");
		stage.setScene(scene);
		execute();
	}
	
	@Override
	public void execute() {
		comboBox.setOnAction(e -> {
			if(comboBox.getValue() != null) {
				error1.setVisible(false);
				label3.setVisible(false);
				label2.setText(c.getTestStringByIndex(comboBox.getValue()));
			}
		});
		button.setOnAction(e -> {
			if(comboBox.getValue() != null) {
				try {
					error1.setVisible(false);
					c.copyTest(comboBox.getValue());
					label3.setVisible(true);
					openView9();
				} catch (CloneNotSupportedException e1) {
					label3.setVisible(false);
					error1.setVisible(true);
				}
			}
		});
	}
	
	
	public void openView9() {
		comboBox.getItems().clear();
		label2.setText("");
		int numOfTests = c.getAllTestsArraySize();
		for (int i = 1; i < numOfTests + 1; i++) {
			comboBox.getItems().add(i);
		}
		stage.show();
		stage.setAlwaysOnTop(true);
	}

	public void setC(Controller c) {
		this.c = c;
	}
}
