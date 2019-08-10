//    (4) for constructors. First we define an example bean with different constructors:
public class Person
{
	String firstName;
	String lastName;

	Person() {}

	Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
}

//(4) Next we specify a person factory interface to be used for creating new persons:
interface PersonFactory<P extends Person> {
	P create(String firstName, String lastName);
}
