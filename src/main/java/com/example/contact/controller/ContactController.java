package com.example.contact.controller;

import com.example.contact.model.Contact;
import com.example.contact.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contacts")
@CrossOrigin(origins = "*") // Allow requests from any origin (for local development)
public class ContactController {
// ... inside the ContactController class

    // UPDATE a contact's details
    // UPDATE a contact's details
    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable Long id, @RequestBody Contact contactDetails) {
        Optional<Contact> optionalContact = contactRepository.findById(id);
        if (optionalContact.isPresent()) {
            Contact existingContact = optionalContact.get();
            existingContact.setName(contactDetails.getName());
            existingContact.setPhoneNumber(contactDetails.getPhoneNumber());


            existingContact.setProfileImageUrl(contactDetails.getProfileImageUrl());
            return ResponseEntity.ok(contactRepository.save(existingContact));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @Autowired
    private ContactRepository contactRepository;

    // GET all contacts
    @GetMapping
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    // CREATE a new contact
    @PostMapping
    public Contact createContact(@RequestBody Contact contact) {
        return contactRepository.save(contact);
    }

    // GET all favorite contacts
    @GetMapping("/favorites")
    public List<Contact> getFavoriteContacts() {
        return contactRepository.findByIsFavoriteTrue();
    }

    // UPDATE favorite status (Add to/Remove from Favorites)
    @PutMapping("/{id}/favorite")
    public ResponseEntity<Contact> toggleFavorite(@PathVariable Long id, @RequestBody boolean isFavorite) {
        Optional<Contact> optionalContact = contactRepository.findById(id);
        if (optionalContact.isPresent()) {
            Contact contact = optionalContact.get();
            contact.setFavorite(isFavorite);
            return ResponseEntity.ok(contactRepository.save(contact));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE a contact
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        contactRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}