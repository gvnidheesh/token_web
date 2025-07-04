package in.gov.kerala.ehealth.token.service;

import in.gov.kerala.ehealth.token.model.Person;
import in.gov.kerala.ehealth.token.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

   
 // ✅ Cache person by ID
    @Cacheable(value = "personCache", key = "#id")
    public Person getPersonById(Long id) {
        System.out.println("Fetching from DB...");
        return personRepository.findById(id).orElse(null);
    }
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }
    
    
}
