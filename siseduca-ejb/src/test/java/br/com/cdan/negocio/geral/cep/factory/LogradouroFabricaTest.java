package br.com.cdan.negocio.geral.cep.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.geral.cep.EnumChaveCidade;
import br.com.cdan.model.geral.cep.EnumFlagTipoDeCep;
import br.com.cdan.model.geral.cep.EnumLadoDaRua;
import br.com.cdan.model.geral.cep.Logradouro;
import br.com.cdan.negocio.geral.cep.LogradouroDao;

public class LogradouroFabricaTest {
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
		l.setBairroFinalLogradouro("bairroFinalLogradouro");
		l.setBairroInicioLogradouro("bairroInicioLogradouro");
		l.setCep("cep");
		l.setFlagTipoDeCep(EnumFlagTipoDeCep.SEIS);
		l.setLadoDaRua(EnumLadoDaRua.DUZENTOSEQUARENTA);
		l.setLimiteInferiorLadoImpar("limiteInferiorLadoImpar");
		l.setLimiteInferiorLadoPar("limiteInferiorLadoPar");
		l.setLimiteSuperiorLadoImpar("limiteSuperiorLadoImpar");
		l.setLimiteSuperiorLadoPar("limiteSuperiorLadoPar");
		l.setNome("nome");
		l.setPreposicaoInicialLogradouro("preposicaoInicialLogradouro");
		l.setTipoLogradouro("tipoLogradouro");
		l.setTituloLogradouro("tituloLogradouro");
		l.setUF("uF");
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
