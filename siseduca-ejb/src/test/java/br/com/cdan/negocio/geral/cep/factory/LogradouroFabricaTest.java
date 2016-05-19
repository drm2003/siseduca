package br.com.cdan.negocio.geral.cep.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.geral.cep.EnumChaveCidade;
import br.com.cdan.model.geral.cep.EnumFlagTipoDeCep;
import br.com.cdan.model.geral.cep.EnumLadoDaRua;
import br.com.cdan.model.geral.cep.Logradouro;
import br.com.cdan.negocio.comum.FabricaTest;
import br.com.cdan.negocio.geral.cep.LogradouroDao;

public class LogradouroFabricaTest extends FabricaTest {
	private static LogradouroFabricaTest instance = null;

	public static synchronized LogradouroFabricaTest getInstance() {
		if (instance == null) {
			instance = new LogradouroFabricaTest();
		}
		return instance;
	}

	public Logradouro criaLogradouro() {
		Logradouro l = new Logradouro();
		l.setAtivo(Boolean.TRUE);
		l.setChave(EnumChaveCidade.C);
		l.setBairroFinalLogradouro(criarStringDinamicaPorTamanho(30));
		l.setBairroInicioLogradouro(criarStringDinamicaPorTamanho(30));
		l.setCep(criarStringDinamicaPorTamanho(20));
		l.setFlagTipoDeCep(EnumFlagTipoDeCep.SEIS);
		l.setLadoDaRua(EnumLadoDaRua.DUZENTOSEQUARENTA);
		l.setLimiteInferiorLadoImpar(criarStringDinamicaPorTamanho(8));
		l.setLimiteInferiorLadoPar(criarStringDinamicaPorTamanho(8));
		l.setLimiteSuperiorLadoImpar(criarStringDinamicaPorTamanho(8));
		l.setLimiteSuperiorLadoPar(criarStringDinamicaPorTamanho(8));
		l.setNome(criarStringDinamicaPorTamanho(50));
		l.setPreposicaoInicialLogradouro(criarStringDinamicaPorTamanho(15));
		l.setTipoLogradouro(criarStringDinamicaPorTamanho(15));
		l.setTituloLogradouro(criarStringDinamicaPorTamanho(15));
		l.setUF(criarStringDinamicaPorTamanho(2));
		return l;
	}

	public Logradouro criaLogradouroPersistido(EntityManager em) {
		Logradouro l = criaLogradouro();
		LogradouroDao dao = new LogradouroDao(em);
		//
		dao.persist(l);
		return l;
	}

}
