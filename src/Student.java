
public class Student {

	private double age;
	private String major;

	public Student(String firstName, String surname, double age, String major) {
		this.age = age;
		this.major = major;
	}

	public String getMajor() {
		return major;
	}

	public boolean isMature() {
		return age >= 21;
	}

	public double getAge() {
		// TODO Auto-generated method stub
		return age;
	}
}
