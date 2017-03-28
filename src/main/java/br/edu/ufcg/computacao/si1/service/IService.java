package br.edu.ufcg.computacao.si1.service;

import java.util.Collection;
import java.util.Optional;

/**
 * Definindo-se uma interface genérica, a qual reflete todas as operações báscias
 * que todo repositório deve possuir.
 * 
 * @author Thaynan Andrey
 * @author Giuseppe Mongiovi
 *
 * @param <Entidade> representa a entidade que está ou será armazenada no banco de dados.
 * @param <EntidadeForm> utilizando-se o padrão DTO, a mesma reflete os dados necessários 
 * 						para a construção de uma nova entidade que será armazenada no banco de dados.
 */
public interface IService<Entidade, EntidadeForm> {
	
	/**
	 * Método implementado pelas subclasses que cria uma entidade e salva no BD
	 * @param form - Formulario com as infomações do objeto a ser criado
	 * @return Entidade - entidade criada e salva no BD
	 */
	public Entidade criarNovaEntidade(EntidadeForm form);

	/**
	 * Retorna um objeto(se existir) pelo seu id, implementado nas subclasses
	 * @param id - Id do objeto
	 * @return Objeto encontrado pelo id
	 */
	public Optional<Entidade> obterEntidadePorId(Long id);

	/**
	 * Retorna uma coleção com todos os objetos de um certo tipo cadastrados, implementado nas subclasses
	 * @return Colecao com todos os objetos do mesmo tipo
	 */
	public Collection<Entidade> obterTodasEntidadesCadastradas();

	/**
	 * Atualiza uma entidade no BD, implementado nas subclasses
	 * @param modelo - Novo modelo do objeto
	 * @return Boolean referente ao sucesso da atualizacao
	 */
	public boolean atualizarEntidade(Entidade modelo);

	/**
	 * Deleta um objeto atras vez do seu id, implementado nas subclasses
	 * @param Long id - id referente ao objeto
	 * @return Boolean referente ao sucesso da delecao
	 */
	public boolean deletarEntidade(Long id);
}
