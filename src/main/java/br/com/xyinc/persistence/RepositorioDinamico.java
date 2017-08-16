package br.com.xyinc.persistence;

import java.util.List;
import java.util.Map;

public interface RepositorioDinamico {

    String criarDataBase(String nome);

    void removerDataBase(String nome);

    List<Object> buscarTodos(Class clazz, String nomeModelo);

    <T> T  buscarRegistro(Class<T> clazz, String nomeModelo, String id);

    void salvarRegistro(String nomeModelo, Map<String, Object> atributos);

    void removerRegistro(Object registro, String nomeModelo);

}
