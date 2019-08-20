package demo.mvc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class PeopleController {
	
		
	@RequestMapping(value = "/person", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String person(@RequestParam int id) throws JsonProcessingException{	
		
		List <Person> persons = getPersons();
		
		for (Person p : persons){
			if (p.getPersonId() == id) {
				return p.getFirstName();
			}
		}
		return "Not Found";
			
    }	
	
	@RequestMapping(value = "/people", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String listPerson() throws JsonProcessingException{	
		
		List <Person> persons = getPersons();
		
		return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(persons);		
    }	
		
        
    @RequestMapping("/user") 
    public String register(@RequestBody String pp) throws JsonParseException, JsonMappingException, IOException  {
    	
    	Person p = new ObjectMapper().readValue(pp, Person.class);
    	
    	return p.getFirstName();
    }
    
    public List <Person> getPersons() {
    	
        List <Person> persons = new ArrayList<Person>();
        
        persons.add(new Person(1, "Ricky", "Man", 35));
        persons.add(new Person(2, "Fiona", "Lark", 40));
        persons.add(new Person(3, "Gouda", "Rodal", 30));
       
        return persons;
    }
}
