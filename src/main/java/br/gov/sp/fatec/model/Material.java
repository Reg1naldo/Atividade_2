package br.gov.sp.fatec.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MTR_MATERIAL")
public class Material {
	@Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "MTR_ID")
	private Long id;
    
    @Column(name = "MTR_NOME", length = 30, nullable = false)
    private String nome;
    
    @Column(name = "MTR_DESCRICAO", length = 50, nullable = false)
    private String descricao;
    
    @Column(name = "MTR_DATAINCLUSAO")
    private Date dataInclusao;
    
    @ManyToOne
    @JoinColumn(name = "TPO_ID")
    private TipoMaterial tipo;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "RCM_RECEITAMATERIAL", 
    	joinColumns = { @JoinColumn(name = "MTR_ID") }, 
    	inverseJoinColumns = { @JoinColumn(name = "RCT_ID") })
    private List<Receita> receitas;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public TipoMaterial getTipo() {
		return tipo;
	}

	public void setTipo(TipoMaterial tipo) {
		this.tipo = tipo;
	}
    
    

}