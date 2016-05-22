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
	private PlanoDeConta planoDeContas;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_centroDeCustos")
	private CentroDeCustos centroDeCustos;

	@NotNull
	@DecimalMin(value = "0")
	@DecimalMax(value = "100")
	@Column(name = "percentual")
	private BigDecimal percentual;

	@NotNull
	@Column(name = "ativo")
	private Boolean ativo;

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

	public PlanoDeConta getPlanoDeContas() {
		return planoDeContas;
	}

	public void setPlanoDeContas(PlanoDeConta planoDeContas) {
		this.planoDeContas = planoDeContas;
	}

	public BigDecimal getPercentual() {
		return percentual;
	}

	public void setPercentual(BigDecimal percentual) {
		this.percentual = percentual;
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
		result = prime * result + ((centroDeCustos == null) ? 0 : centroDeCustos.hashCode());
		result = prime * result + ((percentual == null) ? 0 : percentual.hashCode());
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
		PlanoDeContas_CentroDeCustos other = (PlanoDeContas_CentroDeCustos) obj;
		if (centroDeCustos == null) {
			if (other.centroDeCustos != null)
				return false;
		} else if (!centroDeCustos.equals(other.centroDeCustos))
			return false;
		if (percentual == null) {
			if (other.percentual != null)
				return false;
		} else if (!percentual.equals(other.percentual))
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
		return "PlanoDeContas_CentroDeCustos [id=" + id + ", planoDeContas=" + planoDeContas + ", centroDeCustos="
				+ centroDeCustos + ", percentual=" + percentual + ", ativo=" + ativo + "]";
	}
}
