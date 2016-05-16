package br.com.cdan.model.pessoa;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.cdan.model.clientefornecedor.ClienteFornecedor;

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

	@ManyToMany(mappedBy = "follows", fetch = FetchType.LAZY)
	private Set<ClienteFornecedor> clientesFornecedores;

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

	public Set<ClienteFornecedor> getClientesFornecedores() {
		return clientesFornecedores;
	}

	public void setClientesFornecedores(Set<ClienteFornecedor> clientesFornecedores) {
		this.clientesFornecedores = clientesFornecedores;
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
		result = prime * result + ((alunoInteressado == null) ? 0 : alunoInteressado.hashCode());
		result = prime * result + ((clientesFornecedores == null) ? 0 : clientesFornecedores.hashCode());
		result = prime * result + ((funcionario == null) ? 0 : funcionario.hashCode());
		result = prime * result + ((interessado == null) ? 0 : interessado.hashCode());
		result = prime * result + ((responsavel == null) ? 0 : responsavel.hashCode());
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
		Follow other = (Follow) obj;
		if (alunoInteressado == null) {
			if (other.alunoInteressado != null)
				return false;
		} else if (!alunoInteressado.equals(other.alunoInteressado))
			return false;
		if (clientesFornecedores == null) {
			if (other.clientesFornecedores != null)
				return false;
		} else if (!clientesFornecedores.equals(other.clientesFornecedores))
			return false;
		if (funcionario == null) {
			if (other.funcionario != null)
				return false;
		} else if (!funcionario.equals(other.funcionario))
			return false;
		if (interessado == null) {
			if (other.interessado != null)
				return false;
		} else if (!interessado.equals(other.interessado))
			return false;
		if (responsavel == null) {
			if (other.responsavel != null)
				return false;
		} else if (!responsavel.equals(other.responsavel))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Follow [id=" + id + ", interessado=" + interessado + ", funcionario=" + funcionario + ", responsavel="
				+ responsavel + ", alunoInteressado=" + alunoInteressado + ", clientesFornecedores="
				+ clientesFornecedores + ", ativo=" + ativo + "]";
	}
}
