package Iteration1;

public class Humans {
	// Encapsulation: Private info obtainable through methods
	private String Password;
	public String Username;
	private String securityQuestion;
	private String securityAnswer;
	private boolean forgotPassword;
	
	// Setters
	public void setUsername(String UN) {
		this.Username = UN;
	}
	public void setPassword(String PASS) {
		this.Password = PASS;
	}
	public void setSecurityQuestion(String SQ) {
		this.securityQuestion = SQ;
	}
	public void setSecurityAnswer(String SA) {
		this.securityAnswer = SA;
	}
	public void setForgotPassword(boolean FP) {
		this.forgotPassword = FP;
	}
	
	// Getters
	public String getUsername() {
		return Username;
	}
	public String getPassword() {
		return Password;
	}
	public String getSecurityQuestion() {
		return securityQuestion;
	}
	public String getSecurityAnswer() {
		return securityAnswer;
	}
	public boolean getForgotPassword() {
		return forgotPassword;
	}
}
