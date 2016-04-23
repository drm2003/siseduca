package br.com.cdan.model.pedagogico.curso;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.cdan.comum.EnumDiaDaSemana;
import br.com.cdan.model.pedagogico.diario.DiarioDeAula;

@Entity
@Table(name = "DiaDaSemanaAula")
public class DiaDaSemanaAula implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "diaDaSemana")
	private EnumDiaDaSemana diaDaSemana;

	@Temporal(TemporalType.TIME)
	@Column(name = "horaInicio")
	private Date horaInicio;

	@Temporal(TemporalType.TIME)
	@Column(name = "horaTermino")
	private Date horaTermino;

	@OneToOne
	@JoinColumns(value = {
			@JoinColumn(name = "DIADASEMANA_ID_TURMA", referencedColumnName = "id_turma", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "DIADASEMANA_ID_DISCIPLINA", referencedColumnName = "id_disciplina", nullable = false, insertable = false, updatable = false) })
	private Turma_Disciplina turma_Disciplina;

	@ManyToMany(mappedBy = "diasDaSemanaAula", fetch = FetchType.LAZY)
	private Set<DiarioDeAula> diariosDeAula;

	@Column(name = "ativo")
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EnumDiaDaSemana getDiaDaSemana() {
		return diaDaSemana;
	}

	public void setDiaDaSemana(EnumDiaDaSemana diaDaSemana) {
		this.diaDaSemana = diaDaSemana;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getHoraTermino() {
		return horaTermino;
	}

	public void setHoraTermino(Date horaTermino) {
		this.horaTermino = horaTermino;
	}

	public Turma_Disciplina getTurma_Disciplina() {
		return turma_Disciplina;
	}

	public void setTurma_Disciplina(Turma_Disciplina turma_Disciplina) {
		this.turma_Disciplina = turma_Disciplina;
	}

	public Set<DiarioDeAula> getDiariosDeAula() {
		return diariosDeAula;
	}

	public void setDiariosDeAula(Set<DiarioDeAula> diariosDeAula) {
		this.diariosDeAula = diariosDeAula;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
}
