package ml.wonwoo.springjpahibernate.model;

import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Embeddable
@Getter
@ToString
@EqualsAndHashCode
public class Phone {

    private String number1;
    private String number2;
    private String number3;

    Phone() {
    }

    public Phone(String number1, String number2, String number3) {
        this.number1 = number1;
        this.number2 = number2;
        this.number3 = number3;
    }
}
