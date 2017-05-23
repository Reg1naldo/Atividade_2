package br.gov.sp.fatec;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.model.Material;
import br.gov.sp.fatec.model.TipoMaterial;
import br.gov.sp.fatec.repository.MaterialRepository;
import br.gov.sp.fatec.repository.ReceitaRepository;
import br.gov.sp.fatec.repository.TipoMaterialRepository;
import br.gov.sp.fatec.service.MaterialService;
import br.gov.sp.fatec.service.MaterialServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@Rollback
@Transactional
@ContextConfiguration(locations = {"classpath:/applicationContext.xml"} )
public class CadastroMaterialServiceTest {
	private static final String TIPONOME = "elemento X";
	private static final String NOMEMATERIAL = "material ABC";
	private static final String DESCRICAOMATERIAL = "Material encontrado em x produtos";
	
	@Autowired
	private MaterialService cadMatSer;
	@Autowired
	private TipoMaterialRepository tipoMaterialRepo;
	@Autowired
	private MaterialRepository materialRepo;
	
	
	public void setCadastroMaterialService(MaterialService cadMatSer){
		this.cadMatSer = cadMatSer;
	}
	
	public void setMaterialRepo(MaterialRepository materialRepo){
		this.materialRepo = materialRepo;
	}
	
	public void setTipoMaterialRepo(TipoMaterialRepository tipoMaterialRepo){
		this.tipoMaterialRepo = tipoMaterialRepo;
	}
	
	@Test
	public void testeCadastroMaterialService(){		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    	
		cadMatSer = (MaterialService) context.getBean("cadastroMaterial");
    	
    	//Cria um tipo de material
    	TipoMaterial tipoMaterial = new TipoMaterial();
    	tipoMaterial.setNome(TIPONOME);
    	System.out.println("tipoMaterial: " +tipoMaterial.getNome());
    	tipoMaterialRepo.save(tipoMaterial);
		
		//Cria um material com tipo nao salvo
    	Material material = new Material();
    	material.setNome(NOMEMATERIAL);
    	material.setDescricao(DESCRICAOMATERIAL);
    	material.setTipo(tipoMaterial);
    	materialRepo.save(material);
		
    	
		try {
			cadMatSer.cadastroMaterial(material);
			System.out.println("Material Cadastrado através do Service");
		}
		catch(Exception e) {
			System.out.println("Cadastro Material: Erro inesperado! Mas o Rollback foi realizado!");
			//e.printStackTrace();
		}
		assertTrue(material.getId()!=null);
	}
}
