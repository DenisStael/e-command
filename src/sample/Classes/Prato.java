package sample.Classes;

public class Prato {
    private int cod_prato, quantidade;
    private String nome, descricao;

    public int getCod_prato() {
        return cod_prato;
    }

    public void setCod_prato(int cod_prato) {
        this.cod_prato = cod_prato;
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
