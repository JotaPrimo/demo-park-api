package com.mballem.demoparkapi.web.dto.mapper;

import com.mballem.demoparkapi.entity.Pet;
import com.mballem.demoparkapi.entity.Usuario;
import com.mballem.demoparkapi.web.dto.PetCreateDto;
import com.mballem.demoparkapi.web.dto.PetResponseDTO;
import com.mballem.demoparkapi.web.dto.UsuarioResponseDTO;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class PetMapper {
    public static PetResponseDTO toDto(Pet pet) {
        return (new ModelMapper()).map(pet, PetResponseDTO.class);
    }

    public static Pet toModel(PetCreateDto dto) {
        return (new ModelMapper()).map(dto, Pet.class);
    }

    public static List<PetResponseDTO> toListDto(List<Pet> pets) {
        return pets.stream().map((pet -> toDto(pet))).collect(Collectors.toList());
    }

}
