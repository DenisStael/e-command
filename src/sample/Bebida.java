package sample;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Bebida extends Produto {
    private SimpleFloatProperty preco;
    private SimpleIntegerProperty codbebida;

    public Bebida(float preco, String nome, int codbebida, String descricao){
        super(nome,descricao);
        this.preco = new SimpleFloatProperty(preco);
        this.codbebida = new SimpleIntegerProperty(codbebida);

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

    public int getCodbebida() {
        return codbebida.get();
    }

    public SimpleIntegerProperty codbebidaProperty() {
        return codbebida;
    }

    public void setCodbebida(int codbebida) {
        this.codbebida.set(codbebida);
    }

}
