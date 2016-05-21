package br.com.cdan.model.pessoa;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.cdan.comum.EnumTurnoPretendido;
import br.com.cdan.model.geral.SituacaoDoAluno;

@Entity
@Table(name = "AlunoInteressado")
public class AlunoInteressado implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "situacaoDoAluno")
	private SituacaoDoAluno situacaoDoAluno;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "pessoa", nullable = false)
	private Pessoa pessoa;

	@Enumerated(EnumType.STRING)
	@Column(name = "turnoPretendido")
	private EnumTurnoPretendido turnoPretendido;

	@Column(name = "receberEmail")
	private Boolean receberEmail;

	@Column(name = "receberSMS")
	private Boolean receberSMS;

	@OneToMany(mappedBy = "alunoInteressado", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Responsavel> responsaveis;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "interessado")
	private Interessado interessado;

	@OneToMany(mappedBy = "alunoInteressado")
	private Set<Follow> follows;

	@Column(name = "observacoes")
	private String observacoes;

	@NotNull
	@Column(name = "ativo")
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SituacaoDoAluno getSituacaoDoAluno() {
		return situacaoDoAluno;
	}

	public void setSituacaoDoAluno(SituacaoDoAluno situacaoDoAluno) {
		this.situacaoDoAluno = situacaoDoAluno;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public EnumTurnoPretendido getTurnoPretendido() {
		return turnoPretendido;
	}

	public void setTurnoPretendido(EnumTurnoPretendido turnoPretendido) {
		this.turnoPretendido = turnoPretendido;
	}

	public Boolean getReceberEmail() {
		return receberEmail;
	}

	public void setReceberEmail(Boolean receberEmail) {
		this.receberEmail = receberEmail;
	}

	public Boolean getReceberSMS() {
		return receberSMS;
	}

	public void setReceberSMS(Boolean receberSMS) {
		this.receberSMS = receberSMS;
	}

	public Set<Responsavel> getResponsaveis() {
		return responsaveis;
	}

	public void setResponsaveis(Set<Responsavel> responsaveis) {
		this.responsaveis = responsaveis;
	}

	public Interessado getInteressado() {
		return interessado;
	}

	public void setInteressado(Interessado interessado) {
		this.interessado = interessado;
	}

	public Set<Follow> getFollows() {
		return follows;
	}

	public void setFollows(Set<Follow> follows) {
		this.follows = follows;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
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
		result = prime * result + ((pessoa == null) ? 0 : pessoa.hashCode());
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
		AlunoInteressado other = (AlunoInteressado) obj;
		if (pessoa == null) {
			if (other.pessoa != null)
				return false;
		} else if (!pessoa.equals(other.pessoa))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AlunoInteressado [situacaoDoAluno=" + situacaoDoAluno + ", pessoa=" + pessoa + ", turnoPretendido="
				+ turnoPretendido + ", receberEmail=" + receberEmail + ", receberSMS=" + receberSMS + ", responsaveis="
				+ responsaveis + ", interessado=" + interessado + ", observacoes=" + observacoes + "]";
	}
}
