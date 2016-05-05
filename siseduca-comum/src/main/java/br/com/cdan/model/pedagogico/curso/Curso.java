package br.com.cdan.model.pedagogico.curso;

import java.io.Serializable;
import java.util.Set;

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

import br.com.cdan.model.acesso.Usuario;
import br.com.cdan.model.pedagogico.SeriePadrao;
import br.com.cdan.model.pedagogico.TipoDeCurso;
import br.com.cdan.model.pessoa.Interessado;

@Entity
@Table(name = "Curso")
public class Curso implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_interessado")
	private Interessado interessado;

	@Column(name = "nome")
	private String nome;

	@OneToOne
	@JoinColumn(name = "tipoDeCurso")
	private TipoDeCurso tipoDeCurso;

	@OneToOne
	@JoinColumn(name = "serieEquivalente")
	private SeriePadrao serieEquivalente;

	@ManyToOne
	@JoinColumn(name = "coordenador")
	private Usuario coordenador;

	@Column(name = "sigla")
	private String sigla;

	@Column(name = "numeroDeModulos")
	private String numeroDeModulos;

	@Column(name = "numeroDeVagas")
	private String numeroDeVagas;

	@Column(name = "pontoDeEquilibrio")
	private String pontoDeEquilibrio;

	@Column(name = "idadeMinima")
	private Long idadeMinima;

	@Column(name = "nomeInstituicao")
	private String nomeInstituicao;

	@Column(name = "atoOficial")
	private String atoOficial;

	@Column(name = "reconheicmentoDoCurso")
	private String reconhecimentoDoCurso;

	@Column(name = "numeroMaximoDeDependencias")
	private Long numeroMaximoDeDependencias;

	@Column(name = "tempoMaximoParaConclusaoDoCurso")
	private Long tempoMaximoParaConclusaoDoCurso;

	@Column(name = "modular")
	private Boolean modular;

	@OneToMany(mappedBy = "curso", fetch = FetchType.LAZY, targetEntity = Curso_MatrizCurricular.class)
	private Set<Curso_MatrizCurricular> cursos_MatrizesCurricular;

	@OneToMany(mappedBy = "curso")
	private Set<RequisitoParaOCurso> requisitosParaOCurso;

	@Column(name = "curriculoDoCurso")
	private String curriculoDoCurso;

	@Column(name = "perfilDoCurso")
	private String perfilDoCurso;

	@OneToMany(mappedBy = "curso")
	private Set<PlanoFinanceiroDoCurso> planosFinanceiroDoCurso;

	@OneToMany(mappedBy = "curso")
	private Set<Turma> turmas;

	@ManyToOne
	@JoinColumn(name = "curso")
	private Usuario usuario;

	@Column(name = "compartilhado")
	private Boolean compartilhado;

	@Column(name = "ativo")
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Interessado getInteressado() {
		return interessado;
	}

	public void setInteressado(Interessado interessado) {
		this.interessado = interessado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoDeCurso getTipoDeCurso() {
		return tipoDeCurso;
	}

	public void setTipoDeCurso(TipoDeCurso tipoDeCurso) {
		this.tipoDeCurso = tipoDeCurso;
	}

	public SeriePadrao getSerieEquivalente() {
		return serieEquivalente;
	}

	public void setSerieEquivalente(SeriePadrao serieEquivalente) {
		this.serieEquivalente = serieEquivalente;
	}

	public Usuario getCoordenador() {
		return coordenador;
	}

	public void setCoordenador(Usuario coordenador) {
		this.coordenador = coordenador;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getNumeroDeModulos() {
		return numeroDeModulos;
	}

	public void setNumeroDeModulos(String numeroDeModulos) {
		this.numeroDeModulos = numeroDeModulos;
	}

	public String getNumeroDeVagas() {
		return numeroDeVagas;
	}

	public void setNumeroDeVagas(String numeroDeVagas) {
		this.numeroDeVagas = numeroDeVagas;
	}

	public String getPontoDeEquilibrio() {
		return pontoDeEquilibrio;
	}

	public void setPontoDeEquilibrio(String pontoDeEquilibrio) {
		this.pontoDeEquilibrio = pontoDeEquilibrio;
	}

	public Long getIdadeMinima() {
		return idadeMinima;
	}

	public void setIdadeMinima(Long idadeMinima) {
		this.idadeMinima = idadeMinima;
	}

	public String getNomeInstituicao() {
		return nomeInstituicao;
	}

	public void setNomeInstituicao(String nomeInstituicao) {
		this.nomeInstituicao = nomeInstituicao;
	}

	public String getAtoOficial() {
		return atoOficial;
	}

	public void setAtoOficial(String atoOficial) {
		this.atoOficial = atoOficial;
	}

	public String getReconhecimentoDoCurso() {
		return reconhecimentoDoCurso;
	}

	public void setReconhecimentoDoCurso(String reconhecimentoDoCurso) {
		this.reconhecimentoDoCurso = reconhecimentoDoCurso;
	}

	public Long getNumeroMaximoDeDependencias() {
		return numeroMaximoDeDependencias;
	}

	public void setNumeroMaximoDeDependencias(Long numeroMaximoDeDependencias) {
		this.numeroMaximoDeDependencias = numeroMaximoDeDependencias;
	}

	public Long getTempoMaximoParaConclusaoDoCurso() {
		return tempoMaximoParaConclusaoDoCurso;
	}

	public void setTempoMaximoParaConclusaoDoCurso(Long tempoMaximoParaConclusaoDoCurso) {
		this.tempoMaximoParaConclusaoDoCurso = tempoMaximoParaConclusaoDoCurso;
	}

	public Boolean getModular() {
		return modular;
	}

	public void setModular(Boolean modular) {
		this.modular = modular;
	}

	public Set<Curso_MatrizCurricular> getCursos_MatrizesCurricular() {
		return cursos_MatrizesCurricular;
	}

	public void setCursos_MatrizesCurricular(Set<Curso_MatrizCurricular> cursos_MatrizesCurricular) {
		this.cursos_MatrizesCurricular = cursos_MatrizesCurricular;
	}

	public Set<RequisitoParaOCurso> getRequisitosParaOCurso() {
		return requisitosParaOCurso;
	}

	public void setRequisitosParaOCurso(Set<RequisitoParaOCurso> requisitosParaOCurso) {
		this.requisitosParaOCurso = requisitosParaOCurso;
	}

	public String getCurriculoDoCurso() {
		return curriculoDoCurso;
	}

	public void setCurriculoDoCurso(String curriculoDoCurso) {
		this.curriculoDoCurso = curriculoDoCurso;
	}

	public String getPerfilDoCurso() {
		return perfilDoCurso;
	}

	public void setPerfilDoCurso(String perfilDoCurso) {
		this.perfilDoCurso = perfilDoCurso;
	}

	public Set<PlanoFinanceiroDoCurso> getPlanosFinanceiroDoCurso() {
		return planosFinanceiroDoCurso;
	}

	public void setPlanosFinanceiroDoCurso(Set<PlanoFinanceiroDoCurso> planosFinanceiroDoCurso) {
		this.planosFinanceiroDoCurso = planosFinanceiroDoCurso;
	}

	public Set<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(Set<Turma> turmas) {
		this.turmas = turmas;
	}

	public Boolean getCompartilhado() {
		return compartilhado;
	}

	public void setCompartilhado(Boolean compartilhado) {
		this.compartilhado = compartilhado;
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
		result = prime * result + ((modular == null) ? 0 : modular.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((sigla == null) ? 0 : sigla.hashCode());
		result = prime * result + ((tipoDeCurso == null) ? 0 : tipoDeCurso.hashCode());
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
		Curso other = (Curso) obj;
		if (modular == null) {
			if (other.modular != null)
				return false;
		} else if (!modular.equals(other.modular))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (sigla == null) {
			if (other.sigla != null)
				return false;
		} else if (!sigla.equals(other.sigla))
			return false;
		if (tipoDeCurso == null) {
			if (other.tipoDeCurso != null)
				return false;
		} else if (!tipoDeCurso.equals(other.tipoDeCurso))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Curso [nome=" + nome + ", tipoDeCurso=" + tipoDeCurso + ", sigla=" + sigla + ", numeroDeModulos="
				+ numeroDeModulos + ", numeroDeVagas=" + numeroDeVagas + ", nomeInstituicao=" + nomeInstituicao
				+ ", curso_MatrizCurricular=" + cursos_MatrizesCurricular + "]";
	}
}