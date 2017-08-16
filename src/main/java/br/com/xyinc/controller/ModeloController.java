package br.com.xyinc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.xyinc.domain.ModeloDTO;
import br.com.xyinc.service.ModeloService;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/modelo")
public class ModeloController {

	@Autowired
	private ModeloService modeloService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity buscarTodos() {
		return ResponseEntity.ok(modeloService.buscarTodos().stream().map(model -> new ModeloDTO(model)).collect(Collectors.toList()));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity buscarPorId(@PathVariable String id) {
		try {
			return ResponseEntity.ok(new ModeloDTO(modeloService.buscarPorId(id)));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity criar(@RequestBody ModeloDTO modelDTO) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(new ModeloDTO(modeloService.criar(modelDTO.unwrap())));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity remover(@PathVariable String id) {
		try {
			return ResponseEntity.ok(new ModeloDTO(modeloService.delete(id)));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

}
