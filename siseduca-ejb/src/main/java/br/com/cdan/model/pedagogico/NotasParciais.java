package br.com.cdan.model.pedagogico;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.cdan.comum.EnumMediaNotasParciais;

@Entity
@Table(name = "SistemaDeAvaliacao")
public class NotasParciais implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "mediaNotasParciais")
	private EnumMediaNotasParciais enumMediaNotasParciais;

	@Column(name = "desconsideraAvaliacao")
	private Boolean desconsideraAvaliacao;

	@Column(name = "utilizaRecuperacaoParcial")
	private Boolean utilizaRecuperacaoParcial;

	@Column(name = "utilizaAgrupamentoDeAvaliacoes")
	private Boolean utilizaAgrupamentoDeAvaliacoes;

	@OneToOne(mappedBy = "notasParciais")
	private SistemaDeAvaliacao sistemaDeAvaliacao;

	@Column(name = "ativo")
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EnumMediaNotasParciais getEnumMediaNotasParciais() {
		return enumMediaNotasParciais;
	}

	public void setEnumMediaNotasParciais(
			EnumMediaNotasParciais enumMediaNotasParciais) {
		this.enumMediaNotasParciais = enumMediaNotasParciais;
	}

	public Boolean getDesconsideraAvaliacao() {
		return desconsideraAvaliacao;
	}

	public void setDesconsideraAvaliacao(Boolean desconsideraAvaliacao) {
		this.desconsideraAvaliacao = desconsideraAvaliacao;
	}

	public Boolean getUtilizaRecuperacaoParcial() {
		return utilizaRecuperacaoParcial;
	}

	public void setUtilizaRecuperacaoParcial(Boolean utilizaRecuperacaoParcial) {
		this.utilizaRecuperacaoParcial = utilizaRecuperacaoParcial;
	}

	public Boolean getUtilizaAgrupamentoDeAvaliacoes() {
		return utilizaAgrupamentoDeAvaliacoes;
	}

	public void setUtilizaAgrupamentoDeAvaliacoes(
			Boolean utilizaAgrupamentoDeAvaliacoes) {
		this.utilizaAgrupamentoDeAvaliacoes = utilizaAgrupamentoDeAvaliacoes;
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
		result = prime
				* result
				+ ((enumMediaNotasParciais == null) ? 0
						: enumMediaNotasParciais.hashCode());
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
		NotasParciais other = (NotasParciais) obj;
		if (enumMediaNotasParciais != other.enumMediaNotasParciais)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NotasParciais [enumMediaNotasParciais="
				+ enumMediaNotasParciais + ", desconsideraAvaliacao="
				+ desconsideraAvaliacao + ", utilizaRecuperacaoParcial="
				+ utilizaRecuperacaoParcial
				+ ", utilizaAgrupamentoDeAvaliacoes="
				+ utilizaAgrupamentoDeAvaliacoes + ", sistemaDeAvaliacao="
				+ sistemaDeAvaliacao + "]";
	}
}
