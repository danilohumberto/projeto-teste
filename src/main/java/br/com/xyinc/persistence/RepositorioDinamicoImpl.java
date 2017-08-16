package br.com.xyinc.persistence;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.DBCollection;

@Repository
public class RepositorioDinamicoImpl implements RepositorioDinamico {
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public String criarDataBase(String name) {
		DBCollection collection = mongoTemplate.createCollection(name);
		return collection.getName();
	}

	@Override
	public void removerDataBase(String name) {
		mongoTemplate.dropCollection(name);
	}

	@Override
	public List<Object> buscarTodos(Class clazz, String modelName) {
		return mongoTemplate.findAll(clazz, modelName);
	}

	@Override
	public <T> T buscarRegistro(Class<T> clazz, String modelName, String id) {
		return mongoTemplate.findById(id, clazz, modelName);
	}

	@Override
	public void salvarRegistro(String modelName, Map<String, Object> attributes) {
		mongoTemplate.save(attributes, modelName);
	}

	@Override
	public void removerRegistro(Object record, String modelName) {
		mongoTemplate.remove(record, modelName);
	}

}
