package br.com.xyinc.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.xyinc.domain.Modelo;

public interface ModeloRepositorio extends MongoRepository<Modelo, String> {

	Modelo buscarPorNome(String nome);
}

