package br.com.xyinc.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xyinc.domain.Modelo;
import br.com.xyinc.persistence.ModeloRepositorio;

@Service
public class ModeloService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ModeloService.class);

	@Autowired
	private ModeloRepositorio modeloRepositorio;

	@Autowired
	private GeradorDinamicoService geradorDinamicoService;

	public List<Modelo> buscarTodos() {
		LOGGER.info("BUSCANDO TODOS MODELOS.....");
		return modeloRepositorio.findAll();
	}

	public Modelo buscarPorId(String id) throws Exception {
		LOGGER.info("BUSCANDO MODELO POR ID: '{}'", id);
		Modelo model = modeloRepositorio.findOne(id);
		if (model == null) {
			throw new Exception(String.format("Modelo com id '%s' não existe na base.", id));
		}
		return model;
	}

	public Modelo buscarPorNome(String nome) {
		LOGGER.info("BUSCANDO MODELO POR NOME '{}'", nome);
		return modeloRepositorio.buscarPorNome(nome);
	}

	public Modelo criar(Modelo modelo) throws Exception {

		if (buscarPorNome(modelo.getNome()) != null) {
			throw new Exception(String.format("Modelo '%s' já existe na base ", modelo.getNome()));
		}

		LOGGER.info("CRIANDO MODELO: {}", modelo);

		geradorDinamicoService.gerar(modelo);
		geradorDinamicoService.createCollection(modelo.getNome());
		return modeloRepositorio.save(modelo);
	}

	public Modelo delete(String id) throws Exception {
		LOGGER.info("REMOVENDO MODELO ID: '{}'", id);
		Modelo model = buscarPorId(id);
		geradorDinamicoService.dropCollection(model.getNome());
		modeloRepositorio.delete(id);
		return model;
	}

}
