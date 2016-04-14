package br.com.cdan.model.pessoa;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.cdan.comum.EnumTipoDeCor;

@Entity
@Table(name = "ObservacaoDoAluno")
public class ObservacaoDoAluno implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(mappedBy = "observacaoDoAluno", fetch = FetchType.EAGER)
	private Interessado interessado;

	@Column(name = "observacao")
	private String observacao;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipoDeCor")
	private EnumTipoDeCor tipoDeCor;

	@Column(name = "tipoSanguineo")
	private String tipoSanguineo;

	@Column(name = "alergias")
	private String alergias;

	@Column(name = "doencas")
	private String doencas;

	@Column(name = "primeirosSocorros")
	private String primeirosSocorros;

	@Column(name = "cursouOutrasInstituicoesDeEnsino")
	private Boolean cursouOutrasInstituicoesDeEnsino;

	@OneToMany(mappedBy = "observacaoDoAluno")
	private Set<ProcessoSeletivo> processoSeletivo;

	@Column(name = "ativo")
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Interessado getInteressado() {
		return interessado;
	}

	public void setInteressado(Interessado interessado) {
		this.interessado = interessado;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public EnumTipoDeCor getTipoDeCor() {
		return tipoDeCor;
	}

	public void setTipoDeCor(EnumTipoDeCor tipoDeCor) {
		this.tipoDeCor = tipoDeCor;
	}

	public String getTipoSanguineo() {
		return tipoSanguineo;
	}

	public void setTipoSanguineo(String tipoSanguineo) {
		this.tipoSanguineo = tipoSanguineo;
	}

	public String getAlergias() {
		return alergias;
	}

	public void setAlergias(String alergias) {
		this.alergias = alergias;
	}

	public String getDoencas() {
		return doencas;
	}

	public void setDoencas(String doencas) {
		this.doencas = doencas;
	}

	public String getPrimeirosSocorros() {
		return primeirosSocorros;
	}

	public void setPrimeirosSocorros(String primeirosSocorros) {
		this.primeirosSocorros = primeirosSocorros;
	}

	public Boolean getCursouOutrasInstituicoesDeEnsino() {
		return cursouOutrasInstituicoesDeEnsino;
	}

	public void setCursouOutrasInstituicoesDeEnsino(
			Boolean cursouOutrasInstituicoesDeEnsino) {
		this.cursouOutrasInstituicoesDeEnsino = cursouOutrasInstituicoesDeEnsino;
	}

	public Set<ProcessoSeletivo> getProcessoSeletivo() {
		return processoSeletivo;
	}

	public void setProcessoSeletivo(Set<ProcessoSeletivo> processoSeletivo) {
		this.processoSeletivo = processoSeletivo;
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
		result = prime * result
				+ ((interessado == null) ? 0 : interessado.hashCode());
		result = prime * result
				+ ((observacao == null) ? 0 : observacao.hashCode());
		result = prime
				* result
				+ ((processoSeletivo == null) ? 0 : processoSeletivo.hashCode());
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
		ObservacaoDoAluno other = (ObservacaoDoAluno) obj;
		if (interessado == null) {
			if (other.interessado != null)
				return false;
		} else if (!interessado.equals(other.interessado))
			return false;
		if (observacao == null) {
			if (other.observacao != null)
				return false;
		} else if (!observacao.equals(other.observacao))
			return false;
		if (processoSeletivo == null) {
			if (other.processoSeletivo != null)
				return false;
		} else if (!processoSeletivo.equals(other.processoSeletivo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ObservacaoDoAluno [interessado=" + interessado
				+ ", observacao=" + observacao + ", tipoDeCor=" + tipoDeCor
				+ ", tipoSanguineo=" + tipoSanguineo + ", alergias=" + alergias
				+ ", doencas=" + doencas + ", primeirosSocorros="
				+ primeirosSocorros + ", cursouOutrasInstituicoesDeEnsino="
				+ cursouOutrasInstituicoesDeEnsino + ", processoSeletivo="
				+ processoSeletivo + "]";
	}

}
