package br.com.cdan.negocio.teste.pedagogico.curso;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.curso.PlanoFinanceiroDoCurso;
import br.com.cdan.negocio.pedagogico.curso.PlanoFinanceiroDoCursoDao;
import br.com.cdan.negocio.pedagogico.curso.factory.PlanoFinanceiroDoCursoFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class PlanoFinanceiroDoCursoDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(PlanoFinanceiroDoCursoDAOTeste.class);
	PlanoFinanceiroDoCursoDao dao;

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
		dao = new PlanoFinanceiroDoCursoDao(getEntityManager());
	}

	@Test
	public void inserir() {
		PlanoFinanceiroDoCurso a = criaPlanoFinanceiroDoCurso();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		PlanoFinanceiroDoCurso consulta = dao.find(PlanoFinanceiroDoCurso.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		PlanoFinanceiroDoCurso a = criaPlanoFinanceiroDoCurso();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		PlanoFinanceiroDoCurso consulta = dao.find(PlanoFinanceiroDoCurso.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		PlanoFinanceiroDoCurso a = criaPlanoFinanceiroDoCurso();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		PlanoFinanceiroDoCurso consulta = dao.find(PlanoFinanceiroDoCurso.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(PlanoFinanceiroDoCurso.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		PlanoFinanceiroDoCurso a1 = criaPlanoFinanceiroDoCurso();
		dao.persist(a1);
		PlanoFinanceiroDoCurso a2 = criaPlanoFinanceiroDoCurso();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM PlanoFinanceiroDoCurso a";
		TypedQuery<PlanoFinanceiroDoCurso> query = dao.getEntityManager().createQuery(sql,
				PlanoFinanceiroDoCurso.class);
		//
		List<PlanoFinanceiroDoCurso> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		PlanoFinanceiroDoCurso a = criaPlanoFinanceiroDoCurso();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		PlanoFinanceiroDoCurso a = criaPlanoFinanceiroDoCurso();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		PlanoFinanceiroDoCurso a = criaPlanoFinanceiroDoCurso();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		PlanoFinanceiroDoCurso a = criaPlanoFinanceiroDoCurso();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_descricao() {
		PlanoFinanceiroDoCurso a = criaPlanoFinanceiroDoCurso();
		dao.persist(a);
		//
		String sql = "SELECT a FROM PlanoFinanceiroDoCurso a WHERE a.descricao = :descricao";
		TypedQuery<PlanoFinanceiroDoCurso> query = dao.getEntityManager().createQuery(sql,
				PlanoFinanceiroDoCurso.class);
		query.setParameter("descricao", a.getDescricao());
		//
		PlanoFinanceiroDoCurso m = query.getSingleResult(); //
		//
		Assert.assertEquals(a.hashCode(), m.hashCode());
	}

	private PlanoFinanceiroDoCurso criaPlanoFinanceiroDoCurso() {
		return PlanoFinanceiroDoCursoFabricaTest.getInstance().criaPlanoFinanceiroDoCurso(getEntityManager());
	}
}