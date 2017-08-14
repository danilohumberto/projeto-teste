package br.com.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.domain.Modelo;

public interface ModeloRepositorio extends MongoRepository<Modelo, String> {

	Modelo buscarPorNome(String nome);
}

