package br.com.cdan.model.pedagogico.curso;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.cdan.model.pedagogico.SistemaDeAvaliacao;

@Entity
@Table(name = "Disciplina_MatrizCurricular")
public class Disciplina_MatrizCurricular implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@EmbeddedId
	private Disciplina_MatrizCurricularPK id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_disciplina", nullable = false)
	private Disciplina disciplina;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_matrizCurricular", nullable = false)
	private MatrizCurricular matrizCurricular;

	@Column(name = "codigo", nullable = false)
	private Long codigo;

	@Column(name = "numeroModulo", nullable = false)
	private Long numeroModulo;

	@NotNull
	@NotEmpty
	@Size(min = 3)
	@Column(name = "descricao")
	private String descricao;

	@Temporal(TemporalType.TIME)
	@Column(name = "cargaHorariaSemanal")
	private Date cargaHorariaSemanal;

	@Temporal(TemporalType.TIME)
	@Column(name = "cargaHorariaTotal")
	private Date cargaHorariaTotal;

	@Temporal(TemporalType.TIME)
	@Column(name = "cargaHorariaPratica")
	private Date cargaHorariaPratica;

	@Column(name = "ordem", nullable = false)
	private Long ordem;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_sistemaDeAvaliacao")
	private SistemaDeAvaliacao sistemaDeAvaliacao;

	@Column(name = "equivalencia")
	private String equivalencia;

	@Column(name = "requisitos")
	private String requisitos;

	@Column(name = "disciplinaOptativa")
	private Boolean disciplinaOptativa;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_metodologia")
	private Metodologia metodologia;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_planoDeEnsino")
	private PlanoDeEnsino planoDeEnsino;

	@OneToMany(mappedBy = "disciplina_MatrizCurricular")
	private Set<LinhaProgramatica> linhasProgramaticas;

	@OneToMany(mappedBy = "disciplina_MatrizCurricular")
	private Set<Conteudo> conteudos;

	@Column(name = "equivalencias")
	private String equivalencias;

	@NotNull
	@Column(name = "ativo")
	private Boolean ativo;

	public Disciplina_MatrizCurricularPK getId() {
		return id;
	}

	public void setId(Disciplina_MatrizCurricularPK id) {
		this.id = id;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public MatrizCurricular getMatrizCurricular() {
		return matrizCurricular;
	}

	public void setMatrizCurricular(MatrizCurricular matrizCurricular) {
		this.matrizCurricular = matrizCurricular;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Long getNumeroModulo() {
		return numeroModulo;
	}

	public void setNumeroModulo(Long numeroModulo) {
		this.numeroModulo = numeroModulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getCartaHorariaSemanal() {
		return cargaHorariaSemanal;
	}

	public void setCartaHorariaSemanal(Date cartaHorariaSemanal) {
		this.cargaHorariaSemanal = cartaHorariaSemanal;
	}

	public Date getCartaHorariaTotal() {
		return cargaHorariaTotal;
	}

	public void setCartaHorariaTotal(Date cartaHorariaTotal) {
		this.cargaHorariaTotal = cartaHorariaTotal;
	}

	public Date getCartaHorariaPratica() {
		return cargaHorariaPratica;
	}

	public void setCartaHorariaPratica(Date cartaHorariaPratica) {
		this.cargaHorariaPratica = cartaHorariaPratica;
	}

	public Long getOrdem() {
		return ordem;
	}

	public void setOrdem(Long ordem) {
		this.ordem = ordem;
	}

	public SistemaDeAvaliacao getSistemaDeAvaliacao() {
		return sistemaDeAvaliacao;
	}

	public void setSistemaDeAvaliacao(SistemaDeAvaliacao sistemaDeAvaliacao) {
		this.sistemaDeAvaliacao = sistemaDeAvaliacao;
	}

	public String getEquivalencia() {
		return equivalencia;
	}

	public void setEquivalencia(String equivalencia) {
		this.equivalencia = equivalencia;
	}

	public String getRequisitos() {
		return requisitos;
	}

	public void setRequisitos(String requisitos) {
		this.requisitos = requisitos;
	}

	public Boolean getDisciplinaOptativa() {
		return disciplinaOptativa;
	}

	public void setDisciplinaOptativa(Boolean disciplinaOptativa) {
		this.disciplinaOptativa = disciplinaOptativa;
	}

	public Metodologia getMetodologia() {
		return metodologia;
	}

	public void setMetodologia(Metodologia metodologia) {
		this.metodologia = metodologia;
	}

	public PlanoDeEnsino getPlanoDeEnsino() {
		return planoDeEnsino;
	}

	public void setPlanoDeEnsino(PlanoDeEnsino planoDeEnsino) {
		this.planoDeEnsino = planoDeEnsino;
	}

	public Set<LinhaProgramatica> getLinhasProgramaticas() {
		return linhasProgramaticas;
	}

	public void setLinhasProgramaticas(Set<LinhaProgramatica> linhasProgramaticas) {
		this.linhasProgramaticas = linhasProgramaticas;
	}

	public Set<Conteudo> getConteudos() {
		return conteudos;
	}

	public void setConteudos(Set<Conteudo> conteudos) {
		this.conteudos = conteudos;
	}

	public String getEquivalencias() {
		return equivalencias;
	}

	public void setEquivalencias(String equivalencias) {
		this.equivalencias = equivalencias;
	}

	public Date getCargaHorariaSemanal() {
		return cargaHorariaSemanal;
	}

	public void setCargaHorariaSemanal(Date cargaHorariaSemanal) {
		this.cargaHorariaSemanal = cargaHorariaSemanal;
	}

	public Date getCargaHorariaTotal() {
		return cargaHorariaTotal;
	}

	public void setCargaHorariaTotal(Date cargaHorariaTotal) {
		this.cargaHorariaTotal = cargaHorariaTotal;
	}

	public Date getCargaHorariaPratica() {
		return cargaHorariaPratica;
	}

	public void setCargaHorariaPratica(Date cargaHorariaPratica) {
		this.cargaHorariaPratica = cargaHorariaPratica;
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Disciplina_MatrizCurricular other = (Disciplina_MatrizCurricular) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Disciplina_MatrizCurricular [id=" + id + ", disciplina=" + disciplina + ", matrizCurricular="
				+ matrizCurricular + ", codigo=" + codigo + ", numeroModulo=" + numeroModulo + ", descricao="
				+ descricao + ", cargaHorariaSemanal=" + cargaHorariaSemanal + ", cargaHorariaTotal="
				+ cargaHorariaTotal + ", cargaHorariaPratica=" + cargaHorariaPratica + ", ordem=" + ordem
				+ ", sistemaDeAvaliacao=" + sistemaDeAvaliacao + ", equivalencia=" + equivalencia + ", requisitos="
				+ requisitos + ", disciplinaOptativa=" + disciplinaOptativa + ", metodologia=" + metodologia
				+ ", planoDeEnsino=" + planoDeEnsino + ", linhaProgramatica=" + linhasProgramaticas + ", conteudos="
				+ conteudos + ", equivalencias=" + equivalencias + "]";
	}
}
