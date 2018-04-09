package ml.wonwoo.springjpahibernate;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ml.wonwoo.springjpahibernate.model.Person;
import ml.wonwoo.springjpahibernate.model.PersonRepository;
import ml.wonwoo.springjpahibernate.model.Phone;
import ml.wonwoo.springjpahibernate.model.Type;

@SpringBootApplication
public class SpringJpaHibernateApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaHibernateApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner (PersonRepository personRepository) {
		return args -> {
			personRepository.save(new Person("wonwoo", "wonwoo@test.com", LocalDateTime.now(),
					new Phone("000", "111", "2222"), new Phone("333", "444", "5555"), new Type("FOO")));
			List<Person> persons = personRepository.findAll();
			System.out.println(persons);
		};
	}
}
