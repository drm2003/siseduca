package br.com.cdan.model.financeiro;

import java.io.Serializable;
import java.math.BigDecimal;

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
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "PlanoDeContas_CentroDeCustos")
public class PlanoDeContas_CentroDeCustos implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@EmbeddedId
	private PlanoDeContas_CentroDeCustosPK id;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_PlanoDeContas")
	private PlanoDeContas planoDeContas;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_centroDeCustos")
	private CentroDeCustos centroDeCustos;

	@NotNull
	@NotBlank
	@NotEmpty
	@DecimalMin(value = "0")
	@DecimalMax(value = "100")
	@Column(name = "percentual")
	private BigDecimal percentual;

	public PlanoDeContas_CentroDeCustosPK getId() {
		return id;
	}

	public void setId(PlanoDeContas_CentroDeCustosPK id) {
		this.id = id;
	}

	public CentroDeCustos getCentroDeCustos() {
		return centroDeCustos;
	}

	public void setCentroDeCustos(CentroDeCustos centroDeCustos) {
		this.centroDeCustos = centroDeCustos;
	}

	public PlanoDeContas getPlanoDeContas() {
		return planoDeContas;
	}

	public void setPlanoDeContas(PlanoDeContas planoDeContas) {
		this.planoDeContas = planoDeContas;
	}

	public BigDecimal getPercentual() {
		return percentual;
	}

	public void setPercentual(BigDecimal percentual) {
		this.percentual = percentual;
	}
}
