package br.edu.ufcg.computacao.si1.model.anuncio;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import br.edu.ufcg.computacao.si1.model.enumeration.TipoDeAnuncioEnum;
import br.edu.ufcg.computacao.si1.model.usuario.Usuario;

/**
 * Classe concreta para objetos do tipo AnuncioEmprego que herda da superclasse Anuncio, onde seráo contidos, valores e metodos para o mesmo.
 */
@Entity(name="AnuncioServico")
@Table(name="tb_anuncio_servico")
public class AnuncioServico extends Anuncio{

    @Column(name = "data_agendamento", nullable = false)
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
	public AnuncioServico(String titulo, double quantia,Usuario dono, Date dataDeCriacao, Date dataDeAgendamento){
		super(titulo,quantia,dono, dataDeCriacao);
		this.dataDeAgendamento = dataDeAgendamento;
	}

	/**
     * Metodo override para retorno do tipo do anúncio.
     * @return String - Tipo do anuncio.
     */
	@Override
	public String getTipo() {
		return TipoDeAnuncioEnum.SERVICO.toString().toLowerCase();
	}
}
