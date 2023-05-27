package Controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.text.View;

import Model.Model;
import Model.Observer;
import Model.Question;
import Model.Test;
import View.View1;
import View.View10;
import View.View2;
import View.View3;
import View.View4;
import View.View5;
import View.View6;
import View.View7;
import View.View8;
import View.View9;
import View.View11;
import View.View12;
import View.View13;
import View.View14;


public class Controller {
	Model model;
	View1 view1;
	View2 view2;
	View3 view3;
	View4 view4;
	View5 view5;
	View6 view6;
	View7 view7;
	View8 view8;
	View9 view9;
	View10 view10;
	View11 view11;
	View12 view12;
	View13 view13;
	View14 view14;

	public Controller(Model model, View1 view1, View2 view2, View3 view3, View4 view4, View5 view5, View6 view6,
			View7 view7, View8 view8, View9 view9, View10 view10,View11 view11, View12 view12, View13 view13, View14 view14) {
		this.model = model;
		this.view1 = view1;
		this.view2 = view2;
		this.view3 = view3;
		this.view4 = view4;
		this.view5 = view5;
		this.view6 = view6;
		this.view7 = view7;
		this.view8 = view8;
		this.view9 = view9;
		this.view10 = view10;
		this.view11 = view11;
		this.view12 = view12;
		this.view13 = view13;
		this.view14 = view14;
		view1.setC(this);
		view2.setC(this);
		view3.setC(this);
		view4.setC(this);
		view5.setC(this);
		view6.setC(this);
		view7.setC(this);
		view8.setC(this);
		view9.setC(this);
		view10.setC(this);
		view11.setC(this);
		view12.setC(this);
		view13.setC(this);
		view14.setC(this);
	}

	public boolean save() throws FileNotFoundException, IOException {
		return model.save();
	}

	public String getAllQuestionsString() {
		return model.getAllQuestionsString();
	}

	public ArrayList<Question> getAllQuestions() {
		return model.getAllQuestions();
	}

	public void openView2() {
		view2.execute();
	}

	public void openView3() {
		view3.openView3();
	}

	public void openView4() {
		view4.openView4();
	}

	public void openView5() {
		view5.openView5();
	}

	public void openView6() {
		view6.openView6();
	}

	public void openView7() {
		view7.openView7();
	}

	public void openView8() {
		view8.openView8();
	}

	public void openView9() {
		view9.openView9();
	}

	public void openView10() {
		view10.openView10();
	}
	
	public void openView11() {
		view11.openView11();
	}
	
	public void openView12() {
		view12.openView12();
	}
	
	public void openView13() {
		view13.openView13();
	}
	
	public void openView14() {
		view14.openView14();
	}
	
	public boolean checkValid(boolean typeOfQuestion, String questionText) {
		return model.checkValid(typeOfQuestion, questionText);
	}

	public boolean addQuestion(String questionText, boolean typeOfQuestion) {
		return model.addQuestion(questionText, typeOfQuestion);
	}

	public boolean addAnswertoClosedQuestion(String answerText, boolean fCorrect) {
		return model.addAnswertoClosedQuestion(answerText, fCorrect);
	}

	public boolean addAnswertoOpenQuestion(String answerText, boolean fCorrect) {
		return model.addAnswertoOpenQuestion(answerText, true);
	}

	public void removeLastQuestion() {
		model.removeLastQuestion();
	}

	public String getQuestionTextById(int id) {
		return model.getQuestionTextById(id);
	}

	public void updateQuestion(String questionText, int id) {
		model.updateQuestion(questionText, id);
	}

	public int getClosedQuestionAnswerArraySize(int questionIndex) {
		return model.getClosedQuestionAnswerArraySize(questionIndex);
	}

	public int getQuestionIndexById(int questionId) {
		return model.getQuestionIndexById(questionId);
	}

	public void removeAnswer(int ansNum, int questionIndex) {
		model.removeAnswer(ansNum, questionIndex);
	}

	public void updateAnswerOnClosedQuestion(int answerIndex, String answerText, boolean isTrue, int id) {
		model.updateAnswerOnClosedQuestion(answerIndex, answerText, isTrue, id);
	}

	public void updateAnswerOnOpenedQuestion(String answerText, int id) {
		model.updateAnswerOnOpenedQuestion(answerText, id);
	}

	public int getNumOfQuestionsForAutoTest() {
		return model.getNumOfQuestionsForAutoTest();
	}

	public boolean createAutoTest(int numOfQuestions) throws FileNotFoundException {
		return model.createAutoTest(numOfQuestions);
	}

	public String getLastTestString() {
		return model.getLastTestString();
	}

	public boolean testsArrIsEmpty() {
		return model.testsArrIsEmpty();
	}
	
	public boolean newtestsCollectionIsEmpty() {
		return model.testsArrIsEmpty();
	}

	public int getAllTestsArraySize() {
		return model.getAllTestsArraySize();
	}

	public String getTestStringByIndex(int index) {
		return model.getTestStringByIndex(index);
	}

	public void copyTest(int testNum) throws CloneNotSupportedException {
		model.copyTest(testNum);
	}
	public void addAnsToUsedAnsArr(boolean isUsed) {
		model.addAnsToUsedAnsArr(isUsed);
	}
	public void addQuestionToQuestionIdArr(int questionId) {
		model.addQuestionToQuestionIdArr(questionId);
	}
	public void resetQuestionIdArr() {
		model.resetQuestionIdArr();
	}
	public void resetUsedAnsArr() {
		model.resetUsedAnsArr();
	}
	public int getQuestionIdArrSize() {
		return model.getQuestionIdArrSize();
	}
	public void createManualTest() throws FileNotFoundException {
		model.createManualTest();
	}
	
	public boolean isExistTreeCollection() {
		return model.isExistTreeCollection();
	}
	
	
	public String getNewAllTestsStringOnHash() {
		return model.getNewAllTestsStringOnHash();
	}
	
	public String getNewAllTestsString() {
		return model.getNewAllTestsString();
	}
	
	public String getMyArrayList() {
		return model.getMyArrayList();
	}

	public void changeToMyArrayListWithObserver(Observer lab, Observer b16) {
		model.changeToMyArrayListWithObserver(lab,b16);
	}
	
	public void changeToMyArrayList() {
		model.changeToMyArrayList();
	}
	
	public String getMyArrayListWithRemove() {
		return model.getMyArrayListWithRemove();
	}
	
	public String getTheLinkedArrayListWithRemove(){
		return model.getTheLinkedArrayListWithRemove();
	}
	
	public int getArrayListLength() {
		return model.getArrayListLength();
	}
	
	public void getMemento() {
		model.getMemento();
	}
	
	public void setMemento() {
		model.setMemento();
	}
	
}
