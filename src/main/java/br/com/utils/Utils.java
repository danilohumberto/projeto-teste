package br.com.utils;

import br.com.domain.Tipo;

public class Utils {

	public static Tipo getTipo(String tipo) throws Exception {

		switch (tipo.toLowerCase()) {
		case "bool":
			return Tipo.BOOLEAN;
		case "boolean":
			return Tipo.BOOLEAN;
		case "char":
			return Tipo.CHARACTER;
		case "character":
			return Tipo.CHARACTER;
		case "date":
			return Tipo.DATE;
		case "double":
			return Tipo.DOUBLE;
		case "float":
			return Tipo.FLOAT;
		case "int":
			return Tipo.INTEGER;
		case "integer":
			return Tipo.INTEGER;
		case "long":
			return Tipo.LONG;
		case "string":
			return Tipo.STRING;
		case "decimal":
			return Tipo.DECIMAL;
		default:
			throw new Exception(String.format("Tipo '%s' não encontrado.", tipo));
		}
	}
}
