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
import br.com.cdan.model.pessoa.CarteiraHabilitacao;

@Entity
@Table(name = "EstadoUF")
public class EstadoUF implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@NotEmpty
	@Size(min = 3, max = 150)
	@Column(name = "descricao", nullable = false, unique = true)
	private String descricao;

	@OneToMany(mappedBy = "estadoUF", fetch = FetchType.LAZY)
	private Set<Cidade> cidades;

	@ManyToOne
	@JoinColumn(name = "id_Pais")
	private Pais pais;

	@OneToMany(mappedBy = "estado")
	private Set<Endereco> enderecos;

	@OneToOne(mappedBy = "estado")
	private CarteiraHabilitacao carteiraHabilitacao;

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

	public Set<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(Set<Cidade> cidades) {
		this.cidades = cidades;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Set<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(Set<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public CarteiraHabilitacao getCarteiraHabilitacao() {
		return carteiraHabilitacao;
	}

	public void setCarteiraHabilitacao(CarteiraHabilitacao carteiraHabilitacao) {
		this.carteiraHabilitacao = carteiraHabilitacao;
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
		result = prime * result + ((pais == null) ? 0 : pais.hashCode());
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
		EstadoUF other = (EstadoUF) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (pais == null) {
			if (other.pais != null)
				return false;
		} else if (!pais.equals(other.pais))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EstadoUF [descricao=" + descricao + ", cidades=" + cidades + ", pais=" + pais + "]";
	}
}
