package sample;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Prato {

    //Atributos da classe que serão utilizados na tabela de pratos
    private final SimpleStringProperty nome;
    private final SimpleStringProperty descricao;
    private final SimpleFloatProperty preco;
    private final SimpleIntegerProperty codprato;

    //construtor com todos os parâmetros
    public Prato(String nome, int codprato, String descricao, float preco) {
        this.nome = new SimpleStringProperty(nome);
        this.descricao = new SimpleStringProperty(descricao);
        this.preco = new SimpleFloatProperty(preco);
        this.codprato = new SimpleIntegerProperty(codprato);
    }

    //Getters e Setters
    public String getNome() {
        return nome.get();
    }

    public SimpleStringProperty nomeProperty() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public String getDescricao() {
        return descricao.get();
    }

    public SimpleStringProperty descricaoProperty() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao.set(descricao);
    }

    public float getPreco() {
        return preco.get();
    }

    public SimpleFloatProperty precoProperty() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco.set(preco);
    }

    public int getCodprato() {
        return codprato.get();
    }

    public SimpleIntegerProperty codpratoProperty() {
        return codprato;
    }

    public void setCodprato(int codprato) {
        this.codprato.set(codprato);
    }
}
