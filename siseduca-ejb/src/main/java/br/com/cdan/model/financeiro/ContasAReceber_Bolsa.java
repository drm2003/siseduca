package br.com.cdan.model.financeiro;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

@Entity
@Table(name = "ContasAReceber_Bolsa")
public class ContasAReceber_Bolsa implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@EmbeddedId
	private ContasAReceber_BolsaPK id;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_contasAReceber")
	private ContasAReceber contasAReceber;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_bolsa")
	private Bolsa bolsa;

	@Column(name = "lancarParcelasComDesconto")
	private Boolean lancarParcelasComDesconto;

	@DecimalMin(value = "0")
	@DecimalMax(value = "100")
	@Column(name = "percentual")
	private Long percentual;

	@Column(name = "motivoDescontoManual")
	private String motivoDescontoManual;

	public ContasAReceber_BolsaPK getId() {
		return id;
	}

	public void setId(ContasAReceber_BolsaPK id) {
		this.id = id;
	}

	public ContasAReceber getContasAReceber() {
		return contasAReceber;
	}

	public void setContasAReceber(ContasAReceber contasAReceber) {
		this.contasAReceber = contasAReceber;
	}

	public Bolsa getBolsa() {
		return bolsa;
	}

	public void setBolsa(Bolsa bolsa) {
		this.bolsa = bolsa;
	}

	public Boolean getLancarParcelasComDesconto() {
		return lancarParcelasComDesconto;
	}

	public void setLancarParcelasComDesconto(Boolean lancarParcelasComDesconto) {
		this.lancarParcelasComDesconto = lancarParcelasComDesconto;
	}

	public Long getPercentual() {
		return percentual;
	}

	public void setPercentual(Long percentual) {
		this.percentual = percentual;
	}

	public String getMotivoDescontoManual() {
		return motivoDescontoManual;
	}

	public void setMotivoDescontoManual(String motivoDescontoManual) {
		this.motivoDescontoManual = motivoDescontoManual;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bolsa == null) ? 0 : bolsa.hashCode());
		result = prime * result + ((contasAReceber == null) ? 0 : contasAReceber.hashCode());
		result = prime * result + ((percentual == null) ? 0 : percentual.hashCode());
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
		ContasAReceber_Bolsa other = (ContasAReceber_Bolsa) obj;
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
		if (percentual == null) {
			if (other.percentual != null)
				return false;
		} else if (!percentual.equals(other.percentual))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ContasAReceber_Bolsa [id=" + id + ", contasAReceber=" + contasAReceber + ", bolsa=" + bolsa
				+ ", lancarParcelasComDesconto=" + lancarParcelasComDesconto + ", percentual=" + percentual
				+ ", motivoDescontoManual=" + motivoDescontoManual + "]";
	}
}
