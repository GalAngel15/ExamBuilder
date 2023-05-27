package Model;

import java.util.ArrayList;



public class OpenedQuestion extends Question implements Cloneable{
	private Answer ans;

	public OpenedQuestion(String quesText,Answer ans) {
		super(quesText);
		this.ans = ans;
	}
	

	public Answer getAns() {
		return ans;
	}

	public boolean setAns(Answer ans) {
		this.ans = ans;
		return true;
	}
	@Override
	public boolean isClosedQuestion() {
		return false;
	}
	
	@Override
	public int getAnsLength() {
		return ans.getAnsText().length();
	}
	
	@Override
	public OpenedQuestion clone() throws CloneNotSupportedException{
		OpenedQuestion temp = (OpenedQuestion)super.clone();
		temp.ans = (Answer)ans.clone();
		return temp;
	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof OpenedQuestion))
			return false;
		if (!(super.equals(other)))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String str = "[id=" + this.id + "], The question is: " + this.quesText;
		str = str + "\n The answer is: " + ans.toString() +"\n";
		return str;
	}

	
	
}
