package br.com.cdan.model.biblioteca;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.cdan.model.empresa.Empresa;

@Entity
@Table(name = "TipoDeObra_Empresa")
public class TipoDeObra_Empresa implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@EmbeddedId
	private TipoDeObra_EmpresaPK id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_tipoDeObra")
	private TipoDeObra tipoDeObra;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;

	@Column(name = "utiliza")
	private Boolean utiliza;

	@Column(name = "diasDevolucao")
	private Long diasDevolucao;

	@Column(name = "diasDevolucaoParaAlunos")
	private Long diasDevolucaoParaAlunos;

	@Column(name = "diasDevolucaoParaFuncionarios")
	private Long diasDevolucaoParaFuncionarios;

	@Column(name = "ativo")
	private Boolean ativo;

	public TipoDeObra_EmpresaPK getId() {
		return id;
	}

	public void setId(TipoDeObra_EmpresaPK id) {
		this.id = id;
	}

	public TipoDeObra getTipoDeObra() {
		return tipoDeObra;
	}

	public void setTipoDeObra(TipoDeObra tipoDeObra) {
		this.tipoDeObra = tipoDeObra;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Boolean getUtiliza() {
		return utiliza;
	}

	public void setUtiliza(Boolean utiliza) {
		this.utiliza = utiliza;
	}

	public Long getDiasDevolucao() {
		return diasDevolucao;
	}

	public void setDiasDevolucao(Long diasDevolucao) {
		this.diasDevolucao = diasDevolucao;
	}

	public Long getDiasDevolucaoParaAlunos() {
		return diasDevolucaoParaAlunos;
	}

	public void setDiasDevolucaoParaAlunos(Long diasDevolucaoParaAlunos) {
		this.diasDevolucaoParaAlunos = diasDevolucaoParaAlunos;
	}

	public Long getDiasDevolucaoParaFuncionarios() {
		return diasDevolucaoParaFuncionarios;
	}

	public void setDiasDevolucaoParaFuncionarios(
			Long diasDevolucaoParaFuncionarios) {
		this.diasDevolucaoParaFuncionarios = diasDevolucaoParaFuncionarios;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
}
