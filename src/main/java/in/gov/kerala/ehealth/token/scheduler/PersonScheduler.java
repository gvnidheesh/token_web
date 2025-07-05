package in.gov.kerala.ehealth.token.scheduler;
import in.gov.kerala.ehealth.token.dto.PersonDTO;
import in.gov.kerala.ehealth.token.model.Person;
import in.gov.kerala.ehealth.token.repository.PersonRepository;
import in.gov.kerala.ehealth.token.service.PersonBroadcaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonScheduler {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonBroadcaster personBroadcaster;

    /**
     * This method is triggered every 10 seconds and broadcasts person list via WebSocket.
     */
    @Scheduled(fixedRate = 20000) // 20 seconds
    public void broadcastPersons() {
        List<PersonDTO> persons = personRepository.findAll()
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());

        personBroadcaster.broadcast(persons);
    }

    private PersonDTO convertToDTO(Person person) {
        return new PersonDTO(person.getId(), person.getName());
    }
}
