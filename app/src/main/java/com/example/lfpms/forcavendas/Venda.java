package com.example.lfpms.forcavendas;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Venda {
    private String descricao;
    private float valor;
    private String data;
    private String tipo;

    public Venda() {
    }

    public Venda(String descricao, float valor, String data, String tipo) {
        this.descricao = descricao;
        this.data = data;
        this.valor = valor;
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
