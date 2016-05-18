package br.com.cdan.negocio.teste.estoque;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.estoque.TipoDeSaida;
import br.com.cdan.negocio.estoque.TipoDeSaidaDao;
import br.com.cdan.negocio.estoque.factory.TipoDeSaidaFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class TipoDeSaidaDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(TipoDeSaidaDAOTeste.class);
	TipoDeSaidaDao dao;

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
		dao = new TipoDeSaidaDao(getEntityManager());
	}

	@Test
	public void inserir() {
		TipoDeSaida a = criaTipoDeSaida();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		TipoDeSaida consulta = dao.find(TipoDeSaida.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		TipoDeSaida a = criaTipoDeSaida();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setDescricao("");
		dao.merge(a);
		TipoDeSaida consulta = dao.find(TipoDeSaida.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		TipoDeSaida a = criaTipoDeSaida();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		TipoDeSaida consulta = dao.find(TipoDeSaida.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(TipoDeSaida.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		TipoDeSaida a1 = criaTipoDeSaida();
		dao.persist(a1);
		TipoDeSaida a2 = criaTipoDeSaida();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM TipoDeSaida a";
		Query query = dao.getEntityManager().createQuery(sql, TipoDeSaida.class);
		//
		@SuppressWarnings("unchecked")
		List<TipoDeSaida> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test
	public void consultar_por_descricao() {
		TipoDeSaida a = criaTipoDeSaida();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "FROM TipoDeSaida a WHERE a.descricao = :descricao";
		TypedQuery<TipoDeSaida> query = dao.getEntityManager().createQuery(sql, TipoDeSaida.class);
		query.setParameter("descricao", a.getDescricao());
		TipoDeSaida consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		TipoDeSaida a = criaTipoDeSaida();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		TipoDeSaida a = criaTipoDeSaida();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		TipoDeSaida a = criaTipoDeSaida();
		a.setDescricao(criarStringDinamicaPorTamanho(101));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		TipoDeSaida a = criaTipoDeSaida();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		TipoDeSaida a = criaTipoDeSaida();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private TipoDeSaida criaTipoDeSaida() {
		return TipoDeSaidaFabricaTest.getInstance().criaTipoDeSaida();
	}
}