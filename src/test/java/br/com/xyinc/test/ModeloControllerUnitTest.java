package br.com.xyinc.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import br.com.xyinc.SpringJUnitTest;
import br.com.xyinc.domain.ModeloDTO;

@WebAppConfiguration
public class ModeloControllerUnitTest extends SpringJUnitTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private MongoClient mongoClient;

	@Autowired
	private Mongo mongo;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		String dbName = "dbTest";
		String collectionName = "Animal";
		MongoDatabase database = mongoClient.getDatabase(dbName);
		database.createCollection(collectionName);
		DB db = mongo.getDB(dbName);

		Map<Object, Object> att = new HashMap<>();
		att.put("idade", "int");
		att.put("nome", "string");
		att.put("casado", "boolean");
		att.put("sexo", "char");
		Map<Object, Object> doc = new HashMap<>();
		doc.put("id", "5995cab1c05e6757d98a1f13");
		doc.put("name", "Animal");
		doc.put("attributes", att);

		db.getCollection(collectionName).save(new BasicDBObject(doc));
	}

	@After
	public void release() {
		MongoDatabase database = mongoClient.getDatabase("dbTest");
		database.drop();
	}

	@Test
	public void testBuscarTodos() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/modelo"))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}

	@Test
	@Ignore
	public void testBuscarPorId() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/modelo/5995cab1c05e6757d98a1f13"))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}

	@Test
	@Ignore
	public void testCriar() throws Exception {
		ModeloDTO content = new ModeloDTO();
		Map<String, String> att = new HashMap<>();
		att.put("idade", "int");
		att.put("nome", "string");
		att.put("casado", "boolean");
		att.put("sexo", "char");
		content.setNome("Animal");
		content.setAtributos(att);
		mockMvc.perform(MockMvcRequestBuilders.post("/modelo").content(new Gson().toJson(content)))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}

	@Test
	@Ignore
	public void testRemover() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/modelo/5995cab1c05e6757d98a1f13"))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}

}

