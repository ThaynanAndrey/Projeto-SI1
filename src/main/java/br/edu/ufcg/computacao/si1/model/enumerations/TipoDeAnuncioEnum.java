package br.edu.ufcg.computacao.si1.model.enumerations;

public enum TipoDeAnuncioEnum {
	/**
	 * Tipo de anuncio como móvel
	 */
	MOVEL("movel"),
	
	/**
	 * Tipo de anuncio como imóvel
	 */
	IMOVEL("imovel"),
	
	/**
	 * Tipo de anuncio como serviço
	 */
	SERVICO("servico"),
	
	/**
	 * Tipo de anuncio como emprego
	 */
	EMPREGO("emprego");
	
	private String valor;
	
	private TipoDeAnuncioEnum(String valor){
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
}
