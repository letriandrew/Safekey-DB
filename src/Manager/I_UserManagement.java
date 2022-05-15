package Manager;

public interface I_UserManagement {
	void setUpRecovery(String q1, String a1, String q2, String a2);
	String getQuestion1();
	String getQuestion2();
	boolean checkRecovery(String answer1, String answer2);
	void changeAccountPassword(String old, String new1, String new2);
}
