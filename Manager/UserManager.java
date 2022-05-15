package Manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class UserManager extends UserDAO implements I_UserManagement {
	String question1 = "";
	String answer1 = "";
	String question2 = "";
	String answer2 = "";
	String q1Info = "";
	String q2Info = "";
	boolean correct = false;
	static String[] tokens = new String[4];
	static String[] tokens2 = new String[4];
	static String[] tokens3 = new String[4];
	
	public void setUpRecovery(String q1, String a1, String q2, String a2) {
		// recovery questions and answers
		question1 = q1;
		answer1 = a1;
		question2 = q2;
		answer2 = a2;
		
		// saves questions
		PrintWriter setRecovery;
		try {
			setRecovery = new PrintWriter(new File("C:\\Users\\hakih\\eclipse-workspace\\SafeKey\\SafeKey\\src\\Manager\\recovery.txt"));
			setRecovery.println(question1 + "\n" + answer1 + "\n" + question2 + "\n" + answer2);
			setRecovery.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getQuestion1() {
		Scanner getQuestion1;
		try {
			getQuestion1 = new Scanner(
			            new File("C:\\Users\\hakih\\eclipse-workspace\\SafeKey\\SafeKey\\src\\Manager\\recovery.txt"));
			 while (getQuestion1.hasNextLine()) {
		        	for(int i = 0; i < 4; i++) {
		        		String q1Info = getQuestion1.nextLine();
		        		tokens2[i] = q1Info;
		        	}
		        }
			 getQuestion1.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		question1 = tokens2[0];
		return question1;
	}
	
	public String getQuestion2() {
		Scanner getQuestion2;
		try {
			getQuestion2 = new Scanner(
			            new File("C:\\Users\\hakih\\eclipse-workspace\\SafeKey\\SafeKey\\src\\Manager\\recovery.txt"));
			 while (getQuestion2.hasNextLine()) {
		        	for(int i = 0; i < 4; i++) {
		        		String q2Info = getQuestion2.nextLine();
		        		tokens3[i] = q2Info;
		        	}
		        }
			 getQuestion2.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		question2 = tokens3[2];
		return question2;
	}
	
	public boolean checkRecovery(String enteredAnswer1, String enteredAnswer2) {
		 Scanner getRecovery;
		try {
			getRecovery = new Scanner(
			            new File("C:\\Users\\hakih\\eclipse-workspace\\SafeKey\\SafeKey\\src\\Manager\\recovery.txt"));
			 while (getRecovery.hasNextLine()) {
		        	for(int i = 0; i < 4; i++) {
		        		String recoverInfo = getRecovery.nextLine();
		        		tokens[i] = recoverInfo;
		        	}

		            if (tokens[1].equals(enteredAnswer1) && tokens[3].equals(enteredAnswer2)) {
		            	correct = true;
		                System.out.println(correct);
		            } else {
		            	correct = false;
		                System.out.println(correct);
		            }
		        }
			 getRecovery.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return correct;
	}
	
	public void changeAccountPassword(String old, String new1, String new2) {
		User updatePass = new User();
		boolean verified;
		try {
			verified = updatePass.verifyPassword(old, new1);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
