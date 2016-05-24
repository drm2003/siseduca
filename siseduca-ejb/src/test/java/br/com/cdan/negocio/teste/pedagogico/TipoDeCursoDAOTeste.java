package br.com.cdan.negocio.teste.pedagogico;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.TipoDeCurso;
import br.com.cdan.negocio.pedagogico.TipoDeCursoDao;
import br.com.cdan.negocio.pedagogico.factory.TipoDeCursoFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class TipoDeCursoDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(TipoDeCursoDAOTeste.class);
	TipoDeCursoDao dao;

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
		dao = new TipoDeCursoDao(getEntityManager());
	}

	@Test
	public void inserir() {
		TipoDeCurso a = criaTipoDeCurso();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		TipoDeCurso consulta = dao.find(TipoDeCurso.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		TipoDeCurso a = criaTipoDeCurso();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		TipoDeCurso consulta = dao.find(TipoDeCurso.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		TipoDeCurso a = criaTipoDeCurso();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		TipoDeCurso consulta = dao.find(TipoDeCurso.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(TipoDeCurso.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		TipoDeCurso a1 = criaTipoDeCurso();
		dao.persist(a1);
		TipoDeCurso a2 = criaTipoDeCurso();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM TipoDeCurso a";
		TypedQuery<TipoDeCurso> query = dao.getEntityManager().createQuery(sql, TipoDeCurso.class);
		//
		List<TipoDeCurso> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		TipoDeCurso a = criaTipoDeCurso();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		TipoDeCurso a = criaTipoDeCurso();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		TipoDeCurso a = criaTipoDeCurso();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		TipoDeCurso a = criaTipoDeCurso();
		a.setDescricao(criarStringDinamicaPorTamanho(1001));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_descricao() {
		TipoDeCurso a = criaTipoDeCurso();
		dao.persist(a);
		//
		String sql = "SELECT a FROM TipoDeCurso a WHERE a.descricao = :descricao";
		TypedQuery<TipoDeCurso> query = dao.getEntityManager().createQuery(sql, TipoDeCurso.class);
		query.setParameter("descricao", a.getDescricao());
		//
		TipoDeCurso m = query.getSingleResult(); //
		//
		Assert.assertEquals(a.hashCode(), m.hashCode());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_compartilhado_nulo() {
		TipoDeCurso a = criaTipoDeCurso();
		a.setCompartilhado(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		TipoDeCurso a = criaTipoDeCurso();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private TipoDeCurso criaTipoDeCurso() {
		return TipoDeCursoFabricaTest.getInstance().criaTipoDeCurso(getEntityManager());
	}
}