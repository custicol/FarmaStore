package com.generation.farmastore.controllers;

import com.generation.farmastore.model.Categoria;
import com.generation.farmastore.model.Produto;
import com.generation.farmastore.repositories.CategoriaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public ResponseEntity<List<Categoria>> listarTodos (){
        return ResponseEntity.ok(categoriaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getById (@PathVariable Long id){
        return categoriaRepository.findById(id).map(res -> ResponseEntity.ok(res))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/tipos/{tipo}")
    public ResponseEntity<List<Categoria>> getByNome(@PathVariable String tipo) {
        return ResponseEntity.ok(categoriaRepository.findAllByTipoContainingIgnoreCase(tipo));
    }
    @PostMapping
    public ResponseEntity<Categoria> post(@Valid @RequestBody Categoria Categoria){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(Categoria));
    }

    @PutMapping
    public ResponseEntity<Categoria> put(@Valid @RequestBody Categoria Categoria){
        return categoriaRepository.findById(Categoria.getId())
                .map(resp -> ResponseEntity.status(HttpStatus.OK).body(categoriaRepository.save(Categoria)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);

        if (categoria.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        categoriaRepository.deleteById(id);
    }
}
