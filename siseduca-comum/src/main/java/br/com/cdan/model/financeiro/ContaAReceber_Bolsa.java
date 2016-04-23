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
@Table(name = "ContaAReceber_Bolsa")
public class ContaAReceber_Bolsa implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@EmbeddedId
	private ContaAReceber_BolsaPK id;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_contaAReceber")
	private ContaAReceber contaAReceber;

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

	@Column(name = "ativo")
	private Boolean ativo;

	public ContaAReceber_BolsaPK getId() {
		return id;
	}

	public void setId(ContaAReceber_BolsaPK id) {
		this.id = id;
	}

	public ContaAReceber getContaAReceber() {
		return contaAReceber;
	}

	public void setContaAReceber(ContaAReceber contaAReceber) {
		this.contaAReceber = contaAReceber;
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
		result = prime * result + ((bolsa == null) ? 0 : bolsa.hashCode());
		result = prime * result + ((contaAReceber == null) ? 0 : contaAReceber.hashCode());
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
		ContaAReceber_Bolsa other = (ContaAReceber_Bolsa) obj;
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
		if (percentual == null) {
			if (other.percentual != null)
				return false;
		} else if (!percentual.equals(other.percentual))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ContaAReceber_Bolsa [id=" + id + ", contaAReceber=" + contaAReceber + ", bolsa=" + bolsa
				+ ", lancarParcelasComDesconto=" + lancarParcelasComDesconto + ", percentual=" + percentual
				+ ", motivoDescontoManual=" + motivoDescontoManual + "]";
	}
}
