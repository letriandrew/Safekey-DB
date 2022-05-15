package Manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

// user transfer object
public class User {

    static boolean access = false;
    static boolean verified = false;
    static String userInfo = "";
    static String newInfo = "";
    static String[] tokens = new String[6];
    static String[] tokens2 = new String[5];

    public User() {
    }// default constructor
    public boolean parseFile(String username, String passcode) throws FileNotFoundException {
        Scanner usersFileReader = new Scanner(
                new File("C:\\Users\\hakih\\eclipse-workspace\\SafeKey\\SafeKey\\src\\Manager\\usersFile.txt"));

        while (usersFileReader.hasNextLine()) {
            String info = usersFileReader.nextLine();
            String delims = "[ ]+";
            tokens = info.split(delims);

            UserDAO u = new UserDAO(tokens[0], tokens[1], tokens[2], tokens[3],
                    tokens[4], tokens[5]);

            if (username.equals(u.getUsername()) && passcode.equals(u.getPasscode())) {
                access = true;
                System.out.println(access);
            } else {
                access = false;
                System.out.println(access);
            }
        }
        usersFileReader.close();
        return access;
    }

    public void writeFile(String userInfo) throws FileNotFoundException {
        // add to file
        PrintWriter usersFileWriter = new PrintWriter(
                new File("C:\\Users\\hakih\\eclipse-workspace\\SafeKey\\SafeKey\\src\\Manager\\usersFile.txt"));
        usersFileWriter.println(userInfo);
        usersFileWriter.close();
    }
    
    public boolean verifyPassword(String passcode, String newPasscode) throws FileNotFoundException {
        Scanner usersFileReader = new Scanner(
                new File("C:\\Users\\hakih\\eclipse-workspace\\SafeKey\\SafeKey\\src\\Manager\\usersFile.txt"));

        while (usersFileReader.hasNextLine()) {
            String passInfo = usersFileReader.nextLine();
            String delims = "[ ]+";
            tokens2 = passInfo.split(delims);

            UserDAO u = new UserDAO(tokens2[0], tokens2[1], tokens2[2], tokens2[3],
            		tokens2[4], tokens2[5]);
            
           
            if (passcode.equals(u.getPasscode())) {
            	u.setPasscode(newPasscode);
            	newInfo += u.getName() + " " +
     	     	        u.getEmail() + " " +
     	     	        u.getDOB() + " " +
     	     	        u.getUsername() + " " +
     	     	        u.getPasscode();
            	writeFile(newInfo);
                verified = true;
                System.out.println(access);
            } else {
            	verified = false;
                System.out.println(access);
            }
        }
        usersFileReader.close();
        return verified;
    }

}