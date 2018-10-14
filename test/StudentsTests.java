import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class StudentsTests {

	private List<Student> students;
	private final Student mike = new Student("Michael", "Stone", 18, "Fine Art");
	private final Student jill = new Student("Jill", "Potter", 18, "Physics");
	private final Student casey = new Student("Casey", "Jones", 22, "Computer Science");
	private final Student lucy = new Student("Lucy", "Chan", 21, "Civil Engineering");

	@Before
	public void createStudentList() {
		students = Arrays.asList(new Student[] { mike, jill, casey, lucy });
	}

	@Test
	public void filtersMatureStudents() {
		Object[] matureStudents = new Functional<>(students).filter(new Question<Student>() {
			@Override
			public boolean test(Student subject) {
				return subject.isMature();
			}

		}).asList().toArray();

		assertArrayEquals(new Student[] { casey, lucy }, matureStudents);
	}

	@Test
	public void mapsMajors() {
		Object[] majors = new Functional<>(students).map(new MappingFunction<Student, String>() {
			@Override
			public String apply(Student input) {
				return input.getMajor();
			}
		}).asList().toArray();

		assertArrayEquals(new String[] { "Fine Art", "Physics", "Computer Science", "Civil Engineering" }, majors);
	}

	@Test
	public void reducesStudentAge() {
		double ageSum = new Functional<>(students).map(student -> student.getAge()).reduce(0d,
				new Accumulator<Double>() {
					@Override
					public Double apply(Double sum, Double age) {
						return sum + age;
					}

				});
		assertEquals(79, ageSum, 0);
	}

}
