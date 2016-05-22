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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

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

	@NotNull
	@NotEmpty
	@Size(min = 3)
	@Column(name = "descricao", unique = true, nullable = false)
	private String descricao;

	@OneToMany(mappedBy = "tipoDeInvestimento", fetch = FetchType.EAGER)
	private Set<Turma_Disciplina> turmas_Disciplinas;

	@OneToMany(mappedBy = "tipoDeInvestimento", fetch = FetchType.EAGER)
	private Set<Investimento> investimento;

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

	public Set<Turma_Disciplina> getTurmas_Disciplinas() {
		return turmas_Disciplinas;
	}

	public void setTurmas_Disciplinas(Set<Turma_Disciplina> turmas_Disciplinas) {
		this.turmas_Disciplinas = turmas_Disciplinas;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
}
