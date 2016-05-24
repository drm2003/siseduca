package br.com.cdan.negocio.teste.pedagogico;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.TipoDeDisciplina;
import br.com.cdan.negocio.pedagogico.TipoDeDisciplinaDao;
import br.com.cdan.negocio.pedagogico.factory.TipoDeDisciplinaFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class TipoDeDisciplinaDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(TipoDeDisciplinaDAOTeste.class);
	TipoDeDisciplinaDao dao;

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
		dao = new TipoDeDisciplinaDao(getEntityManager());
	}

	@Test
	public void inserir() {
		TipoDeDisciplina a = criaTipoDeDisciplina();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		TipoDeDisciplina consulta = dao.find(TipoDeDisciplina.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		TipoDeDisciplina a = criaTipoDeDisciplina();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		TipoDeDisciplina consulta = dao.find(TipoDeDisciplina.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		TipoDeDisciplina a = criaTipoDeDisciplina();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		TipoDeDisciplina consulta = dao.find(TipoDeDisciplina.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(TipoDeDisciplina.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		TipoDeDisciplina a1 = criaTipoDeDisciplina();
		dao.persist(a1);
		TipoDeDisciplina a2 = criaTipoDeDisciplina();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM TipoDeDisciplina a";
		TypedQuery<TipoDeDisciplina> query = dao.getEntityManager().createQuery(sql, TipoDeDisciplina.class);
		//
		List<TipoDeDisciplina> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		TipoDeDisciplina a = criaTipoDeDisciplina();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		TipoDeDisciplina a = criaTipoDeDisciplina();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		TipoDeDisciplina a = criaTipoDeDisciplina();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		TipoDeDisciplina a = criaTipoDeDisciplina();
		a.setDescricao(criarStringDinamicaPorTamanho(1001));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_descricao() {
		TipoDeDisciplina a = criaTipoDeDisciplina();
		dao.persist(a);
		//
		String sql = "SELECT a FROM TipoDeDisciplina a WHERE a.descricao = :descricao";
		TypedQuery<TipoDeDisciplina> query = dao.getEntityManager().createQuery(sql, TipoDeDisciplina.class);
		query.setParameter("descricao", a.getDescricao());
		//
		TipoDeDisciplina m = query.getSingleResult(); //
		//
		Assert.assertEquals(a.hashCode(), m.hashCode());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		TipoDeDisciplina a = criaTipoDeDisciplina();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private TipoDeDisciplina criaTipoDeDisciplina() {
		return TipoDeDisciplinaFabricaTest.getInstance().criaTipoDeDisciplina();
	}
}