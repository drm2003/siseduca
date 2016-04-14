package br.com.cdan.model.pedagogico;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.cdan.model.acesso.AcessoDiasDaSemana;
import br.com.cdan.model.empresa.Empresa;
import br.com.cdan.model.pedagogico.curso.Turma;

@Entity
@Table(name = "HorarioDeAula")
public class HorarioDeAula implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "descricao", length = 150, nullable = false, unique = true)
	private String descricao;

	@Column(name = "quantidadeDeAula")
	private Long quantidadeDeAula;

	@ManyToMany
	@JoinTable(name = "horarioDeAula_empresa", joinColumns = @JoinColumn(name = "id_horarioDeAula"), inverseJoinColumns = @JoinColumn(name = "id_empresa"))
	private Set<Empresa> empresas;

	@OneToMany(mappedBy = "horarioDeAula")
	private Set<AcessoDiasDaSemana> acessoDiasDaSemana;

	@OneToMany(mappedBy = "horarioDeAula")
	private Set<Turma> turmas;

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

	public Long getQuantidadeDeAula() {
		return quantidadeDeAula;
	}

	public void setQuantidadeDeAula(Long quantidadeDeAula) {
		this.quantidadeDeAula = quantidadeDeAula;
	}

	public Set<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(Set<Empresa> empresas) {
		this.empresas = empresas;
	}

	public Set<AcessoDiasDaSemana> getAcessoDiasDaSemana() {
		return acessoDiasDaSemana;
	}

	public void setAcessoDiasDaSemana(Set<AcessoDiasDaSemana> acessoDiasDaSemana) {
		this.acessoDiasDaSemana = acessoDiasDaSemana;
	}

	public Set<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(Set<Turma> turmas) {
		this.turmas = turmas;
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
		HorarioDeAula other = (HorarioDeAula) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "HorarioDeAula [descricao=" + descricao + ", quantidadeDeAula="
				+ quantidadeDeAula + ", empresas=" + empresas + "]";
	}
}
