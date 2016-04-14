package br.com.cdan.model.pessoa;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ProcessoSeletivo")
public class ProcessoSeletivo implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_aluno")
	private Aluno aluno;

	@Column(name = "instituicao")
	private String instituicao;

	@Column(name = "ano")
	private Long ano;

	@Column(name = "semestre")
	private String semestre;

	@Column(name = "formaDeIngresso")
	private String formaDeIngresso;

	@Column(name = "prova")
	private BigDecimal prova;

	@Column(name = "redacao")
	private BigDecimal redacao;

	@Column(name = "media")
	private BigDecimal media;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_observacaoDoAluno")
	private ObservacaoDoAluno observacaoDoAluno;

	@Column(name = "ativo")
	private Boolean ativo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public String getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}

	public Long getAno() {
		return ano;
	}

	public void setAno(Long ano) {
		this.ano = ano;
	}

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	public String getFormaDeIngresso() {
		return formaDeIngresso;
	}

	public void setFormaDeIngresso(String formaDeIngresso) {
		this.formaDeIngresso = formaDeIngresso;
	}

	public BigDecimal getProva() {
		return prova;
	}

	public void setProva(BigDecimal prova) {
		this.prova = prova;
	}

	public BigDecimal getRedacao() {
		return redacao;
	}

	public void setRedacao(BigDecimal redacao) {
		this.redacao = redacao;
	}

	public BigDecimal getMedia() {
		return media;
	}

	public void setMedia(BigDecimal media) {
		this.media = media;
	}

	public ObservacaoDoAluno getObservacaoDoAluno() {
		return observacaoDoAluno;
	}

	public void setObservacaoDoAluno(ObservacaoDoAluno observacaoDoAluno) {
		this.observacaoDoAluno = observacaoDoAluno;
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
		result = prime * result + ((aluno == null) ? 0 : aluno.hashCode());
		result = prime * result + ((formaDeIngresso == null) ? 0 : formaDeIngresso.hashCode());
		result = prime * result + ((instituicao == null) ? 0 : instituicao.hashCode());
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
		ProcessoSeletivo other = (ProcessoSeletivo) obj;
		if (aluno == null) {
			if (other.aluno != null)
				return false;
		} else if (!aluno.equals(other.aluno))
			return false;
		if (formaDeIngresso == null) {
			if (other.formaDeIngresso != null)
				return false;
		} else if (!formaDeIngresso.equals(other.formaDeIngresso))
			return false;
		if (instituicao == null) {
			if (other.instituicao != null)
				return false;
		} else if (!instituicao.equals(other.instituicao))
			return false;
		return true;
	}

}
