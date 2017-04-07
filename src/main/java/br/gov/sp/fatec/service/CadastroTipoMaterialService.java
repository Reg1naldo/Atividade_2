package br.gov.sp.fatec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.model.TipoMaterial;
import br.gov.sp.fatec.repository.TipoMaterialRepository;

@Service("cadastroTipoMaterial")
public class CadastroTipoMaterialService {
	@Autowired
	private TipoMaterialRepository tipoMaterialRepo;
	
	public void cadastroTipoMaterial(TipoMaterial tipo){
		TipoMaterial tipo1 = new TipoMaterial();
		if (tipoMaterialRepo.findByNome(tipo.getNome())==null){
			tipo1 = tipoMaterialRepo.findByNome(tipo.getNome());
		}
		tipo1.setNome(tipo.getNome());	
		
		tipoMaterialRepo.save(tipo);
	}
	
	public void setTipoMaterialRepository(TipoMaterialRepository tipoMaterialRepo){
		this.tipoMaterialRepo = tipoMaterialRepo;
	}
}