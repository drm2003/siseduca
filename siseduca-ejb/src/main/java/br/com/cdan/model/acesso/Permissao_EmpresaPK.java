package br.com.cdan.model.acesso;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Permissao_EmpresaPK implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Long permissao;

	private Long empresa;

	public Permissao_EmpresaPK() {
	}

	public Permissao_EmpresaPK(Long permissao, Long empresa) {
		this.permissao = permissao;
		this.empresa = empresa;
	}

	public Long getPermissao() {
		return permissao;
	}

	public void setPermissao(Long permissao) {
		this.permissao = permissao;
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
		result = prime * result + ((permissao == null) ? 0 : permissao.hashCode());
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
		Permissao_EmpresaPK other = (Permissao_EmpresaPK) obj;
		if (empresa == null) {
			if (other.empresa != null)
				return false;
		} else if (!empresa.equals(other.empresa))
			return false;
		if (permissao == null) {
			if (other.permissao != null)
				return false;
		} else if (!permissao.equals(other.permissao))
			return false;
		return true;
	}

}
