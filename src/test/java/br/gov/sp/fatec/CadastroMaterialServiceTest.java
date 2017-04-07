package br.gov.sp.fatec;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.gov.sp.fatec.model.Material;
import br.gov.sp.fatec.model.TipoMaterial;
import br.gov.sp.fatec.repository.MaterialRepository;
import br.gov.sp.fatec.repository.ReceitaRepository;
import br.gov.sp.fatec.repository.TipoMaterialRepository;
import br.gov.sp.fatec.service.CadastroMaterialService;

public class CadastroMaterialServiceTest {
	private static final String TIPONOME = "elemento X";
	private static final String NOMEMATERIAL = "material ABC";
	private static final String DESCRICAOMATERIAL = "Material encontrado em x produtos";
	
	@Autowired
	private CadastroMaterialService cadMatSer;
	@Autowired
	private ApplicationContext context;
	@Autowired
	private TipoMaterialRepository tipoMaterialRepo;
	@Autowired
	private MaterialRepository materialRepo;
	@Autowired
	private Material material;
	
	public void setCadastroMaterialService(CadastroMaterialService cadMatSer){
		this.cadMatSer = cadMatSer;
	}
	
	public void setMaterialRepo(MaterialRepository materialRepo){
		this.materialRepo = materialRepo;
	}
	
	@Test
	public void testeCadastroMaterial(){
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
    	
		cadMatSer = (CadastroMaterialService)context.getBean("cadastroMaterial");
    	
    	//Cria um tipo de material
    	TipoMaterial tipoMaterial = new TipoMaterial();
    	tipoMaterial.setNome(TIPONOME);
    	tipoMaterialRepo.save(tipoMaterial);
    	assertTrue(tipoMaterial.getId()!=null);
		
		//Cria um material com tipo nao salvo
    	
    	material.setNome(NOMEMATERIAL);
    	material.setDescricao(DESCRICAOMATERIAL);
    	material.setTipo(tipoMaterial);
    	materialRepo.save(material);
    	assertTrue(material.getId()!=null);
	}
	
	@Test
	public void testeMaterialService(){
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
    	
		cadMatSer = (CadastroMaterialService)context.getBean("cadastroMaterial");
		
		try {
			cadMatSer.cadastroMaterial(material);
			System.out.println("Material Cadastrado através do Service");
		}
		catch(Exception e) {
			System.out.println("Cadastro Material: Erro inesperado! Mas o Rollback foi realizado!");
			
			// e.printStackTrace();
		}
		assertTrue(material.getId()!=null);
	}
}
