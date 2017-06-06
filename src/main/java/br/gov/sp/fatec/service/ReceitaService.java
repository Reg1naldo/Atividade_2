package br.gov.sp.fatec.service;

import java.util.List;

import br.gov.sp.fatec.model.Receita;

public interface ReceitaService {
	public Receita cadastraReceita(Receita receita);

	public Receita buscar(Long id);
	
	public List<Receita> buscarPorNomeContem(String nome);
}
