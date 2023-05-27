package Model;

import java.io.Serializable;
import java.util.Comparator;


public abstract class Question implements Serializable, Comparable<Question>,Cloneable{

	private static final long serialVersionUID = 1L;
	protected String quesText;
	private static int counter = 1000;
	protected int id;

	public Question(String quesText) {
		this.quesText = quesText;
		id = counter++;
	}
	
	public Memento createMemento() {
		return new Memento(quesText,id);
	}

	public void setMemento(Memento m) {
		quesText =  m.getQuesText();
		id = m.getId();
	}

	public static class Memento {
		protected String quesText;
		protected int id;

		private Memento(String quesText, int id) {
			this.quesText = quesText;
			this.id = id;
		}
		
		public String getQuesText(){
			return quesText;
		}
		
		public int getId() {
			return id;
		}

	}

	@Override
	public boolean equals(Object other) {
		if(!(other instanceof Question))
			return false;
		
		Question q = (Question)other;
		return q.quesText.equals(quesText);
	}
	
	public abstract boolean isClosedQuestion();
	
	public boolean setQuesText(String updatedText) {
		this.quesText = updatedText;
		return true;
	}
	
	public String getQuesText() {
		return quesText;
	}


	public int getId() {
		return id;
	}
	

	public void setCounter(int counter) {
		Question.counter = counter;
	}
	public int getCounter() {
		return Question.counter;
	}
	
	@Override
	public Question clone() throws CloneNotSupportedException {
		Question temp = (Question)super.clone();
		return temp;
	}
	
	public abstract int getAnsLength();
	
	@Override
	public int compareTo(Question q) {
	
		if(this.getAnsLength() < q.getAnsLength())
			return -1;
		else if(this.getAnsLength() > q.getAnsLength())
			return 1;
		else
			return 0;
	}
	
		
	
	@Override
	public String toString() {
		return "[id=" + id + "], The question is: " + quesText; // add correct answer
	}
	
}
	
