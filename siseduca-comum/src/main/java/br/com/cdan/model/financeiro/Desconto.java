package br.com.cdan.model.financeiro;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Desconto")
public class Desconto implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "diasAntesDoVencimento")
	private Long diasAntesDoVencimento;

	@Column(name = "percentualDesconto")
	private BigDecimal percentualDesconto;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_bolsa")
	private Bolsa bolsa;

	@NotNull
	@Column(name = "ativo")
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDiasAntesDoVencimento() {
		return diasAntesDoVencimento;
	}

	public void setDiasAntesDoVencimento(Long diasAntesDoVencimento) {
		this.diasAntesDoVencimento = diasAntesDoVencimento;
	}

	public BigDecimal getPercentualDesconto() {
		return percentualDesconto;
	}

	public void setPercentualDesconto(BigDecimal percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}

	public Bolsa getBolsa() {
		return bolsa;
	}

	public void setBolsa(Bolsa bolsa) {
		this.bolsa = bolsa;
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
		Desconto other = (Desconto) obj;
		if (bolsa == null) {
			if (other.bolsa != null)
				return false;
		} else if (!bolsa.equals(other.bolsa))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Desconto [diasAntesDoVencimento=" + diasAntesDoVencimento + ", percentualDesconto=" + percentualDesconto
				+ ", bolsa=" + bolsa + "]";
	}
}
