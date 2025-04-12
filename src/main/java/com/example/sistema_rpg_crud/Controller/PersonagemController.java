package com.example.sistema_rpg_crud.Controller;

import com.example.sistema_rpg_crud.Model.ItemMagico;
import com.example.sistema_rpg_crud.Model.Personagem;
import com.example.sistema_rpg_crud.Service.PersonagemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/personagem")
public class PersonagemController {

    @Autowired
    private PersonagemService personagemService;

    @PostMapping
    public ResponseEntity<Personagem> create(@Valid @RequestBody Personagem personagem) {
        Personagem created = personagemService.createPersonagem(personagem);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Personagem>> findAll() {
        return ResponseEntity.ok(personagemService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Long id) {
        Personagem personagem = personagemService.findByID(id);
        Map<String, Object> response = new HashMap<>();
        response.put("id", personagem.getId());
        response.put("nome", personagem.getNome());
        response.put("nomeAventureiro", personagem.getNomeAventureiro());
        response.put("classe", personagem.getClasse());
        response.put("level", personagem.getLevel());
        response.put("forcaTotal", personagemService.calculateForcaTotal(personagem));
        response.put("defesaTotal", personagemService.calculateDefesaTotal(personagem));
        response.put("itensMagicos", personagem.getItensMagicos());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/nome")
    public ResponseEntity<Personagem> updateNomeAventureiro(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String novoNome = body.get("nomeAventureiro");
        if (novoNome == null || novoNome.isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        Personagem updated = personagemService.toUpdateNomeAventureiro(id, novoNome);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        personagemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/itens/{itemId}")
    public ResponseEntity<Personagem> addItem(@PathVariable Long id, @PathVariable Long itemId) {
        Personagem updated = personagemService.toAddItemMagico(id, itemId);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{id}/itens")
    public ResponseEntity<List<ItemMagico>> listItens(@PathVariable Long id) {
        return ResponseEntity.ok(personagemService.listItensDoPersonagem(id));
    }

    @DeleteMapping("/{id}/itens/{itemId}")
    public ResponseEntity<Personagem> removeItem(@PathVariable Long id, @PathVariable Long itemId) {
        Personagem updated = personagemService.removeItemMagico(id, itemId);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{id}/amuleto")
    public ResponseEntity<ItemMagico> findAmuleto(@PathVariable Long id) {
        ItemMagico amuleto = personagemService.findAmuletoDoPersonagem(id);
        return ResponseEntity.ok(amuleto);
    }
}
