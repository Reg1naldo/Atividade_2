package br.gov.sp.fatec.service;

import java.util.List;

import br.gov.sp.fatec.model.TipoMaterial;

public interface TipoMaterialService {
	public TipoMaterial buscar(String nome);	
	public TipoMaterial buscar(Long id);
	public List<TipoMaterial> todos();
	public TipoMaterial cadastroTipoMaterial(TipoMaterial tipo);
}