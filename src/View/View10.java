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

public class View10 {
	Controller c;
	Stage stage;
	private Label error1 = new Label("ERROR: Must choose a question");
	private Label error2 = new Label("ERROR: Cannot create empty test");
	private Label error3 = new Label("ERROR: FileNotFoundException");
	private Label label1 = new Label("Choose a question:");
	private Label label2 = new Label("");
	private Label label3 = new Label("Choose answers:");
	private ComboBox<Integer> questionComboBox = new ComboBox<>();
	private ArrayList<RadioButton> answersRadioButton = new ArrayList<>();
	private Button button1 = new Button("Add Question");
	private Button button2 = new Button("Create Test");

	public View10(Stage stage) {
		this.stage = stage;
		ScrollPane sp = new ScrollPane();
		GridPane gp = new GridPane();
		gp.setAlignment(Pos.CENTER);
		gp.setHgap(10);
		gp.setVgap(10);
		for (int i = 1; i < 11; i++) {
			RadioButton rb = new RadioButton(Integer.toString(i));
			answersRadioButton.add(rb);
			if (i < 6)
				gp.add(answersRadioButton.get(i - 1), 0, i);
			else
				gp.add(answersRadioButton.get(i - 1), 1, i - 5);
			rb.setVisible(false);
		}
		VBox vb = new VBox(30, label1, questionComboBox, label2, label3, gp, button1, button2, error1, error2,error3);
		sp.setContent(vb);
		vb.setPadding(new Insets(20));
		vb.setAlignment(Pos.CENTER);
		sp.setFitToWidth(true);
		error1.setVisible(false);
		error2.setVisible(false);
		error3.setVisible(false);
		Scene scene = new Scene(sp, 500, 700);
		stage.setTitle("Create Manual Test");
		stage.setScene(scene);
		action();
	}

	public void action() {
		questionComboBox.setOnAction(e -> {
			if (questionComboBox.getValue() != null) {
				label3.setVisible(true);
				label2.setText(c.getQuestionTextById(questionComboBox.getValue()));
				if (c.getAllQuestions().get(c.getQuestionIndexById(questionComboBox.getValue())).isClosedQuestion()) {
					for (int i = 0; i < 10; i++) {
						if (i < 2) {
							answersRadioButton.get(i).setSelected(true);
							answersRadioButton.get(i).setDisable(true);
						}
						if (i < c.getClosedQuestionAnswerArraySize(c.getQuestionIndexById(questionComboBox.getValue())))
							answersRadioButton.get(i).setVisible(true);
						else
							answersRadioButton.get(i).setVisible(false);
					}
				} else {
					label3.setVisible(false);
					for (int i = 0; i < 10; i++) {
						answersRadioButton.get(i).setVisible(false);
					}
				}
			}
		});
		button1.setOnAction(e -> {
			if (questionComboBox.getValue() != null) {
				error1.setVisible(false);
				int questionIndex = c.getQuestionIndexById(questionComboBox.getValue());
				if (c.getAllQuestions().get(questionIndex).isClosedQuestion()) {
					for (int i = 0; i < c.getClosedQuestionAnswerArraySize(questionIndex); i++) {
						c.addAnsToUsedAnsArr(answersRadioButton.get(i).isSelected());
					}
				}
				c.addQuestionToQuestionIdArr(questionComboBox.getValue());
				c.resetUsedAnsArr();
				questionComboBox.getItems().remove(questionComboBox.getValue());
				questionComboBox.getSelectionModel().clearSelection();
				label2.setText("");
				label3.setVisible(false);
				for (int i = 0; i < 10; i++) {
					answersRadioButton.get(i).setVisible(false);
				}
			} else {
				error1.setVisible(true);
			}
		});
		button2.setOnAction(e -> {
			if (c.getQuestionIdArrSize() != 0) {
				try {
					c.createManualTest();
					c.resetQuestionIdArr();
					error2.setVisible(false);
					c.openView8();
					stage.close();
				} catch (FileNotFoundException e1) {
					error3.setVisible(true);
				}
			}
			else {
				error2.setVisible(true);
			}
		});
	}

	public void openView10() {
		int questionIndex = 0;
		questionComboBox.getSelectionModel().clearSelection();
		label2.setText("");
		for (int i = 0; i < c.getAllQuestions().size(); i++) {
			questionIndex = i;
			if (!questionComboBox.getItems().contains(c.getAllQuestions().get(i).getId()))
				questionComboBox.getItems().add(c.getAllQuestions().get(i).getId());
		}
		stage.show();
		stage.setAlwaysOnTop(true);
	}

	public void setC(Controller c) {
		this.c = c;
	}

}
