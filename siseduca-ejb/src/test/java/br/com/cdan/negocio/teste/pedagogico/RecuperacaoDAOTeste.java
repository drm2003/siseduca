package br.com.cdan.negocio.teste.pedagogico;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.Recuperacao;
import br.com.cdan.negocio.pedagogico.RecuperacaoDao;
import br.com.cdan.negocio.pedagogico.factory.RecuperacaoFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class RecuperacaoDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(RecuperacaoDAOTeste.class);
	RecuperacaoDao dao;

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
		dao = new RecuperacaoDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Recuperacao a = criaRecuperacao();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Recuperacao consulta = dao.find(Recuperacao.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		Recuperacao a = criaRecuperacao();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		Recuperacao consulta = dao.find(Recuperacao.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		Recuperacao a = criaRecuperacao();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Recuperacao consulta = dao.find(Recuperacao.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Recuperacao.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		Recuperacao a1 = criaRecuperacao();
		dao.persist(a1);
		Recuperacao a2 = criaRecuperacao();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Recuperacao a";
		TypedQuery<Recuperacao> query = dao.getEntityManager().createQuery(sql, Recuperacao.class);
		//
		List<Recuperacao> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		Recuperacao a = criaRecuperacao();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private Recuperacao criaRecuperacao() {
		return RecuperacaoFabricaTest.getInstance().criaRecuperacao(getEntityManager());
	}
}