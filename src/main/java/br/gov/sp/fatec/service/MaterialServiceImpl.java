package br.gov.sp.fatec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.model.Material;
import br.gov.sp.fatec.model.TipoMaterial;
import br.gov.sp.fatec.repository.MaterialRepository;
import br.gov.sp.fatec.repository.TipoMaterialRepository;

@Service("cadastroMaterial")
public class MaterialServiceImpl implements MaterialService {
	@Autowired
	private MaterialRepository materialRepo;

	@Autowired
	private TipoMaterialRepository tipoMaterialRepo;

	@Transactional
	public Material cadastroMaterial(Material material) {
		Material nmaterial = materialRepo.findByNome(material.getNome());
		if (nmaterial != null) {
			nmaterial.setNome(material.getNome());
			nmaterial.setDescricao(material.getDescricao());
			nmaterial.setTipo(material.getTipo());
			return materialRepo.save(nmaterial);
		} else {
			TipoMaterial tipo = tipoMaterialRepo.findByNome(material.getTipo().getNome());
			material.setTipo(tipo);
			return materialRepo.save(material);
		}
	}

	public Material buscar(Long id) {
		return materialRepo.findById(id);
	}
	
	public List<Material> buscarPorNome(String nome) {
		return materialRepo.findByNomeContains(nome);
	}

	public void setMaterialRepository(MaterialRepository materialRepo) {
		this.materialRepo = materialRepo;
	}

}