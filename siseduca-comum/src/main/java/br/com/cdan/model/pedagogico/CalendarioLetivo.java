package br.com.cdan.model.pedagogico;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.cdan.model.acesso.HorarioDeAcesso;
import br.com.cdan.model.pedagogico.curso.Turma;
import br.com.cdan.model.pedagogico.diario.ControleDeFrequencia;
import br.com.cdan.model.pessoa.FeriadoEvento;

@Entity
@Table(name = "CalendarioLetivo")
public class CalendarioLetivo implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@NotEmpty
	@Size(min = 3, max = 150)
	@Column(name = "nome", length = 150, nullable = false)
	private String nome;

	@Column(name = "calendarioPadrao")
	private boolean calendarioPadrao;

	@Column(name = "selecionarSabado")
	private boolean selecionarSabado;

	@Column(name = "selecionarDomingo")
	private boolean selecionarDomingo;

	@Column(name = "anoLetivo")
	private Long anoLetivo;

	@OneToMany(mappedBy = "calendarioLetivo")
	private Set<FeriadoEvento> feriadoEvento;

	@ManyToMany(mappedBy = "calendarioLetivo")
	private Set<HorarioDeAcesso> horarioDeAcesso;

	@OneToMany(mappedBy = "calendarioPadrao")
	private Set<Turma> turmas;

	@OneToMany(mappedBy = "calendarioLetivo")
	private Set<ControleDeFrequencia> controleDeFrequencia;

	@NotNull
	@Column(name = "ativo")
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<HorarioDeAcesso> getHorarioDeAcesso() {
		return horarioDeAcesso;
	}

	public void setHorarioDeAcesso(Set<HorarioDeAcesso> horarioDeAcesso) {
		this.horarioDeAcesso = horarioDeAcesso;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isCalendarioPadrao() {
		return calendarioPadrao;
	}

	public void setCalendarioPadrao(boolean calendarioPadrao) {
		this.calendarioPadrao = calendarioPadrao;
	}

	public boolean isSelecionarSabado() {
		return selecionarSabado;
	}

	public void setSelecionarSabado(boolean selecionarSabado) {
		this.selecionarSabado = selecionarSabado;
	}

	public boolean isSelecionarDomingo() {
		return selecionarDomingo;
	}

	public void setSelecionarDomingo(boolean selecionarDomingo) {
		this.selecionarDomingo = selecionarDomingo;
	}

	public Long getAnoLetivo() {
		return anoLetivo;
	}

	public void setAnoLetivo(Long anoLetivo) {
		this.anoLetivo = anoLetivo;
	}

	public Set<FeriadoEvento> getFeriadoEvento() {
		return feriadoEvento;
	}

	public void setFeriadoEvento(Set<FeriadoEvento> feriadoEvento) {
		this.feriadoEvento = feriadoEvento;
	}

	public Set<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(Set<Turma> turmas) {
		this.turmas = turmas;
	}

	public Set<ControleDeFrequencia> getControleDeFrequencia() {
		return controleDeFrequencia;
	}

	public void setControleDeFrequencia(Set<ControleDeFrequencia> controleDeFrequencia) {
		this.controleDeFrequencia = controleDeFrequencia;
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
		result = prime * result + ((horarioDeAcesso == null) ? 0 : horarioDeAcesso.hashCode());
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
		CalendarioLetivo other = (CalendarioLetivo) obj;
		if (horarioDeAcesso == null) {
			if (other.horarioDeAcesso != null)
				return false;
		} else if (!horarioDeAcesso.equals(other.horarioDeAcesso))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CalendarioLetivo [horarioDeAcesso=" + horarioDeAcesso + "]";
	}
}
