package ml.wonwoo.springjpahibernate.model;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data

public class Person {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String email;

    private LocalDateTime date;

    @Embedded
    private Phone phone;

    @Embedded
    @AttributeOverride(name = "number1", column = @Column(name = "telNumber1"))
    @AttributeOverride(name = "number2", column = @Column(name = "telNumber2"))
    @AttributeOverride(name = "number3", column = @Column(name = "telNumber3"))
    private Phone telNumber;

    private Type type;

    public Person() {

    }

    public Person(String name, String email, LocalDateTime date) {
        this.name = name;
        this.email = email;
        this.date = date;
    }

    public Person(String name, String email, LocalDateTime date, Phone phone, Phone telNumber) {
        this.name = name;
        this.email = email;
        this.date = date;
        this.phone = phone;
        this.telNumber = telNumber;
    }

    public Person(String name, String email, LocalDateTime date, Phone phone, Phone telNumber, Type type) {
        this.name = name;
        this.email = email;
        this.date = date;
        this.phone = phone;
        this.telNumber = telNumber;
        this.type = type;
    }
}
