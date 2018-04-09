package ml.wonwoo.springjpahibernate.model;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import ml.wonwoo.springjpahibernate.dto.PersonDto;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig
@DataJpaTest
class PersonRepositoryTests {

    @Test
    void personTest(@Autowired PersonRepository personRepository) {
        personRepository.save(new Person("wonwoo", "wonwoo@test.com", LocalDateTime.now()));
        List<Person> persons = personRepository.findAll();
        assertThat(persons).hasSize(1);
    }

    @Test
    void personTest1(@Autowired PersonRepository personRepository) {
        Person newPerson = personRepository.save(new Person("wonwoo", "wonwoo@test.com", LocalDateTime.now()));
        Person person = personRepository.findByName("wonwoo").orElseThrow(IllegalArgumentException::new);
        assertThat(newPerson).isEqualTo(person);
    }

    @Test
    void personTest2(@Autowired PersonRepository personRepository) {
        personRepository.save(new Person("wonwoo", "wonwoo@test.com", LocalDateTime.now(),
                new Phone("000", "111", "2222"), new Phone("333", "444", "5555")));
        Person person = personRepository.findByName("wonwoo").orElseThrow(IllegalArgumentException::new);
        assertThat(person.getPhone()).isEqualTo(new Phone("000", "111", "2222"));
        assertThat(person.getTelNumber()).isEqualTo(new Phone("333", "444", "5555"));
    }

    @Test
    void entityManagerStream(@Autowired EntityManager entityManager) {
        entityManager.persist(new Person("wonwoo", "wonwoo@test.com", LocalDateTime.now(),
                new Phone("000", "111", "2222"), new Phone("333", "444", "5555")));
        List<PersonDto> persons = entityManager.createQuery("select p from Person p", Person.class)
                .getResultStream()
                .map(person -> new PersonDto(person.getName(), person.getEmail())).collect(Collectors.toList());
        assertThat(persons).hasSize(1);
        PersonDto personDto = persons.iterator().next();
        assertThat(personDto.getName()).isEqualTo("wonwoo");
        assertThat(personDto.getEmail()).isEqualTo("wonwoo@test.com");
    }

    //FIXME CDI Injection Not working
    @Test
    void typeConverter(@Autowired EntityManager entityManager) {
        entityManager.persist(new Person("wonwoo", "wonwoo@test.com", LocalDateTime.now(),
                new Phone("000", "111", "2222"), new Phone("333", "444", "5555"), new Type("FOO")));
        List<PersonDto> persons = entityManager.createQuery("select p from Person p", Person.class)
                .getResultStream()
                .map(person -> new PersonDto(person.getName(), person.getEmail())).collect(Collectors.toList());
        assertThat(persons).hasSize(1);
        PersonDto personDto = persons.iterator().next();
        assertThat(personDto.getName()).isEqualTo("wonwoo");
        assertThat(personDto.getEmail()).isEqualTo("wonwoo@test.com");
    }
}