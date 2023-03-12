package test;

import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.val;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;

public class TestPerson {
    @SneakyThrows
    public static void main(String[] args) {
        Person person1 = Person.builder().address("tt").zip("bb").firstname("1991").lastname("ASf").build();
        System.out.println("TEst" + person1.getAddress());
        Person person = new Person();

        // Getter and setter
        person.setFirstname("Donald");
        person.setLastname("Duck");
        person.setAddress("Entenstrasse 5");
        person.setCity("Entenhausen");
        person.setZip("1234");

        System.out.println("Lastname: " + person.getLastname());

        // Logging and toString
        System.out.println(person.toString());

        // val
        val persons = new ArrayList<Person>();
        persons.add(person);

        // Constructor
        Person person2 = new Person("Duck", "Dagobert", null, "Entenstrasse 1", "Entenhausen", "1234");

        // equals and hashcode
        System.out.println("Same values except firstname and address: " + person.equals(person2));
        System.out.println("Hashcode " + person.getFirstname() + ": " + person.hashCode());
        System.out.println("Hashcode " + person2.getFirstname() + ": " + person2.hashCode());

        // NotNull values in constructor
        try {
            new Person("Duck", null, new Date(), "Entenstrasse 5", "Entenhausen", "1234");
        } catch (NullPointerException exception) {
            System.out.println("Expected NPE.");
        }

        // Close open streams
        @Cleanup FileOutputStream fos = new FileOutputStream("DonaldDuck.ser");
        @Cleanup ObjectOutputStream out = new ObjectOutputStream(fos);
        out.writeObject(person);

        @Cleanup FileInputStream fis = new FileInputStream("DonaldDuck.ser");
        @Cleanup ObjectInputStream in = new ObjectInputStream(fis);
        Person person4 = (Person) in.readObject();
        System.out.println("Serialized Person: " + person4);

    }
}
