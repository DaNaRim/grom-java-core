package gromcode.main.lesson8.Homework2;

public class Demo {

    Student createHighestParent(){
        Course[] coursesTaken = new Course[3];
        Student student = new Student("Denis", "Doroshenko", 123, coursesTaken);
        return student;
    }

    SpecialStudent createLowestChild(){
        Course[] coursesTaken = new Course[3];
        SpecialStudent specialStudent = new SpecialStudent("Denis", "Doroshenko", 123, coursesTaken, 2496234, "someemail");
        return specialStudent;
    }

}
