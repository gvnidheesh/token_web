package in.gov.kerala.ehealth.token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;

import in.gov.kerala.ehealth.token.config.AppProperties;

@Controller
@RestController
public class HelloController {
	
	@Autowired
    private AppProperties appProperties;

	    public HelloController(AppProperties appProperties) {
	        this.appProperties = appProperties;
	        // Print to console
	        System.out.println("App Name: " + appProperties.getName());
	        System.out.println("App Version: " + appProperties.getVersion());
	    } 
	
	 
	 @GetMapping("/hello")  
		 public String hello(Model model) {
		  model.addAttribute("appName", appProperties.getName());
	        model.addAttribute("appVersion", appProperties.getVersion());
	        
	       
	        
        return "hello"; // maps to hello.html in templates folder
    }
    
}
