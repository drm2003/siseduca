package br.com.cdan.model.biblioteca;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.cdan.model.empresa.Empresa;

@Entity
@Table(name = "Obra")
public class Obra implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@NotEmpty
	@NotNull
	@Size(max = 350, min = 3)
	@Column(name = "nome", length = 350, nullable = false, unique = true)
	private String nome;

	@Size(max = 350, min = 3)
	@Column(name = "subtitulo", length = 350)
	private String subtitulo;

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_tipoDeObra")
	private TipoDeObra tipoDeObra;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_editora")
	private Editora editora;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_idioma")
	private Idioma idioma;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_origem")
	private Origem origem;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_nivel")
	private Nivel nivel;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_serieColecaoLiteral", insertable = false, updatable = false)
	private SerieColecaoLiteral serieColecaoLiteral;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_serieColecaoLiteral")
	private Setor setor;

	@Column(name = "classificacaoCutter")
	private String classificacaoCutter;

	@Column(name = "classificacaoCDD")
	private String classificacaoCDD;

	@Column(name = "assuntoPalavraChave")
	private String assuntoPalavraChave;

	@Column(name = "observacoes")
	private String observacoes;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "Obra_Autor", joinColumns = @JoinColumn(name = "id_obra"), inverseJoinColumns = @JoinColumn(name = "id_autor"))
	private Set<Autor> autores;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "Obra_ClassificacaoLiteraria", joinColumns = @JoinColumn(name = "id_obra"), inverseJoinColumns = @JoinColumn(name = "id_classificacaoLiteraria"))
	private Set<ClassificacaoLiteraria> classificacoesLiterarias;

	@OneToMany(mappedBy = "obra", fetch = FetchType.LAZY)
	private Set<Exemplar> exemplares;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "Obra_AreaDeConhecimento", joinColumns = @JoinColumn(name = "id_obra"), inverseJoinColumns = @JoinColumn(name = "id_AreaDeConhecimento"))
	private Set<AreasDeConhecimento> areasDeConhecimento;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "Obra_Empresa", joinColumns = @JoinColumn(name = "id_obra"), inverseJoinColumns = @JoinColumn(name = "id_AreaDeConhecimento"))
	private Set<Empresa> empresas;

	@NotNull
	@Column(name = "compartilhado", nullable = false)
	private Boolean compartilhado;

	@NotNull
	@Column(name = "ativo", nullable = false)
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSubtitulo() {
		return subtitulo;
	}

	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}

	public TipoDeObra getTipoDeObra() {
		return tipoDeObra;
	}

	public void setTipoDeObra(TipoDeObra tipoDeObra) {
		this.tipoDeObra = tipoDeObra;
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	public Idioma getIdioma() {
		return idioma;
	}

	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}

	public Origem getOrigem() {
		return origem;
	}

	public void setOrigem(Origem origem) {
		this.origem = origem;
	}

	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

	public SerieColecaoLiteral getSerieColecaoLiteral() {
		return serieColecaoLiteral;
	}

	public void setSerieColecaoLiteral(SerieColecaoLiteral serieColecaoLiteral) {
		this.serieColecaoLiteral = serieColecaoLiteral;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public String getClassificacaoCutter() {
		return classificacaoCutter;
	}

	public void setClassificacaoCutter(String classificacaoCutter) {
		this.classificacaoCutter = classificacaoCutter;
	}

	public String getClassificacaoCDD() {
		return classificacaoCDD;
	}

	public void setClassificacaoCDD(String classificacaoCDD) {
		this.classificacaoCDD = classificacaoCDD;
	}

	public String getAssuntoPalavraChave() {
		return assuntoPalavraChave;
	}

	public void setAssuntoPalavraChave(String assuntoPalavraChave) {
		this.assuntoPalavraChave = assuntoPalavraChave;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Set<Autor> getAutores() {
		return autores;
	}

	public void setAutores(Set<Autor> autores) {
		this.autores = autores;
	}

	public Set<ClassificacaoLiteraria> getClassificacoesLiterarias() {
		return classificacoesLiterarias;
	}

	public void setClassificacoesLiterarias(Set<ClassificacaoLiteraria> classificacoesLiterarias) {
		this.classificacoesLiterarias = classificacoesLiterarias;
	}

	public Set<Exemplar> getExemplares() {
		return exemplares;
	}

	public void setExemplares(Set<Exemplar> exemplares) {
		this.exemplares = exemplares;
	}

	public Set<AreasDeConhecimento> getAreasDeConhecimento() {
		return areasDeConhecimento;
	}

	public void setAreasDeConhecimento(Set<AreasDeConhecimento> areasDeConhecimento) {
		this.areasDeConhecimento = areasDeConhecimento;
	}

	public Set<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(Set<Empresa> empresas) {
		this.empresas = empresas;
	}

	public Boolean getCompartilhado() {
		return compartilhado;
	}

	public void setCompartilhado(Boolean compartilhado) {
		this.compartilhado = compartilhado;
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
		result = prime * result + ((editora == null) ? 0 : editora.hashCode());
		result = prime * result + ((idioma == null) ? 0 : idioma.hashCode());
		result = prime * result + ((nivel == null) ? 0 : nivel.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((origem == null) ? 0 : origem.hashCode());
		result = prime * result + ((tipoDeObra == null) ? 0 : tipoDeObra.hashCode());
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
		Obra other = (Obra) obj;
		if (editora == null) {
			if (other.editora != null)
				return false;
		} else if (!editora.equals(other.editora))
			return false;
		if (idioma == null) {
			if (other.idioma != null)
				return false;
		} else if (!idioma.equals(other.idioma))
			return false;
		if (nivel == null) {
			if (other.nivel != null)
				return false;
		} else if (!nivel.equals(other.nivel))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (origem == null) {
			if (other.origem != null)
				return false;
		} else if (!origem.equals(other.origem))
			return false;
		if (tipoDeObra == null) {
			if (other.tipoDeObra != null)
				return false;
		} else if (!tipoDeObra.equals(other.tipoDeObra))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Obra [id=" + id + ", nome=" + nome + ", subtitulo=" + subtitulo + ", tipoDeObra=" + tipoDeObra
				+ ", editora=" + editora + ", idioma=" + idioma + ", origem=" + origem + ", nivel=" + nivel
				+ ", serieColecaoLiteral=" + serieColecaoLiteral + ", setor=" + setor + ", classificacaoCutter="
				+ classificacaoCutter + ", classificacaoCDD=" + classificacaoCDD + ", assuntoPalavraChave="
				+ assuntoPalavraChave + ", observacoes=" + observacoes + ", autores=" + autores
				+ ", classificacoesLiterarias=" + classificacoesLiterarias + ", exemplares=" + exemplares + ", ativo="
				+ ativo + "]";
	}
}