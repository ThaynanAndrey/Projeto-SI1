package br.edu.ufcg.computacao.si1.model.anuncio;


import javax.persistence.*;

import br.edu.ufcg.computacao.si1.model.enumeration.AvaliacaoEnum;
import br.edu.ufcg.computacao.si1.model.usuario.Usuario;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Marcus Oliveira on 08/12/16.
 */
@Entity
@Table(name="tb_anuncio")
public class Anuncio {

    private final static DateFormat DATE_FORMAT = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "anuncio_id", nullable = false, unique = true)
    private Long _id;

    @Column(name = "titulo", nullable = false)
    private String titulo;
    
    @Column(name = "preco", nullable = false)
    private double preco;
    
    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "data_criacao", nullable = false)
    private Date dataDeCriacao;

    @Column(name = "avaliacao")
    @Enumerated(EnumType.STRING)
    private AvaliacaoEnum avaliacao;
    
    @ManyToOne
    @JoinColumn(name="usuario_id")
    private Usuario dono;

    /**
	 * Construtor default
	 */
	public Anuncio() {
        titulo = "";
        dataDeCriacao = new Date();
        preco = 0;
        avaliacao = null;
        tipo = "";
    }
    
    public Anuncio(String titulo, double preco, String tipo, Date dataDeCriacao, AvaliacaoEnum avaliacao, Usuario dono) {
		this.titulo = titulo;
		this.preco = preco;
		this.tipo = tipo;
		this.dataDeCriacao = dataDeCriacao;
		this.avaliacao = avaliacao;
		this.dono = dono;
	}
    
    /**
     * Define o dono do anúncio
     * @param dono
     */
	public void setDono(Usuario dono) {
		this.dono = dono;
	}

    /**
     * Retorna o id do anúncio
     * @return o id do anuncio
     */
    public Long get_id() {
        return _id;
    }

    /**
     * Modifica o id do anúncio
     * @param _id id a ser colocado no anuncio
     */public void set_id(Long _id) {
        this._id = _id;
    }
    
    /**
     * Retorna o titulo do anúncio
     * @return
     */
    public String getTitulo() {
        return titulo;
    }
    
    /**
     * Define o titulo do anúncio
     * @param titulo
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    /**
     * Retorna a data de criação do anúncio
     * @return
     */
    public String getDataDeCriacao() {
        return DATE_FORMAT.format(dataDeCriacao);
    }
    
    /**
     * Define a data de criação do anúncio
     * @param dataDeCriacao
     */
    public void setDataDeCriacao(Date dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }
    
    /**
     * Retorno o preço do anúncio
     * @return
     */
    public double getPreco() {
        return preco;
    }
    
    /**]
     * Define o preço do anúncio
     * @param preco
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }
    
    /**
     * Retorna a nota do anúncio
     * @return
     */
    public AvaliacaoEnum getNota() {
        return avaliacao;
    }
    
    /**
     * Define a nota do anúncio
     * @param nota
     */
    public void setNota(AvaliacaoEnum avaliacao) {
        this.avaliacao = avaliacao;
    }
    
    /**
     * Retorna o tipo do anúncio
     * @return
     */
    public String getTipo() {
        return tipo;
    }
    
    /**
     * Define o tipo do anúncio
     * @param tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Anuncio)) return false;

        Anuncio anuncio = (Anuncio) o;

        if (Double.compare(anuncio.getPreco(), getPreco()) != 0) return false;
        if (!get_id().equals(anuncio.get_id())) return false;
        if (!getTitulo().equals(anuncio.getTitulo())) return false;
        if (!getDataDeCriacao().equals(anuncio.getDataDeCriacao())) return false;
        if (getNota() != null ? !getNota().equals(anuncio.getNota()) : anuncio.getNota() != null) return false;
        return getTipo().equals(anuncio.getTipo());

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = get_id().hashCode();
        result = 31 * result + getTitulo().hashCode();
        result = 31 * result + getDataDeCriacao().hashCode();
        temp = Double.doubleToLongBits(getPreco());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getNota() != null ? getNota().hashCode() : 0);
        result = 31 * result + getTipo().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Anuncio{" +
                "_id=" + _id +
                ", titulo='" + titulo + '\'' +
                ", dataDeCriacao=" + getDataDeCriacao() +
                ", preco=" + preco +
                ", avaliacao=" + avaliacao.toString() +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
