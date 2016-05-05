package br.com.cdan.negocio.biblioteca.factory;

import java.math.BigDecimal;

import br.com.cdan.comum.EnumCalculoMediaFinal;
import br.com.cdan.comum.EnumPeriodoAvaliacao;
import br.com.cdan.comum.EnumTipoDeAvaliacao;
import br.com.cdan.comum.EnumValorAvaliacao;
import br.com.cdan.model.pedagogico.SistemaDeAvaliacao;

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
		s.setPadrao(Boolean.TRUE));
		s.setPesoDaMedia(BigDecimal.TEN);
		s.setPesoPontos(BigDecimal.TEN);
		s.setRecuperacao(RecuperacaoFabricaTest.getInstance().criaRecuperacao());
		s.setSintese("sintese");
		return s;
	}
}