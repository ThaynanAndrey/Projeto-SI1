package br.edu.ufcg.computacao.si1.model.notificacao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.edu.ufcg.computacao.si1.model.usuario.Usuario;

@Entity
public class Notificacao {

	    @Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
	    @Column(name = "notificacao_id", nullable = false, unique = true)
	    private Long _id;

	    @Column(name = "titulo", nullable = false)
	    private String titulo;
	    
	    @Column(name = "data_notificacao", nullable = false)
	    private Long dataDeNotificacao;
	    
	    @ManyToOne(targetEntity = Usuario.class)
	    @JoinColumn(name="usuario_id")
	    private Usuario dono;
	    
	    /**
		 * Construtor default
		 */
		public Notificacao() {
	        titulo = "";
	        dataDeNotificacao = new Date().getTime();
	    }
		
	    /**
	     * Construtor do objeto
	     */
	    public Notificacao(String titulo,Usuario dono, Long dataDeNotificacao) {
			this.titulo = titulo;
			this.dataDeNotificacao =  dataDeNotificacao;
			//this.dono = dono;
		}
	    
	    
		    /**
		     * Metodo para alteracao do dono da notificacao
		     * @param Usuario dono - Dono da notificacao
		     */
			public void setDono(Usuario dono) {
				this.dono = dono;
			}
	
			/**
			 * Metodo para retorno do dono da notificacao
			 * @return Usuario dono - Dono da notificacao
			 */
			public Usuario getDono() {
				return this.dono;
			}
			
	    /**
	     * Metodo para retorno do id da notificacao
	     * @return Long id - o id da notificacao
	     */
	    public Long get_id() {
	        return _id;
	    }

	    /**
	     * Metodo para alteracao do id da notificacao
	     * @param long _id -  id a ser colocado no notificacao
	     */public void set_id(Long _id) {
	        this._id = _id;
	    }
	    
	    /**
	     * Metodo para retorno do titulo da notificacao
	     * @return String - Titulo da notificacao
	     */
	    public String getTitulo() {
	        return titulo;
	    }
	    
	    /**
	     * Metodo para alteracao do titulo da notificacao
	     * @param String titulo - Titulo da notificacao.
	     */
	    public void setTitulo(String titulo) {
	        this.titulo = titulo;
	    }
	    
	    /**
	     * Metodo para retorno da data de notificacao
	     * @return String - Data de notificacao.
	     */
	    public Long getdataDeNotificacao() {
	        return dataDeNotificacao;
	    }
	    
	    /**
	     * Metodo para alteracao da data de notificacao
	     * @param String dataDeNotificacao - Data de notificacao.
	     */
	    public void setdataDeNotificacao(Long dataDeNotificacao) {
	        this.dataDeNotificacao = dataDeNotificacao;
	    }
	    

}
