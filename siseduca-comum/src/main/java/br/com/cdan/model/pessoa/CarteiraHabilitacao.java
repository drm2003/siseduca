package br.com.cdan.model.pessoa;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.cdan.model.geral.cep.EstadoUF;

@Entity
@Table(name = "CarteiraHabilitacao")
public class CarteiraHabilitacao implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "orgaoEmissor")
	private String orgaoEmissor;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "estado")
	private EstadoUF estado;

	@Column(name = "validade")
	@Temporal(TemporalType.DATE)
	private Calendar validade;

	@Column(name = "numeroCNH")
	private String numeroCNH;

	@Column(name = "numeroRegistro")
	private String numeroRegistro;

	@Column(name = "categoria")
	private String categoria;

	@OneToOne(mappedBy = "carteiraHabilitacao", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Pessoa pessoa;

	@Column(name = "ativo")
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrgaoEmissor() {
		return orgaoEmissor;
	}

	public void setOrgaoEmissor(String orgaoEmissor) {
		this.orgaoEmissor = orgaoEmissor;
	}

	public EstadoUF getEstado() {
		return estado;
	}

	public void setEstado(EstadoUF estado) {
		this.estado = estado;
	}

	public Calendar getValidade() {
		return validade;
	}

	public void setValidade(Calendar validade) {
		this.validade = validade;
	}

	public String getNumeroCNH() {
		return numeroCNH;
	}

	public void setNumeroCNH(String numeroCNH) {
		this.numeroCNH = numeroCNH;
	}

	public String getNumeroRegistro() {
		return numeroRegistro;
	}

	public void setNumeroRegistro(String numeroRegistro) {
		this.numeroRegistro = numeroRegistro;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
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
		result = prime * result + ((numeroCNH == null) ? 0 : numeroCNH.hashCode());
		result = prime * result + ((numeroRegistro == null) ? 0 : numeroRegistro.hashCode());
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
		CarteiraHabilitacao other = (CarteiraHabilitacao) obj;
		if (numeroCNH == null) {
			if (other.numeroCNH != null)
				return false;
		} else if (!numeroCNH.equals(other.numeroCNH))
			return false;
		if (numeroRegistro == null) {
			if (other.numeroRegistro != null)
				return false;
		} else if (!numeroRegistro.equals(other.numeroRegistro))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CarteiraHabilitacao [orgaoEmissor=" + orgaoEmissor + ", estado=" + estado + ", validade=" + validade
				+ ", numeroCNH=" + numeroCNH + ", numeroRegistro=" + numeroRegistro + ", categoria=" + categoria + "]";
	}
}
