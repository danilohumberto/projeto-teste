package br.com.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.utils.AtributoBuilder;
import br.com.utils.ModeloBuilder;
import br.com.utils.Utils;

public class ModeloDTO implements Serializable {

	private String id;

	private String nome;

	private Map<String, String> atributos;

	public ModeloDTO() {

	}

	public ModeloDTO(Modelo modelo) {
		id = modelo.getId();
		nome = modelo.getNome();
		atributos = new HashMap<>();
		modelo.getAtributos().forEach(att -> atributos.put(att.getNome(), att.getNomeTipo()));
	}

	public Modelo unwrap() throws Exception {
		List<Atributo> attList = new ArrayList<>();
		for (Map.Entry<String, String> entry : atributos.entrySet()) {
			attList.add(new AtributoBuilder().nome(entry.getKey()).tipo(Utils.getTipo((entry.getValue()))).nomeTipo(entry.getValue()).build());
		}
		
		return new ModeloBuilder().id(id).nome(nome).atributos(attList).build();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Map<String, String> getAtributos() {
		return atributos;
	}

	public void setAtributos(Map<String, String> atributos) {
		this.atributos = atributos;
	}

}
