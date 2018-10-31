package sample;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Prato {

    //Atributos da classe que serão utilizados na tabela de pratos
    private SimpleStringProperty nome;
    private SimpleStringProperty descricao;
    private SimpleFloatProperty preco;
    private SimpleIntegerProperty codprato;

    //construtor com 4 parâmetros
    public Prato(String nome, int codprato, String descricao, float preco) {
        this.nome = new SimpleStringProperty(nome);
        this.descricao = new SimpleStringProperty(descricao);
        this.preco = new SimpleFloatProperty(preco);
        this.codprato = new SimpleIntegerProperty(codprato);
    }

    public Prato(){
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

    public String getPreco() {
        return String.valueOf(preco.get());
    }

    public SimpleFloatProperty precoProperty() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco.set(preco);
    }

    public String getCodprato() {
        return String.valueOf(codprato.get());
    }

    public SimpleIntegerProperty codpratoProperty() {
        return codprato;
    }

    public void setCodprato(int codprato) {
        this.codprato.set(codprato);
    }

}
