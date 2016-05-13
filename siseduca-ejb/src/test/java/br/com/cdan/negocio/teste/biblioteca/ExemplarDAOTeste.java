package br.com.cdan.negocio.teste.biblioteca;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.biblioteca.Exemplar;
import br.com.cdan.negocio.biblioteca.ExemplarDao;
import br.com.cdan.negocio.biblioteca.factory.ExemplarFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class ExemplarDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(ExemplarDAOTeste.class);
	ExemplarDao dao;

	/**
	 * <c> Ao criar um teste da camada de persistï¿½ncia utilizando o JUnit ï¿½
	 * preciso ter acesso ao cont\exto de persistï¿½ncia fornecido pelo JPA.
	 * <c> Deste modo, antes da execuï¿½ï¿½o dos testes fornecemos este acesso
	 * ï¿½ camada de persistï¿½ncia por meio de uma instï¿½ncia
	 * <code>EntityManager</code> gerada pela <code>PersistenciaJUnit</code>.
	 * 
	 * @throws Exception
	 * @see br.jus.tjdft.siscor.util.PersistenciaJUnit
	 */
	@Before
	public void setUp() throws Exception {
		LOG.info("Instanciando DAOTest.");
		dao = new ExemplarDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Exemplar a = criaExemplarPersistido();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Exemplar consulta = dao.find(Exemplar.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void alterar() {
		Exemplar a = criaExemplarPersistido();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setDescricao("alteração");
		dao.merge(a);
		Exemplar consulta = dao.find(Exemplar.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		Exemplar a = criaExemplarPersistido();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Exemplar consulta = dao.find(Exemplar.class, a.getId());// CONSULTA
		consulta.setAtivo(false);
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Exemplar.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		Exemplar a1 = criaExemplarPersistido();
		dao.persist(a1);
		Exemplar a2 = criaExemplarPersistido();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Exemplar a";
		TypedQuery<Exemplar> query = dao.getEntityManager().createQuery(sql, Exemplar.class);
		//
		List<Exemplar> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test
	public void consultar_por_descricao() {
		Exemplar a = criaExemplarPersistido();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "SELECT a FROM Exemplar a WHERE a.descricao = :descricao";
		Query query = dao.getEntityManager().createQuery(sql);
		query.setParameter("descricao", a.getDescricao());
		Exemplar consulta = (Exemplar) query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nula() {
		Exemplar a = criaExemplarPersistido();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazia() {
		Exemplar a = criaExemplarPersistido();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		Exemplar a = criaExemplarPersistido();
		a.setDescricao(criarStringDinamicaPorTamanho(251));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		Exemplar a = criaExemplarPersistido();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private Exemplar criaExemplarPersistido() {
		return ExemplarFabricaTest.getInstance().criaExemplarPersistido(getEntityManager());
	}
}