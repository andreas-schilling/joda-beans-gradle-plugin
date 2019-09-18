import org.joda.beans.Bean;
import org.joda.beans.PropertyDefinition;

@BeanDefinition
public class Person implements Bean {
    @PropertyDefinition
    private String firstName;

    @PropertyDefinition
    private String lastName;

    public Person() {
        super();
    }

    public Person(final String firstName, final String lastName) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}