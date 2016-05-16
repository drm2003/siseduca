package br.com.cdan.negocio.teste.biblioteca;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.biblioteca.SerieColecaoLiteral;
import br.com.cdan.negocio.biblioteca.SerieColecaoLiteralDao;
import br.com.cdan.negocio.biblioteca.factory.SerieColecaoLiteralFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class SerieColecaoLiteralDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(SerieColecaoLiteralDAOTeste.class);
	SerieColecaoLiteralDao dao;

	/**
	 * <c> Ao criar um teste da camada de persist�ncia utilizando o JUnit �
	 * preciso ter acesso ao cont\exto de persist�ncia fornecido pelo JPA.
	 * <c> Deste modo, antes da execu��o dos testes fornecemos este acesso
	 * � camada de persist�ncia por meio de uma inst�ncia
	 * <code>EntityManager</code> gerada pela <code>PersistenciaJUnit</code>.
	 * 
	 * @throws Exception
	 * @see br.jus.tjdft.siscor.util.PersistenciaJUnit
	 */
	@Before
	public void setUp() throws Exception {
		LOG.info("Instanciando DAOTest.");
		dao = new SerieColecaoLiteralDao(getEntityManager());
	}

	@Test
	public void inserir() {
		SerieColecaoLiteral a = criaSerieColecaoLiteral();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		SerieColecaoLiteral consulta = dao.find(SerieColecaoLiteral.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSER��O
	}

	@Test
	public void alterar() {
		SerieColecaoLiteral a = criaSerieColecaoLiteral();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setDescricao("");
		dao.merge(a);
		SerieColecaoLiteral consulta = dao.find(SerieColecaoLiteral.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSER��O
	}

	@Test
	public void excluir() {
		SerieColecaoLiteral a = criaSerieColecaoLiteral();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		SerieColecaoLiteral consulta = dao.find(SerieColecaoLiteral.class, a.getId());// CONSULTA
		consulta.setAtivo(false);
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(SerieColecaoLiteral.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		SerieColecaoLiteral a1 = criaSerieColecaoLiteral();
		dao.persist(a1);
		SerieColecaoLiteral a2 = criaSerieColecaoLiteral();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM SerieColecaoLiteral a";
		Query query = dao.getEntityManager().createQuery(sql, SerieColecaoLiteral.class);
		//
		@SuppressWarnings("unchecked")
		List<SerieColecaoLiteral> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test
	public void consultar_por_descricao() {
		SerieColecaoLiteral a = criaSerieColecaoLiteral();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "FROM SerieColecaoLiteral a WHERE a.descricao = :descricao";
		TypedQuery<SerieColecaoLiteral> query = dao.getEntityManager().createQuery(sql, SerieColecaoLiteral.class);
		query.setParameter("descricao", a.getDescricao());
		SerieColecaoLiteral consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		SerieColecaoLiteral a = criaSerieColecaoLiteral();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		SerieColecaoLiteral a = criaSerieColecaoLiteral();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		SerieColecaoLiteral a = criaSerieColecaoLiteral();
		a.setDescricao(criarStringDinamicaPorTamanho(356));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		SerieColecaoLiteral a = criaSerieColecaoLiteral();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		SerieColecaoLiteral a = criaSerieColecaoLiteral();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private SerieColecaoLiteral criaSerieColecaoLiteral() {
		return SerieColecaoLiteralFabricaTest.getInstance().criaSerieColecaoLiteral();
	}
}