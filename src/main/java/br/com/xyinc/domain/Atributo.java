package br.com.xyinc.domain;

import java.io.Serializable;

public class Atributo implements Serializable {

	private static final long serialVersionUID = 8178870598890752518L;

	private String nome;

	private Tipo tipo;

	private String nomeTipo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public String getNomeTipo() {
		return nomeTipo;
	}

	public void setNomeTipo(String nomeTipo) {
		this.nomeTipo = nomeTipo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + ((nomeTipo == null) ? 0 : nomeTipo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atributo other = (Atributo) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (tipo != other.tipo)
			return false;
		if (nomeTipo == null) {
			if (other.nomeTipo != null)
				return false;
		} else if (!nomeTipo.equals(other.nomeTipo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Atributo [nome=" + nome + ", tipo=" + tipo + ", tipoNome=" + nomeTipo + "]";
	}

}
