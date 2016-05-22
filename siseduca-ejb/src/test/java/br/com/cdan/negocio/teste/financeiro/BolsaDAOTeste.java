package br.com.cdan.negocio.teste.financeiro;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.financeiro.Bolsa;
import br.com.cdan.negocio.financeiro.BolsaDao;
import br.com.cdan.negocio.financeiro.factory.BolsaFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class BolsaDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(BolsaDAOTeste.class);
	BolsaDao dao;

	/**
	 * <c> Ao criar um teste da camada de persist�ncia utilizando o JUnit �
	 * preciso ter acesso ao cont\exto de persist�ncia fornecido pelo JPA.
	 * <c> Deste modo, antes da execu��o dos testes fornecemos este acesso �
	 * camada de persist�ncia por meio de uma inst�ncia
	 * <code>EntityManager</code> gerada pela <code>PersistenciaJUnit</code>.
	 * 
	 * @throws Exception
	 * @see br.jus.tjdft.siscor.util.PersistenciaJUnit
	 */
	@Before
	public void setUp() throws Exception {
		LOG.info("Instanciando DAOTest.");
		dao = new BolsaDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Bolsa a = criaBolsa();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Bolsa consulta = dao.find(Bolsa.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSER��O
	}

	@Test
	public void alterar() {
		Bolsa a = criaBolsa();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setDescricao("");
		dao.merge(a);
		Bolsa consulta = dao.find(Bolsa.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSER��O
	}

	@Test
	public void excluir() {
		Bolsa a = criaBolsa();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Bolsa consulta = dao.find(Bolsa.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Bolsa.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		Bolsa a1 = criaBolsa();
		dao.persist(a1);
		Bolsa a2 = criaBolsa();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Bolsa a";
		Query query = dao.getEntityManager().createQuery(sql, Bolsa.class);
		//
		@SuppressWarnings("unchecked")
		List<Bolsa> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test
	public void consultar_por_descricao() {
		Bolsa a = criaBolsa();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "FROM Bolsa a WHERE a.descricao = :descricao";
		TypedQuery<Bolsa> query = dao.getEntityManager().createQuery(sql, Bolsa.class);
		query.setParameter("descricao", a.getDescricao());
		Bolsa consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		Bolsa a = criaBolsa();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		Bolsa a = criaBolsa();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		Bolsa a = criaBolsa();
		a.setDescricao(criarStringDinamicaPorTamanho(101));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		Bolsa a = criaBolsa();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		Bolsa a = criaBolsa();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private Bolsa criaBolsa() {
		return BolsaFabricaTest.getInstance().criaBolsa(getEntityManager());
	}
}