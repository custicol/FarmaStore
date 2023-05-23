package com.generation.farmastore.repositories;

import com.generation.farmastore.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findAllByTipoContainingIgnoreCase(@Param("tipo") String tipo);
}
