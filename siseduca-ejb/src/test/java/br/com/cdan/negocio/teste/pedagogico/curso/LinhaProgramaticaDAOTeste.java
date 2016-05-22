package br.com.cdan.negocio.teste.pedagogico.curso;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.curso.LinhaProgramatica;
import br.com.cdan.negocio.pedagogico.curso.LinhaProgramaticaDao;
import br.com.cdan.negocio.pedagogico.curso.factory.LinhaProgramaticaFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class LinhaProgramaticaDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(LinhaProgramaticaDAOTeste.class);
	LinhaProgramaticaDao dao;

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
		dao = new LinhaProgramaticaDao(getEntityManager());
	}

	@Test
	public void inserir() {
		LinhaProgramatica a = criaLinhaProgramatica();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		LinhaProgramatica consulta = dao.find(LinhaProgramatica.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		LinhaProgramatica a = criaLinhaProgramatica();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		LinhaProgramatica consulta = dao.find(LinhaProgramatica.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		LinhaProgramatica a = criaLinhaProgramatica();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		LinhaProgramatica consulta = dao.find(LinhaProgramatica.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(LinhaProgramatica.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		LinhaProgramatica a1 = criaLinhaProgramatica();
		dao.persist(a1);
		LinhaProgramatica a2 = criaLinhaProgramatica();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM LinhaProgramatica a";
		TypedQuery<LinhaProgramatica> query = dao.getEntityManager().createQuery(sql, LinhaProgramatica.class);
		//
		List<LinhaProgramatica> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		LinhaProgramatica a = criaLinhaProgramatica();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private LinhaProgramatica criaLinhaProgramatica() {
		return LinhaProgramaticaFabricaTest.getInstance().criaLinhaProgramatica(getEntityManager());
	}
}