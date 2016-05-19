package br.com.cdan.model.geral.cep;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.cdan.model.geral.Endereco;
import br.com.cdan.model.pedagogico.contrato.Transferencia;
import br.com.cdan.model.pessoa.Certidao;
import br.com.cdan.model.pessoa.Pessoa;

@Entity
@Table(name = "Cidade")
public class Cidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@NotEmpty
	@Size(min = 3, max = 150)
	@Column(name = "descricao", length = 150, nullable = false, unique = true)
	private String descricao;

	@OneToMany(mappedBy = "cidade", fetch = FetchType.LAZY)
	private Set<Bairro> bairros;

	@OneToMany(mappedBy = "cidade", fetch = FetchType.LAZY)
	private Set<Endereco> enderecos;

	@ManyToOne
	@JoinColumn(name = "id_estadoUF")
	private EstadoUF estadoUF;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cidade")
	private Set<Transferencia> transferencias;

	@OneToOne(mappedBy = "cidadeNatal")
	private Pessoa pessoa;

	@OneToOne(mappedBy = "municipioCartorio")
	private Certidao certidao;

	@NotNull
	@Column(name = "ativo")
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Set<Bairro> getBairros() {
		return bairros;
	}

	public void setBairros(Set<Bairro> bairros) {
		this.bairros = bairros;
	}

	public EstadoUF getEstadoUF() {
		return estadoUF;
	}

	public void setEstadoUF(EstadoUF estadoUF) {
		this.estadoUF = estadoUF;
	}

	public Set<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(Set<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Set<Transferencia> getTransferencias() {
		return transferencias;
	}

	public void setTransferencias(Set<Transferencia> transferencias) {
		this.transferencias = transferencias;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Certidao getCertidao() {
		return certidao;
	}

	public void setCertidao(Certidao certidao) {
		this.certidao = certidao;
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
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((estadoUF == null) ? 0 : estadoUF.hashCode());
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
		Cidade other = (Cidade) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (estadoUF == null) {
			if (other.estadoUF != null)
				return false;
		} else if (!estadoUF.equals(other.estadoUF))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cidade [descricao=" + descricao + ", bairros=" + bairros + ", estadoUF=" + estadoUF + "]";
	}
}
