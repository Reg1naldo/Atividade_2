package br.gov.sp.fatec.web.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import br.gov.sp.fatec.model.Material;
import br.gov.sp.fatec.service.MaterialService;
import br.gov.sp.fatec.view.View;

@RestController
@RequestMapping(value = "/material")
public class MaterialController {

	@Autowired
	private MaterialService cadastroMaterialService;

	public void setCadastroMaterialService(MaterialService cadastroMaterialService) {
		this.cadastroMaterialService = cadastroMaterialService;
	}

	@RequestMapping(value = "/getById/{id}")
	@JsonView(View.All.class)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Material> get(@PathVariable("id") Long id) {
		Material material = cadastroMaterialService.buscar(id);
		if (material == null) {
			return new ResponseEntity<Material>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Material>(material, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getAllFilter")
	@JsonView(View.All.class)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Collection<Material>> getAll(@RequestParam(value="nome", defaultValue="" ) String nome, @RequestParam(value="descricao", defaultValue="" ) String descricao ) {
		return new ResponseEntity<Collection<Material>>(cadastroMaterialService.buscarPorNome(nome), HttpStatus.OK);
		
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(View.All.class)
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("isAuthenticated()")
	public Material save(@RequestBody Material material, HttpServletRequest request, HttpServletResponse response) {
		material = cadastroMaterialService.cadastroMaterial(material);
		response.addHeader("Location", request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + "/material/getById/" + material.getId());
		return material;
	}
}