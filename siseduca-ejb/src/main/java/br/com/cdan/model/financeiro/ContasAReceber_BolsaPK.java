package br.com.cdan.model.financeiro;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ContasAReceber_BolsaPK implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Long contasAReceber;
	private Long bolsa;

	public ContasAReceber_BolsaPK() {
	}

	public ContasAReceber_BolsaPK(Long contasAReceber, Long bolsa) {
		this.contasAReceber = contasAReceber;
		this.bolsa = bolsa;
	}

	public Long getBolsa() {
		return bolsa;
	}

	public void setBolsa(Long bolsa) {
		this.bolsa = bolsa;
	}

	public Long getContasAReceber() {
		return contasAReceber;
	}

	public void setContasAReceber(Long contasAReceber) {
		this.contasAReceber = contasAReceber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bolsa == null) ? 0 : bolsa.hashCode());
		result = prime * result + ((contasAReceber == null) ? 0 : contasAReceber.hashCode());
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
		ContasAReceber_BolsaPK other = (ContasAReceber_BolsaPK) obj;
		if (bolsa == null) {
			if (other.bolsa != null)
				return false;
		} else if (!bolsa.equals(other.bolsa))
			return false;
		if (contasAReceber == null) {
			if (other.contasAReceber != null)
				return false;
		} else if (!contasAReceber.equals(other.contasAReceber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ContasAReceber_BolsaPK [contasAReceber=" + contasAReceber + ", bolsa=" + bolsa + "]";
	}

}
