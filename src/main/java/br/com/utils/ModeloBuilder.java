package br.com.utils;

import java.util.List;

import br.com.domain.Atributo;
import br.com.domain.Modelo;

public class ModeloBuilder {

    private Modelo model;

    public ModeloBuilder() {
        model = new Modelo();
    }

    public Modelo build() {
        return model;
    }

    public ModeloBuilder id(String id) {
        model.setId(id);
        return this;
    }

    public ModeloBuilder nome(String nome) {
        model.setNome(nome);
        return this;
    }

    public ModeloBuilder atributos(List<Atributo> atributos) {
        model.setAtributos(atributos);
        return this;
    }

}
