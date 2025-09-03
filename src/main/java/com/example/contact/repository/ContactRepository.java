package com.example.contact.repository;

import com.example.contact.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    // Find all contacts that are marked as favorite
    List<Contact> findByIsFavoriteTrue();
}