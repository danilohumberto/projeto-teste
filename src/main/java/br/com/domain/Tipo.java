package br.com.domain;

import java.math.BigDecimal;
import java.util.Date;

public enum Tipo {
	BOOLEAN(Boolean.class), CHARACTER(Character.class), DATE(Date.class), DOUBLE(Double.class), FLOAT(
			Float.class), INTEGER(Integer.class), LONG(Long.class), STRING(String.class), DECIMAL(BigDecimal.class);

	private Class clazz;

	Tipo(Class clazz) {
		this.clazz = clazz;
	}

	public Class getClazz() {
		return clazz;
	}
}
