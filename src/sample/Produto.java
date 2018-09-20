package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Produto {

    //atributos da classe que serão utilizados nas colunas da tabela
    private final SimpleStringProperty nome;
    private SimpleStringProperty descricao;
    private SimpleIntegerProperty quantidade;
    private final SimpleIntegerProperty codproduto;

    //construtor com 4 parâmetros
    public Produto(String nome, int codproduto, String descricao, int quantidade) {
        this.nome = new SimpleStringProperty(nome);
        this.descricao = new SimpleStringProperty(descricao);
        this.quantidade = new SimpleIntegerProperty(quantidade);
        this.codproduto = new SimpleIntegerProperty(codproduto);
    }
    //construtor com 3 parâmetros
    public Produto(String nome, int codproduto, String descricao) {
        this.nome = new SimpleStringProperty(nome);
        this.descricao = new SimpleStringProperty(descricao);
        this.codproduto = new SimpleIntegerProperty(codproduto);
    }
    //construtor com 2 parâmetros
    public Produto(String nome, int codproduto) {
        this.nome = new SimpleStringProperty(nome);
        this.codproduto = new SimpleIntegerProperty(codproduto);
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

    public int getQuantidade() {
        return quantidade.get();
    }

    public SimpleIntegerProperty quantidadeProperty() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade.set(quantidade);
    }

    public int getCodproduto() {
        return codproduto.get();
    }

    public SimpleIntegerProperty codprodutoProperty() {
        return codproduto;
    }

    public void setCodproduto(int codproduto) {
        this.codproduto.set(codproduto);
    }
}
