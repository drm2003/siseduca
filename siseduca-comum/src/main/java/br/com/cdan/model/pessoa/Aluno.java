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
import javax.persistence.JoinColumns;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.cdan.model.financeiro.ContasAPagar;
import br.com.cdan.model.financeiro.ContasAReceber;
import br.com.cdan.model.geral.SituacaoDoAluno;
import br.com.cdan.model.pedagogico.contrato.Dependencia;
import br.com.cdan.model.pedagogico.contrato.EstagioMonografia;
import br.com.cdan.model.pedagogico.contrato.Matricula;
import br.com.cdan.model.pedagogico.contrato.Ocorrencia;
import br.com.cdan.model.pedagogico.curso.Turma_Disciplina;
import br.com.cdan.model.pedagogico.diario.DiarioDeAula;

@Entity
@Table(name = "Aluno")
public class Aluno implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "numeroMatricula")
	private String numeroMatricula;

	@OneToMany(mappedBy = "aluno")
	private Set<Matricula> matriculas;

	@Column(name = "ra")
	private String ra;

	@Column(name = "codigoDeBarras")
	private String codigoDeBarras;

	@Column(name = "loginPortal")
	private String loginPortal;

	@Column(name = "senha")
	private String senha;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "situacaoDoAluno")
	private SituacaoDoAluno situacaoDoAluno;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "pessoa", nullable = false)
	private Pessoa pessoa;

	@Column(name = "receberEmail")
	private Boolean receberEmail;

	@Column(name = "receberSMS")
	private Boolean receberSMS;

	@OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Responsavel> responsaveis;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "dadoBancario")
	private DadoBancario dadoBancario;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "responsavelFinanceiro")
	private Responsavel responsavelFinanceiro;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "responsavelDidatico")
	private Responsavel responsavelDidatico;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "responsavelEmpresa")
	private Responsavel responsavelEmpresa;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "interessado")
	private Interessado interessado;

	@OneToOne(mappedBy = "aluno")
	private Dependencia dependencia;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "aluno")
	private DiarioDeAula diarioDeAula;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns(value = {
			@JoinColumn(name = "ALUNO_id_turma", referencedColumnName = "id_turma", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "ALUNO_id_disciplina", referencedColumnName = "id_disciplina", nullable = false, insertable = false, updatable = false) })
	private Turma_Disciplina turma_Disciplina;

	@OneToMany(mappedBy = "aluno")
	private Set<EstagioMonografia> estagioMonografia;

	@ManyToMany(mappedBy = "alunos", fetch = FetchType.LAZY)
	private Set<ContasAReceber> contasAReceber;

	@ManyToMany(mappedBy = "alunos", fetch = FetchType.LAZY)
	private Set<ContasAPagar> contasAPagar;

	@Column(name = "ativo")
	private Boolean ativo;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "aluno")
	private Set<Ocorrencia> ocorrencias;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroMatricula() {
		return numeroMatricula;
	}

	public void setNumeroMatricula(String numeroMatricula) {
		this.numeroMatricula = numeroMatricula;
	}

	public Set<Matricula> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(Set<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	public Dependencia getDependencia() {
		return dependencia;
	}

	public void setDependencia(Dependencia dependencia) {
		this.dependencia = dependencia;
	}

	public String getRa() {
		return ra;
	}

	public void setRa(String ra) {
		this.ra = ra;
	}

	public String getCodigoDeBarras() {
		return codigoDeBarras;
	}

	public void setCodigoDeBarras(String codigoDeBarras) {
		this.codigoDeBarras = codigoDeBarras;
	}

	public String getLoginPortal() {
		return loginPortal;
	}

	public void setLoginPortal(String loginPortal) {
		this.loginPortal = loginPortal;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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

	public DadoBancario getDadoBancario() {
		return dadoBancario;
	}

	public void setDadoBancario(DadoBancario dadoBancario) {
		this.dadoBancario = dadoBancario;
	}

	public Responsavel getResponsavelFinanceiro() {
		return responsavelFinanceiro;
	}

	public void setResponsavelFinanceiro(Responsavel responsavelFinanceiro) {
		this.responsavelFinanceiro = responsavelFinanceiro;
	}

	public Responsavel getResponsavelDidatico() {
		return responsavelDidatico;
	}

	public void setResponsavelDidatico(Responsavel responsavelDidatico) {
		this.responsavelDidatico = responsavelDidatico;
	}

	public Responsavel getResponsavelEmpresa() {
		return responsavelEmpresa;
	}

	public void setResponsavelEmpresa(Responsavel responsavelEmpresa) {
		this.responsavelEmpresa = responsavelEmpresa;
	}

	public Interessado getInteressado() {
		return interessado;
	}

	public void setInteressado(Interessado interessado) {
		this.interessado = interessado;
	}

	public Turma_Disciplina getTurma_Disciplina() {
		return turma_Disciplina;
	}

	public void setTurma_Disciplina(Turma_Disciplina turma_Disciplina) {
		this.turma_Disciplina = turma_Disciplina;
	}

	public DiarioDeAula getDiarioDeAula() {
		return diarioDeAula;
	}

	public void setDiarioDeAula(DiarioDeAula diarioDeAula) {
		this.diarioDeAula = diarioDeAula;
	}

	public Set<EstagioMonografia> getEstagioMonografia() {
		return estagioMonografia;
	}

	public void setEstagioMonografia(Set<EstagioMonografia> estagioMonografia) {
		this.estagioMonografia = estagioMonografia;
	}

	public Set<Ocorrencia> getOcorrencias() {
		return ocorrencias;
	}

	public void setOcorrencias(Set<Ocorrencia> ocorrencias) {
		this.ocorrencias = ocorrencias;
	}

	public Set<ContasAReceber> getContasAReceber() {
		return contasAReceber;
	}

	public void setContasAReceber(Set<ContasAReceber> contasAReceber) {
		this.contasAReceber = contasAReceber;
	}

	public Set<ContasAPagar> getContasAPagar() {
		return contasAPagar;
	}

	public void setContasAPagar(Set<ContasAPagar> contasAPagar) {
		this.contasAPagar = contasAPagar;
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
		result = prime * result + ((numeroMatricula == null) ? 0 : numeroMatricula.hashCode());
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
		Aluno other = (Aluno) obj;
		if (numeroMatricula == null) {
			if (other.numeroMatricula != null)
				return false;
		} else if (!numeroMatricula.equals(other.numeroMatricula))
			return false;
		if (pessoa == null) {
			if (other.pessoa != null)
				return false;
		} else if (!pessoa.equals(other.pessoa))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Aluno [numeroMatricula=" + numeroMatricula + ", ra=" + ra + ", codigoDeBarras=" + codigoDeBarras
				+ ", loginPortal=" + loginPortal + ", senha=" + senha + ", situacaoDoAluno=" + situacaoDoAluno
				+ ", pessoa=" + pessoa + ", receberEmail=" + receberEmail + ", receberSMS=" + receberSMS
				+ ", responsaveis=" + responsaveis + ", dadoBancario=" + dadoBancario + ", responsavelFinanceiro="
				+ responsavelFinanceiro + ", responsavelDidatico=" + responsavelDidatico + ", responsavelEmpresa="
				+ responsavelEmpresa + ", interessado=" + interessado + "]";
	}
}
