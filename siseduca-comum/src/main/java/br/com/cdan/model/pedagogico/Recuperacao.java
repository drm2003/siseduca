package br.com.cdan.model.pedagogico;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.cdan.comum.EnumTipoDeRecuperacao;

@Entity
@Table(name = "Recuperacao")
public class Recuperacao implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipoDeRecuperacao")
	private EnumTipoDeRecuperacao enumTipoDeRecuperacao;

	@Column(name = "pesoDaRecuperacao")
	private Long pesoDaRecuperacao;

	@Column(name = "valorMediaAposRecuperacao")
	private BigDecimal valorMediaAposRecuperacao;

	@OneToOne
	@JoinColumn(name = "mediaAposRecuperacao")
	private MediaAposRecuperacao mediaAposRecuperacao;

	@OneToOne(mappedBy = "recuperacao")
	private SistemaDeAvaliacao sistemaDeAvaliacao;

	@NotNull
	@Column(name = "ativo")
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EnumTipoDeRecuperacao getEnumTipoDeRecuperacao() {
		return enumTipoDeRecuperacao;
	}

	public void setEnumTipoDeRecuperacao(EnumTipoDeRecuperacao enumTipoDeRecuperacao) {
		this.enumTipoDeRecuperacao = enumTipoDeRecuperacao;
	}

	public Long getPesoDaRecuperacao() {
		return pesoDaRecuperacao;
	}

	public void setPesoDaRecuperacao(Long pesoDaRecuperacao) {
		this.pesoDaRecuperacao = pesoDaRecuperacao;
	}

	public BigDecimal getValorMediaAposRecuperacao() {
		return valorMediaAposRecuperacao;
	}

	public void setValorMediaAposRecuperacao(BigDecimal valorMediaAposRecuperacao) {
		this.valorMediaAposRecuperacao = valorMediaAposRecuperacao;
	}

	public MediaAposRecuperacao getMediaAposRecuperacao() {
		return mediaAposRecuperacao;
	}

	public void setMediaAposRecuperacao(MediaAposRecuperacao mediaAposRecuperacao) {
		this.mediaAposRecuperacao = mediaAposRecuperacao;
	}

	public SistemaDeAvaliacao getSistemaDeAvaliacao() {
		return sistemaDeAvaliacao;
	}

	public void setSistemaDeAvaliacao(SistemaDeAvaliacao sistemaDeAvaliacao) {
		this.sistemaDeAvaliacao = sistemaDeAvaliacao;
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
		result = prime * result + ((enumTipoDeRecuperacao == null) ? 0 : enumTipoDeRecuperacao.hashCode());
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
		Recuperacao other = (Recuperacao) obj;
		if (enumTipoDeRecuperacao != other.enumTipoDeRecuperacao)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Recuperacao [enumTipoDeRecuperacao=" + enumTipoDeRecuperacao + ", pesoDaRecuperacao="
				+ pesoDaRecuperacao + ", valorMediaAposRecuperacao=" + valorMediaAposRecuperacao + "]";
	}
}
