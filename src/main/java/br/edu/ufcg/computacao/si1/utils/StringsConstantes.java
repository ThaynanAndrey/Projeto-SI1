package br.edu.ufcg.computacao.si1.utils;

import java.util.Arrays;
import java.util.List;

public class StringsConstantes {

	private final static String TITULO = "O titulo";
	private final static String NOME = "O nome";
	private final static String PRECO = "O preço";
	private final static String TIPO_DE_ANUNCIO = "O tipo de anúncio";
	private final static String EMAIL = "O email";
	private static final String SENHA = "A senha";
	
	public final static String PRECO_MINIMO_ANUNCIO = "0.1";
	
	//----------Titulo----------//
	public final static String MESAGEM_DE_ERRO_TITULO_NULO = StringsConstantes.TITULO + " não pode ser nulo.";
	public final static String MESAGEM_DE_ERRO_TITULO_VAZIO = StringsConstantes.TITULO + " não pode esta vazio.";
	public final static String MESAGEM_DE_ERRO_TITULO_QTD_CARACTERES = StringsConstantes.TITULO + " deve ter entre 2 e 100 caracters";
	
	//---------Preco-----------//
	public final static String MESAGEM_DE_ERRO_PRECO_NULO = StringsConstantes.PRECO + " não pode ser nulo.";
	public final static String MESAGEM_DE_ERRO_PRECO_MINIMO_PERMETIDO = StringsConstantes.PRECO + " minimo é " +  StringsConstantes.PRECO_MINIMO_ANUNCIO + " para um anúncio.";
	
	//---------Tipo de Anuncio-----------//
	public final static String MESAGEM_DE_ERRO_TIPO_ANUNCIO_NULO = StringsConstantes.TIPO_DE_ANUNCIO + " não pode ser nulo.";
	public final static String MESAGEM_DE_ERRO_TIPO_ANUNCIO_VAZIO = "Escolha um tipo para o anúncio.";
	
	//----------Nome-------------//
	public final static String MESAGEM_DE_ERRO_NOME_NULO = StringsConstantes.NOME + " não pode ser nulo.";
	public final static String MESAGEM_DE_ERRO_NOME_VAZIO = StringsConstantes.NOME + " não pode ser vazio.";
	public final static String MESAGEM_DE_ERRO_QTD_CARACTERES_NOME = StringsConstantes.NOME + " deve ter entre 2 e 100 caracteres.";
	
    //--------Array de Tipos de Anuncio---------//
private static final String[] tiposFisico = {"movel", "imovel"};
private static final String[] tiposJuridico = {"movel", "imovel", "emprego", "servico"};
public static final List<String> TIPOS_DE_ANUNCIO_USUARIO_FISICO = Arrays.asList(tiposFisico); 
public static final List<String> TIPOS_DE_ANUNCIO_USUARIO_JURIDICO =  Arrays.asList(tiposJuridico); 
	
	//----------Email----------//
	public final static String MESAGEM_DE_ERRO_EMAIL_VAZIO = StringsConstantes.EMAIL + " não pode ser vazio.";
	
	//----------Senha----------//
	public final static String MESAGEM_DE_ERRO_SENHA_NULA = StringsConstantes.SENHA + " não pode ser nula.";
	public final static String MESAGEM_DE_ERRO_SENHA_QTD_CARACTERES = StringsConstantes.SENHA + " deve ter entre 4 e 16 caracteres.";
}