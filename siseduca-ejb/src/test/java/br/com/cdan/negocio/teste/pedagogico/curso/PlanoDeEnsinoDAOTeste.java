package br.com.cdan.negocio.teste.pedagogico.curso;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.curso.PlanoDeEnsino;
import br.com.cdan.negocio.pedagogico.curso.PlanoDeEnsinoDao;
import br.com.cdan.negocio.pedagogico.curso.factory.PlanoDeEnsinoFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class PlanoDeEnsinoDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(PlanoDeEnsinoDAOTeste.class);
	PlanoDeEnsinoDao dao;

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
		dao = new PlanoDeEnsinoDao(getEntityManager());
	}

	@Test
	public void inserir() {
		PlanoDeEnsino a = criaPlanoDeEnsino();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		PlanoDeEnsino consulta = dao.find(PlanoDeEnsino.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		PlanoDeEnsino a = criaPlanoDeEnsino();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		PlanoDeEnsino consulta = dao.find(PlanoDeEnsino.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		PlanoDeEnsino a = criaPlanoDeEnsino();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		PlanoDeEnsino consulta = dao.find(PlanoDeEnsino.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(PlanoDeEnsino.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		PlanoDeEnsino a1 = criaPlanoDeEnsino();
		dao.persist(a1);
		PlanoDeEnsino a2 = criaPlanoDeEnsino();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM PlanoDeEnsino a";
		TypedQuery<PlanoDeEnsino> query = dao.getEntityManager().createQuery(sql, PlanoDeEnsino.class);
		//
		List<PlanoDeEnsino> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		PlanoDeEnsino a = criaPlanoDeEnsino();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ementa_nulo() {
		PlanoDeEnsino a = criaPlanoDeEnsino();
		a.setEmenta(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ementa_vazio() {
		PlanoDeEnsino a = criaPlanoDeEnsino();
		a.setEmenta("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void ementa_menor_que_tamanho_minimo_permitido() {
		PlanoDeEnsino a = criaPlanoDeEnsino();
		a.setEmenta(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_ementa() {
		PlanoDeEnsino a = criaPlanoDeEnsino();
		dao.persist(a);
		//
		String sql = "SELECT a FROM PlanoDeEnsino a WHERE a.ementa = :ementa";
		TypedQuery<PlanoDeEnsino> query = dao.getEntityManager().createQuery(sql, PlanoDeEnsino.class);
		query.setParameter("ementa", a.getEmenta());
		//
		PlanoDeEnsino m = query.getSingleResult(); //
		//
		Assert.assertEquals(a.hashCode(), m.hashCode());
	}

	private PlanoDeEnsino criaPlanoDeEnsino() {
		return PlanoDeEnsinoFabricaTest.getInstance().criaPlanoDeEnsino(getEntityManager());
	}
}