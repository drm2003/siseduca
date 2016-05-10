package br.com.cdan.model.acesso;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Permissao")
public class Permissao implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(mappedBy = "permissao")
	private Usuario usuario;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "permissao")
	private Set<Permissao_Empresa> permissoesEmpresas;

	@Column(name = "ativo")
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Set<Permissao_Empresa> getPermissoesEmpresas() {
		return permissoesEmpresas;
	}

	public void setPermissoesEmpresas(Set<Permissao_Empresa> permissoesEmpresas) {
		this.permissoesEmpresas = permissoesEmpresas;
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
		result = prime * result + ((permissoesEmpresas == null) ? 0 : permissoesEmpresas.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		Permissao other = (Permissao) obj;
		if (permissoesEmpresas == null) {
			if (other.permissoesEmpresas != null)
				return false;
		} else if (!permissoesEmpresas.equals(other.permissoesEmpresas))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Permissao [id=" + id + ", usuario=" + usuario + ", empresas=" + permissoesEmpresas + "]";
	}
}
