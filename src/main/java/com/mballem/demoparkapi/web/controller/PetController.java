package com.mballem.demoparkapi.web.controller;

import com.mballem.demoparkapi.entity.Pet;
import com.mballem.demoparkapi.service.PetService;
import com.mballem.demoparkapi.web.dto.PetCreateDto;
import com.mballem.demoparkapi.web.dto.PetResponseDTO;
import com.mballem.demoparkapi.web.dto.mapper.PetMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/pets")
public class PetController {
    private final PetService service;

    public PetController(PetService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<PetResponseDTO>> index() {
        List<Pet> listPets = service.list();
        return ResponseEntity.ok().body(PetMapper.toListDto(listPets));
    }

    @PostMapping
    public ResponseEntity<PetResponseDTO> store(@Valid @RequestBody PetCreateDto pet) {
        Pet pet1 = service.create(PetMapper.toModel(pet));
        return ResponseEntity.status(HttpStatus.CREATED).body(PetMapper.toDto(pet1));
    }

}
