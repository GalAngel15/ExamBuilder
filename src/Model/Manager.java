package Model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Stack;
import java.util.TreeSet;

import Model.Question.Memento;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Manager {
	private ArrayList<Test> allTests;
	private ArrayList<Question> allQuestions;
	private TreeSet <Test> newTreeCollectionQuestions;
	private LinkedHashSet <Test> newHashCollectionQuestions;
	private MyArrayList myArray;
	private ArrayList<Test> theLinkedArrayList;
	private ArrayList<Memento> previousArraylist;
	private static int counter = 1;
	private int testNum;

	public Manager(Question q1, Question q2, Question q3, Question q4, Question q5)
			throws FileNotFoundException, ClassNotFoundException, IOException {
		allTests = new ArrayList<Test>();
		allQuestions = new ArrayList<Question>();
		allQuestions.add(q1);
		allQuestions.add(q2);
		allQuestions.add(q3);
		allQuestions.add(q4);
		allQuestions.add(q5);
		Test theTest = new Test(5);
		Test theOtherTest = new Test(4);
		theTest.addQuestionToTest(q1);
		theTest.addQuestionToTest(q1);
		theTest.addQuestionToTest(q2);
		theTest.addQuestionToTest(q3);
		theTest.addQuestionToTest(q4);
		theTest.addQuestionToTest(q5);
		theOtherTest.addQuestionToTest(q1);
		theOtherTest.addQuestionToTest(q2);
		theOtherTest.addQuestionToTest(q3);
		theOtherTest.addQuestionToTest(q4);
		allTests.add(theOtherTest);
		allTests.add(theTest);
		allTests.add(theTest);
		
		newTreeCollectionQuestions= new TreeSet<Test>((p1,p2)->{
			int res1 = p1.getQuestions().size();
			int res2= p2.getQuestions().size();
			if(res1 > res2)
				return -1;
			return 1;
    });

		newHashCollectionQuestions = new LinkedHashSet<>();
		previousArraylist = new ArrayList<>();
		
		try {
			read();
		} catch (FileNotFoundException e) {
		}
		
		
	}
	//Part3
	
	public void createMemento() {
		for(int i = 0 ; i < allQuestions.size();i++) {
			previousArraylist.add(allQuestions.get(i).createMemento());
		}
	}
	
	public void setMemento() {
		for(int i = 0 ; i < allQuestions.size();i++) {
			allQuestions.get(i).setMemento(previousArraylist.get(i));
		}
	}
	
	//Part2
	public void changeToMyArrayListWithObserver(Observer lab, Observer b16) {
		myArray = new MyArrayList();
		myArray.attach(lab);
		myArray.attach(b16);
		theLinkedArrayList = new ArrayList<>();
		Iterator<Test> itr = newHashCollectionQuestions.iterator();
		myArray.click();
		Test theTest;
		while(itr.hasNext()) {
			theTest = itr.next();
			myArray.add(theTest);
			theLinkedArrayList.add(theTest);
		}
	}
	
	public void changeToMyArrayList() {
		myArray = new MyArrayList();
		theLinkedArrayList = new ArrayList<>();
		Iterator<Test> itr = newHashCollectionQuestions.iterator();
		Test theTest;
		while(itr.hasNext()) {
			theTest = itr.next();
			myArray.add(theTest);
			theLinkedArrayList.add(theTest);
		}
	}
	
	
	public String printMyArray() {
		String str = "";
		Iterator<Test> itr = myArray.iterator();
		while(itr.hasNext()) {
			str += itr.next() + "\n";
		}
		return str;
	}
	
	public String removeOnMyArray() {
		StringBuffer str1 = new StringBuffer();
		Iterator<Test> itr = myArray.iterator();
		int i = 0;
		itr.next();
		itr.remove();
		while(itr.hasNext()) {
			str1.append("Test number " + (i + 1) + ":" + "\n");
			str1.append(itr.next() + "\n");
			i++;
		}
		return str1.toString();
	}
	
	public String removeOnArrayList() {
		StringBuffer str1 = new StringBuffer();
		Iterator<Test> itr = theLinkedArrayList.iterator();
		itr.next();
		itr.remove();
		for (int i = 0; i < theLinkedArrayList.size(); i++) {
			str1.append("Test number " + (i + 1) + ":" + "\n");
			str1.append(theLinkedArrayList.get(i).toString()+ "\n");
		}
		return str1.toString();
	}
	
	public int getArrayListLength() {
		return theLinkedArrayList.size();
	}
	
	
	
	
	
	
	//Part1
	public void changeToHashCollection(){
		newHashCollectionQuestions.addAll(newTreeCollectionQuestions);
	}
	
	public String printHashCollection() {
		String str = "";
		Iterator<Test> itr = newHashCollectionQuestions.iterator();
		while(itr.hasNext())
			str += itr.next() + "\n";
		return str;
	}
	
	public void changeToTreeCollection(){
		newTreeCollectionQuestions.clear();
		for(int i = 0; i < allTests.size(); i++)
			newTreeCollectionQuestions.add(allTests.get(i));
	}
	
	public String printCollection() {
		String str = "";
		Iterator<Test> itr = newTreeCollectionQuestions.iterator();
		while(itr.hasNext())
			str +=  itr.next() + "\n";
		return str;
	}
	
	public boolean isExistTreeCollection() {
		if(newTreeCollectionQuestions.isEmpty())
				return true;
		return false;
	}
	//
	
	

	public void updateAnswer(Question q, int index) {
		ClosedQuestion ques = (ClosedQuestion) q;
		ques.getAns().get(index).setIsUsed(true);
		ques.updateUsedAnwserArray();
	}

	public int getNumOfAnswersOnClosedQuestion(Question q) {
		ClosedQuestion ques = (ClosedQuestion) q;
		return ques.getAns().size();
	}

	public boolean createManualTest(ArrayList<Integer> questionIdArr) throws FileNotFoundException {
		Test theTest = new Test(questionIdArr.size());
		for (int i = 0; i < questionIdArr.size(); i++) {
			for (int j = 0; j < allQuestions.size(); j++) {
				if (questionIdArr.get(i) == allQuestions.get(j).getId()) {
					theTest.addQuestionToTest(allQuestions.get(j));
				}
			}
		}
		bubbleSortTest(theTest);
		System.out.println(theTest.toString());
		allTests.add(theTest);
		try {
			copyTest(allTests.size());
			allTests.remove(allTests.size()-2);
			
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		testNum--;
		saveTest(theTest);
		resetUsedAnswers();
		return true;
	}
	
	public void resetUsedAnswers() {
		for(int i = 0 ; i < allQuestions.size(); i++) {
			if(allQuestions.get(i) instanceof ClosedQuestion) {
				ClosedQuestion q = (ClosedQuestion) allQuestions.get(i);
				for(int j = 2; j<q.getAns().size(); j++) {
					q.getAns().get(j).setIsUsed(false);
				}
				q.updateUsedAnwserArray();
			}
		}
	}

	public boolean deleteAnswerOnCloseQuestion(int ansId, int i) {
		ClosedQuestion q = (ClosedQuestion) allQuestions.get(i);
		Answer ans = q.getAns().get(ansId - 1);
		if (q.getAns().contains(ans)) {
			q.getAns().remove(ansId - 1);
			return true;
		}
		return false;
	}

	public boolean isAnswerExistsOnCloseQuestion(int ansNum, int i) {
		ClosedQuestion q;
		q = (ClosedQuestion) allQuestions.get(i);
		if (ansNum > 0 && ansNum < q.getAns().size())
			return true;
		return false;
	}

	public void updateAnswerOnCloseQuestion(int ansNum, String updatedAns, boolean isCorrect, int i) {
		Answer updateAnswer = new Answer(updatedAns, isCorrect, true);
		ClosedQuestion q;
		q = (ClosedQuestion) allQuestions.get(i);
		q.updateAnswer(q.getAns().get(ansNum - 1), updateAnswer);
	}

	public boolean updateAnswerOnOpenedQuestion(String ans, int i) {
		Answer ans1 = new Answer(ans, true, true);
		OpenedQuestion q;
		q = (OpenedQuestion) allQuestions.get(i);
		q.setAns(ans1);
		return true;
	}

	public boolean createAutoTest(int numOfQuestionsAuto) throws FileNotFoundException {
		int randNum, randAns;
		if (getNumOfClosedQuestionsForAutoTest() + getNumOfOpenedQuestionsForAutoTest() < numOfQuestionsAuto)
			return false;
		Test theTest = new Test(numOfQuestionsAuto);
		for (int i = 0; i < numOfQuestionsAuto; i++) {
			randNum = (int) (Math.random() * (allQuestions.size()));
			if (!theTest.getQuestions().contains(allQuestions.get(randNum))) {
				if (allQuestions.get(randNum) instanceof ClosedQuestion) {
					ClosedQuestion q = (ClosedQuestion) allQuestions.get(randNum);
					if (q.getAns().size() < 4) {
						i--;
					} else {
						if (q.getAns().size() == 4) {
							q.getAns().get(2).setIsUsed(true);
							q.getAns().get(3).setIsUsed(true);
						} else {
							for (int j = 2; j < q.getAns().size(); j++) {
								q.getAns().get(j).setIsUsed(false);
							}
							for (int j = 2; j < 4; j++) {
								randAns = (int) (Math.random() * (q.getAns().size() - 2) + 2);
								if (!q.getAns().get(randAns).getIsUsed()) {
									q.getAns().get(randAns).setIsUsed(true);
								} else
									j--;
							}
						}
						q.updateUsedAnwserArray();
						theTest.addQuestionToTest(allQuestions.get(randNum));
					}
				} else
					theTest.addQuestionToTest(allQuestions.get(randNum));
			} else
				i--;
		}
		bubbleSortTest(theTest);
		System.out.println(theTest.toString());
		allTests.add(theTest);
		try {
			copyTest(allTests.size());
			allTests.remove(allTests.size()-2);
			
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		testNum--;
		saveTest(theTest);
		resetUsedAnswers();
		return true;
	}

	public void bubbleSortTest(Test theTest) {
		int n = theTest.getQuestions().size();
		int i, indMax;
		for (; n > 1; n--) {
			for (indMax = 0, i = 1; i < n; i++) {
				if (theTest.getQuestions().get(indMax).compareTo(theTest.getQuestions().get(i)) < 0) {
					indMax = i;
				}
				swap(theTest.getQuestions(), n - 1, indMax);
			}
		}
	}

	public void swap(ArrayList<Question> allQuestions, int i, int j) {
		Question tmp = allQuestions.get(i);
		allQuestions.set(i, allQuestions.get(j));
		allQuestions.set(j, tmp);
	}

	public int getNumOfOpenedQuestionsForAutoTest() {
		int numOfQuestions = 0;
		for (int i = 0; i < allQuestions.size(); i++) {
			if (allQuestions.get(i) instanceof OpenedQuestion) {
				numOfQuestions++;
			}
		}
		return numOfQuestions;
	}

	public int getNumOfClosedQuestionsForAutoTest() {// +4 answers
		int numOfQuestions = 0;
		for (int i = 0; i < allQuestions.size(); i++) {
			if (allQuestions.get(i) instanceof ClosedQuestion) {
				ClosedQuestion q = (ClosedQuestion) allQuestions.get(i);
				if (q.getAns().size() >= 4)
					numOfQuestions++;
			}

		}
		return numOfQuestions;
	}

	public boolean addAnswerToClosedQuestion(String ansText, boolean isCorrect, ClosedQuestion question) {
		Answer newAnswer = new Answer(ansText, isCorrect, false);
		return question.addAnswer(newAnswer);
	}

	public boolean addAnswerToOpenedQuestion(String ansText, boolean isCorrect, OpenedQuestion question) {
		Answer newAnswer = new Answer(ansText, isCorrect, true);
		question.setAns(newAnswer);
		return true;
	}

	public boolean addQuestion(String newQuesText, boolean choice) {
		if (choice == true) {
			ArrayList<Answer> answers = new ArrayList<>(8);
			allQuestions.add(new ClosedQuestion(newQuesText, answers));
			return true;
		}
		Answer answerOpen = new Answer("Placeholder", false, false);
		allQuestions.add(new OpenedQuestion(newQuesText, answerOpen));
		return true;
	}

	public ArrayList<Question> getAllQuestionsArray() {
		return allQuestions;
	}

	public ArrayList<Test> getAllTestsArray() {
		return allTests;
	}
	

	public int getNumOfAnswersForCloseQuestion(int i) {
		ClosedQuestion q;
		q = (ClosedQuestion) allQuestions.get(i);
		return q.getAns().size();
	}

	public void saveTest(Test theTest) throws FileNotFoundException {
		boolean isExam = true;
		File f1 = new File("" + saveDate(isExam));
		PrintWriter pw1 = new PrintWriter(f1);
		pw1.print(theTest.testWithNoAnswers());
		pw1.close();

		isExam = false;
		File f2 = new File("" + saveDate(isExam));
		PrintWriter pw2 = new PrintWriter(f2);
		pw2.print(theTest.toString());
		pw2.close();

	}

	public String saveDate(boolean isExam) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd");
		LocalDateTime now = LocalDateTime.now();
		if (isExam) {
			String str = "exam_" + testNum + "_" + dtf.format(now);
			return str;
		}
		String str = "solution_" + testNum + "_" + dtf.format(now);
		testNum = ++counter;
		return str;
	}

	public void saveAllQuestion() throws FileNotFoundException, IOException {
		ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream("questions.dat"));
		outFile.writeObject(allQuestions);
		outFile.close();
	}

	public void read() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream il = new ObjectInputStream(new FileInputStream("questions.dat"));
		allQuestions = (ArrayList<Question>) il.readObject();
		il.close();
		Question q = allQuestions.get(0);
		allQuestions.get(allQuestions.lastIndexOf(q)).setCounter(q.getCounter() + 1);
	}

	public void printTestsArr() {
		for (int i = 0; i < allTests.size(); i++) {
			System.out.println("Test number " + (i + 1) + ":" + "\n");
			System.out.println(allTests.get(i));
		}
	}

	public void copyTest(int numOfTest) throws CloneNotSupportedException {
		Test theNewTest = (Test) allTests.get(numOfTest - 1).clone();
		allTests.add(theNewTest);
	}

	public String getLastTestString() {
		return allTests.get(allTests.size() - 1).toString();
	}
	

	@Override
	public String toString() {
		if (allQuestions.get(0) == null)
			return "There is no questions yet\n";
		String str = "The Questions are: \n";
		for (int i = 0; i < allQuestions.size(); i++) {
			str = str + (i + 1) + ")" + allQuestions.get(i).toString() + "\n";
		}
		return str;
	}

}
