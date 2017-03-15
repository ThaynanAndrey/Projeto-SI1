package br.edu.ufcg.computacao.si1.model.enumeration;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Marcus Oliveira on 08/12/16.
 */
public enum AvaliacaoEnum {
	
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
	
	/**
	 * Metodo de definicao do enum
	 * @param String estrelas - quantidade de estrelas referente a avaliacao do usuario
	 */
	AvaliacaoEnum(String estrelas){
		this.estrelas = estrelas;
	}

	/**
	 * Metodo para retorno das estrelas do usuario
	 * @return estrelas - estrelas de avaliacao do usuario
	 */
	public String getEstrelas() {
		return estrelas;
	}
}
