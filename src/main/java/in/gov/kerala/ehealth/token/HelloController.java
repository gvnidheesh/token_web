package in.gov.kerala.ehealth.token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import in.gov.kerala.ehealth.token.config.AppProperties;

@Controller
public class HelloController {
	
	@Autowired
    private AppProperties appProperties;

	    public HelloController(AppProperties appProperties) {
	        this.appProperties = appProperties;
	    } 
	
	 
	 @GetMapping("/hello")  
		 public String hello(Model model) {
		  model.addAttribute("appName", appProperties.getName());
	        model.addAttribute("appVersion", appProperties.getVersion());
        return "hello"; // maps to hello.html in templates folder
    }
    
}
