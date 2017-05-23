package br.gov.sp.fatec.service;

import java.util.List;

import br.gov.sp.fatec.model.Material;

public interface MaterialService {
	public Material cadastroMaterial(Material material);
	
	public Material buscar(Long id);
	
	public List<Material> buscarPorNome(String nome);
}
