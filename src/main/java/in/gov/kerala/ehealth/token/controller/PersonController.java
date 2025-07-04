package in.gov.kerala.ehealth.token.controller;

import in.gov.kerala.ehealth.token.model.Person;
import in.gov.kerala.ehealth.token.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/persons")
    public String getPersons(Model model) {
        model.addAttribute("persons", personService.getAllPersons());
        return "persons"; // corresponds to persons.html
    }
    
    @GetMapping("/person/{id}")
    public String getPerson(@PathVariable Long id, Model model) {
        Person person = personService.getPersonById(id);
        model.addAttribute("person", person);
        return "person-details";
    }
    
}
