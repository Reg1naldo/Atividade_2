package br.gov.sp.fatec.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TPO_TIPO")
public class TipoMaterial {
	@Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "TPO_ID")
	private Long id;
    
    @Column(name = "TPO_NOME", unique=true, length = 30, nullable = false)
    private String nome;
    
    @Column(name = "TPO_DATAINCLUSAO")
    private Date dataInclusao;
    
    @OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name="TPO_ID")
    private List<Material> materiais;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public List<Material> getMateriais() {
		return materiais;
	}

	public void setMateriais(List<Material> materiais) {
		this.materiais = materiais;
	}
    
    
}
