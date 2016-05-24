package br.com.cdan.negocio.teste.pedagogico;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.TipoDeSala;
import br.com.cdan.negocio.pedagogico.TipoDeSalaDao;
import br.com.cdan.negocio.pedagogico.factory.TipoDeSalaFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class TipoDeSalaDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(TipoDeSalaDAOTeste.class);
	TipoDeSalaDao dao;

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
		dao = new TipoDeSalaDao(getEntityManager());
	}

	@Test
	public void inserir() {
		TipoDeSala a = criaTipoDeSala();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		TipoDeSala consulta = dao.find(TipoDeSala.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		TipoDeSala a = criaTipoDeSala();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		TipoDeSala consulta = dao.find(TipoDeSala.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		TipoDeSala a = criaTipoDeSala();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		TipoDeSala consulta = dao.find(TipoDeSala.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(TipoDeSala.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		TipoDeSala a1 = criaTipoDeSala();
		dao.persist(a1);
		TipoDeSala a2 = criaTipoDeSala();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM TipoDeSala a";
		TypedQuery<TipoDeSala> query = dao.getEntityManager().createQuery(sql, TipoDeSala.class);
		//
		List<TipoDeSala> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		TipoDeSala a = criaTipoDeSala();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		TipoDeSala a = criaTipoDeSala();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		TipoDeSala a = criaTipoDeSala();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		TipoDeSala a = criaTipoDeSala();
		a.setDescricao(criarStringDinamicaPorTamanho(1001));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_descricao() {
		TipoDeSala a = criaTipoDeSala();
		dao.persist(a);
		//
		String sql = "SELECT a FROM TipoDeSala a WHERE a.descricao = :descricao";
		TypedQuery<TipoDeSala> query = dao.getEntityManager().createQuery(sql, TipoDeSala.class);
		query.setParameter("descricao", a.getDescricao());
		//
		TipoDeSala m = query.getSingleResult(); //
		//
		Assert.assertEquals(a.hashCode(), m.hashCode());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		TipoDeSala a = criaTipoDeSala();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private TipoDeSala criaTipoDeSala() {
		return TipoDeSalaFabricaTest.getInstance().criaTipoDeSala();
	}
}