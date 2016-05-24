package br.com.cdan.negocio.teste.pedagogico;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.MediaAposRecuperacao;
import br.com.cdan.negocio.pedagogico.MediaAposRecuperacaoDao;
import br.com.cdan.negocio.pedagogico.factory.MediaAposRecuperacaoFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class MediaAposRecuperacaoDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(MediaAposRecuperacaoDAOTeste.class);
	MediaAposRecuperacaoDao dao;

	/**
	 * <c> Ao criar um teste da camada de persistência utilizando o JUnit é
	 * preciso ter acesso ao cont\exto de persistência fornecido pelo JPA.
	 * <c> Deste modo, antes da execução dos testes fornecemos este acesso é
	 * camada de persistência por meio de uma instância
	 * <code>EntityManager</code> gerada pela <code>PersistenciaJUnit</code>.
	 * 
	 * @throws Exception
	 * @see br.jus.tjdft.siscor.util.PersistenciaJUnit
	 */
	@Before
	public void setUp() throws Exception {
		LOG.info("Instanciando DAOTest.");
		dao = new MediaAposRecuperacaoDao(getEntityManager());
	}

	@Test
	public void inserir() {
		MediaAposRecuperacao a = criaMediaAposRecuperacao();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		MediaAposRecuperacao consulta = dao.find(MediaAposRecuperacao.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		MediaAposRecuperacao a = criaMediaAposRecuperacao();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		MediaAposRecuperacao consulta = dao.find(MediaAposRecuperacao.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		MediaAposRecuperacao a = criaMediaAposRecuperacao();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		MediaAposRecuperacao consulta = dao.find(MediaAposRecuperacao.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(MediaAposRecuperacao.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		MediaAposRecuperacao a1 = criaMediaAposRecuperacao();
		dao.persist(a1);
		MediaAposRecuperacao a2 = criaMediaAposRecuperacao();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM MediaAposRecuperacao a";
		TypedQuery<MediaAposRecuperacao> query = dao.getEntityManager().createQuery(sql, MediaAposRecuperacao.class);
		//
		List<MediaAposRecuperacao> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		MediaAposRecuperacao a = criaMediaAposRecuperacao();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private MediaAposRecuperacao criaMediaAposRecuperacao() {
		return MediaAposRecuperacaoFabricaTest.getInstance().criaMediaAposRecuperacao(getEntityManager());
	}
}