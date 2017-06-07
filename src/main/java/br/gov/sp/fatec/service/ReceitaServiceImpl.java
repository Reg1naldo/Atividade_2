package br.gov.sp.fatec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.model.Material;
import br.gov.sp.fatec.model.Receita;
import br.gov.sp.fatec.model.Usuario;
import br.gov.sp.fatec.repository.ReceitaRepository;
import br.gov.sp.fatec.repository.UsuarioRepository;

@Service("cadastroReceita")
public class ReceitaServiceImpl implements ReceitaService {
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private ReceitaRepository receitaRepo;
	
	@Transactional
	public Receita cadastraReceita(Receita receita){
		Receita nreceita = new Receita();
		if (usuarioRepo.findByNome(receita.getUsuario().getNome()) != null){
			Usuario usuario = new Usuario();
			usuario = usuarioRepo.findByNome(receita.getUsuario().getNome());
			if(receitaRepo.findByNome(receita.getNome()) != null){
				nreceita = receitaRepo.findByNome(receita.getNome());
			}
			nreceita.setDescricao(receita.getDescricao());
			nreceita.setNome(receita.getNome());
			nreceita.setUsuario(usuario);
			nreceita.setMateriais(receita.getMateriais());
			
			return receitaRepo.save(nreceita);
		}
		else{
			System.out.println("Usuário inexistente, impossível cadastrar receita.");
			return receita;
		}
		
	}
	
	public Receita buscar(Long id){
		return receitaRepo.findOne(id);
	}
	
	public List<Receita> buscarPorNomeContem(String nome){
		return receitaRepo.findByNomeContains(nome);
	}
	
	public void setReceitaRepository(ReceitaRepository receitaRepo){
		this.receitaRepo = receitaRepo;
	}

}
