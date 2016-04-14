package br.com.cdan.model.biblioteca;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.cdan.model.pessoa.Aluno;
import br.com.cdan.model.pessoa.Funcionario;

@Entity
@Table(name = "ReservaEmprestimo")
public class ReservaEmprestimo implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "aluno")
	private Aluno aluno;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "funcionario")
	private Funcionario funcionario;

	@Column(name = "dataHora")
	private Calendar dataHora;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "obra")
	private Obra obra;

	@Column(name = "dataEntrega")
	private Calendar dataEntrega;

	@Column(name = "reservado")
	private Boolean reservado;

	@Column(name = "emprestado")
	private Boolean emprestado;

	@Column(name = "devolvido")
	private Boolean devolvido;

	@Temporal(TemporalType.DATE)
	@Column(name = "dataDevolucao")
	private Calendar dataDevolucao;

	@Column(name = "ativo")
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Calendar getDataHora() {
		return dataHora;
	}

	public void setDataHora(Calendar dataHora) {
		this.dataHora = dataHora;
	}

	public Obra getObra() {
		return obra;
	}

	public void setObra(Obra obra) {
		this.obra = obra;
	}

	public Calendar getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Calendar dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Boolean getReservado() {
		return reservado;
	}

	public void setReservado(Boolean reservado) {
		this.reservado = reservado;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Boolean getEmprestado() {
		return emprestado;
	}

	public void setEmprestado(Boolean emprestado) {
		this.emprestado = emprestado;
	}

	public Boolean getDevolvido() {
		return devolvido;
	}

	public void setDevolvido(Boolean devolvido) {
		this.devolvido = devolvido;
	}

	public Calendar getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Calendar dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aluno == null) ? 0 : aluno.hashCode());
		result = prime * result + ((funcionario == null) ? 0 : funcionario.hashCode());
		result = prime * result + ((obra == null) ? 0 : obra.hashCode());
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
		ReservaEmprestimo other = (ReservaEmprestimo) obj;
		if (aluno == null) {
			if (other.aluno != null)
				return false;
		} else if (!aluno.equals(other.aluno))
			return false;
		if (funcionario == null) {
			if (other.funcionario != null)
				return false;
		} else if (!funcionario.equals(other.funcionario))
			return false;
		if (obra == null) {
			if (other.obra != null)
				return false;
		} else if (!obra.equals(other.obra))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReservaEmprestimo [id=" + id + ", aluno=" + aluno + ", funcionario=" + funcionario + ", dataHora="
				+ dataHora + ", obra=" + obra + ", dataEntrega=" + dataEntrega + ", reservado=" + reservado
				+ ", emprestado=" + emprestado + ", devolvido=" + devolvido + ", dataDevolucao=" + dataDevolucao
				+ ", ativo=" + ativo + "]";
	}
}
