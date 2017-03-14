package br.edu.ufcg.computacao.si1.model.enumerations;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Marcus Oliveira on 08/12/16.
 */
public enum NotaEnum {
	
	ZERO(""),
	/**
	 * Nota de uma estrela
	 */
	UM("*"),
	/**
	 * Nota de duas estrelas
	 */
	DOIS("**"),
	/**
	 * Nota de três estrelas
	 */
	TRES("***"),
	/**
	 * Nota de quatro estrelas
	 */
	QUATRO("****"),
	/**
	 * Nota de cinco estrelas
	 */
	CINCO("*****");
	
	private String estrelas;
	
	NotaEnum(String estrelas){
		this.estrelas = estrelas;
	}

	public String getEstrelas() {
		return estrelas;
	}
}
