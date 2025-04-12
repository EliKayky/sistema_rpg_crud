package com.example.sistema_rpg_crud.Repository;

import com.example.sistema_rpg_crud.Model.Personagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonagemRepository extends JpaRepository<Personagem, Long> {
}

