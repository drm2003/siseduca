package br.com.cdan.model.pedagogico.contrato;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.cdan.comum.EnumTipoEstagioMonografia;
import br.com.cdan.model.pedagogico.curso.Turma_Disciplina;
import br.com.cdan.model.pessoa.Aluno;
import br.com.cdan.model.pessoa.AnexoDocumentos;
import br.com.cdan.model.pessoa.Funcionario;

@Entity
@Table(name = "EstagioMonografia")
public class EstagioMonografia implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated
	@Column(name = "tipoEstagioMonografia")
	private EnumTipoEstagioMonografia tipoEstagioMonografia;

	@Column(name = "concluido")
	private Boolean concluido;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "aluno")
	private Aluno aluno;

	@OneToMany(mappedBy = "estagioMonografia")
	private Set<Turma_Disciplina> turma_Disciplina;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "orientadorSupervisor")
	private Funcionario orientadorSupervisor;

	@Column(name = "tituloTema")
	private String tituloTema;

	@Column(name = "dataInicio")
	private Calendar dataInicio;

	@Column(name = "dataTermino")
	private Calendar dataTermino;

	@Column(name = "horaInicio")
	private Long horaInicio;

	@Column(name = "horaTermino")
	private Long horaTermino;

	@Column(name = "nota")
	private BigDecimal nota;

	@Column(name = "resultado")
	private String resultado;

	@Column(name = "observacao")
	private String observacao;

	@Embedded
	private DadosEmpresaConcedente dadosEmpresaConcedente;

	@OneToMany(mappedBy = "estagioMonografia")
	private Set<AnexoDocumentos> anexos;

	@Column(name = "ativo")
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EnumTipoEstagioMonografia getTipoEstagioMonografia() {
		return tipoEstagioMonografia;
	}

	public void setTipoEstagioMonografia(EnumTipoEstagioMonografia tipoEstagioMonografia) {
		this.tipoEstagioMonografia = tipoEstagioMonografia;
	}

	public Boolean getConcluido() {
		return concluido;
	}

	public void setConcluido(Boolean concluido) {
		this.concluido = concluido;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Set<Turma_Disciplina> getTurma_Disciplina() {
		return turma_Disciplina;
	}

	public void setTurma_Disciplina(Set<Turma_Disciplina> turma_Disciplina) {
		this.turma_Disciplina = turma_Disciplina;
	}

	public Funcionario getOrientadorSupervisor() {
		return orientadorSupervisor;
	}

	public void setOrientadorSupervisor(Funcionario orientadorSupervisor) {
		this.orientadorSupervisor = orientadorSupervisor;
	}

	public String getTituloTema() {
		return tituloTema;
	}

	public void setTituloTema(String tituloTema) {
		this.tituloTema = tituloTema;
	}

	public Calendar getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Calendar getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(Calendar dataTermino) {
		this.dataTermino = dataTermino;
	}

	public Long getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Long horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Long getHoraTermino() {
		return horaTermino;
	}

	public void setHoraTermino(Long horaTermino) {
		this.horaTermino = horaTermino;
	}

	public BigDecimal getNota() {
		return nota;
	}

	public void setNota(BigDecimal nota) {
		this.nota = nota;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public DadosEmpresaConcedente getDadosEmpresaConcedente() {
		return dadosEmpresaConcedente;
	}

	public void setDadosEmpresaConcedente(DadosEmpresaConcedente dadosEmpresaConcedente) {
		this.dadosEmpresaConcedente = dadosEmpresaConcedente;
	}

	public Set<AnexoDocumentos> getAnexos() {
		return anexos;
	}

	public void setAnexos(Set<AnexoDocumentos> anexos) {
		this.anexos = anexos;
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
		result = prime * result + ((dataInicio == null) ? 0 : dataInicio.hashCode());
		result = prime * result + ((horaInicio == null) ? 0 : horaInicio.hashCode());
		result = prime * result + ((orientadorSupervisor == null) ? 0 : orientadorSupervisor.hashCode());
		result = prime * result + ((tipoEstagioMonografia == null) ? 0 : tipoEstagioMonografia.hashCode());
		result = prime * result + ((tituloTema == null) ? 0 : tituloTema.hashCode());
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
		EstagioMonografia other = (EstagioMonografia) obj;
		if (aluno == null) {
			if (other.aluno != null)
				return false;
		} else if (!aluno.equals(other.aluno))
			return false;
		if (dataInicio == null) {
			if (other.dataInicio != null)
				return false;
		} else if (!dataInicio.equals(other.dataInicio))
			return false;
		if (horaInicio == null) {
			if (other.horaInicio != null)
				return false;
		} else if (!horaInicio.equals(other.horaInicio))
			return false;
		if (orientadorSupervisor == null) {
			if (other.orientadorSupervisor != null)
				return false;
		} else if (!orientadorSupervisor.equals(other.orientadorSupervisor))
			return false;
		if (tipoEstagioMonografia != other.tipoEstagioMonografia)
			return false;
		if (tituloTema == null) {
			if (other.tituloTema != null)
				return false;
		} else if (!tituloTema.equals(other.tituloTema))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EstagioMonografia [id=" + id + ", tipoEstagioMonografia=" + tipoEstagioMonografia + ", concluido="
				+ concluido + ", aluno=" + aluno + ", turma_Disciplina=" + turma_Disciplina + ", orientadorSupervisor="
				+ orientadorSupervisor + ", tituloTema=" + tituloTema + ", dataInicio=" + dataInicio + ", dataTermino="
				+ dataTermino + ", horaInicio=" + horaInicio + ", horaTermino=" + horaTermino + ", nota=" + nota
				+ ", resultado=" + resultado + ", observacao=" + observacao + ", dadosEmpresaConcedente="
				+ dadosEmpresaConcedente + ", anexos=" + anexos + ", ativo=" + ativo + "]";
	}
}