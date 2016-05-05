package br.com.cdan.model.pedagogico.contrato;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.cdan.model.empresa.Empresa;

@Embeddable
public class DadosEmpresaConcedente implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Column(name = "areaEstagioMonografia")
	private String areaEstagioMonografia;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "empresa")
	private Empresa empresa;

	@Column(name = "representanteLegal")
	private String representanteLegal;

	@Column(name = "responsavel")
	private String responsavel;

	@Column(name = "numeroRegistroConselho")
	private String numeroRegistroConselho;

	@Column(name = "formacaoAcademica")
	private String formacaoAcademica;

	@Column(name = "observacaoDoResponsavel")
	private String observacaoDoResponsavel;

	@Column(name = "ativo")
	private Boolean ativo;

	public String getAreaEstagioMonografia() {
		return areaEstagioMonografia;
	}

	public void setAreaEstagioMonografia(String areaEstagioMonografia) {
		this.areaEstagioMonografia = areaEstagioMonografia;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getRepresentanteLegal() {
		return representanteLegal;
	}

	public void setRepresentanteLegal(String representanteLegal) {
		this.representanteLegal = representanteLegal;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public String getNumeroRegistroConselho() {
		return numeroRegistroConselho;
	}

	public void setNumeroRegistroConselho(String numeroRegistroConselho) {
		this.numeroRegistroConselho = numeroRegistroConselho;
	}

	public String getFormacaoAcademica() {
		return formacaoAcademica;
	}

	public void setFormacaoAcademica(String formacaoAcademica) {
		this.formacaoAcademica = formacaoAcademica;
	}

	public String getObservacaoDoResponsavel() {
		return observacaoDoResponsavel;
	}

	public void setObservacaoDoResponsavel(String observacaoDoResponsavel) {
		this.observacaoDoResponsavel = observacaoDoResponsavel;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
}
