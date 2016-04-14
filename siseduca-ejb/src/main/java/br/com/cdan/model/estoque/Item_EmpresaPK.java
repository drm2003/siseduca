package br.com.cdan.model.estoque;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Item_EmpresaPK implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	private Long item;

	private Long empresa;

	public Item_EmpresaPK() {
	}

	public Item_EmpresaPK(Long item, Long empresa) {
		this.item = item;
		this.empresa = empresa;
	}

	public Long getItem() {
		return item;
	}

	public void setItem(Long item) {
		this.item = item;
	}

	public Long getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Long empresa) {
		this.empresa = empresa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
		result = prime * result + ((item == null) ? 0 : item.hashCode());
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
		Item_EmpresaPK other = (Item_EmpresaPK) obj;
		if (empresa == null) {
			if (other.empresa != null)
				return false;
		} else if (!empresa.equals(other.empresa))
			return false;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		return true;
	}
}
