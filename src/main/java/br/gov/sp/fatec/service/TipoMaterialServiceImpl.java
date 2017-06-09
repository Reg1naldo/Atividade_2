package br.gov.sp.fatec.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.model.TipoMaterial;
import br.gov.sp.fatec.model.Usuario;
import br.gov.sp.fatec.repository.TipoMaterialRepository;

@Service("cadastroTipoMaterial")
public class TipoMaterialServiceImpl implements TipoMaterialService{
	@Autowired
	private TipoMaterialRepository tipoMaterialRepo;
	
	public TipoMaterial buscar(String nome) {
		return tipoMaterialRepo.findByNome(nome);
	}
	
	
	public TipoMaterial buscar(Long id) {
		return tipoMaterialRepo.findOne(id);
	}

	public List<TipoMaterial> todos() {
		List<TipoMaterial> retorno = new ArrayList<TipoMaterial>();
		for(TipoMaterial tipoMaterial: tipoMaterialRepo.findAll() ) {
			retorno.add(tipoMaterial);
		}
		return retorno;
	}
	
	@Transactional
	public TipoMaterial cadastroTipoMaterial(TipoMaterial tipo){
		TipoMaterial tipo1 = new TipoMaterial();
		if (tipoMaterialRepo.findByNome(tipo.getNome())!=null){
			tipo1 = tipoMaterialRepo.findByNome(tipo.getNome());
		}
		tipo1.setNome(tipo.getNome());
		
		return tipoMaterialRepo.save(tipo1);
	}
	/*
	public Boolean deleteTipoMaterial(Long id){
		if (tipoMaterialRepo.findById(id)!=null){
			tipoMaterialRepo.deleteById(id);
			return true;
		}else{
			return false;
		}
		
	}
	*/
	public void setTipoMaterialRepository(TipoMaterialRepository tipoMaterialRepo){
		this.tipoMaterialRepo = tipoMaterialRepo;
	}
}
