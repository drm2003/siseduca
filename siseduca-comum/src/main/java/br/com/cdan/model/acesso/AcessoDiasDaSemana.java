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
import javax.validation.constraints.NotNull;

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

	@NotNull
	@Column(name = "ativo", nullable = false)
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
		result = prime * result + ((ativo == null) ? 0 : ativo.hashCode());
		result = prime * result + ((diaDaSemana == null) ? 0 : diaDaSemana.hashCode());
		result = prime * result + ((horaEntrada == null) ? 0 : horaEntrada.hashCode());
		result = prime * result + ((horaSaida == null) ? 0 : horaSaida.hashCode());
		result = prime * result + ((horarioDeAcesso == null) ? 0 : horarioDeAcesso.hashCode());
		result = prime * result + ((horarioDeAula == null) ? 0 : horarioDeAula.hashCode());
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
		AcessoDiasDaSemana other = (AcessoDiasDaSemana) obj;
		if (ativo == null) {
			if (other.ativo != null)
				return false;
		} else if (!ativo.equals(other.ativo))
			return false;
		if (diaDaSemana != other.diaDaSemana)
			return false;
		if (horaEntrada == null) {
			if (other.horaEntrada != null)
				return false;
		} else if (!horaEntrada.equals(other.horaEntrada))
			return false;
		if (horaSaida == null) {
			if (other.horaSaida != null)
				return false;
		} else if (!horaSaida.equals(other.horaSaida))
			return false;
		if (horarioDeAcesso == null) {
			if (other.horarioDeAcesso != null)
				return false;
		} else if (!horarioDeAcesso.equals(other.horarioDeAcesso))
			return false;
		if (horarioDeAula == null) {
			if (other.horarioDeAula != null)
				return false;
		} else if (!horarioDeAula.equals(other.horarioDeAula))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AcessoDiasDaSemana [id=" + id + ", diaDaSemana=" + diaDaSemana + ", horarioDeAula=" + horarioDeAula
				+ ", horarioDeAcesso=" + horarioDeAcesso + ", horaEntrada=" + horaEntrada + ", horaSaida=" + horaSaida
				+ ", ativo=" + ativo + "]";
	}
}
