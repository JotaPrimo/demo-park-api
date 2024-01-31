package com.mballem.demoparkapi.entity;

import com.mballem.demoparkapi.enums.Pelagem;
import com.mballem.demoparkapi.enums.Sexo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "pets")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 200)
    private String nome;

    @Column(name = "pelagem", nullable = false, length = 200)
    private Pelagem pelagem;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    private boolean ativo = false;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Column(name = "data_modificacao")
    private LocalDateTime dataModificacao;

    @Column(name = "criado_por")
    private String criadoPor;

    @Column(name = "modificado_por")
    private String modificadoPor;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Objects.equals(id, pet.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
