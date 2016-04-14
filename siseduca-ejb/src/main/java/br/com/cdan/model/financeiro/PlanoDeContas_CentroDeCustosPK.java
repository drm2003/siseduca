package br.com.cdan.model.financeiro;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class PlanoDeContas_CentroDeCustosPK implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Long planoDeContas;

	private Long centroDeCustos;

	public PlanoDeContas_CentroDeCustosPK() {
	}

	public PlanoDeContas_CentroDeCustosPK(Long planoDeContas, Long centroDeCustos) {
		this.planoDeContas = planoDeContas;
		this.centroDeCustos = centroDeCustos;
	}

	public Long getPlanoDeContas() {
		return planoDeContas;
	}

	public void setPlanoDeContas(Long planoDeContas) {
		this.planoDeContas = planoDeContas;
	}

	public Long getCentroDeCustos() {
		return centroDeCustos;
	}

	public void setCentroDeCustos(Long centroDeCustos) {
		this.centroDeCustos = centroDeCustos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((centroDeCustos == null) ? 0 : centroDeCustos.hashCode());
		result = prime * result + ((planoDeContas == null) ? 0 : planoDeContas.hashCode());
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
		PlanoDeContas_CentroDeCustosPK other = (PlanoDeContas_CentroDeCustosPK) obj;
		if (centroDeCustos == null) {
			if (other.centroDeCustos != null)
				return false;
		} else if (!centroDeCustos.equals(other.centroDeCustos))
			return false;
		if (planoDeContas == null) {
			if (other.planoDeContas != null)
				return false;
		} else if (!planoDeContas.equals(other.planoDeContas))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PlanoDeContas_CentroDeCustosPK [planoDeContas=" + planoDeContas + ", centroDeCustos=" + centroDeCustos
				+ "]";
	}
}
