package View;


import Model.Observer;
import javafx.scene.control.Button;

public class MyButton extends Button implements Observer {
	private Button b;
	
	public MyButton(Button b) {
		this.b=b;
	}
	
	@Override
	public void update() {
		b.setDisable(false);
	}
	
}

