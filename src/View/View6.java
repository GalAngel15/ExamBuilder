package View;

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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class View6 {
	Controller c;
	Stage stage;
	private Label label1 = new Label("Choose the question ID you want to update answer on:");
	private Label label2 = new Label("The question is:");
	private Label label3 = new Label("Choose the number of the answer you want to update:");
	private Label label4 = new Label("Type new answer");
	private Label label5 = new Label(""); // question text
	private Label label6 = new Label("Answer updated successfully");
	private Label label7 = new Label("Check if True: (disabled for open questions)");
	private Label error1 = new Label("ERROR: Cannot accpet empty text");
	private Label error2 = new Label("ERROR: Must choose a question");
	private ComboBox<Integer> comboBox1 = new ComboBox<>();
	private ComboBox<Integer> comboBox2 = new ComboBox<>();
	private CheckBox cb = new CheckBox();
	private TextField tf = new TextField();
	private Button button = new Button("Update");

	public View6(Stage stage) {
		this.stage = stage;
		ScrollPane sp = new ScrollPane();
		VBox vb = new VBox(30, label1, comboBox1, label2, label5, label3, comboBox2, label4, tf, label7, cb, button,
				label6, error2, error1);
		sp.setContent(vb);
		vb.setPadding(new Insets(20));
		vb.setAlignment(Pos.CENTER);
		sp.setFitToWidth(true);
		Scene scene = new Scene(sp, 500, 700);
		stage.setTitle("Update answer");
		stage.setScene(scene);
		error1.setTextFill(Color.RED);
		error2.setTextFill(Color.RED);
		label6.setVisible(false);
		error1.setVisible(false);
		error2.setVisible(false);
		action();
	}

	public void action() {
		comboBox1.setOnAction(e -> {
			if (comboBox1.getValue() != null) {
				error1.setVisible(false);
				error2.setVisible(false);
				label5.setText(c.getQuestionTextById(comboBox1.getValue()));
				if (c.getAllQuestions().get(c.getQuestionIndexById(comboBox1.getValue())).isClosedQuestion()) {
					label3.setVisible(true);
					comboBox2.setDisable(false);
					comboBox2.getItems().clear();
					for (int i = 2; i < c
							.getClosedQuestionAnswerArraySize(c.getQuestionIndexById(comboBox1.getValue())); i++) {
						comboBox2.getItems().add(i + 1);
					}
					comboBox2.getSelectionModel().selectFirst();
					cb.setDisable(false);
				} else {
					cb.setDisable(true);
					label3.setVisible(false);
					comboBox2.setDisable(true);
				}
			}
		});
		button.setOnAction(e -> {
			if (comboBox1.getValue() == null) {
				error2.setVisible(true);
			} else if (tf.getText().equals("")) {
				error1.setVisible(true);
				label6.setVisible(false);
			} else {
				error1.setVisible(false);
				error2.setVisible(false);
				int id = comboBox1.getValue();
				String answerText = tf.getText();
				int questionIndex = c.getQuestionIndexById(id);
				boolean isTrue = cb.isSelected();
				if (c.getAllQuestions().get(questionIndex).isClosedQuestion()) {
					int answerIndex = comboBox2.getValue();
					c.updateAnswerOnClosedQuestion(answerIndex, answerText, isTrue, questionIndex);
				} else
					c.updateAnswerOnOpenedQuestion(answerText, questionIndex);
				label6.setVisible(true);
			}
		});
		tf.textProperty().addListener(e -> {
			label6.setVisible(false);
			error1.setVisible(false);
			error2.setVisible(false);
		});
		;
	}

	public void openView6() {
		int questionIndex = 0;
		comboBox1.getSelectionModel().clearSelection();
		comboBox2.getSelectionModel().clearSelection();
		label5.setText("");
		for (int i = 0; i < c.getAllQuestions().size(); i++) {
			questionIndex = i;
			if (!comboBox1.getItems().contains(c.getAllQuestions().get(i).getId()))
				comboBox1.getItems().add(c.getAllQuestions().get(i).getId());
		}
		if (c.getAllQuestions().get(questionIndex).isClosedQuestion()) {
			for (int i = 2; i < c.getClosedQuestionAnswerArraySize(questionIndex); i++) {
				comboBox2.getItems().add(i + 1);
			}
		} else {
			label3.setVisible(false);
			comboBox2.setDisable(true);
			cb.setDisable(true);
		}
		stage.show();
		stage.setAlwaysOnTop(true);
	}

	public void setC(Controller c) {
		this.c = c;
	}
}
