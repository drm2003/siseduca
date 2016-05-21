package br.com.cdan.model.pessoa;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.cdan.model.empresa.Empresa;
import br.com.cdan.model.geral.Endereco;
import br.com.cdan.model.geral.Telefone;
import br.com.cdan.model.geral.TipoDeResponsavel;

@Entity
@Table(name = "Responsavel")
public class Responsavel implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "loginPortal")
	private String loginPortal;

	@Column(name = "senhaPortal")
	private String senhaPortal;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_pessoa")
	private Pessoa pessoa;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "tipoDeResponsavel")
	private TipoDeResponsavel tipoDeResponsavel;

	@OneToMany(mappedBy = "responsavel", targetEntity = Follow.class, fetch = FetchType.LAZY)
	private Set<Follow> follows;

	@OneToMany(mappedBy = "responsavel", targetEntity = Telefone.class, fetch = FetchType.LAZY)
	private Set<Telefone> telefones;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_aluno")
	private Aluno aluno;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "responsavelFinanceiro")
	private Aluno responsavelFinanceiro;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "responsavelDidatico")
	private Aluno responsavelDidatico;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "responsavelEmpresa")
	private Aluno responsavelEmpresa;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;

	@Column(name = "observacoes")
	private String observacoes;

	@OneToMany(mappedBy = "responsavel", targetEntity = Endereco.class, fetch = FetchType.LAZY)
	private Set<Endereco> enderecos;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_alunoInteressado")
	private AlunoInteressado alunoInteressado;

	@NotNull
	@Column(name = "ativo")
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public TipoDeResponsavel getTipoDeResponsavel() {
		return tipoDeResponsavel;
	}

	public void setTipoDeResponsavel(TipoDeResponsavel tipoDeResponsavel) {
		this.tipoDeResponsavel = tipoDeResponsavel;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getLoginPortal() {
		return loginPortal;
	}

	public void setLoginPortal(String loginPortal) {
		this.loginPortal = loginPortal;
	}

	public String getSenhaPortal() {
		return senhaPortal;
	}

	public void setSenhaPortal(String senhaPortal) {
		this.senhaPortal = senhaPortal;
	}

	public Set<Follow> getFollows() {
		return follows;
	}

	public void setFollows(Set<Follow> follows) {
		this.follows = follows;
	}

	public Set<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<Telefone> telefones) {
		this.telefones = telefones;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Set<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(Set<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public AlunoInteressado getAlunoInteressado() {
		return alunoInteressado;
	}

	public void setAlunoInteressado(AlunoInteressado alunoInteressado) {
		this.alunoInteressado = alunoInteressado;
	}

	public Aluno getResponsavelFinanceiro() {
		return responsavelFinanceiro;
	}

	public void setResponsavelFinanceiro(Aluno responsavelFinanceiro) {
		this.responsavelFinanceiro = responsavelFinanceiro;
	}

	public Aluno getResponsavelDidatico() {
		return responsavelDidatico;
	}

	public void setResponsavelDidatico(Aluno responsavelDidatico) {
		this.responsavelDidatico = responsavelDidatico;
	}

	public Aluno getResponsavelEmpresa() {
		return responsavelEmpresa;
	}

	public void setResponsavelEmpresa(Aluno responsavelEmpresa) {
		this.responsavelEmpresa = responsavelEmpresa;
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
		result = prime * result + ((aluno == null) ? 0 : aluno.hashCode());
		result = prime * result + ((tipoDeResponsavel == null) ? 0 : tipoDeResponsavel.hashCode());
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
		Responsavel other = (Responsavel) obj;
		if (aluno == null) {
			if (other.aluno != null)
				return false;
		} else if (!aluno.equals(other.aluno))
			return false;
		if (tipoDeResponsavel == null) {
			if (other.tipoDeResponsavel != null)
				return false;
		} else if (!tipoDeResponsavel.equals(other.tipoDeResponsavel))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Responsavel [pessoa=" + pessoa + ", tipoDeResponsavel=" + tipoDeResponsavel + ", aluno=" + aluno
				+ ", empresa=" + empresa + "]";
	}
}
