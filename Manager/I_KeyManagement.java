package Manager;

public interface I_KeyManagement {
	void addPassword(String namePass, String password);
	void editPassword(String oldPass, String newPass);
	void viewAllPasswords();
	void deleteAPassword(String passwordName);
}
