

public class Employee {

    private int id;
    private String firstName;
    private String lastName;
    
    public Employee(int id, String firstName, String lastName) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    
    public boolean equals(Object o) {
        Employee o2 = (Employee)o;
        
        return id == o2.getId();
    }
    
    public int hashCode() {
        return id;
    }
}
