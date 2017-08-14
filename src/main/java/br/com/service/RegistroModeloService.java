package br.com.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.domain.Atributo;
import br.com.domain.Modelo;
import br.com.domain.Tipo;

@Service
public class RegistroModeloService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RegistroModeloService.class);

	@Autowired
	private ModeloService modeloService;

	@Autowired
	private GeradorDinamicoService geradorDinamicoService;

	public List<Object> buscarTodos(String nomeModelo) throws Exception, Exception {
		Modelo modelo = carregarModelo(nomeModelo);
		LOGGER.info("BUSCANDO TODOS OS REGISTROS DO MODELO {}", modelo);
		return geradorDinamicoService.buscarTodos(modelo);
	}

	public Object buscarPorId(String nomeModelo, String id) throws Exception, Exception {
		Modelo modelo = carregarModelo(nomeModelo);
		LOGGER.info("BUSCANDO REGISTRO DO MODELO {} COM ID '{}'", modelo, id);
		return geradorDinamicoService.buscarRegistro(modelo, id);
	}

	public Map<String, Object> criar(String nomeModelo, Map<String, Object> attributes) throws Exception, Exception {
		attributes.put("_id", UUID.randomUUID().toString());
		return salvar(nomeModelo, attributes);
	}

	public Map<String, Object> atualizar(String nomeModelo, String id, Map<String, Object> attributes)
			throws Exception, Exception {
		attributes.put("_id", id);
		return salvar(nomeModelo, attributes);
	}

	private Map<String, Object> salvar(String nomeModelo, Map<String, Object> attributes) throws Exception, Exception {
		Modelo modelo = carregarModelo(nomeModelo);
		validarAtributos(modelo, attributes);
		LOGGER.info("SALVANDO NOVO REGISTRO PARA O MODELO {} COM OS ATRIBUTOS {}", modelo, attributes);
		geradorDinamicoService.salvarRegistro(modelo, attributes);
		return attributes;
	}

	public Object removerPorId(String nomeModelo, String id) throws Exception {
		Modelo modelo = carregarModelo(nomeModelo);
		Object registro = buscarPorId(modelo.getNome(), id);
		LOGGER.info("REMOVENDO REGISTRO ID '{}' PARA O MODELO {}", id, modelo);
		geradorDinamicoService.removerRegistro(registro, modelo.getNome());
		return registro;
	}

	private Modelo carregarModelo(String nomeModelo) throws Exception {
		Modelo modelo = modeloService.buscarPorNome(nomeModelo);
		if (modelo == null) {
			throw new Exception(String.format("Modelo '%s' não existe na base.", nomeModelo));
		}
		return modelo;
	}

	private void validarAtributos(Modelo modelo, Map<String, Object> atributos) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		for (Atributo atributo : modelo.getAtributos()) {
			if (atributo.getTipo().equals(Tipo.DATE)) {
				try {
					new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(atributos.get(atributo.getNome()).toString());
				} catch (ParseException e) {
					sb.append(String.format(" %s.", atributo.getNome()));
				}
			} else {
				Class clazz1 = atributo.getTipo().getClazz();
				Class clazz2 = atributos.get(atributo.getNome()).getClass();
				if (!clazz1.isAssignableFrom(clazz2)) {
					sb.append(String.format(" %s.", atributo.getNome()));
				}
			}
		}

		if (!sb.toString().isEmpty()) {
			throw new Exception(String.format("Tipo do atributo inválido: %s", sb.toString()));
		}
	}

}
