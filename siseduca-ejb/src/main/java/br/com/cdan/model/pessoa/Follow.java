package br.com.cdan.model.pessoa;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Follow")
public class Follow implements Serializable {
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_funcionario")
	private Funcionario funcionario;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_responsavel")
	private Responsavel responsavel;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_alunoInteressado")
	private AlunoInteressado alunoInteressado;

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

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	public AlunoInteressado getAlunoInteressado() {
		return alunoInteressado;
	}

	public void setAlunoInteressado(AlunoInteressado alunoInteressado) {
		this.alunoInteressado = alunoInteressado;
	}

}
