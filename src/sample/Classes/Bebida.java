package sample.Classes;

public class Bebida {
    private int cod_bebida, quantidade;
    private String nome, descricao;

    public int getCod_bebida() {
        return cod_bebida;
    }

    public void setCod_bebida(int cod_bebida) {
        this.cod_bebida = cod_bebida;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
