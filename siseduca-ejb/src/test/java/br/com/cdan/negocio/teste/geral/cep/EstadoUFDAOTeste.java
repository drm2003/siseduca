package br.com.cdan.negocio.teste.geral.cep;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.geral.cep.EstadoUF;
import br.com.cdan.negocio.geral.EstadoUFDao;
import br.com.cdan.negocio.geral.cep.factory.EstadoUFFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class EstadoUFDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(EstadoUFDAOTeste.class);
	EstadoUFDao dao;

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
		dao = new EstadoUFDao(getEntityManager());
	}

	@Test
	public void inserir() {
		EstadoUF a = criaEstadoUF();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		EstadoUF consulta = dao.find(EstadoUF.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		EstadoUF a = criaEstadoUF();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setDescricao("");
		dao.merge(a);
		EstadoUF consulta = dao.find(EstadoUF.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		EstadoUF a = criaEstadoUF();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		EstadoUF consulta = dao.find(EstadoUF.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(EstadoUF.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		EstadoUF a1 = criaEstadoUF();
		dao.persist(a1);
		EstadoUF a2 = criaEstadoUF();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM EstadoUF a";
		Query query = dao.getEntityManager().createQuery(sql, EstadoUF.class);
		//
		@SuppressWarnings("unchecked")
		List<EstadoUF> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test
	public void consultar_por_descricao() {
		EstadoUF a = criaEstadoUF();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "FROM EstadoUF a WHERE a.descricao = :descricao";
		TypedQuery<EstadoUF> query = dao.getEntityManager().createQuery(sql, EstadoUF.class);
		query.setParameter("descricao", a.getDescricao());
		EstadoUF consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		EstadoUF a = criaEstadoUF();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private EstadoUF criaEstadoUF() {
		return EstadoUFFabricaTest.getInstance().criaEstadoUF(getEntityManager());
	}
}