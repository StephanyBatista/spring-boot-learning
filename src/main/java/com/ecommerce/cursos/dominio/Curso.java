package com.ecommerce.cursos.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @NotNull(message = "Nome do curso é obrigatório")
    private String nome;

    @Min(value = 100, message = "O valor do curso não pode ser menor que {value}")
    private double valor;

    private Curso(){}

    public Curso(String nome, double valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() {
        return this.nome;
    }

    public double getValor() {
        return this.valor;
    }

    public long getId() {
        return id;
    }
}
