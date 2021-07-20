package Iteration1;

// Inheritance of Humans
public class Teachers extends Humans {
	private int numberOfStudents;

	// Setters
	public void setNumberOfStudents(int NUMB) {
		this.numberOfStudents = NUMB;
	}

	// Getters
	public int getNumberOfStudents() {
		return numberOfStudents;
	}
	
	public void addStudent() {
		this.numberOfStudents++;
	}
}
