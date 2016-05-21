package br.com.cdan.negocio.teste.pedagogico.contrato;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.contrato.Dependencia;
import br.com.cdan.negocio.pedagogico.contrato.DependenciaDao;
import br.com.cdan.negocio.pedagogico.contrato.factory.DependenciaFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class DependenciaDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(DependenciaDAOTeste.class);
	DependenciaDao dao;

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
		dao = new DependenciaDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Dependencia a = criaDependencia();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Dependencia consulta = dao.find(Dependencia.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		Dependencia a = criaDependencia();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		Dependencia consulta = dao.find(Dependencia.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		Dependencia a = criaDependencia();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Dependencia consulta = dao.find(Dependencia.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Dependencia.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		Dependencia a1 = criaDependencia();
		dao.persist(a1);
		Dependencia a2 = criaDependencia();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Dependencia a";
		TypedQuery<Dependencia> query = dao.getEntityManager().createQuery(sql, Dependencia.class);
		//
		List<Dependencia> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		Dependencia a = criaDependencia();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private Dependencia criaDependencia() {
		return DependenciaFabricaTest.getInstance().criaDependencia(getEntityManager());
	}
}