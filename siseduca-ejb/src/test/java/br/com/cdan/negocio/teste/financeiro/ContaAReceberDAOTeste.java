package br.com.cdan.negocio.teste.financeiro;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Query;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.financeiro.ContaAReceber;
import br.com.cdan.negocio.financeiro.ContaAReceberDao;
import br.com.cdan.negocio.financeiro.factory.ContaAReceberFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class ContaAReceberDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(ContaAReceberDAOTeste.class);
	ContaAReceberDao dao;

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
		dao = new ContaAReceberDao(getEntityManager());
	}

	@Test
	public void inserir() {
		ContaAReceber a = criaContaAReceber();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		ContaAReceber consulta = dao.find(ContaAReceber.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		ContaAReceber a = criaContaAReceber();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setComplementoPlanoDeConta("complementoPlanoDeConta");
		a.setDataCompetenciaPlanoDeConta(Calendar.getInstance());
		a.setDataVencimento(Calendar.getInstance());
		a.setDocumentoPlanoDeConta("documentoPlanoDeConta");
		dao.merge(a);
		ContaAReceber consulta = dao.find(ContaAReceber.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		ContaAReceber a = criaContaAReceber();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		ContaAReceber consulta = dao.find(ContaAReceber.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(ContaAReceber.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		ContaAReceber a1 = criaContaAReceber();
		dao.persist(a1);
		ContaAReceber a2 = criaContaAReceber();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM ContaAReceber a";
		Query query = dao.getEntityManager().createQuery(sql, ContaAReceber.class);
		//
		@SuppressWarnings("unchecked")
		List<ContaAReceber> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		ContaAReceber a = criaContaAReceber();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private ContaAReceber criaContaAReceber() {
		return ContaAReceberFabricaTest.getInstance().criaContaAReceber(getEntityManager());
	}
}