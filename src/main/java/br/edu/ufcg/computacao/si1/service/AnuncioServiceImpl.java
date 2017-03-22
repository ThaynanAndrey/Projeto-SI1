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
 * Created by Marcus Oliveira on 28/12/16.
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

    private Usuario getUsuarioLogado() {
    	String email = Utils.userNameUsuarioLogado();    	
		Optional<Usuario> usuarioLogado = usuarioService.getByEmail(email);
		Usuario usuario = usuarioLogado.get();
		
		return usuario;
    }
    
    @Override
    public Anuncio create(AnuncioForm anuncioForm) {
    	
    	Usuario usuario = getUsuarioLogado(); 
    	anuncioForm.setDono(usuario);
    	
    	Anuncio anuncio = anuncioFactory.criarAnuncio(anuncioForm);
        /*aqui salvamos o anuncio recem criado no repositorio jpa*/
        return anuncioRepository.save(anuncio);
    }

    @Override
    public Optional<Anuncio> getById(Long id) {
        /*aqui recuperamos o anuncio pelo seu id*/
        return Optional.ofNullable(anuncioRepository.findOne(id));
    }


    public Collection<Anuncio> getByTipo(String tipo) {

        /*pegamos aqui todos os anuncios, mas retornamos os anuncios por tipo
        * filtrando o tipo, pelo equals, retornando um arrayLista*/
        return anuncioRepository.findAll().stream()
                .filter(anuncio -> anuncio.getTipo().equals(tipo))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Collection<Anuncio> getAll() {
        /*aqui retornamos todos os anuncios, sem distincao*/

        return anuncioRepository.findAll();
    }

    @Override
    public boolean update(Anuncio anuncio) {
        /*a atualizacao do anuncio eh feita apenas se o anuncio ja existir*/
        if (anuncioRepository.exists(anuncio.get_id())) {
            anuncioRepository.save(anuncio);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        /*aqui se apaga o anuncio se ele existir*/


        if (anuncioRepository.exists(id)) {
            anuncioRepository.delete(id);
            return true;
        }
        return false;
    }
    
    public Collection<Anuncio> anunciosDisponiveisParaUsuarioComprar() {
    	Collection<Anuncio> listaDeAnuncios= this.getAll();
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
		Usuario usuario = getUsuarioLogado();
		
		return usuario.getAnuncios();
    };
    
    public List<String> tiposAnunciosParaCadastrarUsuarioLogado() {
		Usuario usuario = getUsuarioLogado();
		
		return usuario.getTiposDeAnunciosDisponiveis();
    }

	public void comprarAnuncio(AnuncioForm anuncioForm) {
       	if(!anuncioForm.getTipo().equals(TipoDeAnuncioEnum.EMPREGO.getValor())){
       		Usuario comprador = getUsuarioLogado(); 
           	
           	Anuncio anuncio = this.getById(anuncioForm.getId()).get();
           	Usuario vendedor = anuncio.pegueDono();
       		
       		vendedor.venderAnuncio(anuncio.getQuantia());
           	comprador.comprarAnuncio(anuncio.getQuantia());
		}
   		this.delete(anuncioForm.getId());
	};
}