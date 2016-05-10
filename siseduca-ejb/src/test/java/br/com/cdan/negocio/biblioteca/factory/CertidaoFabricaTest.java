package br.com.cdan.negocio.biblioteca.factory;

import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.cdan.comum.EnumTipoCertidao;
import br.com.cdan.model.geral.Cidade;
import br.com.cdan.model.pessoa.Certidao;
import br.com.cdan.model.pessoa.Pessoa;
import br.com.cdan.negocio.biblioteca.CertidaoDao;
import br.com.cdan.negocio.biblioteca.CidadeDao;
import br.com.cdan.negocio.biblioteca.PessoaDao;

public class CertidaoFabricaTest {
	private static CertidaoFabricaTest instance = null;

	public static synchronized CertidaoFabricaTest getInstance() {
		if (instance == null) {
			instance = new CertidaoFabricaTest();
		}
		return instance;
	}

	public Certidao criaCertidao() {
		Certidao c = new Certidao();
		c.setAtivo(Boolean.TRUE);
		c.setCartorio("cartorio");
		c.setDataEmissao(Calendar.getInstance());
		c.setFolha("folha");
		c.setLivro("livro");
		c.setMunicipioCartorio(CidadeFabricaTest.getInstance().criaCidade());
		c.setNumeroMatricula("numeroMatricula");
		c.setNumeroTermo("numeroTermo");
		c.setPessoa(PessoaFabricaTest.getInstance().criaPessoa());
		c.setTipoCertidao(EnumTipoCertidao.CASAMENTO);
		return c;
	}

	public Certidao criaCertidaoPersistido(EntityManager em) {
		Certidao c = criaCertidao();
		CertidaoDao dao = new CertidaoDao(em);
		//
		CidadeDao cidadeDao = new CidadeDao(em);
		Cidade municipioCartorio = c.getMunicipioCartorio();
		cidadeDao.persist(municipioCartorio);
		c.setMunicipioCartorio(municipioCartorio);
		//
		PessoaDao pessoaDao = new PessoaDao(em);
		Pessoa pessoa = c.getPessoa();
		pessoaDao.persist(pessoa);
		c.setPessoa(pessoa);
		//
		dao.persist(c);
		return c;
	}

}
