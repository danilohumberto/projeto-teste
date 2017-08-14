package br.com.utils;

import java.io.Serializable;
import java.util.Map;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.NotFoundException;

public class GeradorDinamicoModelo {

	private static final ClassPool pool = ClassPool.getDefault();
	
	private GeradorDinamicoModelo() {
		
	}

	public static Class gerar(String nomeClasse, Map<String, Class<?>> atributos)
			throws ClassNotFoundException, NotFoundException, CannotCompileException {

		CtClass cc = pool.getOrNull(nomeClasse);

		if (cc != null) {
			return Class.forName(nomeClasse, false, pool.getClassLoader());
		}

		cc = pool.makeClass(nomeClasse);
		cc.addInterface(retornarCtClass(Serializable.class));

		for (Map.Entry<String, Class<?>> entry : atributos.entrySet()) {
			cc.addField(new CtField(retornarCtClass(entry.getValue()), entry.getKey(), cc));
			cc.addMethod(gerarGetter(cc, entry.getKey(), entry.getValue()));
			cc.addMethod(gerarSetter(cc, entry.getKey(), entry.getValue()));
		}

		cc.addField(new CtField(retornarCtClass(String.class), "_id", cc));

		return cc.toClass();
	}

	private static CtClass retornarCtClass(Class clazz) throws NotFoundException {
		ClassPool pool = ClassPool.getDefault();
		return pool.get(clazz.getName());
	}

	private static CtMethod gerarGetter(CtClass declaringClass, String fieldName, Class fieldClass)
			throws CannotCompileException {

		String getterName = gerarNomeGetter(fieldName);

		StringBuffer sb = new StringBuffer();
		sb.append("public ").append(fieldClass.getName()).append(" ").append(getterName).append("(){")
				.append("return this.").append(fieldName).append(";").append("}");

		return CtMethod.make(sb.toString(), declaringClass);
	}

	private static CtMethod gerarSetter(CtClass declaringClass, String fieldName, Class fieldClass)
			throws CannotCompileException {

		String nomeGetter = gerarNomeSetter(fieldName);

		StringBuffer sb = new StringBuffer();
		sb.append("public void ").append(nomeGetter).append("(").append(fieldClass.getName()).append(" ")
				.append(fieldName).append(")").append("{").append("this.").append(fieldName).append("=")
				.append(fieldName).append(";").append("}");

		return CtMethod.make(sb.toString(), declaringClass);
	}

	private static String gerarNomeGetter(String nomeMetodo) {
		return gerarNomeMetodoComPrefixo("get", nomeMetodo);
	}

	private static String gerarNomeSetter(String nomeMetodo) {
		return gerarNomeMetodoComPrefixo("set", nomeMetodo);
	}

	private static String gerarNomeMetodoComPrefixo(String prefixo, String nomeMetodo) {
		return prefixo + nomeMetodo.substring(0, 1).toUpperCase() + nomeMetodo.substring(1);
	}

}
