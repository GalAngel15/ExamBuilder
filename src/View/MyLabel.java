package View;

import Model.Observer;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class MyLabel extends Label implements Observer{
	private Label lab;
	
	public MyLabel(Label lab) {
		this.lab=lab;
	}
	@Override
	public void update() {
		lab.setTextFill(Color.GREEN);
		lab.setVisible(true);
		
	}

}
