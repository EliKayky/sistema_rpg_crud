package com.example.sistema_rpg_crud.Service;

import java.util.List;
import com.example.sistema_rpg_crud.Model.ItemMagico;
import com.example.sistema_rpg_crud.Repository.ItemMagicoRepository;
import com.example.sistema_rpg_crud.Enums.TipoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemMagicoService {

    @Autowired
    private ItemMagicoRepository itemMagicoRepository;

    public ItemMagico createItemMagico(ItemMagico itemMagico) {
        validateItemMagico(itemMagico);
        return itemMagicoRepository.save(itemMagico);
    }

    public List<ItemMagico> findAll() {
        return itemMagicoRepository.findAll();
    }

    public ItemMagico findByID(Long id) {
        return itemMagicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Item Mágico não encontrado"));
    }

    public void deleteById(Long id) {
        itemMagicoRepository.deleteById(id);
    }

    private void validateItemMagico(ItemMagico itemMagico) {
        int forca = itemMagico.getForca();
        int defesa = itemMagico.getDefesa();

        if (forca == 0 && defesa == 0) {
            throw new RuntimeException("A Força ou Defesa do Item Mágico precisa ser maior que zero.");
        }

        if (forca > 10 && defesa > 10) {
            throw new RuntimeException("A Força e a Defesa, não podem passar de 10.");
        }

        switch (itemMagico.getTipoItem()) {
            case ARMA:
                if (defesa != 0) {
                    throw new RuntimeException("Itens do tipo ARMA, obrigatoriamente tem defesa igual a 0.");
                }
                break;

            case ARMADURA:
                if (forca != 0) {
                    throw new RuntimeException("Itens do tipo ARMADURA, obrigatoriamente tem força igual a 0.");
                }
                break;

            case AMULETO:
                break;

            default:
                throw new RuntimeException("Tipo de Item Mágico é INVÁLIDO!");
        }
    }
}

