package br.com.cdan.model.pedagogico.diario;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.cdan.model.pedagogico.contrato.DisciplinaMatricula;
import br.com.cdan.model.pedagogico.curso.DiaDaSemanaAula;
import br.com.cdan.model.pedagogico.curso.Turma_Disciplina;
import br.com.cdan.model.pessoa.Aluno;
import br.com.cdan.model.pessoa.Funcionario;

@Entity
@Table(name = "DiarioDeAula")
public class DiarioDeAula implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumns(value = {
			@JoinColumn(name = "DIARIODEAULA_id_turma", referencedColumnName = "id_turma", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "DIARIODEAULA_id_disciplina", referencedColumnName = "id_disciplina", nullable = false, insertable = false, updatable = false) })
	private Turma_Disciplina turma_Disciplina;

	@OneToMany(mappedBy = "diarioDeAula")
	private Set<Aluno> alunos;

	@ManyToMany
	@JoinTable(name = "disciplinaMatricula_diarioDeAula", joinColumns = @JoinColumn(name = "id_disciplinaMatricula"), inverseJoinColumns = @JoinColumn(name = "id_diarioDeAula"))
	private Set<DisciplinaMatricula> disciplinaMatricula;

	@ManyToOne
	@JoinColumn(name = "controleDeFrequencia")
	private ControleDeFrequencia controleDeFrequencia;

	@ManyToOne
	@JoinColumn(name = "controleDeConteudo")
	private ControleDeConteudo controleDeConteudo;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "diarioDeAula_diaDaSemanaAula", joinColumns = @JoinColumn(name = "id_diarioDeAula"), inverseJoinColumns = @JoinColumn(name = "id_diaDaSemanaAula"))
	private Set<DiaDaSemanaAula> diasDaSemanaAula;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "professor")
	private Funcionario professor;

}