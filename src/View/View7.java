package View;

import java.io.FileNotFoundException;

import javax.swing.plaf.basic.BasicComboBoxUI.ComboBoxLayoutManager;

import Controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class View7 implements Command {
	Controller c;
	Stage stage;
	private Label label1 = new Label("How many questions do you want?");
	private Label label2 = new Label("(Questions shown are only \n closed questions with 4 or more answers \n or open questions)");
	private Label error1 = new Label("ERROR: Must choose number of questions");
	private Label error2 = new Label("ERROR: FileNotFoundException");
	private ComboBox<Integer> comboBox = new ComboBox<>();
	private Button button = new Button("Create");

	public View7(Stage stage) {
		this.stage = stage;
		GridPane gp = new GridPane();
		gp.setPadding(new Insets(5));
		gp.setAlignment(Pos.TOP_LEFT);
		gp.setHgap(10);
		gp.setVgap(10);
		gp.add(label1, 0, 0);
		gp.add(comboBox, 1, 0);
		gp.add(button, 1, 1);
		gp.add(error1, 0, 2);
		gp.add(error2, 1, 2);
		gp.add(label2, 3, 0);
		label2.setVisible(true);
		error1.setVisible(false);
		error2.setVisible(false);
		error1.setTextFill(Color.RED);
		error2.setTextFill(Color.RED);
		Scene scene = new Scene(gp, 800, 150);
		stage.setTitle("Create test automatically");
		stage.setScene(scene);
		execute();
	}

	public void execute() {
		button.setOnAction(e -> {
			if (comboBox.getValue() == null) {
				error1.setVisible(true);
			} else {
				try {
					c.createAutoTest(comboBox.getValue());
					error1.setVisible(false);
					error2.setVisible(false);
					c.openView8();
				} catch (FileNotFoundException t) {
					error2.setVisible(true);
				}
			}
		});
	}

	public void openView7() {
		comboBox.getItems().clear();
		int numOfQuestions = c.getNumOfQuestionsForAutoTest();
		for (int i = 1; i < numOfQuestions + 1; i++) {
			comboBox.getItems().add(i);
		}
		stage.show();
		stage.setAlwaysOnTop(true);
	}

	public void setC(Controller c) {
		this.c = c;
	}
}
