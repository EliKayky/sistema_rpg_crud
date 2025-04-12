package com.example.sistema_rpg_crud.Service;

import com.example.sistema_rpg_crud.Enums.TipoItem;
import com.example.sistema_rpg_crud.Model.ItemMagico;
import com.example.sistema_rpg_crud.Model.Personagem;
import com.example.sistema_rpg_crud.Repository.ItemMagicoRepository;
import com.example.sistema_rpg_crud.Repository.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonagemService {

    @Autowired
    private PersonagemRepository personagemRepository;

    @Autowired
    private ItemMagicoRepository itemMagicoRepository;

    private final List<String> CLASSES_VALIDAS = List.of("Guerreiro", "Mago", "Arqueiro", "Ladino", "Bardo");

    public Personagem createPersonagem(Personagem personagem) {
        validateClasse(personagem.getClasse());
        validateDistribuicaoDePontos(personagem);
        return personagemRepository.save(personagem);
    }

    public List<Personagem> findAll() {
        return personagemRepository.findAll();
    }

    public Personagem findByID(Long id) {
        return personagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));
    }

    public Personagem toUpdateNomeAventureiro(Long id, String novoNome) {
        Personagem personagem = findByID(id);
        personagem.setNomeAventureiro(novoNome);
        return personagemRepository.save(personagem);
    }

    public void deleteById(Long id) {
        personagemRepository.deleteById(id);
    }

    public Personagem toAddItemMagico(Long personagemId, Long itemId) {
        Personagem personagem = findByID(personagemId);
        ItemMagico itemMagico = itemMagicoRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item Mágico não encontrado."));

        if (itemMagico.getTipoItem() == TipoItem.AMULETO) {
            boolean jaTemAmuleto = personagem.getItensMagicos().stream()
                    .anyMatch(i -> i.getTipoItem() == TipoItem.AMULETO);
            if (jaTemAmuleto) {
                throw new RuntimeException("Este personagem já possui um Amuleto.");
            }
        }

        personagem.getItensMagicos().add(itemMagico);
        return personagemRepository.save(personagem);
    }

    public Personagem removeItemMagico(Long personagemId, Long itemId) {
        Personagem personagem = findByID(personagemId);
        personagem.getItensMagicos().removeIf(i -> i.getId().equals(itemId));
        return personagemRepository.save(personagem);
    }

    public List<ItemMagico> listItensDoPersonagem(Long personagemId) {
        return findByID(personagemId).getItensMagicos();
    }

    public ItemMagico findAmuletoDoPersonagem(Long personagemId) {
        return findByID(personagemId).getItensMagicos().stream()
                .filter(i -> i.getTipoItem() == TipoItem.AMULETO)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Este personagem não possui Amuleto."));
    }

    public int calculateForcaTotal(Personagem personagem) {
        int base = personagem.getForca();
        int itens = personagem.getItensMagicos().stream().mapToInt(ItemMagico::getForca).sum();
        return base + itens;
    }

    public int calculateDefesaTotal(Personagem personagem) {
        int base = personagem.getDefesa();
        int itens = personagem.getItensMagicos().stream().mapToInt(ItemMagico::getDefesa).sum();
        return base + itens;
    }

    private void validateClasse(String classe) {
        if (!CLASSES_VALIDAS.contains(classe)) {
            throw new RuntimeException("Classe inválida. Classes permitidas: " + CLASSES_VALIDAS);
        }
    }

    private void validateDistribuicaoDePontos(Personagem personagem) {
        if (personagem.getForca() + personagem.getDefesa() > 10) {
            throw new RuntimeException("A soma de força e defesa não pode ultrapassar 10.");
        }
    }
}
