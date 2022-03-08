package gromcode.main.lesson8.Homework2;

public class Demo {

    Student createHighestParent() {
        return new Student("Denis", "Doroshenko", 123, new Course[3]);
    }

    SpecialStudent createLowestChild() {
        return new SpecialStudent("Denis", "Doroshenko", 123, new Course[3], 2496234, "someEmail");
    }
}
