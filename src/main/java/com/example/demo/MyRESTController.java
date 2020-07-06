package com.example.demo;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRESTController {

	@Autowired
	ContactRepository repository;

	@GetMapping("/contacts")
	public List<Contact> getContacts() {
		return (List<Contact>) repository.findAll();
	}

	@PostMapping("/contacts")
	public Contact addContact(@RequestBody Contact contact)
	{
		contact.setId(0);
		repository.save(contact);
		return contact;
	}

	@DeleteMapping("/contacts/{searchString}")
	public String deleteContact(@PathVariable String searchString)
	{
		if(searchString.contains("@") && repository.findByEmail(searchString).size()!=0)
			repository.deleteByEmail(searchString);
		else if(repository.findByName(searchString).size()!=0)
			repository.deleteByName(searchString);
		else
			return "No Record Matching";

		return "Deletion Success";
	}

	@GetMapping("/contacts/{searchString}")
	public List<Contact> getContacts(@PathVariable String searchString)
	{
		if(searchString.contains("@"))
			return repository.findByEmail(searchString);
		else
			return repository.findByName(searchString);
	}

}
