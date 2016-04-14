package br.com.cdan.model.estoque;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.cdan.model.empresa.Empresa;

@Entity
@Table(name = "Item_Empresa")
public class Item_Empresa implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@EmbeddedId
	private Item_EmpresaPK id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_item", nullable = false)
	private Item item;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_empresa", nullable = false)
	private Empresa empresa;

	@Column(name = "utiliza")
	private Boolean utiliza;

	@Column(name = "valorCusto")
	private BigDecimal valorCusto;

	@Column(name = "valorVenda")
	private BigDecimal valorVenda;

	@Column(name = "estMaximo")
	private BigDecimal estMaximo;

	@Column(name = "estMinimo")
	private BigDecimal estMinimo;

	@Column(name = "ativo")
	private Boolean ativo;

	public Item_Empresa() {
	}

	public Item_EmpresaPK getId() {
		return id;
	}

	public void setId(Item_EmpresaPK id) {
		this.id = id;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Boolean getUtiliza() {
		return utiliza;
	}

	public void setUtiliza(Boolean utiliza) {
		this.utiliza = utiliza;
	}

	public BigDecimal getValorCusto() {
		return valorCusto;
	}

	public void setValorCusto(BigDecimal valorCusto) {
		this.valorCusto = valorCusto;
	}

	public BigDecimal getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(BigDecimal valorVenda) {
		this.valorVenda = valorVenda;
	}

	public BigDecimal getEstMaximo() {
		return estMaximo;
	}

	public void setEstMaximo(BigDecimal estMaximo) {
		this.estMaximo = estMaximo;
	}

	public BigDecimal getEstMinimo() {
		return estMinimo;
	}

	public void setEstMinimo(BigDecimal estMinimo) {
		this.estMinimo = estMinimo;
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Item_Empresa other = (Item_Empresa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Item_Empresa [id=" + id + ", item=" + item + ", empresa=" + empresa + ", utiliza=" + utiliza
				+ ", valorCusto=" + valorCusto + ", valorVenda=" + valorVenda + ", estMaximo=" + estMaximo
				+ ", estMinimo=" + estMinimo + ", ativo=" + ativo + "]";
	}
}
