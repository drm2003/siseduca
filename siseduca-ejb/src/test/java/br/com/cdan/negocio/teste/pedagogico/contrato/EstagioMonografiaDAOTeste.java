package br.com.cdan.negocio.teste.pedagogico.contrato;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.contrato.EstagioMonografia;
import br.com.cdan.negocio.pedagogico.contrato.EstagioMonografiaDao;
import br.com.cdan.negocio.pedagogico.contrato.factory.EstagioMonografiaFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class EstagioMonografiaDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(EstagioMonografiaDAOTeste.class);
	EstagioMonografiaDao dao;

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
		dao = new EstagioMonografiaDao(getEntityManager());
	}

	@Test
	public void inserir() {
		EstagioMonografia a = criaEstagioMonografia();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		EstagioMonografia consulta = dao.find(EstagioMonografia.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		EstagioMonografia a = criaEstagioMonografia();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		EstagioMonografia consulta = dao.find(EstagioMonografia.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		EstagioMonografia a = criaEstagioMonografia();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		EstagioMonografia consulta = dao.find(EstagioMonografia.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(EstagioMonografia.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		EstagioMonografia a1 = criaEstagioMonografia();
		dao.persist(a1);
		EstagioMonografia a2 = criaEstagioMonografia();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM EstagioMonografia a";
		TypedQuery<EstagioMonografia> query = dao.getEntityManager().createQuery(sql, EstagioMonografia.class);
		//
		List<EstagioMonografia> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		EstagioMonografia a = criaEstagioMonografia();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private EstagioMonografia criaEstagioMonografia() {
		return EstagioMonografiaFabricaTest.getInstance().criaEstagioMonografia(getEntityManager());
	}
}