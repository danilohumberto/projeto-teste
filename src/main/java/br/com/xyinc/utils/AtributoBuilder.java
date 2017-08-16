package br.com.xyinc.utils;

import br.com.xyinc.domain.Atributo;
import br.com.xyinc.domain.Tipo;

public class AtributoBuilder {

    private Atributo atributo;

    public AtributoBuilder() {
        atributo = new Atributo();
    }

    public Atributo build() {
        return atributo;
    }

    public AtributoBuilder nome(String name) {
        atributo.setNome(name);
        return this;
    }

    public AtributoBuilder tipo(Tipo tipo) {
        atributo.setTipo(tipo);
        return this;
    }

    public AtributoBuilder nomeTipo(String nomeTipo) {
        atributo.setNomeTipo(nomeTipo);
        return this;
    }

}
