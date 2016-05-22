package br.com.cdan.negocio.teste.pedagogico.diario;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.diario.ControleDeFrequencia;
import br.com.cdan.negocio.pedagogico.diario.ControleDeFrequenciaDao;
import br.com.cdan.negocio.pedagogico.diario.factory.ControleDeFrequenciaFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class ControleDeFrequenciaDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(ControleDeFrequenciaDAOTeste.class);
	ControleDeFrequenciaDao dao;

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
		dao = new ControleDeFrequenciaDao(getEntityManager());
	}

	@Test
	public void inserir() {
		ControleDeFrequencia a = criaControleDeFrequencia();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		ControleDeFrequencia consulta = dao.find(ControleDeFrequencia.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		ControleDeFrequencia a = criaControleDeFrequencia();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		ControleDeFrequencia consulta = dao.find(ControleDeFrequencia.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		ControleDeFrequencia a = criaControleDeFrequencia();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		ControleDeFrequencia consulta = dao.find(ControleDeFrequencia.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(ControleDeFrequencia.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		ControleDeFrequencia a1 = criaControleDeFrequencia();
		dao.persist(a1);
		ControleDeFrequencia a2 = criaControleDeFrequencia();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM ControleDeFrequencia a";
		TypedQuery<ControleDeFrequencia> query = dao.getEntityManager().createQuery(sql, ControleDeFrequencia.class);
		//
		List<ControleDeFrequencia> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		ControleDeFrequencia a = criaControleDeFrequencia();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private ControleDeFrequencia criaControleDeFrequencia() {
		return ControleDeFrequenciaFabricaTest.getInstance().criaControleDeFrequencia(getEntityManager());
	}
}