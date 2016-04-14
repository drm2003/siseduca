package br.com.cdan.model.pedagogico;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "GrupoDeHabilidades")
public class GrupoDeHabilidades implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "descricao", length = 150, nullable = false, unique = true)
	private String descricao;

	@Column(name = "peso")
	private Long peso;

	@Column(name = "ordem")
	private Long ordem;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_DescricaoDeHabilidades")
	private DescricaoDeHabilidades descricaoDeHabilidades;

	@Column(name = "ativo")
	private Boolean ativo;

	@Column(name = "compartilhado")
	private Boolean compartilhado;

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

	public Long getPeso() {
		return peso;
	}

	public void setPeso(Long peso) {
		this.peso = peso;
	}

	public Long getOrdem() {
		return ordem;
	}

	public void setOrdem(Long ordem) {
		this.ordem = ordem;
	}

	public DescricaoDeHabilidades getDescricaoDeHabilidades() {
		return descricaoDeHabilidades;
	}

	public void setDescricaoDeHabilidades(
			DescricaoDeHabilidades descricaoDeHabilidades) {
		this.descricaoDeHabilidades = descricaoDeHabilidades;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Boolean getCompartilhado() {
		return compartilhado;
	}

	public void setCompartilhado(Boolean compartilhado) {
		this.compartilhado = compartilhado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
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
		GrupoDeHabilidades other = (GrupoDeHabilidades) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GrupoDeHabilidades [descricao=" + descricao + ", peso=" + peso
				+ ", ordem=" + ordem + ", descricaoDeHabilidades="
				+ descricaoDeHabilidades + "]";
	}
}
