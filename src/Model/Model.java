package Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Set;

import Controller.Controller;

public class Model {
	private Manager theManager;
	ArrayList<Integer> questionIdArr = new ArrayList<>();
	ArrayList<Boolean> usedAns = new ArrayList<>();

	public Model() throws FileNotFoundException, ClassNotFoundException, IOException {
		Answer a1 = new Answer("7", true, false);
		Question q1 = new OpenedQuestion("The world has ___ continents:", a1);
		Answer b2 = new Answer("2", true, false);
		Answer b3 = new Answer("-2", true, false);
		Answer b4 = new Answer("3", false, false);
		Answer b5 = new Answer("-4", false, false);
		Answer b6 = new Answer("5", false, false);
		Answer b7 = new Answer("-6", false, false);
		ArrayList<Answer> b = new ArrayList<Answer>();
		b.add(b2);
		b.add(b3);
		b.add(b4);
		b.add(b5);
		b.add(b6);
		b.add(b7);
		Question q2 = new ClosedQuestion("Sqrt4=", b);
		Answer c2 = new Answer("n", false, false);
		Answer c3 = new Answer("g", true, false);
		ArrayList<Answer> c = new ArrayList<Answer>();
		c.add(c2);
		c.add(c3);
		Question q3 = new ClosedQuestion("Abcdef? complete the sentence", c);
		Answer d2 = new Answer("blue", false, false);
		Answer d3 = new Answer("purple", false, false);
		ArrayList<Answer> d = new ArrayList<Answer>();
		d.add(d2);
		d.add(d3);
		Question q4 = new ClosedQuestion("When you mix blue and yellow, you get? ", d);
		Answer e1 = new Answer("2", true, false);
		Question q5 = new OpenedQuestion("Sqrt4=", e1);
		theManager = new Manager(q1, q2, q3, q4, q5);
	}

	public boolean save() throws FileNotFoundException, IOException {
		theManager.saveAllQuestion();
		return true;
	}

	public String getAllQuestionsString() {
		return theManager.toString();
	}

	public ArrayList<Question> getAllQuestions() {
		return theManager.getAllQuestionsArray();
	}

	public boolean checkValid(boolean typeOfQuestion, String quesText) {
		if(quesText.equals(""))
			return false;
		for (int i = 0; i < theManager.getAllQuestionsArray().size(); i++) {
			if (quesText.equals(theManager.getAllQuestionsArray().get(i).getQuesText()))
				if ((typeOfQuestion && theManager.getAllQuestionsArray().get(i) instanceof ClosedQuestion)
						|| (!typeOfQuestion && theManager.getAllQuestionsArray().get(i) instanceof OpenedQuestion))
					return false;
		}
		return true;
	}

	public boolean addQuestion(String newQuesText, boolean choice) {
		return theManager.addQuestion(newQuesText, choice);
	}

	public boolean addAnswertoClosedQuestion(String answerText, boolean fCorrect) {
		return theManager.addAnswerToClosedQuestion(answerText, fCorrect,
				(ClosedQuestion) theManager.getAllQuestionsArray().get(theManager.getAllQuestionsArray().size() - 1));
	}

	public boolean addAnswertoOpenQuestion(String answerText, boolean fCorrect) {
		return theManager.addAnswerToOpenedQuestion(answerText, fCorrect,
				(OpenedQuestion) theManager.getAllQuestionsArray().get(theManager.getAllQuestionsArray().size() - 1));
	}

	public void removeLastQuestion() {
		theManager.getAllQuestionsArray().remove(theManager.getAllQuestionsArray().size() - 1);
	}

