package br.edu.ufcg.computacao.si1.model.anuncio;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.edu.ufcg.computacao.si1.model.enumerations.TipoDeAnuncioEnum;
import br.edu.ufcg.computacao.si1.model.usuario.Usuario;

/**
 * Classe concreta para objetos do tipo AnuncioEmprego que herda da classe Anuncio, onde estarão contidos, valores e metodos para o mesmo.
 * 
 * @author Thaynan Andrey
 * @author Giuseppe Mongiovi
 */
@Entity(name="AnuncioMovel")
@Table(name="tb_anuncio_movel")
public class AnuncioMovel extends Anuncio{

	/**
     * Construtor default
     */
	public AnuncioMovel(){
		super();
	}
	
	/**
     * Construtor do objeto
     */
	public AnuncioMovel(String titulo, double quantia,Usuario dono, Long dataDeCriacao, int diasDeVidaUtil){
		super(titulo,quantia,dono, dataDeCriacao,diasDeVidaUtil);
	}

	/**
     * Metodo override para retorno do tipo do anúncio.
     * @return String - Tipo do anuncio.
     */
	@Override
	public String getTipo() {
		return TipoDeAnuncioEnum.MOVEL.getValor();
	}
}
