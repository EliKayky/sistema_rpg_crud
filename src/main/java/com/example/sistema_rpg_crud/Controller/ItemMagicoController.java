package com.example.sistema_rpg_crud.Controller;


import com.example.sistema_rpg_crud.Model.ItemMagico;
import com.example.sistema_rpg_crud.Service.ItemMagicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens")
public class ItemMagicoController {

    @Autowired
    private ItemMagicoService itemMagicoService;

    @PostMapping
    public ResponseEntity<ItemMagico> create(@RequestBody ItemMagico itemMagico) {
        ItemMagico created = itemMagicoService.createItemMagico(itemMagico);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public List<ItemMagico> findAll() {
        return itemMagicoService.findAll();
    }

    @GetMapping("/id")
    public ResponseEntity<ItemMagico> findById(@PathVariable Long id) {
        ItemMagico itemMagico = itemMagicoService.findByID(id);
        return ResponseEntity.ok(itemMagico);
    }
}

