package View;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class View1 {
	Controller c;
	private Label label = new Label(
			"Welcome to the GREATEST Test creator of all times!\nChoose one of the following options:");
	private Label error1 = new Label("ERROR: Not enough valid questions");
	private Label error2 = new Label("ERROR: No Tests");
	private Label error3 = new Label("ERROR: No old Collection has been made");
	private Label lab = new Label("Iterator has been made");
	private Button b1 = new Button("Show all questions");
	private Button b2 = new Button("Add question");
	private Button b3 = new Button("Update question");
	private Button b4 = new Button("Update answer for question");
	private Button b5 = new Button("Remove answer from question (permitted only to closed questions)");
	private Button b6 = new Button("Create test manually");
	private Button b7 = new Button("Create test automatically");
	private Button b8 = new Button("Create test from a test that exists");
	private Button b10 = new Button("Change All Tests ArrayList to TreeSet And Show it");
	private Button b11 = new Button("Change All tests TreeSet to Linked HashSet And Show it");
	private Button b12 = new Button("Change All tests Linked HashSet to My Array List and Show it");
	private Button b13 = new Button("Remove a test from MyArrayList and Java ArrayList");
	private Button b14 = new Button("Create iterator for the obsevers");
	private Button b15 = new Button("Change All tests Linked HashSet to My Array List and Show it With Observer");
	private Button b9 = new Button("Exit");
	
	private MyButton b16 = new MyButton(b15);
	private MyLabel lab1 = new MyLabel(lab);

	public View1(Stage primaryStage) {
		ScrollPane sp = new ScrollPane();
		VBox vb = new VBox(20, label, b2, b3, b1, b6, b7, b4, b8, b5,b10,b11,b12,b13,b14,b15, b9,lab, error1, error2,error3);
		vb.setPadding(new Insets(20));
		vb.setAlignment(Pos.CENTER);
		error1.setTextFill(Color.RED);
		error1.setVisible(false);
		error2.setTextFill(Color.RED);
		error2.setVisible(false);
		error3.setTextFill(Color.RED);
		error3.setVisible(false);
		b12.setDisable(true);
		b13.setDisable(true);
		b15.setDisable(true);
		b14.setDisable(true);
		lab.setVisible(false);
		sp.setContent(vb);
		sp.setFitToWidth(true);
		Scene menuScene = new Scene(sp, 600, 800);
		primaryStage.setTitle("Roy's and Gal's Test Creator");
		primaryStage.setScene(menuScene);
		primaryStage.show();
		primaryStage.setAlwaysOnTop(true);
		action(primaryStage);
	}

	public void setC(Controller c) {
		this.c = c;
	}

	public void action(Stage primaryStage) {
		b10.setOnAction(e -> {
			error1.setVisible(false);
			error3.setVisible(false);
			if(c.testsArrIsEmpty()) {
				error2.setVisible(true);
			}
			else {
			error2.setVisible(false);
			c.openView11();
			}
		});
		b11.setOnAction(e-> {
			error1.setVisible(false);
			error2.setVisible(false);
			if(c.isExistTreeCollection()) {
				error3.setVisible(true);
			}
			else {
			error2.setVisible(false);
			c.openView12();
			b12.setDisable(false);
			b13.setDisable(false);
			}
		});
		b12.setOnAction(e-> {
			c.changeToMyArrayList();
			c.openView13();
			b13.setDisable(false);
			b15.setDisable(true);
		});
		b13.setOnAction(e->{
			if(c.getArrayListLength() > 0) {
				b13.setDisable(false);
				c.openView14();
			}
			else
				b13.setDisable(true);
			b14.setDisable(false);
		});
		b14.setOnAction(e->{
			c.changeToMyArrayListWithObserver(lab1, b16);
			b13.setDisable(true);
		});
		b15.setOnAction(e->{
			c.openView13();
		});
		
		b1.setOnAction(e -> {
			error1.setVisible(false);
			error2.setVisible(false);
			error3.setVisible(false);
			c.openView2();
			});
		b2.setOnAction(e -> {
			error1.setVisible(false);
			error2.setVisible(false);
			error3.setVisible(false);
			c.openView3();
			});
		b3.setOnAction(e -> {
			error1.setVisible(false);
			error2.setVisible(false);
			error3.setVisible(false);
			c.openView4();
			});
		b4.setOnAction(e -> {
			error1.setVisible(false);
			error2.setVisible(false);
			error3.setVisible(false);
			c.openView6();
			});
		b5.setOnAction(e -> {
			error1.setVisible(false);
			error2.setVisible(false);
			error3.setVisible(false);
			c.openView5();
			});
		b6.setOnAction(e -> {
			error1.setVisible(false);
			error2.setVisible(false);
			error3.setVisible(false);
			c.openView10();
			});
		b7.setOnAction(e -> {
			error2.setVisible(false);
			error3.setVisible(false);
			if(c.getNumOfQuestionsForAutoTest() == 0) {
				error1.setVisible(true);
			}
			else {
				error1.setVisible(false);
			c.openView7();
			}
			});
		b8.setOnAction(e -> {
			error1.setVisible(false);
			error3.setVisible(false);
			if(c.testsArrIsEmpty()) {
				error2.setVisible(true);
			}
			else {
			error2.setVisible(false);
			c.openView9();
			}
			});
		b9.setOnAction(e -> {
			error1.setVisible(false);
			error2.setVisible(false);
			error3.setVisible(false);
			try {
				c.save();
				primaryStage.close();
			} catch (FileNotFoundException ex) {
				JOptionPane.showMessageDialog(null, ex.toString());
				primaryStage.close();
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(null, ex.toString());
				primaryStage.close();
			}
		});
	}

}
