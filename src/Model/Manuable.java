package Model;

public interface Manuable {
	void printAllQuestionsList();
	
	void addCloseQuestion();
	
	void addOpenQuestion();
	
	boolean checkIfQuestionExistOnQuestionsList();
	
	boolean checkIfAnswerExistOnCloseQuestion();
	
	void updateQuestion();
	
	void updateAnswerOnCloseQuestion();
	
	void updateAnswerOnOpenQuestion();
	
	void deleteAnswerOnCloseQuestion();
	
	void createAutoTest();
	
	void createManualTest();
	
	void createTestFromATestThatExist();
}
