package br.com.cdan.model.pedagogico.contrato;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Matricula_DisciplinaMatricula")
public class Matricula_DisciplinaMatricula implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@EmbeddedId
	private Matricula_DisciplinaMatriculaPK id;

	@Column(name = "quantidadeNotas")
	private Long quantidadeNotas;

	@Column(name = "quantidadeRecuperacao")
	private Long quantidadeRecuperacao;

	@Column(name = "notas")
	private BigDecimal[] notas;

	@Column(name = "recuperacao")
	private BigDecimal[] recuperacao;

	@Column(name = "observacao")
	private String observacao;

	public Matricula_DisciplinaMatriculaPK getId() {
		return id;
	}

	public void setId(Matricula_DisciplinaMatriculaPK id) {
		this.id = id;
	}

	public Long getQuantidadeNotas() {
		return quantidadeNotas;
	}

	public void setQuantidadeNotas(Long quantidadeNotas) {
		this.quantidadeNotas = quantidadeNotas;
	}

	public Long getQuantidadeRecuperacao() {
		return quantidadeRecuperacao;
	}

	public void setQuantidadeRecuperacao(Long quantidadeRecuperacao) {
		this.quantidadeRecuperacao = quantidadeRecuperacao;
	}

	public BigDecimal[] getNotas() {
		return notas;
	}

	public void setNotas(BigDecimal[] notas) {
		this.notas = notas;
	}

	public BigDecimal[] getRecuperacao() {
		return recuperacao;
	}

	public void setRecuperacao(BigDecimal[] recuperacao) {
		this.recuperacao = recuperacao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
}
