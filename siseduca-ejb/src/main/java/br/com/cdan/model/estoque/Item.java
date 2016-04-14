package br.com.cdan.model.estoque;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Item")
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "descricao", length = 250, nullable = false, unique = true)
	private String descricao;

	@Column(name = "codigoBarra")
	private String codigoBarra;

	@ManyToOne
	@JoinColumn(name = "id_UnidadeDeMedida")
	private UnidadeDeMedida unidadeDeMedida;

	@ManyToOne
	@JoinColumn(name = "id_Finalidade")
	private Finalidade finalidade;

	@ManyToOne
	@JoinColumn(name = "id_NCM")
	private NCM ncm;

	@ManyToOne
	@JoinColumn(name = "id_ClassificacaoDeItens")
	private ClassificacaoDeItens classificacaoDeItens;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "item", targetEntity = Item_Empresa.class)
	private Set<Item_Empresa> item_Empresa;

	@Column(name = "observacoes")
	private String observacoes;

	@Column(name = "ativo")
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCodigoBarra() {
		return codigoBarra;
	}

	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}

	public UnidadeDeMedida getUnidadeDeMedida() {
		return unidadeDeMedida;
	}

	public void setUnidadeDeMedida(UnidadeDeMedida unidadeDeMedida) {
		this.unidadeDeMedida = unidadeDeMedida;
	}

	public Finalidade getFinalidade() {
		return finalidade;
	}

	public void setFinalidade(Finalidade finalidade) {
		this.finalidade = finalidade;
	}

	public NCM getNcm() {
		return ncm;
	}

	public void setNcm(NCM ncm) {
		this.ncm = ncm;
	}

	public ClassificacaoDeItens getClassificacaoDeItens() {
		return classificacaoDeItens;
	}

	public void setClassificacaoDeItens(
			ClassificacaoDeItens classificacaoDeItens) {
		this.classificacaoDeItens = classificacaoDeItens;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Set<Item_Empresa> getItem_Empresa() {
		return item_Empresa;
	}

	public void setItem_Empresa(Set<Item_Empresa> item_Empresa) {
		this.item_Empresa = item_Empresa;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codigoBarra == null) ? 0 : codigoBarra.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (codigoBarra == null) {
			if (other.codigoBarra != null)
				return false;
		} else if (!codigoBarra.equals(other.codigoBarra))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Item [descricao=" + descricao + ", codigoBarra=" + codigoBarra
				+ ", unidadeMedida=" + unidadeDeMedida + ", finalidade="
				+ finalidade + ", ncm=" + ncm + ", classificacaoDeItens="
				+ classificacaoDeItens + ", Empresa=" + item_Empresa
				+ ", observacoes=" + observacoes + "]";
	}
}