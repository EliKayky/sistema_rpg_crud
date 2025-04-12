package com.example.sistema_rpg_crud.Model;

import com.example.sistema_rpg_crud.Enums.TipoItem;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Schema(description = "Item mágico utilizado pelo personagem.")
@Entity
public class ItemMagico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Personagem personagem;

    @Schema(description = "Nome do item mágico.")
    private String nome;

    @Schema(description = "Tipo do item mágico.")
    @Enumerated(EnumType.STRING)
    private TipoItem tipoItem;

    private int forca;
    private int defesa;

    public ItemMagico() {
    }

    public ItemMagico(Long id, String nome, TipoItem tipoItem, int forca, int defesa) {
        this.id = id;
        this.nome = nome;
        this.tipoItem = tipoItem;
        this.forca = forca;
        this.defesa = defesa;
    }

    public Long getId() {
        return id;
    }

    public Personagem getPersonagem() {
        return personagem;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }

    public String getNome() {
        return nome;
    }

    public TipoItem getTipoItem() {
        return tipoItem;
    }

    public int getForca() {
        return forca;
    }

    public int getDefesa() {
        return defesa;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipoItem(TipoItem tipoItem) {
        this.tipoItem = tipoItem;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }
}

