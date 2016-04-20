package br.com.cdan.model.financeiro;

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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.cdan.comum.EnumEspecieDesconto;
import br.com.cdan.comum.EnumTipoDeDesconto;
import br.com.cdan.model.empresa.Empresa;
import br.com.cdan.model.pessoa.DadoBancario;

@Entity
@Table(name = "Bolsa")
public class Bolsa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "descricao", length = 60, nullable = false, unique = true)
	private String descricao;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipoDeDesconto", nullable = false)
	private EnumTipoDeDesconto tipoDeDesconto;

	@Enumerated(EnumType.STRING)
	@Column(name = "especieDesconto")
	private EnumEspecieDesconto especieDesconto;

	@OneToMany(mappedBy = "bolsa")
	private Set<Desconto> descontos;

	@Column(name = "padraoFranquia")
	private String padraoFranquia;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "bolsa_empresa", joinColumns = @JoinColumn(name = "id_bolsa"), inverseJoinColumns = @JoinColumn(name = "id_empresa"))
	private Set<Empresa> empresas;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_dadoBancario")
	private DadoBancario dadoBancario;

	@OneToMany(mappedBy = "bolsa", fetch = FetchType.LAZY)
	private Set<ContasAReceber_Bolsa> bolsa;

	@Column(name = "ativo")
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public EnumTipoDeDesconto getTipoDeDesconto() {
		return tipoDeDesconto;
	}

	public void setTipoDeDesconto(EnumTipoDeDesconto tipoDeDesconto) {
		this.tipoDeDesconto = tipoDeDesconto;
	}

	public Set<ContasAReceber_Bolsa> getBolsa() {
		return bolsa;
	}

	public void setBolsa(Set<ContasAReceber_Bolsa> bolsa) {
		this.bolsa = bolsa;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getPadraoFranquia() {
		return padraoFranquia;
	}

	public void setPadraoFranquia(String padraoFranquia) {
		this.padraoFranquia = padraoFranquia;
	}

	public Set<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(Set<Empresa> empresas) {
		this.empresas = empresas;
	}

	public EnumEspecieDesconto getEspecieDesconto() {
		return especieDesconto;
	}

	public void setEspecieDesconto(EnumEspecieDesconto especieDesconto) {
		this.especieDesconto = especieDesconto;
	}

	public Set<Desconto> getDescontos() {
		return descontos;
	}

	public void setDescontos(Set<Desconto> descontos) {
		this.descontos = descontos;
	}

	public DadoBancario getDadoBancario() {
		return dadoBancario;
	}

	public void setDadoBancario(DadoBancario dadoBancario) {
		this.dadoBancario = dadoBancario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Bolsa other = (Bolsa) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
