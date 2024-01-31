package com.mballem.demoparkapi.service;

import com.mballem.demoparkapi.entity.Pet;
import com.mballem.demoparkapi.repository.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {
    private final PetRepository repository;

    public PetService(PetRepository repository) {
        this.repository = repository;
    }

    public List<Pet> list() {
        return repository.findAll();
    }

    public Pet create(Pet model) {
        return repository.save(model);
    }
}
