package in.gov.kerala.ehealth.token.service;

import in.gov.kerala.ehealth.token.dto.PersonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonBroadcaster {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public PersonBroadcaster(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    /**
     * Sends a list of persons to all subscribers over /topic/persons
     *
     * @param persons List of PersonDTOs to broadcast
     */
    public void broadcast(List<PersonDTO> persons) {
        messagingTemplate.convertAndSend("/topic/persons", persons);
    }
}
