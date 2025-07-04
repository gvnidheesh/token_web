package in.gov.kerala.ehealth.token;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello"; // maps to hello.html in templates folder
    }
}
