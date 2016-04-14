package br.com.cdan.model.pedagogico.curso;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TipoDeInvestimento")
public class TipoDeInvestimento implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "descricao", unique = true, nullable = false)
	private String descricao;

	@OneToMany(mappedBy = "tipoDeInvestimento", fetch = FetchType.EAGER)
	private Set<Turma_Disciplina> Turma_Disciplina;

	@OneToMany(mappedBy = "tipoDeInvestimento", fetch = FetchType.EAGER)
	private Set<Investimento> investimento;

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

	public Set<Turma_Disciplina> getTurma_Disciplina() {
		return Turma_Disciplina;
	}

	public void setTurma_Disciplina(Set<Turma_Disciplina> turma_Disciplina) {
		Turma_Disciplina = turma_Disciplina;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
}
