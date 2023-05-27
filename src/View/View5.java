package View;

import Controller.Controller;
import Model.ClosedQuestion;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class View5 {
	Controller c;
	Stage stage;
	private Label label1 = new Label("Choose the question ID you want to delete an answer from:");
	private Label label2 = new Label("The question is:");
	private Label label3 = new Label("Number of answer to delete:");
	private Label label4 = new Label(); // question text
	private Label label5 = new Label("Answer deleted!");
	private Label error1 = new Label("ERROR: Must choose question and answer");
	private ComboBox<Integer> comboBox1 = new ComboBox<>();
	private ComboBox<Integer> comboBox2 = new ComboBox<>();
	private Button button = new Button("Remove answer");

	public View5(Stage stage) {
		this.stage = stage;
		ScrollPane sp = new ScrollPane();
		VBox vb = new VBox(30, label1, comboBox1, label2, label4, label3, comboBox2, button, label5, error1);
		error1.setTextFill(Color.RED);
		sp.setContent(vb);
		sp.setFitToWidth(true);
		vb.setPadding(new Insets(20));
		vb.setAlignment(Pos.CENTER);
		Scene scene = new Scene(sp, 600, 600);
		stage.setTitle("Remove answer");
		stage.setScene(scene);
		comboBox2.setDisable(true);
		label5.setVisible(false);
		error1.setVisible(false);
		action();
	}

	public void action() {
		comboBox1.setOnAction(e -> {
			label5.setVisible(false);
			comboBox2.setDisable(false);
			error1.setVisible(false);
			label4.setText(c.getQuestionTextById(comboBox1.getValue()));
			comboBox2.getItems().clear();
			for (int i = 2; i < c.getClosedQuestionAnswerArraySize(c.getQuestionIndexById(comboBox1.getValue())); i++) {
				comboBox2.getItems().add(i + 1);
			}
			comboBox2.getSelectionModel().selectFirst();
		});
		comboBox2.setOnAction(e -> {
			label5.setVisible(false);
			error1.setVisible(false);
		});

		button.setOnAction(e -> {
			if (comboBox1.getValue() == null || comboBox2.getValue() == null) {
				error1.setVisible(true);
				label5.setVisible(false);
			} else {
				error1.setVisible(false);
				c.removeAnswer(comboBox2.getValue(), c.getQuestionIndexById(comboBox1.getValue()));
				label4.setText(c.getQuestionTextById(comboBox1.getValue()));
				comboBox2.getItems().remove(comboBox2.getValue());
				comboBox2.getItems().clear();
				for (int i = 2; i < c
						.getClosedQuestionAnswerArraySize(c.getQuestionIndexById(comboBox1.getValue())); i++) {
					comboBox2.getItems().add(i + 1);
				}
				label5.setVisible(true);
			}
		});
	}

	public void openView5() {
		int questionIndex = 0;
		for (int i = 0; i < c.getAllQuestions().size(); i++) {
			if (c.getAllQuestions().get(i).isClosedQuestion()) {
				questionIndex = i;
				if (!comboBox1.getItems().contains(c.getAllQuestions().get(i).getId())) {
					comboBox1.getItems().add(c.getAllQuestions().get(i).getId());
				}
			}
		}
		for (int i = 2; i < c.getClosedQuestionAnswerArraySize(questionIndex); i++) {
			comboBox2.getItems().add(i + 1);
		}
		stage.show();
		stage.setAlwaysOnTop(true);
	}

	public void setC(Controller c) {
		this.c = c;
	}
}
