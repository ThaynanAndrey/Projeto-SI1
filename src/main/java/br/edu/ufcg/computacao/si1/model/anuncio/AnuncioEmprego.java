package br.edu.ufcg.computacao.si1.model.anuncio;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import br.edu.ufcg.computacao.si1.model.enumeration.TipoDeAnuncioEnum;
import br.edu.ufcg.computacao.si1.model.usuario.Usuario;

@Entity(name="AnuncioEmprego")
@Table(name="tb_anuncio_emprego")
public class AnuncioEmprego extends Anuncio{
	
	public AnuncioEmprego(){
		super();
	}
	
	public AnuncioEmprego(String titulo, double quantia,Usuario dono, Date dataDeCriacao){
		super(titulo,quantia,dono, dataDeCriacao);
	}

	@Override
	public String getTipo() {
		return TipoDeAnuncioEnum.EMPREGO.toString().toLowerCase();
	}
	
	
}
