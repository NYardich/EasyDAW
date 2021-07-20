package Iteration1;

// Inheritance of Humans
public class Students extends Humans {
	private String teacherUsername;
	private int rating;

	// Setters
	public void setTeacherUsername(String TEACH) {
		this.teacherUsername = TEACH;
	}
	public void setRating(int RATE) {
		this.rating = RATE;
	}

	// GETTERS
	public String getTeacherUsername() {
		return teacherUsername;
	}
	public int getRating() {
		return rating;
	}
}
