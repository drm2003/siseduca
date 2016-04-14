package br.com.cdan.model.pedagogico.curso;

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
import javax.persistence.Table;

@Entity
@Table(name = "PlanoFinanceiroDoCurso")
public class PlanoFinanceiroDoCurso implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "curso")
	private Curso curso;

	@Column(name = "modulo")
	private Long modulo;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "planoPadrao")
	private Boolean planoPadrao;

	@Column(name = "ativo")
	private Boolean ativo;

	@OneToMany
	private Set<Investimento> investimento;
}
