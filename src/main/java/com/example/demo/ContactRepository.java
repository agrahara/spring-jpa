package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContactRepository extends CrudRepository<Contact, Integer> {

    List<Contact> findAll();

    List<Contact> deleteByName(String name);

    List<Contact> deleteByEmail(String email);

    List<Contact> findByName(String name);

    List<Contact> findByEmail(String email);



}
