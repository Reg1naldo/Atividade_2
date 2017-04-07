package br.gov.sp.fatec.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.gov.sp.fatec.model.Receita;

public interface ReceitaRepository extends CrudRepository<Receita, Long>{
	public Receita findByNome(String nome);	
	
	public Receita findTop1ByNomeContains(String nome);
	
	public List<Receita> findByIdGreaterThan(Long id);
	
	public List<Receita> findFirst10ByOrderByDataInclusaoDesc();
}
