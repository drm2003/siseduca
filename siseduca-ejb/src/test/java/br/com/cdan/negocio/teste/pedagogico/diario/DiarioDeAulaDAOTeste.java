package br.com.cdan.negocio.teste.pedagogico.diario;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.diario.DiarioDeAula;
import br.com.cdan.negocio.pedagogico.diario.DiarioDeAulaDao;
import br.com.cdan.negocio.pedagogico.diario.factory.DiarioDeAulaFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class DiarioDeAulaDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(DiarioDeAulaDAOTeste.class);
	DiarioDeAulaDao dao;

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
		dao = new DiarioDeAulaDao(getEntityManager());
	}

	@Test
	public void inserir() {
		DiarioDeAula a = criaDiarioDeAula();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		DiarioDeAula consulta = dao.find(DiarioDeAula.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		DiarioDeAula a = criaDiarioDeAula();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		DiarioDeAula consulta = dao.find(DiarioDeAula.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		DiarioDeAula a = criaDiarioDeAula();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		DiarioDeAula consulta = dao.find(DiarioDeAula.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(DiarioDeAula.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		DiarioDeAula a1 = criaDiarioDeAula();
		dao.persist(a1);
		DiarioDeAula a2 = criaDiarioDeAula();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM DiarioDeAula a";
		TypedQuery<DiarioDeAula> query = dao.getEntityManager().createQuery(sql, DiarioDeAula.class);
		//
		List<DiarioDeAula> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		DiarioDeAula a = criaDiarioDeAula();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private DiarioDeAula criaDiarioDeAula() {
		return DiarioDeAulaFabricaTest.getInstance().criaDiarioDeAula(getEntityManager());
	}
}