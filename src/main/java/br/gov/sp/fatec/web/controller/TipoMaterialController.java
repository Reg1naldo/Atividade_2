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

import br.gov.sp.fatec.model.TipoMaterial;
import br.gov.sp.fatec.service.TipoMaterialService;
import br.gov.sp.fatec.view.View;

@RestController
@RequestMapping(value = "/tipoMaterial")
public class TipoMaterialController {
	
	@Autowired
	private TipoMaterialService tipoMaterialService;

	public void setTipoMaterialService(TipoMaterialService tipoMaterialService) {
		this.tipoMaterialService = tipoMaterialService;
	}
	
	@RequestMapping(value = "/get/{nome}")
	@JsonView(View.All.class)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<TipoMaterial> pesquisar(@PathVariable("nome") String nome) {
		return new ResponseEntity<TipoMaterial> (tipoMaterialService.buscar(nome), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getById")
	@JsonView(View.All.class)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<TipoMaterial> get(@RequestParam(value="id", defaultValue="1") Long id) {
		TipoMaterial tipoMaterial = tipoMaterialService.buscar(id);
		if(tipoMaterial == null) {
			return new ResponseEntity<TipoMaterial>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<TipoMaterial>(tipoMaterial, HttpStatus.OK);
	}
	
	//@CrossOrigin(origins = "http://localhost:3002")
	@RequestMapping(value = "/getAll")
	@JsonView(View.Alternative.class)
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Collection<TipoMaterial>> getAll() {
		return new ResponseEntity<Collection<TipoMaterial>>(tipoMaterialService.todos(), HttpStatus.OK);
	}
	
	// Voce pode informar o metodo e o tipo de retorno produzido
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonView(View.All.class)
	// E possivel indicar o status por anotacao, mas sera fixo, sem possibilidade de tratar erros
	@ResponseStatus(HttpStatus.CREATED)
	
	public TipoMaterial save(@RequestBody TipoMaterial tipoMaterial, HttpServletRequest request, HttpServletResponse response) {
		tipoMaterial = tipoMaterialService.cadastroTipoMaterial(tipoMaterial);
		response.addHeader("Location", request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/usuario/getById?id=" + tipoMaterial.getId());
		return tipoMaterial;
	}
}
