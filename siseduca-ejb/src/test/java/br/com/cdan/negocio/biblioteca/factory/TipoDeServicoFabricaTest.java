package br.com.cdan.negocio.biblioteca.factory;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.empresa.Empresa;
import br.com.cdan.model.geral.Categoria;
import br.com.cdan.model.geral.TipoDeServico;
import br.com.cdan.model.pedagogico.TipoDeCurso;
import br.com.cdan.negocio.biblioteca.CategoriaDao;
import br.com.cdan.negocio.biblioteca.EmpresaDao;
import br.com.cdan.negocio.biblioteca.TipoDeCursoDao;
import br.com.cdan.negocio.biblioteca.TipoDeServicoDao;

public class TipoDeServicoFabricaTest {
	private static TipoDeServicoFabricaTest instance = null;

	public static synchronized TipoDeServicoFabricaTest getInstance() {
		if (instance == null) {
			instance = new TipoDeServicoFabricaTest();
		}
		return instance;
	}

	public TipoDeServico criaTipoDeServico() {
		TipoDeServico t = new TipoDeServico();
		t.setAtivo(Boolean.TRUE);
		t.setCategoria(CategoriaFabricaTest.getInstance().criaCategoria());
		t.setDescricao("descricao");
		t.setDias(Long.valueOf("10"));
		// Empresas
		Set<Empresa> empresas = new LinkedHashSet<>();
		empresas.add(EmpresaFabricaTest.getInstance().criaEmpresa());
		empresas.add(EmpresaFabricaTest.getInstance().criaEmpresa());
		t.setEmpresas(empresas);
		//
		t.setPrimeiraSolicitacaoGratuita(Boolean.TRUE);
		// Tipos de curso
		Set<TipoDeCurso> tiposDeCurso = new LinkedHashSet<>();
		tiposDeCurso.add(TipoDeCursoFabricaTest.getInstance().criaTipoDeCurso());
		tiposDeCurso.add(TipoDeCursoFabricaTest.getInstance().criaTipoDeCurso());
		t.setTipoDeCurso(tiposDeCurso);
		//
		t.setValor(BigDecimal.TEN);
		//
		return t;
	}

	public TipoDeServico criaTipoDeServicoPersistido(EntityManager em) {
		TipoDeServicoDao dao = new TipoDeServicoDao(em);
		TipoDeServico t = criaTipoDeServico();
		//
		CategoriaDao categoriaDao = new CategoriaDao(em);
		Categoria categoria = t.getCategoria();
		categoriaDao.persist(categoria);
		t.setCategoria(categoria);
		//
		EmpresaDao empresaDao = new EmpresaDao(em);
		Set<Empresa> empresas = new LinkedHashSet<>();
		t.getEmpresas().forEach(empresa -> {
			empresaDao.persist(empresa);
			empresas.add(empresa);
		});
		t.setEmpresas(empresas);
		//
		TipoDeCursoDao tipoDeCursoDao = new TipoDeCursoDao(em);
		Set<TipoDeCurso> tiposDeCurso = new LinkedHashSet<TipoDeCurso>();
		t.getTipoDeCurso().forEach(tipoDeCurso -> {
			tipoDeCursoDao.persist(tipoDeCurso);
			tiposDeCurso.add(tipoDeCurso);
		});
		t.setTipoDeCurso(tiposDeCurso);
		//
		dao.persist(t);
		return t;
	}
}
