package sample;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Produto {

    //atributos da classe que serão utilizados nas colunas da tabela
    private SimpleStringProperty nome;
    private SimpleStringProperty descricao;
    private SimpleFloatProperty quantidade;
    private SimpleIntegerProperty codproduto;
    private SimpleStringProperty medida;

    //construtor com 5 parâmetros
    public Produto(String nome, int codproduto, String descricao, float quantidade, String medida) {
        this.nome = new SimpleStringProperty(nome);
        this.descricao = new SimpleStringProperty(descricao);
        this.quantidade = new SimpleFloatProperty(quantidade);
        this.codproduto = new SimpleIntegerProperty(codproduto);
        this.medida = new SimpleStringProperty(medida);
    }
    public Produto(String nome,String descricao){
        this.nome = new SimpleStringProperty(nome);
        this.descricao = new SimpleStringProperty(descricao);

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

    public String getQuantidade() {
        return String.valueOf(quantidade.get());
    }

    public SimpleFloatProperty quantidadeProperty() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade.set(quantidade);
    }

    public String getCodproduto() {
        return String.valueOf(codproduto.get());
    }

    public SimpleIntegerProperty codprodutoProperty() {
        return codproduto;
    }

    public void setCodproduto(int codproduto) {
        this.codproduto.set(codproduto);
    }

    public String getMedida() {
        return medida.get();
    }

    public SimpleStringProperty medidaProperty() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida.set(medida);
    }
}
