package Manager;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Random;


public class KeyManager extends UserDAO implements I_KeyManagement {
	
	//private String name, password;

	ArrayList<String> inputs = new ArrayList<String>();
	
	public void addPassword(String namePass, String password){
		// gets name of account for password (ex: Email, Instagram, School)
		// gets added password from user
		// EX: Email: sampleEmail123@gmail.com Password: fakepassword890
		// calls encrypt

		
	}
	
	
	
	public void editPassword(String oldPass, String newPass) {
		// gets edited password from user
		// calls encrypt
		
	}
	
	public void viewAllPasswords() {
		// gets all passwords from database		
		// decrypts
	}
	
	public void deleteAPassword(String passwordName) {
		
	}
	
	public void encrypt(String pass, int key) {
		// encrypts password
		// stores in database
		
		//salt
		pass += "soSALtyHahA";
		
		//pepper
		Random random = new Random();
		char randomizedpepper = (char) (random.nextInt(26) + 'a');
		pass += randomizedpepper;
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hash = md.digest(pass.getBytes());
			String passHash = toHexString(hash);
		}
		catch(Exception e) {
			
		}
		
		//return key(passhash) to database
	}
	
	public void decrypt() {
		// will only be called from view all (unless we have a search bar to search what password we want)
		// decrypts password
		// display
		// will have a loop here to decrypt all passwords until there are no more passwords
	}
	
	public String toHexString(byte[] arr) {
		StringBuilder stringg = new StringBuilder(arr.length*2);
		
		for(byte b:arr) {
			int value = 0XFF & b;
			String appendd = Integer.toHexString(value);
			stringg.append(appendd).append("-");
		}
		
		stringg.setLength(stringg.length()-1);
		return stringg.toString().toUpperCase();
	}
}
