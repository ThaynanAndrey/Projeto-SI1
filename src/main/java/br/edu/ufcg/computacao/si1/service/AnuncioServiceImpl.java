package br.edu.ufcg.computacao.si1.service;

import br.edu.ufcg.computacao.si1.model.anuncio.Anuncio;
import br.edu.ufcg.computacao.si1.model.enumerations.TipoDeAnuncioEnum;
import br.edu.ufcg.computacao.si1.model.factories.AnuncioFactory;
import br.edu.ufcg.computacao.si1.model.forms.AnuncioForm;
import br.edu.ufcg.computacao.si1.model.usuario.Usuario;
import br.edu.ufcg.computacao.si1.repository.AnuncioRepository;
import br.edu.ufcg.computacao.si1.utils.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Serviço responsável por gerenciar as operações sobre as entidades anúncios que
 * estão ou serão armazenadas no banco de dados. 
 * 
 * @author Thaynan Andrey
 * 
 */
@Service
public class AnuncioServiceImpl implements IService<Anuncio, AnuncioForm> {
    //TODO add validity checks

    private AnuncioRepository anuncioRepository;
    private AnuncioFactory anuncioFactory = new AnuncioFactory();
    
    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    public AnuncioServiceImpl(AnuncioRepository anuncioRepository) {
        /*neste codigo apenas atribuimos o repositorio jpa ao atributo */
        this.anuncioRepository = anuncioRepository;
    }

    public AnuncioRepository getAnuncioRepository(){
        return this.anuncioRepository;
    }
    
    @Override
    public Anuncio criarNovaEntidade(AnuncioForm anuncioForm) {
    	
    	Usuario usuarioLogado = usuarioService.getUsuarioLogado();
    	anuncioForm.setDono(usuarioLogado);
    	
    	Anuncio anuncio = anuncioFactory.criarAnuncio(anuncioForm);
        /*aqui salvamos o anuncio recem criado no repositorio jpa*/
        return anuncioRepository.save(anuncio);
    }

    @Override
    public Optional<Anuncio> obterEntidadePorId(Long id) {
        /*aqui recuperamos o anuncio pelo seu id*/
        return Optional.ofNullable(anuncioRepository.findOne(id));
    }


    public Collection<Anuncio> obterAnuncioPorTipo(String tipo) {

        /*pegamos aqui todos os anuncios, mas retornamos os anuncios por tipo
        * filtrando o tipo, pelo equals, retornando um arrayLista*/
        return anuncioRepository.findAll().stream()
                .filter(anuncio -> anuncio.getTipo().equals(tipo))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Collection<Anuncio> obterTodasEntidadesCadastradas() {
        /*aqui retornamos todos os anuncios, sem distincao*/

        return anuncioRepository.findAll();
    }

    @Override
    public boolean atualizarEntidade(Anuncio anuncio) {
        /*a atualizacao do anuncio eh feita apenas se o anuncio ja existir*/
    	boolean existeAnuncio = anuncioRepository.exists(anuncio.get_id());
    	
    	if (existeAnuncio)
            anuncioRepository.save(anuncio);
        
        return existeAnuncio;
    }

    @Override
    public boolean deletarEntidade(Long id) {
        /*aqui se apaga o anuncio se ele existir*/
    	boolean existeAnuncio = anuncioRepository.exists(id);

        if (existeAnuncio) 
            anuncioRepository.delete(id);
        
        return existeAnuncio;
    }
    
    public Collection<Anuncio> anunciosDisponiveisParaUsuarioComprar() {
    	Collection<Anuncio> listaDeAnuncios= this.obterTodasEntidadesCadastradas();
   		Collection<Anuncio> anunciosParaComprar = new ArrayList<Anuncio>();
   		
   		String email = Utils.userNameUsuarioLogado();    	
   		 		
   		for (Anuncio anuncio : listaDeAnuncios){
   			if(!anuncio.pegueDono().getEmail().equals(email)){
   				anunciosParaComprar.add(anuncio);
   			}
   		}
   		return anunciosParaComprar;
    }
    
    public List<Anuncio> todosAnunciosUsuarioLogado() {
		Usuario usuario = usuarioService.getUsuarioLogado();
		
		return usuario.getAnuncios();
    };
    
    public List<String> tiposAnunciosParaCadastrarUsuarioLogado() {
		Usuario usuario = usuarioService.getUsuarioLogado();
		
		return usuario.getTiposDeAnunciosDisponiveis();
    }

	public void comprarAnuncio(AnuncioForm anuncioForm) {
       	if(!anuncioForm.getTipo().equals(TipoDeAnuncioEnum.EMPREGO.getValor())){
       		Usuario comprador = usuarioService.getUsuarioLogado();
           	
           	Anuncio anuncio = this.obterEntidadePorId(anuncioForm.getId()).get();
           	Usuario vendedor = anuncio.pegueDono();
       		
       		vendedor.venderAnuncio(anuncio.getQuantia());
           	comprador.comprarAnuncio(anuncio.getQuantia());
		}
   		this.deletarEntidade(anuncioForm.getId());
	}
	
	public Usuario obterDonoDoAnuncio(Long id) {
		
		Usuario dono = null;
		Anuncio anuncio = this.obterEntidadePorId(id).get();

		if(anuncio != null)
			dono = anuncio.pegueDono();
		
		return dono;
	}
}