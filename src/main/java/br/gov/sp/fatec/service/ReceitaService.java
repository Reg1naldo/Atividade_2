package br.gov.sp.fatec.service;

import java.util.List;

import br.gov.sp.fatec.model.Material;
import br.gov.sp.fatec.model.Receita;
import br.gov.sp.fatec.model.Usuario;

public interface ReceitaService {
	public Receita cadastraReceita(Usuario usuario, String nomeReceita, String descricaoReceita, List<Material> materiais);

	public Receita buscar(Long id);
	
	public List<Receita> buscarPorNomeContem(String nome);
}
