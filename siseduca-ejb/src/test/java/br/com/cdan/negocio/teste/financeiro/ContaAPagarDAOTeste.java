package br.com.cdan.negocio.teste.financeiro;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Query;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.financeiro.ContaAPagar;
import br.com.cdan.negocio.financeiro.ContaAPagarDao;
import br.com.cdan.negocio.financeiro.factory.ContaAPagarFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class ContaAPagarDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(ContaAPagarDAOTeste.class);
	ContaAPagarDao dao;

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
		dao = new ContaAPagarDao(getEntityManager());
	}

	@Test
	public void inserir() {
		ContaAPagar a = criaContaAPagar();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		ContaAPagar consulta = dao.find(ContaAPagar.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		ContaAPagar a = criaContaAPagar();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setComplementoPlanoDeConta("complementoPlanoDeConta");
		a.setDataCompetenciaPlanoDeConta(Calendar.getInstance());
		a.setDataVencimento(Calendar.getInstance());
		dao.merge(a);
		ContaAPagar consulta = dao.find(ContaAPagar.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		ContaAPagar a = criaContaAPagar();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		ContaAPagar consulta = dao.find(ContaAPagar.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(ContaAPagar.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		ContaAPagar a1 = criaContaAPagar();
		dao.persist(a1);
		ContaAPagar a2 = criaContaAPagar();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM ContaAPagar a";
		Query query = dao.getEntityManager().createQuery(sql, ContaAPagar.class);
		//
		@SuppressWarnings("unchecked")
		List<ContaAPagar> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		ContaAPagar a = criaContaAPagar();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private ContaAPagar criaContaAPagar() {
		return ContaAPagarFabricaTest.getInstance().criaContaAPagar(getEntityManager());
	}
}