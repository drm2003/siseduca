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

import br.com.cdan.model.geral.Origem;

@Entity
@Table(name = "Obra")
public class Obra implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", length = 200, nullable = false, unique = true)
	private String nome;

	@Column(name = "subtitulo", length = 200)
	private String subtitulo;

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

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "Obra_Autor", joinColumns = @JoinColumn(name = "id_obra"), inverseJoinColumns = @JoinColumn(name = "id_autor"))
	private Set<Autor> autores;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "Obra_ClassificacaoLiteraria", joinColumns = @JoinColumn(name = "id_obra"), inverseJoinColumns = @JoinColumn(name = "id_classificacaoLiteraria"))
	private Set<ClassificacaoLiteraria> classificacoesLiterarias;

	@OneToMany(mappedBy = "obra", fetch = FetchType.LAZY)
	private Set<Exemplar> exemplares;
}
