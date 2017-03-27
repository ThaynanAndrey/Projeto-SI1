package br.edu.ufcg.computacao.si1.model.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import br.edu.ufcg.computacao.si1.model.usuario.Usuario;
import br.edu.ufcg.computacao.si1.utils.Constantes;

public class NotificacaoForm {
	@NotNull(message = Constantes.MESAGEM_DE_ERRO_TITULO_NULO)
    @NotEmpty(message = Constantes.MESAGEM_DE_ERRO_TITULO_VAZIO)
    @Size(min = 10, max = 100, message = Constantes.MESAGEM_DE_ERRO_TITULO_QTD_CARACTERES)
    private String descricao;
    
    private Long dataDeNotificacao;
   
    private Long _id;
   
    private Usuario dono;

    /**
     * Retorna a descricao da notificacao
     * @return String descricao - descricao da notificacao
     */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Altera a descricacao da notificacao
	 * @param String descricao - Nova descricao
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Retorna a data da criacao da notificacao
	 * @return Long dataDeNotificacao - Data de criacao da notificacao
	 */
	public Long getDataDeNotificacao() {
		return dataDeNotificacao;
	}
	/**
	 * Altera a data de criacao da notificacao
	 * @param dataDeNotificacao - Nova data de notificacao
	 */
	public void setDataDeNotificacao(Long dataDeNotificacao) {
		this.dataDeNotificacao = dataDeNotificacao;
	}

	/**
	 * Retorna id da notificacao
	 * @return Long _id - id da notificacao
	 */
	public Long get_id() {
		return _id;
	}

	/**
	 * altera id da notificacao
	 * @param Long _id - novo id da notificacao
	 */
	public void set_id(Long _id) {
		this._id = _id;
	}

	/**
	 * retorna o dono da notificacao
	 * @return Usuario - usuario dono da notificaao
	 */
	public Usuario getDono() {
		return dono;
	}

	/**
	 * Altera/Define Usuario dono da notificacao
	 * @param Usuario donoId - Usuario dono da notificacao
	 */
	public void setDono(Usuario donoId) {
		this.dono = donoId;
	}
    
    
}
