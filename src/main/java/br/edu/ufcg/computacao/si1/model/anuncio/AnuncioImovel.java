package br.edu.ufcg.computacao.si1.model.anuncio;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import br.edu.ufcg.computacao.si1.model.enumeration.TipoDeAnuncioEnum;
import br.edu.ufcg.computacao.si1.model.usuario.Usuario;

/**
 * Classe concreta para objetos do tipo AnuncioImovel que herda da classe Anuncio, onde seráo contidos, valores e metodos para o mesmo.
 */
@Entity(name="AnuncioImovel")
@Table(name="tb_anuncio_imovel")
public class AnuncioImovel extends Anuncio{

	/**
     * Construtor default
     */
	public AnuncioImovel(){
		super();
	}
	
	/**
     * Construtor do objeto
     */
	public AnuncioImovel(String titulo, double quantia,Usuario dono, Date dataDeCriacao){
		super(titulo,quantia,dono, dataDeCriacao);
	}
	
	/**
     * Metodo override para retorno do tipo do anúncio.
     * @return String - Tipo do anuncio.
     */
	@Override
	public String getTipo() {
		return TipoDeAnuncioEnum.IMOVEL.toString().toLowerCase();
	}
}
