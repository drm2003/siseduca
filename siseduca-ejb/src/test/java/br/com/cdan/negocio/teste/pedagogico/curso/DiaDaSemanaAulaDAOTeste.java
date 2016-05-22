package br.com.cdan.negocio.teste.pedagogico.curso;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.curso.DiaDaSemanaAula;
import br.com.cdan.negocio.pedagogico.curso.DiaDaSemanaAulaDao;
import br.com.cdan.negocio.pedagogico.curso.factory.DiaDaSemanaAulaFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class DiaDaSemanaAulaDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(DiaDaSemanaAulaDAOTeste.class);
	DiaDaSemanaAulaDao dao;

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
		dao = new DiaDaSemanaAulaDao(getEntityManager());
	}

	@Test
	public void inserir() {
		DiaDaSemanaAula a = criaDiaDaSemanaAula();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		DiaDaSemanaAula consulta = dao.find(DiaDaSemanaAula.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		DiaDaSemanaAula a = criaDiaDaSemanaAula();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		DiaDaSemanaAula consulta = dao.find(DiaDaSemanaAula.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		DiaDaSemanaAula a = criaDiaDaSemanaAula();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		DiaDaSemanaAula consulta = dao.find(DiaDaSemanaAula.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(DiaDaSemanaAula.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		DiaDaSemanaAula a1 = criaDiaDaSemanaAula();
		dao.persist(a1);
		DiaDaSemanaAula a2 = criaDiaDaSemanaAula();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM DiaDaSemanaAula a";
		TypedQuery<DiaDaSemanaAula> query = dao.getEntityManager().createQuery(sql, DiaDaSemanaAula.class);
		//
		List<DiaDaSemanaAula> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		DiaDaSemanaAula a = criaDiaDaSemanaAula();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private DiaDaSemanaAula criaDiaDaSemanaAula() {
		return DiaDaSemanaAulaFabricaTest.getInstance().criaDiaDaSemanaAula(getEntityManager());
	}
}