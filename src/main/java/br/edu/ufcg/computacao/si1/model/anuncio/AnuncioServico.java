package br.edu.ufcg.computacao.si1.model.anuncio;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.edu.ufcg.computacao.si1.model.enumerations.TipoDeAnuncioEnum;
import br.edu.ufcg.computacao.si1.model.usuario.Usuario;

/**
 * Classe concreta para objetos do tipo AnuncioEmprego que herda da superclasse Anuncio, onde estarão contidos, valores e metodos para o mesmo.
 * 
 * @author Thaynan Andrey
 */
@Entity(name="AnuncioServico")
@Table(name="tb_anuncio_servico")
public class AnuncioServico extends Anuncio{

    @Column(name = "data_agendamento")
    private Date dataDeAgendamento;
	
    /**
     * Construtor default
     */
	public AnuncioServico(){
		super();
	}
	
	/**
     * Construtor do objeto
     */
	public AnuncioServico(String titulo, double quantia,Usuario dono, Long dataDeCriacao, Date dataDeAgendamento, int diasDeVidaUtil){
		super(titulo,quantia,dono, dataDeCriacao, diasDeVidaUtil);
		this.dataDeAgendamento = dataDeAgendamento;
	}

	/**
     * Metodo override para retorno do tipo do anúncio.
     * @return String - Tipo do anuncio.
     */
	@Override
	public String getTipo() {
		return TipoDeAnuncioEnum.SERVICO.getValor();
	}
}
