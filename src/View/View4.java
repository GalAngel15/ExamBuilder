package View;


import Controller.Controller;
import Model.Set;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class View4{
	Controller c;
	Stage stage;
	private Label label1 = new Label("Choose the question ID you want to update:");
	private Label label2 = new Label("The question is:");
	private Label label3 = new Label("New question text:");
	private Label label4 = new Label(); // question text
	private Label error1 = new Label("ERROR: Cannot accpet empty text");
	private Label label5 = new Label("Question updated successfully");
	private ComboBox<Integer> comboBox = new ComboBox<>();
	private TextField tf = new TextField();
	private Button button = new Button("Update");
	private Button Undo = new Button("Undo");

	public View4(Stage stage) {
		this.stage = stage;
		VBox vb = new VBox(30, label1, comboBox, label2, label4, label3, tf, button,Undo, error1, label5);
		vb.setPadding(new Insets(20));
		vb.setAlignment(Pos.CENTER);
		Scene scene = new Scene(vb, 500, 500);
		stage.setTitle("Update Question");
		stage.setScene(scene);
		error1.setTextFill(Color.RED);
		Undo.setDisable(true);
		error1.setVisible(false);
		label5.setVisible(false);
		action();
	}

	public void action() {
		comboBox.setOnAction(e -> {
			label4.setText(c.getQuestionTextById(comboBox.getValue()));
		});
		button.setOnAction(e -> {
			if (tf.getText().equals("")) {
				error1.setVisible(true);
				label5.setVisible(false);
			} else {
				error1.setVisible(false);
				int id = comboBox.getValue();
				String questionText = tf.getText();
				c.getMemento();
				c.updateQuestion(questionText, id);
				label5.setVisible(true);
				Undo.setDisable(false);
			}

		});
		Undo.setOnAction(E->{
			c.setMemento();
		});
	}

	public void openView4() {
		for (int i = 0; i < c.getAllQuestions().size(); i++) {
			if (!comboBox.getItems().contains(c.getAllQuestions().get(i).getId()))
				comboBox.getItems().add(c.getAllQuestions().get(i).getId());
		}
		comboBox.getSelectionModel().selectFirst();
		stage.show();
		stage.setAlwaysOnTop(true);
	}

	public void setC(Controller c) {
		this.c = c;
	}

}
