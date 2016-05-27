package br.com.cdan.negocio.biblioteca.services;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.cdan.model.biblioteca.AreasDeConhecimento;
import br.com.cdan.negocio.biblioteca.AreasDeConhecimentoDao;

@Named
@SessionScoped
public class AreasDeConhecimentoService {

	@Inject
	private AreasDeConhecimentoDao dao;

	private AreasDeConhecimento areasDeConhecimento = new AreasDeConhecimento();
	private List<AreasDeConhecimento> lista = new ArrayList<>();

	public void gravar() {
		dao.persist(areasDeConhecimento);
		this.lista = dao.lista();
	}

	public void altera() {
		dao.merge(areasDeConhecimento);
		this.lista = dao.lista();
	}

	public void exclui() {
		this.dao.remove(areasDeConhecimento);
		this.lista = dao.lista();
	}

	public AreasDeConhecimento busca(Long id) {
		return dao.find(AreasDeConhecimento.class, id);
	}

	public AreasDeConhecimento getAreasDeConhecimento() {
		return areasDeConhecimento;
	}

	public void setAreasDeConhecimento(AreasDeConhecimento areasDeConhecimento) {
		this.areasDeConhecimento = areasDeConhecimento;
	}

	public List<AreasDeConhecimento> todos() {
		return this.dao.lista();
	}
}
