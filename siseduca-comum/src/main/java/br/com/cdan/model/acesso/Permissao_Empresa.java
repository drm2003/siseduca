package br.com.cdan.model.acesso;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.cdan.model.empresa.Empresa;

@Entity
@Table(name = "Permissao_Empresa")
public class Permissao_Empresa implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@EmbeddedId
	private Permissao_EmpresaPK id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_permissao")
	private Permissao permissao;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;

	@Column(name = "usuarioTemAcesso")
	private Boolean usuarioTemAcesso;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_grupoDeUsuarios")
	private GrupoDeUsuarios grupoDeUsuarios;

	@Column(name = "ativo")
	private Boolean ativo;

	public Permissao_EmpresaPK getId() {
		return id;
	}

	public void setId(Permissao_EmpresaPK id) {
		this.id = id;
	}

	public Permissao getPermissao() {
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Boolean getUsuarioTemAcesso() {
		return usuarioTemAcesso;
	}

	public void setUsuarioTemAcesso(Boolean usuarioTemAcesso) {
		this.usuarioTemAcesso = usuarioTemAcesso;
	}

	public GrupoDeUsuarios getGrupoDeUsuarios() {
		return grupoDeUsuarios;
	}

	public void setGrupoDeUsuarios(GrupoDeUsuarios grupoDeUsuarios) {
		this.grupoDeUsuarios = grupoDeUsuarios;
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
		result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
		result = prime * result + ((permissao == null) ? 0 : permissao.hashCode());
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
		Permissao_Empresa other = (Permissao_Empresa) obj;
		if (empresa == null) {
			if (other.empresa != null)
				return false;
		} else if (!empresa.equals(other.empresa))
			return false;
		if (permissao == null) {
			if (other.permissao != null)
				return false;
		} else if (!permissao.equals(other.permissao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Permissao_Empresa [permissao=" + permissao + ", empresa=" + empresa + ", usuarioTemAcesso="
				+ usuarioTemAcesso + ", grupoDeUsuarios=" + grupoDeUsuarios + "]";
	}
}
