package br.com.cdan.negocio.teste.financeiro;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.financeiro.PlanoDeConta;
import br.com.cdan.negocio.financeiro.PlanoDeContaDao;
import br.com.cdan.negocio.financeiro.factory.PlanoDeContaFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class PlanoDeContaDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(PlanoDeContaDAOTeste.class);
	PlanoDeContaDao dao;

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
		dao = new PlanoDeContaDao(getEntityManager());
	}

	@Test
	public void inserir() {
		PlanoDeConta a = criaPlanoDeConta();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		PlanoDeConta consulta = dao.find(PlanoDeConta.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSER��O
	}

	@Test
	public void alterar() {
		PlanoDeConta a = criaPlanoDeConta();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setNome(criarStringDinamicaPorTamanho(50));
		dao.merge(a);
		PlanoDeConta consulta = dao.find(PlanoDeConta.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSER��O
	}

	@Test
	public void excluir() {
		PlanoDeConta a = criaPlanoDeConta();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		PlanoDeConta consulta = dao.find(PlanoDeConta.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(PlanoDeConta.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		PlanoDeConta a1 = criaPlanoDeConta();
		dao.persist(a1);
		PlanoDeConta a2 = criaPlanoDeConta();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM PlanoDeConta a";
		Query query = dao.getEntityManager().createQuery(sql, PlanoDeConta.class);
		//
		@SuppressWarnings("unchecked")
		List<PlanoDeConta> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test
	public void consultar_por_nome() {
		PlanoDeConta a = criaPlanoDeConta();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "FROM PlanoDeConta a WHERE a.nome = :nome";
		TypedQuery<PlanoDeConta> query = dao.getEntityManager().createQuery(sql, PlanoDeConta.class);
		query.setParameter("nome", a.getNome());
		PlanoDeConta consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_nome_nulo() {
		PlanoDeConta a = criaPlanoDeConta();
		a.setNome(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_nome_vazio() {
		PlanoDeConta a = criaPlanoDeConta();
		a.setNome("");
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nome_maior_que_tamanho_maximo_permitido() {
		PlanoDeConta a = criaPlanoDeConta();
		a.setNome(criarStringDinamicaPorTamanho(251));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nome_menor_que_tamanho_minimo_permitido() {
		PlanoDeConta a = criaPlanoDeConta();
		a.setNome(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_codigo_nulo() {
		PlanoDeConta a = criaPlanoDeConta();
		a.setCodigo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		PlanoDeConta a = criaPlanoDeConta();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private PlanoDeConta criaPlanoDeConta() {
		return PlanoDeContaFabricaTest.getInstance().criaPlanoDeContas();
	}
}