package br.com.cdan.model.pedagogico;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.cdan.model.geral.Equipamento;

@Entity
@Table(name = "Sala")
public class Sala implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@NotEmpty
	@Size(min = 2, max = 10)
	@Column(name = "sigla", length = 10, nullable = false, unique = true)
	private String sigla;

	@NotNull
	@NotEmpty
	@Size(min = 2, max = 150)
	@Column(name = "descricao", length = 150, nullable = false, unique = true)
	private String descricao;

	@Column(name = "vagas")
	private Long vagas;

	@OneToMany(mappedBy = "sala")
	private Set<Equipamento> equipamentos;

	@ManyToOne
	@JoinColumn(name = "id_tipoDeSala")
	private TipoDeSala tipoDeSala;

	@Column(name = "locacao")
	private Boolean locacao;

	@NotNull
	@Column(name = "ativo")
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getVagas() {
		return vagas;
	}

	public void setVagas(Long vagas) {
		this.vagas = vagas;
	}

	public Set<Equipamento> getEquipamentos() {
		return equipamentos;
	}

	public void setEquipamentos(Set<Equipamento> equipamentos) {
		this.equipamentos = equipamentos;
	}

	public TipoDeSala getTipoDeSala() {
		return tipoDeSala;
	}

	public void setTipoDeSala(TipoDeSala tipoDeSala) {
		this.tipoDeSala = tipoDeSala;
	}

	public Boolean getLocacao() {
		return locacao;
	}

	public void setLocacao(Boolean locacao) {
		this.locacao = locacao;
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
		result = prime * result + ((sigla == null) ? 0 : sigla.hashCode());
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
		Sala other = (Sala) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (sigla == null) {
			if (other.sigla != null)
				return false;
		} else if (!sigla.equals(other.sigla))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Sala [sigla=" + sigla + ", descricao=" + descricao + ", vagas=" + vagas + ", equipamentos="
				+ equipamentos + ", TipoDeSala=" + tipoDeSala + ", locacao=" + locacao + "]";
	}
}
