package br.com.cdan.model.geral;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.cdan.model.empresa.Empresa;
import br.com.cdan.model.pedagogico.TipoDeCurso;

@Entity
@Table(name = "TipoDeServico")
public class TipoDeServico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@NotEmpty
	@Size(min = 3, max = 150)
	@Column(name = "descricao", length = 150, nullable = false, unique = true)
	private String descricao;

	@Column(name = "primeiraSolicitacaoGratuita")
	private Boolean primeiraSolicitacaoGratuita;

	@ManyToOne
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;

	@Column(name = "valor")
	private BigDecimal valor;

	@Column(name = "dias")
	private Long dias;

	@NotNull
	@Column(name = "ativo")
	private Boolean ativo;

	@ManyToMany
	@JoinTable(name = "tipoDeServico_empresa", joinColumns = @JoinColumn(name = "id_tipoDeServico"), inverseJoinColumns = @JoinColumn(name = "id_empresa"))
	private Set<Empresa> empresas;

	@ManyToMany
	@JoinTable(name = "tipoDeServico_tipoDeCurso", joinColumns = @JoinColumn(name = "id_tipoDeServico"), inverseJoinColumns = @JoinColumn(name = "id_tipoDeCurso"))
	private Set<TipoDeCurso> tipoDeCurso;

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

	public Boolean getPrimeiraSolicitacaoGratuita() {
		return primeiraSolicitacaoGratuita;
	}

	public void setPrimeiraSolicitacaoGratuita(Boolean primeiraSolicitacaoGratuita) {
		this.primeiraSolicitacaoGratuita = primeiraSolicitacaoGratuita;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Long getDias() {
		return dias;
	}

	public void setDias(Long dias) {
		this.dias = dias;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Set<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(Set<Empresa> empresas) {
		this.empresas = empresas;
	}

	public Set<TipoDeCurso> getTipoDeCurso() {
		return tipoDeCurso;
	}

	public void setTipoDeCurso(Set<TipoDeCurso> tipoDeCurso) {
		this.tipoDeCurso = tipoDeCurso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
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
		TipoDeServico other = (TipoDeServico) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TipoDeServico [descricao=" + descricao + ", primeiraSolicitacaoGratuita=" + primeiraSolicitacaoGratuita
				+ ", categoria=" + categoria + ", valor=" + valor + ", dias=" + dias + ", ativo=" + ativo + ", empresa="
				+ empresas + ", tipoDeCurso=" + tipoDeCurso + "]";
	}

}
