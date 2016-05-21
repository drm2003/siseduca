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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.cdan.model.acesso.Usuario;
import br.com.cdan.model.financeiro.ContaAPagar;
import br.com.cdan.model.financeiro.ContaAReceber;
import br.com.cdan.model.geral.Cargo;
import br.com.cdan.model.pedagogico.contrato.Aproveitamento;
import br.com.cdan.model.pedagogico.contrato.EstagioMonografia;
import br.com.cdan.model.pedagogico.contrato.Ocorrencia;
import br.com.cdan.model.pedagogico.curso.Turma;
import br.com.cdan.model.pedagogico.curso.Turma_Disciplina;

@Entity
@Table(name = "Funcionario")
public class Funcionario implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pessoa", nullable = false)
	private Pessoa pessoa;

	@Column(name = "nomeApelido")
	private String nomeApelido;

	@Column(name = "numeroMatricula")
	private String numeroMatricula;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario")
	private Usuario usuario;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "cargo")
	private Cargo cargo;

	@Column(name = "numeroDependentes")
	private Long numeroDependentes;

	@Column(name = "professor")
	private Boolean professor;

	@Column(name = "atendente")
	private Boolean atendente;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "dadosProfissionais")
	private DadosProfissionais dadosProfissionais;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "financeiroFuncionario")
	private FinanceiroFuncionario financeiroFuncionario;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "funcionario")
	private Set<Follow> follows;

	@Column(name = "observacoes")
	private String observacoes;

	@OneToMany(mappedBy = "professor")
	private Set<Turma> turmas;

	@OneToOne(mappedBy = "funcionario")
	/*
	 * @JoinColumns(value = {
	 * 
	 * @JoinColumn(name = "FUNCIONARIO_id_turma", referencedColumnName =
	 * "id_turma", nullable = false),
	 * 
	 * @JoinColumn(name = "FUNCIONARIO_id_disciplina", referencedColumnName =
	 * "id_disciplina", nullable = false) })
	 */
	private Turma_Disciplina Turma_Disciplina;

	@OneToOne(mappedBy = "coordenador", cascade = CascadeType.ALL)
	private Aproveitamento aproveitamento;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orientadorSupervisor")
	private Set<EstagioMonografia> EstagioMonografia;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "funcionario")
	private Set<Ocorrencia> ocorrencias;

	@ManyToMany(mappedBy = "funcionarios", fetch = FetchType.LAZY)
	private Set<ContaAReceber> contasAReceber;

	@ManyToMany(mappedBy = "funcionarios", fetch = FetchType.LAZY)
	private Set<ContaAPagar> contasAPagar;

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

	public String getNomeApelido() {
		return nomeApelido;
	}

	public void setNomeApelido(String nomeApelido) {
		this.nomeApelido = nomeApelido;
	}

	public String getNumeroMatricula() {
		return numeroMatricula;
	}

	public void setNumeroMatricula(String numeroMatricula) {
		this.numeroMatricula = numeroMatricula;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Long getNumeroDependentes() {
		return numeroDependentes;
	}

	public void setNumeroDependentes(Long numeroDependentes) {
		this.numeroDependentes = numeroDependentes;
	}

	public Boolean getProfessor() {
		return professor;
	}

	public void setProfessor(Boolean professor) {
		this.professor = professor;
	}

	public Boolean getAtendente() {
		return atendente;
	}

	public void setAtendente(Boolean atendente) {
		this.atendente = atendente;
	}

	public DadosProfissionais getDadosProfissionais() {
		return dadosProfissionais;
	}

	public void setDadosProfissionais(DadosProfissionais dadosProfissionais) {
		this.dadosProfissionais = dadosProfissionais;
	}

	public FinanceiroFuncionario getFinanceiroFuncionario() {
		return financeiroFuncionario;
	}

	public void setFinanceiroFuncionario(FinanceiroFuncionario financeiroFuncionario) {
		this.financeiroFuncionario = financeiroFuncionario;
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

	public Set<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(Set<Turma> turmas) {
		this.turmas = turmas;
	}

	public Turma_Disciplina getTurma_Disciplina() {
		return Turma_Disciplina;
	}

	public void setTurma_Disciplina(Turma_Disciplina turma_Disciplina) {
		Turma_Disciplina = turma_Disciplina;
	}

	public Aproveitamento getAproveitamento() {
		return aproveitamento;
	}

	public void setAproveitamento(Aproveitamento aproveitamento) {
		this.aproveitamento = aproveitamento;
	}

	public Set<EstagioMonografia> getEstagioMonografia() {
		return EstagioMonografia;
	}

	public void setEstagioMonografia(Set<EstagioMonografia> estagioMonografia) {
		EstagioMonografia = estagioMonografia;
	}

	public Set<Ocorrencia> getOcorrencias() {
		return ocorrencias;
	}

	public void setOcorrencias(Set<Ocorrencia> ocorrencias) {
		this.ocorrencias = ocorrencias;
	}

	public Set<ContaAReceber> getContasAReceber() {
		return contasAReceber;
	}

	public void setContasAReceber(Set<ContaAReceber> contasAReceber) {
		this.contasAReceber = contasAReceber;
	}

	public Set<ContaAPagar> getContasAPagar() {
		return contasAPagar;
	}

	public void setContasAPagar(Set<ContaAPagar> contasAPagar) {
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
		Funcionario other = (Funcionario) obj;
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
		return "Funcionario [pessoa=" + pessoa + ", nomeApelido=" + nomeApelido + ", numeroMatricula=" + numeroMatricula
				+ ", usuario=" + usuario + ", cargo=" + cargo + ", numeroDependentes=" + numeroDependentes
				+ ", professor=" + professor + ", atendente=" + atendente + ", dadosProfissionais=" + dadosProfissionais
				+ "]";
	}
}
