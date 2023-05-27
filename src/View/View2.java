package View;

import Controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class View2 implements Command{
	Controller c;
	Stage stage;
	private Label allQuestions;
	private String t = "";

	public View2(Stage stage) {
		allQuestions = new Label(t);
		this.stage = stage;
		ScrollPane sp = new ScrollPane();
		sp.setContent(allQuestions);
		sp.setPadding(new Insets(10));
		Scene allQuestionScene = new Scene(sp, 500, 500);
		stage.setTitle("All Questions");
		stage.setScene(allQuestionScene);
	}
	
	
	@Override
	public void execute() {
		allQuestions.setText(c.getAllQuestionsString());
		stage.show();
		stage.setAlwaysOnTop(true);
	}

	public void setC(Controller c) {
		this.c = c;
	}

}
