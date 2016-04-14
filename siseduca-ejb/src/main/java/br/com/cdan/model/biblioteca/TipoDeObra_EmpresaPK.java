package br.com.cdan.model.biblioteca;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class TipoDeObra_EmpresaPK implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long tipoDeObra;

	private Long empresa;

	public TipoDeObra_EmpresaPK() {
	}

	public TipoDeObra_EmpresaPK(Long tipoDeObra, Long empresa) {
		super();
		this.tipoDeObra = tipoDeObra;
		this.empresa = empresa;
	}

	public Long getTipoDeObra() {
		return tipoDeObra;
	}

	public void setTipoDeObra(Long tipoDeObra) {
		this.tipoDeObra = tipoDeObra;
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
		result = prime * result + ((tipoDeObra == null) ? 0 : tipoDeObra.hashCode());
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
		TipoDeObra_EmpresaPK other = (TipoDeObra_EmpresaPK) obj;
		if (empresa == null) {
			if (other.empresa != null)
				return false;
		} else if (!empresa.equals(other.empresa))
			return false;
		if (tipoDeObra == null) {
			if (other.tipoDeObra != null)
				return false;
		} else if (!tipoDeObra.equals(other.tipoDeObra))
			return false;
		return true;
	}
}
