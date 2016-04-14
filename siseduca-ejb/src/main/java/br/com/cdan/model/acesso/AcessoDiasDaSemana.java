package br.com.cdan.model.acesso;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.cdan.comum.EnumDiaDaSemana;
import br.com.cdan.model.pedagogico.HorarioDeAula;

@Entity
@Table(name = "AcessoDiasDaSemana")
public class AcessoDiasDaSemana implements Serializable {
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

	@ManyToOne
	@JoinColumn(name = "id_horarioDeAula")
	private HorarioDeAula horarioDeAula;

	@ManyToOne
	@JoinColumn(name = "id_horarioDeAcesso")
	private HorarioDeAcesso horarioDeAcesso;

	@Temporal(TemporalType.TIME)
	@Column(name = "horaEntrada")
	private Date horaEntrada;

	@Temporal(TemporalType.TIME)
	@Column(name = "horaSaida")
	private Date horaSaida;

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

	public HorarioDeAula getHorarioDeAula() {
		return horarioDeAula;
	}

	public void setHorarioDeAula(HorarioDeAula horarioDeAula) {
		this.horarioDeAula = horarioDeAula;
	}

	public HorarioDeAcesso getHorarioDeAcesso() {
		return horarioDeAcesso;
	}

	public void setHorarioDeAcesso(HorarioDeAcesso horarioDeAcesso) {
		this.horarioDeAcesso = horarioDeAcesso;
	}

	public Date getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(Date horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public Date getHoraSaida() {
		return horaSaida;
	}

	public void setHoraSaida(Date horaSaida) {
		this.horaSaida = horaSaida;
	}

}
