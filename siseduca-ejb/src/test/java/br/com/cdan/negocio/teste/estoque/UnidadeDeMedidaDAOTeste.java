package br.com.cdan.negocio.teste.estoque;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.estoque.UnidadeDeMedida;
import br.com.cdan.negocio.estoque.UnidadeDeMedidaDao;
import br.com.cdan.negocio.estoque.factory.UnidadeDeMedidaFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class UnidadeDeMedidaDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(UnidadeDeMedidaDAOTeste.class);
	UnidadeDeMedidaDao dao;

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
		dao = new UnidadeDeMedidaDao(getEntityManager());
	}

	@Test
	public void inserir() {
		UnidadeDeMedida a = criaUnidadeDeMedida();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		UnidadeDeMedida consulta = dao.find(UnidadeDeMedida.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		UnidadeDeMedida a = criaUnidadeDeMedida();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setDescricao("");
		dao.merge(a);
		UnidadeDeMedida consulta = dao.find(UnidadeDeMedida.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		UnidadeDeMedida a = criaUnidadeDeMedida();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		UnidadeDeMedida consulta = dao.find(UnidadeDeMedida.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(UnidadeDeMedida.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		UnidadeDeMedida a1 = criaUnidadeDeMedida();
		dao.persist(a1);
		UnidadeDeMedida a2 = criaUnidadeDeMedida();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM UnidadeDeMedida a";
		Query query = dao.getEntityManager().createQuery(sql, UnidadeDeMedida.class);
		//
		@SuppressWarnings("unchecked")
		List<UnidadeDeMedida> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test
	public void consultar_por_descricao() {
		UnidadeDeMedida a = criaUnidadeDeMedida();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "FROM UnidadeDeMedida a WHERE a.descricao = :descricao";
		TypedQuery<UnidadeDeMedida> query = dao.getEntityManager().createQuery(sql, UnidadeDeMedida.class);
		query.setParameter("descricao", a.getDescricao());
		UnidadeDeMedida consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		UnidadeDeMedida a = criaUnidadeDeMedida();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		UnidadeDeMedida a = criaUnidadeDeMedida();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		UnidadeDeMedida a = criaUnidadeDeMedida();
		a.setDescricao(criarStringDinamicaPorTamanho(101));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		UnidadeDeMedida a = criaUnidadeDeMedida();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		UnidadeDeMedida a = criaUnidadeDeMedida();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private UnidadeDeMedida criaUnidadeDeMedida() {
		return UnidadeDeMedidaFabricaTest.getInstance().criaUnidadeDeMedida();
	}
}