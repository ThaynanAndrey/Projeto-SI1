package br.edu.ufcg.computacao.si1.model.anuncio;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import br.edu.ufcg.computacao.si1.model.enumerations.TipoDeAnuncioEnum;
import br.edu.ufcg.computacao.si1.model.usuario.Usuario;

/**
 * Classe concreta para objetos do tipo AnuncioEmprego que herda da classe Anuncio, onde seráo contidos, valores e metodos para o mesmo.
 */
@Entity(name="AnuncioEmprego")
@Table(name="tb_anuncio_emprego")
public class AnuncioEmprego extends Anuncio{
	
	/**
     * Construtor default
     */
	public AnuncioEmprego(){
		super();
	}
	
	/**
     * Construtor do objeto
     */
	public AnuncioEmprego(String titulo, double quantia,Usuario dono, Long dataDeCriacao, int diasDeVidaUtil){
		super(titulo,quantia,dono, dataDeCriacao,diasDeVidaUtil);
	}
	
	/**
     * Metodo override para retorno do tipo do anúncio.
     * @return String - Tipo do anuncio.
     */
	@Override
	public String getTipo() {
		return TipoDeAnuncioEnum.EMPREGO.getValor();
	}
	
	
}
