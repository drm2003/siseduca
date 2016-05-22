package br.com.cdan.model.pedagogico.diario;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.cdan.model.pedagogico.CalendarioLetivo;
import br.com.cdan.model.pedagogico.HorarioDeAula;
import br.com.cdan.model.pessoa.Aluno;

@Entity
@Table(name = "ControleDeFrequencia")
public class ControleDeFrequencia implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "aluno")
	private Aluno aluno;

	@NotNull
	@Column(name = "data")
	private Calendar data;

	@OneToOne
	@JoinColumn(name = "horarioDeAula")
	private HorarioDeAula horarioDeAula;

	@Column(name = "presenca")
	private Boolean presenca;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_calendarioLetivo")
	private CalendarioLetivo calendarioLetivo;

	@NotNull
	@Column(name = "ativo")
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public HorarioDeAula getHorarioDeAula() {
		return horarioDeAula;
	}

	public void setHorarioDeAula(HorarioDeAula horarioDeAula) {
		this.horarioDeAula = horarioDeAula;
	}

	public Boolean getPresenca() {
		return presenca;
	}

	public void setPresenca(Boolean presenca) {
		this.presenca = presenca;
	}

	public CalendarioLetivo getCalendarioLetivo() {
		return calendarioLetivo;
	}

	public void setCalendarioLetivo(CalendarioLetivo calendarioLetivo) {
		this.calendarioLetivo = calendarioLetivo;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
}
