package br.com.cdan.negocio.biblioteca.factory;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.cdan.comum.EnumCalculoMediaFinal;
import br.com.cdan.comum.EnumPeriodoAvaliacao;
import br.com.cdan.comum.EnumTipoDeAvaliacao;
import br.com.cdan.comum.EnumValorAvaliacao;
import br.com.cdan.model.pedagogico.ExameFinal;
import br.com.cdan.model.pedagogico.NotasParciais;
import br.com.cdan.model.pedagogico.Recuperacao;
import br.com.cdan.model.pedagogico.SistemaDeAvaliacao;
import br.com.cdan.negocio.biblioteca.ExameFinalDao;
import br.com.cdan.negocio.biblioteca.NotasParciaisDao;
import br.com.cdan.negocio.biblioteca.RecuperacaoDao;
import br.com.cdan.negocio.biblioteca.SistemaDeAvaliacaoDao;

public class SistemaDeAvaliacaoFabricaTest {
	private static SistemaDeAvaliacaoFabricaTest instance = null;

	public static synchronized SistemaDeAvaliacaoFabricaTest getInstance() {
		if (instance == null) {
			instance = new SistemaDeAvaliacaoFabricaTest();
		}
		return instance;
	}

	public SistemaDeAvaliacao criaSistemaDeAvaliacao() {
		SistemaDeAvaliacao s = new SistemaDeAvaliacao();
		s.setAtivo(Boolean.TRUE);
		s.setEnumCalculoMediaFinal(EnumCalculoMediaFinal.MEDIAAUTOMATICA);
		s.setEnumPeriodoAvaliacao(EnumPeriodoAvaliacao.BIMESTRE);
		s.setEnumTipoDeAvaliacao(EnumTipoDeAvaliacao.CONCEITOS);
		s.setEnumValorAvaliacao(EnumValorAvaliacao.zeroAdez);
		s.setExameFinal(ExameFinalFabricaTest.getInstance().criaExameFinal());
		s.setFrequenciaMinima(BigDecimal.ZERO);
		s.setMediaMinima(BigDecimal.ONE);
		s.setNome("nome");
		s.setNotasParciais(NotasParciaisFabricaTest.getInstance().criaNotasParciais());
		s.setPadrao(Boolean.TRUE);
		s.setPesoDaMedia(BigDecimal.TEN);
		s.setPesoPontos(BigDecimal.TEN);
		s.setRecuperacao(RecuperacaoFabricaTest.getInstance().criaRecuperacao());
		s.setSintese("sintese");
		return s;
	}

	public SistemaDeAvaliacao criaSistemaDeAvaliacaoPersistido(EntityManager em) {
		SistemaDeAvaliacao s = criaSistemaDeAvaliacao();
		SistemaDeAvaliacaoDao dao = new SistemaDeAvaliacaoDao(em);
		//
		ExameFinalDao exameFinalDao = new ExameFinalDao(em);
		ExameFinal exameFinal = s.getExameFinal();
		exameFinalDao.persist(exameFinal);
		s.setExameFinal(exameFinal);
		//
		NotasParciaisDao notasParciaisDao = new NotasParciaisDao(em);
		NotasParciais notasParciais = s.getNotasParciais();
		notasParciaisDao.persist(notasParciais);
		s.setNotasParciais(notasParciais);
		//
		RecuperacaoDao recuperacaoDao = new RecuperacaoDao(em);
		Recuperacao recuperacao = s.getRecuperacao();
		recuperacaoDao.persist(recuperacao);
		s.setRecuperacao(recuperacao);
		//
		dao.persist(s);
		return s;
	}
}