package br.com.cdan.model.pessoa;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.cdan.model.contato.MelhorHorarioParaContato;
import br.com.cdan.model.contato.Midia;
import br.com.cdan.model.contato.TipoDeContato;
import br.com.cdan.model.contrato.MotivosDeNaoFechamentoDeContrato;
import br.com.cdan.model.geral.Origem;
import br.com.cdan.model.pedagogico.curso.Curso;

@Entity
@Table(name = "Interessado")
public class Interessado implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(mappedBy = "interessado", fetch = FetchType.EAGER)
	private Aluno aluno;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "promotorDeVendas")
	private Funcionario promotorDeVendas;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "atendente")
	private Funcionario atendente;

	@Column(name = "melhorHorarioParaContato")
	private MelhorHorarioParaContato melhorHorarioParaContato;

	@Column(name = "motivosDeNaoFechamentoDeContrato")
	private MotivosDeNaoFechamentoDeContrato motivosDeNaoFechamentoDeContrato;

	@Column(name = "tipoDeContato")
	private TipoDeContato tipoDeContato;

	@Column(name = "origem")
	private Origem origem;

	@Column(name = "midia")
	private Midia midia;

	@Column(name = "outraEscolaDoAluno")
	private String outraEscolaDoAluno;

	@OneToMany(fetch = FetchType.LAZY)
	private Set<Curso> cursosTumasDeInteresse;

	@OneToMany(fetch = FetchType.LAZY)
	private Set<Follow> followsDoInteressado;

	@OneToOne
	@JoinColumn(name = "observacaoDoAluno")
	private ObservacaoDoAluno observacaoDoAluno;

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

	public Funcionario getPromotorDeVendas() {
		return promotorDeVendas;
	}

	public void setPromotorDeVendas(Funcionario promotorDeVendas) {
		this.promotorDeVendas = promotorDeVendas;
	}

	public Funcionario getAtendente() {
		return atendente;
	}

	public void setAtendente(Funcionario atendente) {
		this.atendente = atendente;
	}

	public MelhorHorarioParaContato getMelhorHorarioParaContato() {
		return melhorHorarioParaContato;
	}

	public void setMelhorHorarioParaContato(
			MelhorHorarioParaContato melhorHorarioParaContato) {
		this.melhorHorarioParaContato = melhorHorarioParaContato;
	}

	public MotivosDeNaoFechamentoDeContrato getMotivosDeNaoFechamentoDeContrato() {
		return motivosDeNaoFechamentoDeContrato;
	}

	public void setMotivosDeNaoFechamentoDeContrato(
			MotivosDeNaoFechamentoDeContrato motivosDeNaoFechamentoDeContrato) {
		this.motivosDeNaoFechamentoDeContrato = motivosDeNaoFechamentoDeContrato;
	}

	public TipoDeContato getTipoDeContato() {
		return tipoDeContato;
	}

	public void setTipoDeContato(TipoDeContato tipoDeContato) {
		this.tipoDeContato = tipoDeContato;
	}

	public Origem getOrigem() {
		return origem;
	}

	public void setOrigem(Origem origem) {
		this.origem = origem;
	}

	public Midia getMidia() {
		return midia;
	}

	public void setMidia(Midia midia) {
		this.midia = midia;
	}

	public String getOutraEscolaDoAluno() {
		return outraEscolaDoAluno;
	}

	public void setOutraEscolaDoAluno(String outraEscolaDoAluno) {
		this.outraEscolaDoAluno = outraEscolaDoAluno;
	}

	public Set<Curso> getCursosTumasDeInteresse() {
		return cursosTumasDeInteresse;
	}

	public void setCursosTumasDeInteresse(Set<Curso> cursosTumasDeInteresse) {
		this.cursosTumasDeInteresse = cursosTumasDeInteresse;
	}

	public Set<Follow> getFollowsDoInteressado() {
		return followsDoInteressado;
	}

	public void setFollowsDoInteressado(Set<Follow> followsDoInteressado) {
		this.followsDoInteressado = followsDoInteressado;
	}

	public ObservacaoDoAluno getObservacaoDoAluno() {
		return observacaoDoAluno;
	}

	public void setObservacaoDoAluno(ObservacaoDoAluno observacaoDoAluno) {
		this.observacaoDoAluno = observacaoDoAluno;
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
				+ ((atendente == null) ? 0 : atendente.hashCode());
		result = prime
				* result
				+ ((cursosTumasDeInteresse == null) ? 0
						: cursosTumasDeInteresse.hashCode());
		result = prime
				* result
				+ ((followsDoInteressado == null) ? 0 : followsDoInteressado
						.hashCode());
		result = prime
				* result
				+ ((melhorHorarioParaContato == null) ? 0
						: melhorHorarioParaContato.hashCode());
		result = prime * result + ((midia == null) ? 0 : midia.hashCode());
		result = prime
				* result
				+ ((motivosDeNaoFechamentoDeContrato == null) ? 0
						: motivosDeNaoFechamentoDeContrato.hashCode());
		result = prime
				* result
				+ ((observacaoDoAluno == null) ? 0 : observacaoDoAluno
						.hashCode());
		result = prime * result + ((origem == null) ? 0 : origem.hashCode());
		result = prime
				* result
				+ ((outraEscolaDoAluno == null) ? 0 : outraEscolaDoAluno
						.hashCode());
		result = prime
				* result
				+ ((promotorDeVendas == null) ? 0 : promotorDeVendas.hashCode());
		result = prime * result
				+ ((tipoDeContato == null) ? 0 : tipoDeContato.hashCode());
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
		Interessado other = (Interessado) obj;
		if (atendente == null) {
			if (other.atendente != null)
				return false;
		} else if (!atendente.equals(other.atendente))
			return false;
		if (cursosTumasDeInteresse == null) {
			if (other.cursosTumasDeInteresse != null)
				return false;
		} else if (!cursosTumasDeInteresse.equals(other.cursosTumasDeInteresse))
			return false;
		if (followsDoInteressado == null) {
			if (other.followsDoInteressado != null)
				return false;
		} else if (!followsDoInteressado.equals(other.followsDoInteressado))
			return false;
		if (melhorHorarioParaContato == null) {
			if (other.melhorHorarioParaContato != null)
				return false;
		} else if (!melhorHorarioParaContato
				.equals(other.melhorHorarioParaContato))
			return false;
		if (midia == null) {
			if (other.midia != null)
				return false;
		} else if (!midia.equals(other.midia))
			return false;
		if (motivosDeNaoFechamentoDeContrato == null) {
			if (other.motivosDeNaoFechamentoDeContrato != null)
				return false;
		} else if (!motivosDeNaoFechamentoDeContrato
				.equals(other.motivosDeNaoFechamentoDeContrato))
			return false;
		if (observacaoDoAluno == null) {
			if (other.observacaoDoAluno != null)
				return false;
		} else if (!observacaoDoAluno.equals(other.observacaoDoAluno))
			return false;
		if (origem == null) {
			if (other.origem != null)
				return false;
		} else if (!origem.equals(other.origem))
			return false;
		if (outraEscolaDoAluno == null) {
			if (other.outraEscolaDoAluno != null)
				return false;
		} else if (!outraEscolaDoAluno.equals(other.outraEscolaDoAluno))
			return false;
		if (promotorDeVendas == null) {
			if (other.promotorDeVendas != null)
				return false;
		} else if (!promotorDeVendas.equals(other.promotorDeVendas))
			return false;
		if (tipoDeContato == null) {
			if (other.tipoDeContato != null)
				return false;
		} else if (!tipoDeContato.equals(other.tipoDeContato))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Interessado [promotorDeVendas=" + promotorDeVendas
				+ ", atendente=" + atendente + ", melhorHorarioParaContato="
				+ melhorHorarioParaContato
				+ ", motivosDeNaoFechamentoDeContrato="
				+ motivosDeNaoFechamentoDeContrato + ", tipoDeContato="
				+ tipoDeContato + ", origem=" + origem + ", midia=" + midia
				+ ", outraEscolaDoAluno=" + outraEscolaDoAluno
				+ ", cursosTumasDeInteresse=" + cursosTumasDeInteresse
				+ ", followsDoInteressado=" + followsDoInteressado
				+ ", observacaoDoAluno=" + observacaoDoAluno;
	}

}
