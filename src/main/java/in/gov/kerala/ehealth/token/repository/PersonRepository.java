package  in.gov.kerala.ehealth.token.repository;


import in.gov.kerala.ehealth.token.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
