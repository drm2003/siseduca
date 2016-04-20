package br.com.cdan.model.pessoa;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.cdan.model.financeiro.Bolsa;

@Entity
@Table(name = "DadoBancario")
public class DadoBancario implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(mappedBy = "dadoBancario", fetch = FetchType.LAZY)
	private Set<Bolsa> bolsas;

	@Column(name = "diaDoVencimento")
	private Long diaDoVencimento;

	@Column(name = "inadimplente")
	private Boolean inadimplente;

	@OneToOne(mappedBy = "dadoBancario")
	private Aluno aluno;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Bolsa> getBolsas() {
		return bolsas;
	}

	public void setBolsa(Set<Bolsa> bolsas) {
		this.bolsas = bolsas;
	}

	public Long getDiaDoVencimento() {
		return diaDoVencimento;
	}

	public void setDiaDoVencimento(Long diaDoVencimento) {
		this.diaDoVencimento = diaDoVencimento;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Boolean getInadimplente() {
		return inadimplente;
	}

	public void setInadimplente(Boolean inadimplente) {
		this.inadimplente = inadimplente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bolsas == null) ? 0 : bolsas.hashCode());
		result = prime * result
				+ ((diaDoVencimento == null) ? 0 : diaDoVencimento.hashCode());
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
		DadoBancario other = (DadoBancario) obj;
		if (bolsas == null) {
			if (other.bolsas != null)
				return false;
		} else if (!bolsas.equals(other.bolsas))
			return false;
		if (diaDoVencimento == null) {
			if (other.diaDoVencimento != null)
				return false;
		} else if (!diaDoVencimento.equals(other.diaDoVencimento))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DadoBancario [bolsa=" + bolsas + ", diaDoVencimento="
				+ diaDoVencimento + ", inadimplente=" + inadimplente + "]";
	}
}
