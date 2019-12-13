package com.swami.dev;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public class AddressBookResource {

	ConcurrentHashMap<String, Contact> contacts = new ConcurrentHashMap<String, Contact>();

	@GetMapping("/{id}")
	@ApiOperation(value = "Find contact by id",
				  notes = "provide an id to look up",
				  response = Contact.class)
	public Contact getContact(@PathVariable String id) {
		return contacts.get(id);
	}
	
	@GetMapping("/")
	public List<Contact> getAllContacts() {
		List<Contact> list = new ArrayList<Contact>(contacts.values());
		return list;

	}

	@PostMapping("/")
	public Contact addContact(@RequestBody Contact contact) {
		contacts.put(contact.getId(), contact);
		return contact;
	}
}