	public String getQuestionTextById(int id) {
		String str = "";
		for (int i = 0; i < theManager.getAllQuestionsArray().size(); i++) {
			if (theManager.getAllQuestionsArray().get(i).getId() == id)
				str = theManager.getAllQuestionsArray().get(i).toString();
		}
		return str;
	}
	
	
	public void updateQuestion(String questionText, int id) {
		for (int i = 0; i < theManager.getAllQuestionsArray().size(); i++) {
			if (theManager.getAllQuestionsArray().get(i).getId() == id)
				theManager.getAllQuestionsArray().get(i).setQuesText(questionText);
		}

	}
	public int getClosedQuestionAnswerArraySize(int questionIndex) {
		return theManager.getNumOfAnswersOnClosedQuestion(theManager.getAllQuestionsArray().get(questionIndex));
	}
	public int getQuestionIndexById(int questionId) {
		int questionIndex =0;
		for (int i = 0; i < theManager.getAllQuestionsArray().size(); i++) {
			if (theManager.getAllQuestionsArray().get(i).getId() == questionId)
				questionIndex = i;
		}
		return questionIndex;
	}
	public void removeAnswer(int ansNum,int questionIndex) {
		theManager.deleteAnswerOnCloseQuestion(ansNum, questionIndex);
	}
	public void updateAnswerOnClosedQuestion(int answerIndex,String answerText,boolean isTrue,int id) {
		theManager.updateAnswerOnCloseQuestion(answerIndex, answerText, isTrue, id);
	}
	public void updateAnswerOnOpenedQuestion(String answerText,int id) {
		theManager.updateAnswerOnOpenedQuestion(answerText, id);
	}
	
	public int getNumOfQuestionsForAutoTest() {
		return theManager.getNumOfClosedQuestionsForAutoTest()+theManager.getNumOfOpenedQuestionsForAutoTest();
	}
	public boolean createAutoTest(int numOfQuestions) throws FileNotFoundException {
		return theManager.createAutoTest(numOfQuestions);
	}
	
	public String getLastTestString() {
		return theManager.getLastTestString();
	}
	public boolean testsArrIsEmpty() {
		return theManager.getAllTestsArray().isEmpty();
	}
	public int getAllTestsArraySize() {
		return theManager.getAllTestsArray().size();
	}
	public String getTestStringByIndex(int index) {
		return theManager.getAllTestsArray().get(index-1).toString();
	}
	
	public void copyTest(int testNum) throws CloneNotSupportedException {
		theManager.copyTest(testNum);
	}
	public void addAnsToUsedAnsArr(boolean isUsed) {
		usedAns.add(isUsed);
	}
	public void addQuestionToQuestionIdArr(int questionId) {
		questionIdArr.add(questionId);
		Question q = getAllQuestions().get(getQuestionIndexById(questionId));
		if (q.isClosedQuestion()) {
			ClosedQuestion cq = (ClosedQuestion) q;
			theManager.updateAnswer(cq, 0);
			theManager.updateAnswer(cq, 1);
			for(int i = 2; i < cq.getAns().size(); i++) {
				if(usedAns.get(i)) {
					theManager.updateAnswer(cq, i);
				}
			}
		}
	}
	public void resetQuestionIdArr() {
		questionIdArr.clear();
	}
	public void resetUsedAnsArr() {
		usedAns.clear();
	}
	public int getQuestionIdArrSize() {
		return questionIdArr.size();
	}
	public void createManualTest() throws FileNotFoundException {
		theManager.createManualTest(questionIdArr);
	}
	
	public boolean isExistTreeCollection() {
		return theManager.isExistTreeCollection();
	}
	
	public String getNewAllTestsStringOnHash() {
		theManager.changeToHashCollection();
		return theManager.printHashCollection();
	}
	
	
	
	public String getNewAllTestsString() {
		theManager.changeToTreeCollection();
		return theManager.printCollection();
	}
	
	public void changeToMyArrayListWithObserver(Observer lab, Observer b16) {
		theManager.changeToMyArrayListWithObserver(lab,b16);
	}
	
	public void changeToMyArrayList() {
		theManager.changeToMyArrayList();
	}
	
	public String getMyArrayList() {
		return theManager.printMyArray();
	}
	
	public String getMyArrayListWithRemove() {
		return theManager.removeOnMyArray();
		
	}
	
	public String getTheLinkedArrayListWithRemove() {
		return theManager.removeOnArrayList();
	}
	
	public int getArrayListLength() {
		return theManager.getArrayListLength();
	}
	
	public void getMemento() {
		theManager.createMemento();
	}
	
	public void setMemento() {
		theManager.setMemento();
	}
}
