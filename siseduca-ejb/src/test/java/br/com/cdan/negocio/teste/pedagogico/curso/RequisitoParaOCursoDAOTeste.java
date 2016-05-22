package br.com.cdan.negocio.teste.pedagogico.curso;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.curso.RequisitoParaOCurso;
import br.com.cdan.negocio.pedagogico.curso.RequisitoParaOCursoDao;
import br.com.cdan.negocio.pedagogico.curso.factory.RequisitoParaOCursoFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class RequisitoParaOCursoDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(RequisitoParaOCursoDAOTeste.class);
	RequisitoParaOCursoDao dao;

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
		dao = new RequisitoParaOCursoDao(getEntityManager());
	}

	@Test
	public void inserir() {
		RequisitoParaOCurso a = criaRequisitoParaOCurso();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		RequisitoParaOCurso consulta = dao.find(RequisitoParaOCurso.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		RequisitoParaOCurso a = criaRequisitoParaOCurso();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		RequisitoParaOCurso consulta = dao.find(RequisitoParaOCurso.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		RequisitoParaOCurso a = criaRequisitoParaOCurso();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		RequisitoParaOCurso consulta = dao.find(RequisitoParaOCurso.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(RequisitoParaOCurso.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		RequisitoParaOCurso a1 = criaRequisitoParaOCurso();
		dao.persist(a1);
		RequisitoParaOCurso a2 = criaRequisitoParaOCurso();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM RequisitoParaOCurso a";
		TypedQuery<RequisitoParaOCurso> query = dao.getEntityManager().createQuery(sql,
				RequisitoParaOCurso.class);
		//
		List<RequisitoParaOCurso> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		RequisitoParaOCurso a = criaRequisitoParaOCurso();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		RequisitoParaOCurso a = criaRequisitoParaOCurso();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		RequisitoParaOCurso a = criaRequisitoParaOCurso();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		RequisitoParaOCurso a = criaRequisitoParaOCurso();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_descricao() {
		RequisitoParaOCurso a = criaRequisitoParaOCurso();
		dao.persist(a);
		//
		String sql = "SELECT a FROM RequisitoParaOCurso a WHERE a.descricao = :descricao";
		TypedQuery<RequisitoParaOCurso> query = dao.getEntityManager().createQuery(sql,
				RequisitoParaOCurso.class);
		query.setParameter("descricao", a.getDescricao());
		//
		RequisitoParaOCurso m = query.getSingleResult(); //
		//
		Assert.assertEquals(a.hashCode(), m.hashCode());
	}

	private RequisitoParaOCurso criaRequisitoParaOCurso() {
		return RequisitoParaOCursoFabricaTest.getInstance().criaRequisitoParaOCurso(getEntityManager());
	}
}