package Model;

import java.util.ArrayList;


public class ClosedQuestion extends Question {
	private final int MAX_ANSWERS = 10;
	private Set<Answer> ans;

	public ClosedQuestion(String quesText, ArrayList<Answer> newAns) {
		super(quesText);
		this.ans = new Set<Answer>();
		Answer a1 = new Answer("There is more than one right answer", false, true);
		Answer a2 = new Answer("There are no right answers", true, true);
		ans.add(a1);
		ans.add(a2);
		ans.addAll(newAns);
	}
	
	public String getUsedAnswers() {
		String str = "[id=" + this.id + "], The question is: " + this.quesText;
		str = str + "\nThe answers are: \n";
		int j = 0;
		for (int i = 0; i < ans.size(); i++) {
			if (ans.get(i) != null) {
				if (ans.get(i).getIsUsed()) {
					str = str + (j + 1) + ")" + ans.get(i) + "\n";
					j++;
				}
			}
		}
		return str;

	}

	public Set<Answer> getAns() {
		return ans;
	}

	public void updateUsedAnwserArray() {
		int correctAnsCounter = 0;
		for (int i = 2; i < ans.size(); i++) {
			if (ans.get(i).getIsUsed() && ans.get(i).getIsCorrect())
				correctAnsCounter++;
		}
		if (correctAnsCounter == 1) {
			ans.get(1).setCorrect(false);
			ans.get(0).setCorrect(false);
		}
		else if (correctAnsCounter == 0) {
			ans.get(1).setCorrect(true);
			ans.get(0).setCorrect(false);
		} else {
			ans.get(0).setCorrect(true);
			ans.get(1).setCorrect(false);
			for (int i = 1; i < ans.size(); i++) {
				if (ans.get(i).getIsUsed())
					ans.get(i).setCorrect(false);
			}
		}

	}

	public void printAnswerArray() {
		for (int i = 0; i < ans.size(); i++) {
			System.out.println(i + 1 + ")" + ans.get(i).getAnsText() + "->" + ans.get(i).getIsCorrect());
		}
	}

	public boolean setAns(Set<Answer> ans) {
		this.ans = ans;
		return true;
	}

	public int getMAX_ANSWERS() {
		return MAX_ANSWERS;
	}

	public boolean updateAnswer(Answer theAnswer, Answer updatedAnswer) {
		for (int i = 0; i < ans.size(); i++) {
			if (ans.get(i).equals(theAnswer)) {
				ans.set(i, updatedAnswer);
				System.out.println("Answer updated");
				return true;
			}
		}
		System.out.println("This answer does not exist");
		return false;
	}

	public boolean removeAnswer(Answer theAnswer) {
		for (int i = 0; i < ans.size(); i++) {
			if (ans.get(i).equals(theAnswer)) {
				ans.remove(i);
				System.out.println("Success,answer was removed");
				return true;
			}
		}
		System.out.println("ERROR,answer not exist");
		return false;

	}
	@Override
	public boolean isClosedQuestion() {
		return true;
	}
	
	public boolean addAnswer(Answer theAnswer) {
		if (ans.size() == MAX_ANSWERS) {
			System.out.println("ERROR, There are already 10 answers");
			return false;
		}

		for (int i = 0; i < ans.size(); i++) {
			if (ans.get(i).equals(theAnswer)) {
				System.out.println("ERROR, There is already an answer like that");
				return false;
			}
		}
		ans.add(theAnswer);
		System.out.println("SUCCESS, Answer added succesfully");
		return true;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof ClosedQuestion))
			return false;
		if (!(super.equals(other)))
			return false;
		return true;
	}

	@Override
	public ClosedQuestion clone() throws CloneNotSupportedException {
		ClosedQuestion temp = (ClosedQuestion) super.clone();
		temp.setAns((Set<Answer>) this.ans.clone());
		for (int i = 0; i < ans.size(); i++) {
			temp.ans.set(i, ans.get(i).clone());
		}
		return temp;
	}

	@Override
	public int getAnsLength() {
		int count = 0;
		for (int i = 0; i < ans.size(); i++) {
			count += ans.get(i).getAnsText().length();
		}
		return count;
	}

	@Override
	public String toString() {
		String str = "[id=" + this.id + "], The question is: " + this.quesText;
		str = str + "\nThe answers are: \n";
		for (int i = 0; i < ans.size(); i++) {
			str = str + (i + 1) + ")" + this.ans.get(i).toString() + "\n";
		}
		return str;
	}

}
