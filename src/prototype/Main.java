package prototype;

class Employee implements Cloneable {
    private String name;

    public Employee(String name) {
        this.name = name;
    }

    @Override
    public Employee clone() {
        try {
            return (Employee) super.clone(); // Object.clone() 이 호출됨
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public void introduce() {
        System.out.println("Hi, my name is " + name);
    }
}

public class Main {
    public static void main(String[] args) {
        // 어떤 객체가 생성될 지는 런타임시에 결정됨.
        Employee originalEmployee = new Employee("John Doe");
        Employee clonedEmployee = originalEmployee.clone();

        originalEmployee.introduce(); // Output: Hi, my name is John Doe
        clonedEmployee.introduce();   // Output: Hi, my name is John Doe
    }
}