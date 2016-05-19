package br.com.cdan.model.financeiro;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.com.cdan.model.empresa.Empresa;
import br.com.cdan.model.pedagogico.curso.Investimento;
import br.com.cdan.model.pedagogico.curso.Turma;
import br.com.cdan.model.pessoa.Aluno;
import br.com.cdan.model.pessoa.Funcionario;

@Entity
@Table(name = "ContaAReceber")
public class ContaAReceber implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "ContaAReceber_Aluno", joinColumns = @JoinColumn(name = "id_contaAReceber"), inverseJoinColumns = @JoinColumn(name = "id_aluno"))
	private Set<Aluno> alunos;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "ContaAReceber_Empresa", joinColumns = @JoinColumn(name = "id_contaAReceber"), inverseJoinColumns = @JoinColumn(name = "id_empresa"))
	private Set<Empresa> empresas;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "contaAReceber_Turma", joinColumns = @JoinColumn(name = "id_contaAReceber"), inverseJoinColumns = @JoinColumn(name = "id_turma"))
	private Set<Turma> turmas;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "contaAReceber_Empresa", joinColumns = @JoinColumn(name = "id_contaAReceber"), inverseJoinColumns = @JoinColumn(name = "id_funcionario"))
	private Set<Funcionario> funcionarios;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "contaAReceber")
	private Set<ContaAReceber_Bolsa> contasAReceber_Bolsa;

	@Column(name = "valorDiferenciadoPrimeiraParcela")
	private Boolean valorDiferenciadoPrimeiraParcela;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_PrimeiraParcela")
	private Investimento primeiraParcela;

	@Temporal(TemporalType.DATE)
	@Column(name = "dataVencimento")
	private Calendar dataVencimento;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_planoDeConta")
	private PlanoDeConta planoDeConta;

	@Column(name = "complementoPlanoDeConta")
	private String complementoPlanoDeConta;

	@Temporal(TemporalType.DATE)
	@Column(name = "dataCompetenciaPlanoDeConta")
	private Calendar dataCompetenciaPlanoDeConta;

	@Column(name = "documentoPlanoDeConta")
	private String documentoPlanoDeConta;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tipoDeCobranca")
	private TipoDeCobrancaRecebimento tipoDeCobranca;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "conta")
	private Conta conta;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_Investimento")
	private Investimento investimento;

	@Column(name = "observacao")
	private String observacao;

	@NotNull
	@Column(name = "ativo")
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(Set<Aluno> alunos) {
		this.alunos = alunos;
	}

	public Set<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(Set<Empresa> empresas) {
		this.empresas = empresas;
	}

	public Set<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(Set<Turma> turmas) {
		this.turmas = turmas;
	}

	public Set<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(Set<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public Set<ContaAReceber_Bolsa> getcontasAReceberBolsa() {
		return contasAReceber_Bolsa;
	}

	public void setcontaAReceber_Bolsa(Set<ContaAReceber_Bolsa> contasAReceber_Bolsa) {
		this.contasAReceber_Bolsa = contasAReceber_Bolsa;
	}

	public Boolean getValorDiferenciadoPrimeiraParcela() {
		return valorDiferenciadoPrimeiraParcela;
	}

	public void setValorDiferenciadoPrimeiraParcela(Boolean valorDiferenciadoPrimeiraParcela) {
		this.valorDiferenciadoPrimeiraParcela = valorDiferenciadoPrimeiraParcela;
	}

	public Investimento getPrimeiraParcela() {
		return primeiraParcela;
	}

	public void setPrimeiraParcela(Investimento primeiraParcela) {
		this.primeiraParcela = primeiraParcela;
	}

	public Calendar getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Calendar dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public PlanoDeConta getPlanoDeConta() {
		return planoDeConta;
	}

	public void setPlanoDeConta(PlanoDeConta planoDeConta) {
		this.planoDeConta = planoDeConta;
	}

	public String getComplementoPlanoDeConta() {
		return complementoPlanoDeConta;
	}

	public void setComplementoPlanoDeConta(String complementoPlanoDeConta) {
		this.complementoPlanoDeConta = complementoPlanoDeConta;
	}

	public Calendar getDataCompetenciaPlanoDeConta() {
		return dataCompetenciaPlanoDeConta;
	}

	public void setDataCompetenciaPlanoDeConta(Calendar dataCompetenciaPlanoDeConta) {
		this.dataCompetenciaPlanoDeConta = dataCompetenciaPlanoDeConta;
	}

	public String getDocumentoPlanoDeConta() {
		return documentoPlanoDeConta;
	}

	public void setDocumentoPlanoDeConta(String documentoPlanoDeConta) {
		this.documentoPlanoDeConta = documentoPlanoDeConta;
	}

	public TipoDeCobrancaRecebimento getTipoDeCobranca() {
		return tipoDeCobranca;
	}

	public void setTipoDeCobranca(TipoDeCobrancaRecebimento tipoDeCobranca) {
		this.tipoDeCobranca = tipoDeCobranca;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Investimento getInvestimento() {
		return investimento;
	}

	public void setInvestimento(Investimento investimento) {
		this.investimento = investimento;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
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
		result = prime * result + ((alunos == null) ? 0 : alunos.hashCode());
		result = prime * result + ((empresas == null) ? 0 : empresas.hashCode());
		result = prime * result + ((funcionarios == null) ? 0 : funcionarios.hashCode());
		result = prime * result + ((planoDeConta == null) ? 0 : planoDeConta.hashCode());
		result = prime * result + ((tipoDeCobranca == null) ? 0 : tipoDeCobranca.hashCode());
		result = prime * result + ((turmas == null) ? 0 : turmas.hashCode());
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
		ContaAReceber other = (ContaAReceber) obj;
		if (alunos == null) {
			if (other.alunos != null)
				return false;
		} else if (!alunos.equals(other.alunos))
			return false;
		if (empresas == null) {
			if (other.empresas != null)
				return false;
		} else if (!empresas.equals(other.empresas))
			return false;
		if (funcionarios == null) {
			if (other.funcionarios != null)
				return false;
		} else if (!funcionarios.equals(other.funcionarios))
			return false;
		if (planoDeConta == null) {
			if (other.planoDeConta != null)
				return false;
		} else if (!planoDeConta.equals(other.planoDeConta))
			return false;
		if (tipoDeCobranca == null) {
			if (other.tipoDeCobranca != null)
				return false;
		} else if (!tipoDeCobranca.equals(other.tipoDeCobranca))
			return false;
		if (turmas == null) {
			if (other.turmas != null)
				return false;
		} else if (!turmas.equals(other.turmas))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "contaAReceber [id=" + id + ", alunos=" + alunos + ", empresas=" + empresas + ", turmas=" + turmas
				+ ", funcionarios=" + funcionarios + ", contasAReceber_Bolsa=" + contasAReceber_Bolsa
				+ ", valorDiferenciadoPrimeiraParcela=" + valorDiferenciadoPrimeiraParcela + ", primeiraParcela="
				+ primeiraParcela + ", dataVencimento=" + dataVencimento + ", planoDeConta=" + planoDeConta
				+ ", complementoPlanoDeConta=" + complementoPlanoDeConta + ", dataCompetenciaPlanoDeConta="
				+ dataCompetenciaPlanoDeConta + ", documentoPlanoDeConta=" + documentoPlanoDeConta + ", tipoDeCobranca="
				+ tipoDeCobranca + ", conta=" + conta + ", investimento=" + investimento + "]";
	}
}
