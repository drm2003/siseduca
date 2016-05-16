package br.com.cdan.negocio.pedagogico.pessoa.factory;

import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.cdan.comum.EnumTipoCertidao;
import br.com.cdan.model.pessoa.Certidao;
import br.com.cdan.negocio.geral.factory.CidadeFabricaTest;
import br.com.cdan.negocio.pedagogico.pessoa.CertidaoDao;

public class CertidaoFabricaTest {
	private static CertidaoFabricaTest instance = null;

	public static synchronized CertidaoFabricaTest getInstance() {
		if (instance == null) {
			instance = new CertidaoFabricaTest();
		}
		return instance;
	}

	public Certidao criaCertidao(EntityManager em) {
		Certidao c = new Certidao();
		c.setAtivo(Boolean.TRUE);
		c.setCartorio("cartorio");
		c.setDataEmissao(Calendar.getInstance());
		c.setFolha("folha");
		c.setLivro("livro");
		c.setMunicipioCartorio(CidadeFabricaTest.getInstance().criaCidadePersistido(em));
		c.setNumeroMatricula("numeroMatricula");
		c.setNumeroTermo("numeroTermo");
		c.setTipoCertidao(EnumTipoCertidao.CASAMENTO);
		return c;
	}

	public Certidao criaCertidaoPersistido(EntityManager em) {
		Certidao c = criaCertidao(em);
		CertidaoDao dao = new CertidaoDao(em);
		//
		dao.persist(c);
		return c;
	}

}
