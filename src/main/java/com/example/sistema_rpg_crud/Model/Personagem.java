package com.example.sistema_rpg_crud.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Classe que representa um personagem do RPG.")
@Entity
public class Personagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Nome real do personagem!")
    @NotBlank(message = "O nome do personagem não pode estar em branco!")
    private String nome;

    @Schema(description = "Nome usado nas aventuras!")
    @NotBlank(message = "O nome do aventureiro não pode estar em branco!")
    private String nomeAventureiro;

    @Schema(description = "Classe usado pelo personagem!")
    @NotBlank(message = "A classe do personagem não pode estar em branco!")
    private String classe;

    @Min(value = 1, message = "O level mínimo é 1!")
    @Max(value = 10, message = "O level máximo é 10!")
    private int level;

    @Min(value = 0, message = "A força mínima é 0!")
    @Max(value = 10, message = "A força máxima é 10!")
    private int forca;

    @Min(value = 0, message = "A defesa mínima é 0!")
    @Max(value = 10, message = "A defesa máxima é 10!")
    private int defesa;

    @OneToMany(mappedBy = "personagem")
    private List<ItemMagico> itensMagicos = new ArrayList<>();

    public Personagem() {
    }

    public Personagem(Long id, String nome, String nomeAventureiro, String classe, int level, int forca, int defesa) {
        this.id = id;
        this.nome = nome;
        this.nomeAventureiro = nomeAventureiro;
        this.classe = classe;
        this.level = level;
        this.forca = forca;
        this.defesa = defesa;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getNomeAventureiro() {
        return nomeAventureiro;
    }

    public String getClasse() {
        return classe;
    }

    public int getLevel() {
        return level;
    }

    public int getForca() {
        return forca;
    }

    public int getDefesa() {
        return defesa;
    }

    public List<ItemMagico> getItensMagicos() {
        return itensMagicos;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNomeAventureiro(String nomeAventureiro) {
        this.nomeAventureiro = nomeAventureiro;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    public void setItensMagicos(List<ItemMagico> itensMagicos) {
        this.itensMagicos = itensMagicos;
    }
}
