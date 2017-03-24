package br.edu.ufcg.computacao.si1.model.forms;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getDataDeNotificacao() {
		return dataDeNotificacao;
	}

	public void setDataDeNotificacao(Long dataDeNotificacao) {
		this.dataDeNotificacao = dataDeNotificacao;
	}

	public Long get_id() {
		return _id;
	}

	public void set_id(Long _id) {
		this._id = _id;
	}

	public Usuario getDono() {
		return dono;
	}

	public void setDono(Usuario donoId) {
		this.dono = donoId;
	}
    
    
}
