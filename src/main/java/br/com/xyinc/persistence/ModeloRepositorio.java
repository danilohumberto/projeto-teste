package br.com.xyinc.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import br.com.xyinc.domain.Modelo;

public interface ModeloRepositorio extends MongoRepository<Modelo, String> {

	@Query("{nome: ?0}")
	Modelo buscarPorNome(String nome);
}

