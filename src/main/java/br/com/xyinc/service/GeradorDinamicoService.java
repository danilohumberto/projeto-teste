package br.com.xyinc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xyinc.domain.Modelo;
import br.com.xyinc.persistence.RepositorioDinamico;
import br.com.xyinc.utils.GeradorDinamicoModelo;
import javassist.CannotCompileException;
import javassist.NotFoundException;

@Service
public class GeradorDinamicoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(GeradorDinamicoService.class);

	private static final String CLASS_PATH = "br.gerador.";

	@Autowired
	private RepositorioDinamico repositorio;

	private Map<String, Class<?>> classesDinamicas = new HashMap<>();

	public void gerar(Modelo modelo) throws Exception {
		Map<String, Class<?>> campos = new HashMap<>();
		modelo.getAtributos().forEach(att -> campos.put(att.getNome(), att.getTipo().getClazz()));
		try {
			String nomeClasse = CLASS_PATH + modelo.getNome();
			Class clazz = GeradorDinamicoModelo.gerar(nomeClasse, campos);
			classesDinamicas.put(nomeClasse, clazz);
		} catch (ClassNotFoundException | NotFoundException | CannotCompileException e) {
			String message = String.format("Modelo '%s' não pode ser criado. %s", modelo.getNome(), e.getMessage());
			LOGGER.error(message, e);
			throw new Exception(message);
		}
	}

	public String createCollection(String nome) {
		LOGGER.info("GERANDO DATA BASE -> NOME '{}'", nome);
		return repositorio.criarDataBase(nome);
	}

	public void dropCollection(String nome) {
		LOGGER.info("REMOVENDO DATA BASE -> NOME '{}'", nome);
		repositorio.removerDataBase(nome);
	}

	public List<Object> buscarTodos(Modelo modelo) throws Exception {
		return repositorio.buscarTodos(getClasseDinamica(modelo), modelo.getNome());
	}

	public Object buscarRegistro(Modelo modelo, String id) throws Exception {
		return repositorio.buscarRegistro(getClasseDinamica(modelo), modelo.getNome(), id);
	}

	public void salvarRegistro(Modelo modelo, Map<String, Object> attributes) {
		repositorio.salvarRegistro(modelo.getNome(), attributes);
	}

	public void removerRegistro(Object registro, String nomeModelo) {
		repositorio.removerRegistro(registro, nomeModelo);
	}

	private Class<?> getClasseDinamica(Modelo modelo) throws Exception {
		String className = CLASS_PATH + modelo.getNome();
		if (!classesDinamicas.containsKey(className)) {
			gerar(modelo);
		}
		return classesDinamicas.get(className);
	}

}
