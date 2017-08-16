package br.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.service.RegistroModeloService;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiDinamicaRestController {

	@Autowired
	private RegistroModeloService registroModeloService;

	@RequestMapping(value = "/{modelo}", method = RequestMethod.GET)
	public ResponseEntity buscarTodos(@PathVariable String modelo) {
		try {
			return ResponseEntity.ok(registroModeloService.buscarTodos(modelo));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@RequestMapping(value = "/{modelo}/{id}", method = RequestMethod.GET)
	public ResponseEntity buscarPorId(@PathVariable String modelo, @PathVariable String id) {
		try {
			return ResponseEntity.ok(registroModeloService.buscarPorId(modelo, id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@RequestMapping(value = "/{modelo}", method = RequestMethod.POST)
	public ResponseEntity criar(@PathVariable String modelo, @RequestBody Map<String, Object> atributos) {
		try {
			return ResponseEntity.ok(registroModeloService.criar(modelo, atributos));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@RequestMapping(value = "/{modelo}/{id}", method = RequestMethod.PUT)
	public ResponseEntity atualizarPorId(@PathVariable String modelo, @PathVariable String id,
			@RequestBody Map<String, Object> atributos) {
		try {
			return ResponseEntity.ok(registroModeloService.atualizar(modelo, id, atributos));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@RequestMapping(value = "/{modelo}/{id}", method = RequestMethod.DELETE)
	public ResponseEntity removerPorId(@PathVariable String modelo, @PathVariable String id) {
		try {
			return ResponseEntity.ok(registroModeloService.removerPorId(modelo, id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

}
