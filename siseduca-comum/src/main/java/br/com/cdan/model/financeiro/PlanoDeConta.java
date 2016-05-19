package br.com.cdan.model.financeiro;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.cdan.comum.EnumTipoDePlanoDeContas;

@Entity
@Table(name = "PlanoDeConta")
public class PlanoDeConta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(name = "codigo", nullable = false, unique = true)
	private Long codigo;

	@NotEmpty
	@NotNull
	@Size(min = 3, max = 60)
	@Column(name = "nome", length = 60, nullable = false, unique = true)
	private String nome;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipoDePlanoDeContas")
	private EnumTipoDePlanoDeContas tipoDePlanoDeContas;

	@Column(name = "itemApenasDeGrupo")
	private Boolean itemApenasDeGrupo;

	@Column(name = "itemReferenteAMensalidades")
	private Boolean itemReferenteAMensalidades;

	@Column(name = "itemPermiteDesconto")
	private Boolean itemPermiteDesconto;

	@Column(name = "compartilhado")
	private Boolean compartilhado;

	@Column(name = "itemSujeitoAISS")
	private Boolean itemSujeitoAISS;

	@OneToMany(mappedBy = "planoDeContas")
	private Set<PlanoDeContas_CentroDeCustos> planoDeContas_centroDeCustos;

	@Column(name = "outrosDetalhes")
	private String outrosDetalhes;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<ContaAReceber> ContasAReceber;

	@NotNull
	@Column(name = "ativo")
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public EnumTipoDePlanoDeContas getTipoDePlanoDeContas() {
		return tipoDePlanoDeContas;
	}

	public void setTipoDePlanoDeContas(EnumTipoDePlanoDeContas tipoDePlanoDeContas) {
		this.tipoDePlanoDeContas = tipoDePlanoDeContas;
	}

	public Boolean getItemApenasDeGrupo() {
		return itemApenasDeGrupo;
	}

	public void setItemApenasDeGrupo(Boolean itemApenasDeGrupo) {
		this.itemApenasDeGrupo = itemApenasDeGrupo;
	}

	public Boolean getItemReferenteAMensalidades() {
		return itemReferenteAMensalidades;
	}

	public void setItemReferenteAMensalidades(Boolean itemReferenteAMensalidades) {
		this.itemReferenteAMensalidades = itemReferenteAMensalidades;
	}

	public Boolean getItemPermiteDesconto() {
		return itemPermiteDesconto;
	}

	public void setItemPermiteDesconto(Boolean itemPermiteDesconto) {
		this.itemPermiteDesconto = itemPermiteDesconto;
	}

	public Boolean getCompartilhado() {
		return compartilhado;
	}

	public void setCompartilhado(Boolean compartilhado) {
		this.compartilhado = compartilhado;
	}

	public Boolean getItemSujeitoAISS() {
		return itemSujeitoAISS;
	}

	public void setItemSujeitoAISS(Boolean itemSujeitoAISS) {
		this.itemSujeitoAISS = itemSujeitoAISS;
	}

	public Set<PlanoDeContas_CentroDeCustos> getPlanoDeContas_centroDeCustos() {
		return planoDeContas_centroDeCustos;
	}

	public void setPlanoDeContas_centroDeCustos(Set<PlanoDeContas_CentroDeCustos> planoDeContas_centroDeCustos) {
		this.planoDeContas_centroDeCustos = planoDeContas_centroDeCustos;
	}

	public String getOutrosDetalhes() {
		return outrosDetalhes;
	}

	public void setOutrosDetalhes(String outrosDetalhes) {
		this.outrosDetalhes = outrosDetalhes;
	}

	public Set<ContaAReceber> getContasAReceber() {
		return ContasAReceber;
	}

	public void setContasAReceber(Set<ContaAReceber> contasAReceber) {
		ContasAReceber = contasAReceber;
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
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		PlanoDeConta other = (PlanoDeConta) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PlanoDeContas [id=" + id + ", codigo=" + codigo + ", nome=" + nome + ", tipoDePlanoDeContas="
				+ tipoDePlanoDeContas + ", itemApenasDeGrupo=" + itemApenasDeGrupo + ", itemReferenteAMensalidades="
				+ itemReferenteAMensalidades + ", itemPermiteDesconto=" + itemPermiteDesconto + ", compartilhado="
				+ compartilhado + ", itemSujeitoAISS=" + itemSujeitoAISS + ", planoDeContas_centroDeCustos="
				+ planoDeContas_centroDeCustos + ", outrosDetalhes=" + outrosDetalhes + ", ativo=" + ativo + "]";
	}
}
