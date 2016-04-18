package br.com.cdan.model.pedagogico.contrato;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.cdan.comum.EnumTipoDeTransferencia;
import br.com.cdan.model.geral.Cidade;
import br.com.cdan.model.pedagogico.curso.Turma;
import br.com.cdan.model.pessoa.Aluno;

@Entity
@Table(name = "Transferencia")
public class Transferencia implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipoDeTransferencia")
	private EnumTipoDeTransferencia tipoDeTransferencia;

	@Column(name = "curso")
	private String curso;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_turma")
	private Turma turma;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_aluno")
	private Aluno aluno;

	@Column(name = "data")
	private Calendar data;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_motivoDeTransferencia")
	private MotivoDeTransferencia motivoDeTransferencia;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cidade")
	private Cidade cidade;

	@Column(name = "estabelecimentoProcedencia")
	private String estabelecimentoProcedencia;

	@Column(name = "ativo")
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EnumTipoDeTransferencia getTipoDeTransferencia() {
		return tipoDeTransferencia;
	}

	public void setTipoDeTransferencia(EnumTipoDeTransferencia tipoDeTransferencia) {
		this.tipoDeTransferencia = tipoDeTransferencia;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public MotivoDeTransferencia getMotivoDeTransferencia() {
		return motivoDeTransferencia;
	}

	public void setMotivoDeTransferencia(MotivoDeTransferencia motivoDeTransferencia) {
		this.motivoDeTransferencia = motivoDeTransferencia;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public String getEstabelecimentoProcedencia() {
		return estabelecimentoProcedencia;
	}

	public void setEstabelecimentoProcedencia(String estabelecimentoProcedencia) {
		this.estabelecimentoProcedencia = estabelecimentoProcedencia;
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
		result = prime * result + ((curso == null) ? 0 : curso.hashCode());
		result = prime * result + ((estabelecimentoProcedencia == null) ? 0 : estabelecimentoProcedencia.hashCode());
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
		Transferencia other = (Transferencia) obj;
		if (aluno == null) {
			if (other.aluno != null)
				return false;
		} else if (!aluno.equals(other.aluno))
			return false;
		if (curso == null) {
			if (other.curso != null)
				return false;
		} else if (!curso.equals(other.curso))
			return false;
		if (estabelecimentoProcedencia == null) {
			if (other.estabelecimentoProcedencia != null)
				return false;
		} else if (!estabelecimentoProcedencia.equals(other.estabelecimentoProcedencia))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Transferencia [id=" + id + ", tipoDeTransferencia=" + tipoDeTransferencia + ", curso=" + curso
				+ ", turma=" + turma + ", aluno=" + aluno + ", data=" + data + ", motivoDeTransferencia="
				+ motivoDeTransferencia + ", cidade=" + cidade + ", estabelecimentoProcedencia="
				+ estabelecimentoProcedencia + ", ativo=" + ativo + "]";
	}
}