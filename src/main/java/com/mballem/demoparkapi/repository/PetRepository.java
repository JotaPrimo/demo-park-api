package com.mballem.demoparkapi.repository;

import com.mballem.demoparkapi.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
