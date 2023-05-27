package View;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import Controller.Controller;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class View3 {
	Controller c;
	Stage stage;
	private Label error1 = new Label("ERROR: Theres already a question like that");
	private Label error2 = new Label("ERROR: Duplicated answer");
	private Label error3 = new Label("ERROR: Cannot accept empty text");
	private RadioButton radioButton1 = new RadioButton("Close Question");
	private RadioButton radioButton2 = new RadioButton("Open Question");
	private Label label1 = new Label("Question text:"); // textfield + button(validation)
	private Label label2 = new Label("Answer text:"); // textfield + combo(true/false)
	private Label label3 = new Label("Check if True");
	private Label label4 = new Label("Add");
	private Label label5 = new Label("Question Added successfully");
	private TextField tf1 = new TextField();
	private Button button2 = new Button("Check for validation");
	private ArrayList<TextField> textField = new ArrayList<>();
	private ArrayList<CheckBox> checkBox1 = new ArrayList<>();
	private ArrayList<CheckBox> checkBox = new ArrayList<>();
	private RadioButton radioButton3 = new RadioButton("True");
	private RadioButton radioButton4 = new RadioButton("False");
	private Button button1 = new Button("ADD");
	private ToggleGroup tglgrp1 = new ToggleGroup();

	public View3(Stage stage) {
		radioButton1.setToggleGroup(tglgrp1);
		radioButton2.setToggleGroup(tglgrp1);
		tglgrp1.selectToggle(radioButton1);
		this.stage = stage;
		GridPane gp = new GridPane();
		gp.setPadding(new Insets(15));
		gp.setAlignment(Pos.TOP_LEFT);
		gp.setHgap(10);
		gp.setVgap(10);
		tf1.setPrefWidth(300);
		for (int i = 0; i < 8; i++) {
			checkBox.add(new CheckBox());
			gp.add(checkBox.get(i), 2, i + 4);
			gp.setHalignment(checkBox.get(i), HPos.CENTER);
		}
		for (int i = 0; i < 8; i++) {
			checkBox1.add(new CheckBox());
			gp.add(checkBox1.get(i), 1, i + 4);
			gp.setHalignment(checkBox1.get(i), HPos.CENTER);

		}
		for (int i = 0; i < 8; i++) {
			textField.add(new TextField());
			textField.get(i).setPrefWidth(300);
			gp.add(textField.get(i), 0, i + 4);
		}
		gp.add(label4, 2, 3);
		gp.setHalignment(label4, HPos.CENTER);
		gp.add(radioButton1, 0, 0);
		gp.add(radioButton2, 1, 0);
		gp.add(label1, 0, 1);
		gp.add(tf1, 0, 2);
		gp.add(button2, 1, 2);
		gp.add(label2, 0, 3);
		gp.add(label3, 1, 3);
		gp.setHalignment(label3, HPos.CENTER);
		gp.add(button1, 2, 12);
		gp.add(error1, 0, 13);
		gp.add(error2, 1, 13);
		gp.add(error3, 1, 13);
		gp.add(label5, 1, 13);
		label5.setVisible(false);
		error1.setVisible(false);
		error1.setTextFill(Color.RED);
		error2.setVisible(false);
		error2.setTextFill(Color.RED);
		error3.setVisible(false);
		error3.setTextFill(Color.RED);
		button1.setVisible(false);
		Scene scene = new Scene(gp, 580, 475);
		stage.setTitle("ADD Question");
		stage.setScene(scene);
		action();
	}

	public void action() {
		radioButton2.setOnAction(e -> {
			error3.setVisible(false);
			for (int i = 1; i < 8; i++) {
				textField.get(i).setVisible(false);
				checkBox1.get(i).setVisible(false);
				checkBox.get(i).setVisible(false);
			}
			checkBox1.get(0).setSelected(true);
			checkBox1.get(0).setDisable(true);
			checkBox.get(0).setSelected(true);
			checkBox.get(0).setDisable(true);

		});
		radioButton1.setOnAction(e -> {
			error3.setVisible(false);
			for (int i = 1; i < 8; i++) {
				textField.get(i).setVisible(true);
				checkBox1.get(i).setVisible(true);
				checkBox.get(i).setVisible(true);
			}
			checkBox1.get(0).setDisable(false);
			checkBox.get(0).setDisable(false);

		});
		button2.setOnAction(e -> {
			label5.setVisible(false);
			RadioButton selectedRadioButton = (RadioButton) tglgrp1.getSelectedToggle();
			boolean typeOfQuestion = false; // false == open question
			if (selectedRadioButton == radioButton1)
				typeOfQuestion = true;
			String questionText = tf1.getText();
			boolean fValid = c.checkValid(typeOfQuestion, questionText);
			if (fValid) {
				button1.setVisible(true);
				error1.setVisible(false);
				error3.setVisible(false);
			} else {
				if(tf1.getText().equals("")) {
					error3.setVisible(true);
					button1.setVisible(false);
					error1.setVisible(false);
				}else {
					error3.setVisible(false);
					button1.setVisible(false);
					error1.setVisible(true);
				}
				
			}
		});

		tf1.textProperty().addListener(e -> {
			error3.setVisible(false);
			button1.setVisible(false);
			label5.setVisible(false);
		});

		button1.setOnAction(e -> {
			error3.setVisible(false);
			RadioButton selectedRadioButton = (RadioButton) tglgrp1.getSelectedToggle();
			String questionText = tf1.getText();
			String answerText;
			boolean fCorrect;
			if (selectedRadioButton == radioButton1) { // radiobutton1 = close question
				c.addQuestion(questionText, true);
				int counter = 0;
				for (int i = 0; i < checkBox.size(); i++) {
					if (checkBox.get(i).isSelected()) {
						answerText = textField.get(i).getText();
						fCorrect = checkBox1.get(i).isSelected();
						boolean fAnswerText = c.addAnswertoClosedQuestion(answerText, fCorrect);
						if (!fAnswerText || answerText.equals("")) {
							c.removeLastQuestion();
							if (!fAnswerText) {
								error2.setVisible(true);
								label5.setVisible(false);
								break;
							} else {
								error3.setVisible(true);
								label5.setVisible(false);
								break;
							}
						} else {
							error1.setVisible(false);
							error2.setVisible(false);
							error3.setVisible(false);
							label5.setVisible(true);
						}

					} else 
						counter++;
				}
				if(counter==checkBox.size()) {
					error1.setVisible(false);
					error2.setVisible(false);
					error3.setVisible(false);
					label5.setVisible(true);
				}
			} else {
				c.addQuestion(questionText, false);
				answerText = textField.get(0).getText();
				if (answerText.equals("")) {
					c.removeLastQuestion();
					error3.setVisible(true);
					label5.setVisible(false);
				} else
					c.addAnswertoOpenQuestion(answerText, true);
			}
			button1.setVisible(false);
		});

	}

	public void openView3() {
		stage.show();
		stage.setAlwaysOnTop(true);
	}

	public void setC(Controller c) {
		this.c = c;
	}
}
