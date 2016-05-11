package br.com.cdan.negocio.financeiro.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.financeiro.MotivoDeRenegociacaoDeParcelas;
import br.com.cdan.negocio.financeiro.MotivoDeRenegociacaoDeParcelasDao;

public class MotivoDeRenegociacaoDeParcelasFabricaTest {
	private static MotivoDeRenegociacaoDeParcelasFabricaTest instance = null;

	public static synchronized MotivoDeRenegociacaoDeParcelasFabricaTest getInstance() {
		if (instance == null) {
			instance = new MotivoDeRenegociacaoDeParcelasFabricaTest();
		}
		return instance;
	}

	public MotivoDeRenegociacaoDeParcelas criaMotivoDeRenegociacaoDeParcelas() {
		MotivoDeRenegociacaoDeParcelas m = new MotivoDeRenegociacaoDeParcelas();
		m.setAtivo(Boolean.TRUE);
		m.setDescricao("descricao");
		m.setCompartilhado(Boolean.TRUE);
		return m;
	}

	public MotivoDeRenegociacaoDeParcelas criaMotivoDeRenegociacaoDeParcelasPersistido(EntityManager em) {
		MotivoDeRenegociacaoDeParcelas m = criaMotivoDeRenegociacaoDeParcelas();
		MotivoDeRenegociacaoDeParcelasDao dao = new MotivoDeRenegociacaoDeParcelasDao(em);
		//
		dao.persist(m);
		return m;
	}
}
