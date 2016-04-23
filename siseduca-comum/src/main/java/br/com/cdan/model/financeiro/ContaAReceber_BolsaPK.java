package br.com.cdan.model.financeiro;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ContaAReceber_BolsaPK implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Long contaAReceber;
	private Long bolsa;

	public ContaAReceber_BolsaPK() {
	}

	public ContaAReceber_BolsaPK(Long contaAReceber, Long bolsa) {
		this.contaAReceber = contaAReceber;
		this.bolsa = bolsa;
	}

	public Long getBolsa() {
		return bolsa;
	}

	public void setBolsa(Long bolsa) {
		this.bolsa = bolsa;
	}

	public Long getContaAReceber() {
		return contaAReceber;
	}

	public void setContaAReceber(Long contaAReceber) {
		this.contaAReceber = contaAReceber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bolsa == null) ? 0 : bolsa.hashCode());
		result = prime * result + ((contaAReceber == null) ? 0 : contaAReceber.hashCode());
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
		ContaAReceber_BolsaPK other = (ContaAReceber_BolsaPK) obj;
		if (bolsa == null) {
			if (other.bolsa != null)
				return false;
		} else if (!bolsa.equals(other.bolsa))
			return false;
		if (contaAReceber == null) {
			if (other.contaAReceber != null)
				return false;
		} else if (!contaAReceber.equals(other.contaAReceber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ContaAReceber_BolsaPK [contaAReceber=" + contaAReceber + ", bolsa=" + bolsa + "]";
	}

}
