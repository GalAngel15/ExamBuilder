package Id211424346_Id314689589;

import View.View1;
import View.View10;
import View.View11;
import View.View12;
import View.View13;
import View.View14;
import View.View2;
import View.View3;
import View.View4;
import View.View5;
import View.View6;
import View.View7;
import View.View8;
import View.View9;
import javafx.application.Application;
import javafx.stage.Stage;
import Controller.Controller;
import Model.Model;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		View1 view1 = new View1(primaryStage);
		View2 view2 = new View2(new Stage());
		View3 view3 = new View3(new Stage());
		View4 view4 = new View4(new Stage());
		View5 view5 = new View5(new Stage());
		View6 view6 = new View6(new Stage());
		View7 view7 = new View7(new Stage());
		View8 view8 = new View8(new Stage());
		View9 view9 = new View9(new Stage());
		View10 view10 = new View10(new Stage());
		View11 view11 = new View11(new Stage());
		View12 view12 = new View12(new Stage());
		View13 view13 = new View13(new Stage());
		View14 view14 = new View14(new Stage());
		Model model = new Model();
		Controller controller = new Controller(model, view1, view2, view3,view4,view5,view6, view7, view8, view9, view10, view11, view12, view13, view14);

	}

}
