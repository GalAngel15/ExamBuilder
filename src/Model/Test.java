package Model;

import java.util.ArrayList;
import java.util.Objects;




public class Test implements Cloneable {
	private ArrayList<Question> questions;
	private int numberOfQuestions;
	

	public Test(int numberOfQuestions) {
		this.numberOfQuestions = numberOfQuestions;
		questions = new ArrayList<Question>(numberOfQuestions);
	}
	
	public boolean updateQuestionOnTest(Question theQuestion, Question updatedQuestion) {
		int quesNum;
		if (questions.contains(theQuestion)) {
			quesNum = questions.indexOf(theQuestion);
			questions.set(quesNum, updatedQuestion);
			System.out.println("Question updated");
			return true;
		}
		System.out.println("This Question does not exist");
		return false;
	}

	public boolean removeQuestionFromTest(Question theQuestion) {
		int quesNum;
		if (questions.contains(theQuestion)) {
			quesNum = questions.indexOf(theQuestion);
			questions.remove(quesNum);
			System.out.println("Success,Question was removed");
			return true;
		}
		System.out.println("ERROR,Question not exist");
		return false;
	}

	public boolean addQuestionToTest(Question theQuestion) {
		if (!questions.contains(theQuestion)) {
			questions.add(theQuestion);
			return true;
		}
		System.out.println("ERROR, There is already question like that");
		return false;
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<Question> questions) {
		this.questions = (ArrayList<Question>) questions.clone();
	}

	@Override
	public Test clone() throws CloneNotSupportedException {
		Test temp = (Test) super.clone();
		temp.setQuestions((ArrayList<Question>) this.questions);
		for (int i = 0; i < questions.size(); i++) {
			temp.questions.set(i, questions.get(i).clone());
		}
		return temp;
	}

	public String testWithNoAnswers() {
		String str = "Test: \n The questions are: \n";
		for (int i = 0; i < questions.size(); i++) {
			str = str + (i + 1) + ")" + questions.get(i).getQuesText() + "\n";

		}
		return str;
	}


	@Override
	public int hashCode() {
		return Objects.hash(numberOfQuestions);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Test other = (Test) obj;
		return numberOfQuestions == other.numberOfQuestions && Objects.equals(questions, other.questions);
	}

	@Override
	public String toString() {
		String str = "Test: \n The questions are: \n";
		for (int i = 0; i < questions.size(); i++) {

			if (questions.get(i) instanceof ClosedQuestion) {
				ClosedQuestion q = (ClosedQuestion) questions.get(i);
				str = str + (i + 1) + ")" + q.getUsedAnswers() + "\n";
			} else
				str = str + (i + 1) + ")" + questions.get(i).toString() + "\n";

		}
		return str;
	}

}
