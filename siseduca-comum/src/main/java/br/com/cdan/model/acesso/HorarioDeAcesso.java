package br.com.cdan.model.acesso;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.cdan.model.pedagogico.CalendarioLetivo;

@Entity
@Table(name = "HorarioDeAcesso")
public class HorarioDeAcesso implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(mappedBy = "horarioDeAcesso")
	private Set<Usuario> usuarios;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "calendarioLetivo_horarioDeAcesso", joinColumns = @JoinColumn(name = "id_calendarioLetivo"), inverseJoinColumns = @JoinColumn(name = "id_horarioDeAcesso"))
	private Set<CalendarioLetivo> calendarioLetivo;

	@OneToMany(mappedBy = "horarioDeAcesso")
	private Set<AcessoDiasDaSemana> acessoDiasDaSemana;

	@Column(name = "ativo")
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<AcessoDiasDaSemana> getDiasDaSemana() {
		return acessoDiasDaSemana;
	}

	public void setDiasDaSemana(Set<AcessoDiasDaSemana> acessoDiasDaSemana) {
		this.acessoDiasDaSemana = acessoDiasDaSemana;
	}

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Set<AcessoDiasDaSemana> getAcessoDiasDaSemana() {
		return acessoDiasDaSemana;
	}

	public void setAcessoDiasDaSemana(Set<AcessoDiasDaSemana> acessoDiasDaSemana) {
		this.acessoDiasDaSemana = acessoDiasDaSemana;
	}

	public Set<CalendarioLetivo> getCalendarioLetivo() {
		return calendarioLetivo;
	}

	public void setCalendarioLetivo(Set<CalendarioLetivo> calendarioLetivo) {
		this.calendarioLetivo = calendarioLetivo;
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
		result = prime * result + ((calendarioLetivo == null) ? 0 : calendarioLetivo.hashCode());
		result = prime * result + ((acessoDiasDaSemana == null) ? 0 : acessoDiasDaSemana.hashCode());
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
		HorarioDeAcesso other = (HorarioDeAcesso) obj;
		if (calendarioLetivo == null) {
			if (other.calendarioLetivo != null)
				return false;
		} else if (!calendarioLetivo.equals(other.calendarioLetivo))
			return false;
		if (acessoDiasDaSemana == null) {
			if (other.acessoDiasDaSemana != null)
				return false;
		} else if (!acessoDiasDaSemana.equals(other.acessoDiasDaSemana))
			return false;
		return true;
	}
}
