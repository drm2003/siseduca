package br.com.cdan.model.acesso;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.cdan.model.geral.Email;
import br.com.cdan.model.pedagogico.curso.Curso;
import br.com.cdan.model.pessoa.Funcionario;

@Entity
@Table(name = "Usuario")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(mappedBy = "usuario", fetch = FetchType.EAGER)
	private Funcionario funcionario;

	@Column(name = "nomeUsuario", nullable = false)
	private String nomeUsuario;

	@Column(name = "login", nullable = false, unique = true)
	private String login;

	@Column(name = "senha", nullable = false)
	private String senha;

	@OneToMany(fetch = FetchType.EAGER)
	private Set<Email> emails;

	@Column(name = "professor")
	private Boolean professor;

	@Column(name = "coordenadorDoCurso")
	private Boolean coordenadorDoCurso;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_permissao")
	private Permissao permissao;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_horarioDeAcesso")
	private HorarioDeAcesso horarioDeAcesso;

	@OneToMany(mappedBy = "coordenador")
	private Set<Curso> cursos;

	@Column(name = "ativo")
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<Email> getEmails() {
		return emails;
	}

	public void setEmails(Set<Email> emails) {
		this.emails = emails;
	}

	public Boolean getProfessor() {
		return professor;
	}

	public void setProfessor(Boolean professor) {
		this.professor = professor;
	}

	public Boolean getCoordenadorDoCurso() {
		return coordenadorDoCurso;
	}

	public void setCoordenadorDoCurso(Boolean coordenadorDoCurso) {
		this.coordenadorDoCurso = coordenadorDoCurso;
	}

	public Permissao getPermissao() {
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}

	public HorarioDeAcesso getHorarioDeAcesso() {
		return horarioDeAcesso;
	}

	public void setHorarioDeAcesso(HorarioDeAcesso horarioDeAcesso) {
		this.horarioDeAcesso = horarioDeAcesso;
	}

	public Set<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(Set<Curso> cursos) {
		this.cursos = cursos;
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
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((nomeUsuario == null) ? 0 : nomeUsuario.hashCode());
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
		Usuario other = (Usuario) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (nomeUsuario == null) {
			if (other.nomeUsuario != null)
				return false;
		} else if (!nomeUsuario.equals(other.nomeUsuario))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [funcionario=" + funcionario + ", nomeUsuario=" + nomeUsuario + ", login=" + login + ", senha="
				+ senha + ", emails=" + emails + ", professor=" + professor + ", coordenadorDoCurso="
				+ coordenadorDoCurso + ", permissao=" + permissao + ", horarioDeAcesso=" + horarioDeAcesso + "]";
	}
}
