package Model;

import java.io.Serializable;

public class Answer implements Serializable,Cloneable{
	private static final long serialVersionUID = 1L;
	private String ansText;
	private boolean isCorrect;
	private boolean isUsed;
	
	public Answer(String ansText, boolean isCorrect, boolean isUsed) {
		this.ansText = ansText;
		this.isCorrect = isCorrect;
		this.isUsed = isUsed;
	}
	
	public Answer(Answer other) {
		setAnsText(other.ansText);
		setCorrect(other.isCorrect);
		setIsUsed(other.isUsed);
	}
	
	public String getAnsText() {
		return ansText;
	}

	public boolean setAnsText(String ansText) { // check input-  לעשות בדיקת קלט 
		this.ansText = ansText;
		return true;
	}

	public boolean getIsCorrect() {
		return isCorrect;
	}

	public boolean setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
		return true;
	}
	
	public boolean getIsUsed() {
		return isUsed;
	}

	public boolean setIsUsed(boolean isUsed) {
		this.isUsed = isUsed;
		return true;
	}
	
	@Override
	public Answer clone() throws CloneNotSupportedException {
		Answer temp = (Answer)super.clone();
		return temp;
	}
	
	@Override
	public boolean equals(Object other) {
		if(!(other instanceof Answer))
			return false;
		
		Answer a = (Answer)other;
		return a.ansText.equals(ansText) && a.isCorrect == isCorrect;
	}

	@Override
	public String toString() {
		return ansText + ", true/false: " + isCorrect;
	}

	
	
	
	
}
