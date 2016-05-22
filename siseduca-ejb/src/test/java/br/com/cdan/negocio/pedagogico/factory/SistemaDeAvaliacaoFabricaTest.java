package br.com.cdan.negocio.pedagogico.factory;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.cdan.comum.EnumCalculoMediaFinal;
import br.com.cdan.comum.EnumPeriodoAvaliacao;
import br.com.cdan.comum.EnumTipoDeAvaliacao;
import br.com.cdan.comum.EnumValorAvaliacao;
import br.com.cdan.model.pedagogico.SistemaDeAvaliacao;
import br.com.cdan.negocio.comum.FabricaTest;
import br.com.cdan.negocio.pedagogico.SistemaDeAvaliacaoDao;

public class SistemaDeAvaliacaoFabricaTest extends FabricaTest {
	private static SistemaDeAvaliacaoFabricaTest instance = null;

	public static synchronized SistemaDeAvaliacaoFabricaTest getInstance() {
		if (instance == null) {
			instance = new SistemaDeAvaliacaoFabricaTest();
		}
		return instance;
	}

	public SistemaDeAvaliacao criaSistemaDeAvaliacao(EntityManager em) {
		SistemaDeAvaliacao s = new SistemaDeAvaliacao();
		s.setAtivo(Boolean.TRUE);
		s.setNome(criarStringDinamicaPorTamanho(100));
		s.setEnumCalculoMediaFinal(EnumCalculoMediaFinal.MEDIAAUTOMATICA);
		s.setEnumPeriodoAvaliacao(EnumPeriodoAvaliacao.BIMESTRE);
		s.setEnumTipoDeAvaliacao(EnumTipoDeAvaliacao.CONCEITOS);
		s.setEnumValorAvaliacao(EnumValorAvaliacao.zeroAdez);
		s.setExameFinal(ExameFinalFabricaTest.getInstance().criaExameFinalPersistido(em));
		s.setFrequenciaMinima(BigDecimal.ZERO);
		s.setMediaMinima(BigDecimal.ONE);
		s.setNome(criarStringDinamicaPorTamanho(100));
		s.setNotasParciais(NotasParciaisFabricaTest.getInstance().criaNotasParciaisPersistido(em));
		s.setPadrao(Boolean.TRUE);
		s.setPesoDaMedia(BigDecimal.TEN);
		s.setPesoPontos(BigDecimal.TEN);
		s.setRecuperacao(RecuperacaoFabricaTest.getInstance().criaRecuperacaoPersisito(em));
		s.setSintese(criarStringDinamicaPorTamanho(100));
		return s;
	}

	public SistemaDeAvaliacao criaSistemaDeAvaliacaoPersistido(EntityManager em) {
		SistemaDeAvaliacao s = criaSistemaDeAvaliacao(em);
		SistemaDeAvaliacaoDao dao = new SistemaDeAvaliacaoDao(em);
		//
		dao.persist(s);
		return s;
	}
}