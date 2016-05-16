package br.com.cdan.negocio.teste.biblioteca;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.biblioteca.TipoDeObra;
import br.com.cdan.negocio.biblioteca.TipoDeObraDao;
import br.com.cdan.negocio.biblioteca.factory.TipoDeObraFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class TipoDeObraDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(TipoDeObraDAOTeste.class);
	TipoDeObraDao dao;

	/**
	 * <c> Ao criar um teste da camada de persistï¿½ncia utilizando o JUnit ï¿½
	 * preciso ter acesso ao cont\exto de persistï¿½ncia fornecido pelo JPA.
	 * <c> Deste modo, antes da execuï¿½ï¿½o dos testes fornecemos este acesso
	 * ï¿½ camada de persistï¿½ncia por meio de uma instï¿½ncia
	 * <code>EntityManager</code> gerada pela <code>PersistenciaJUnit</code>.
	 * 
	 * @throws Exception
	 * @see br.jus.tjdft.siscor.util.PersistenciaJUnit
	 */
	@Before
	public void setUp() throws Exception {
		LOG.info("Instanciando DAOTest.");
		dao = new TipoDeObraDao(getEntityManager());
	}

	@Test
	public void inserir() {
		TipoDeObra a = criaTipoDeObra();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		TipoDeObra consulta = dao.find(TipoDeObra.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		TipoDeObra a = criaTipoDeObra();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setDescricao("");
		dao.merge(a);
		TipoDeObra consulta = dao.find(TipoDeObra.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		TipoDeObra a = criaTipoDeObra();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		TipoDeObra consulta = dao.find(TipoDeObra.class, a.getId());// CONSULTA
		consulta.setAtivo(false);
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(TipoDeObra.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		TipoDeObra a1 = criaTipoDeObra();
		dao.persist(a1);
		TipoDeObra a2 = criaTipoDeObra();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM TipoDeObra a";
		Query query = dao.getEntityManager().createQuery(sql, TipoDeObra.class);
		//
		@SuppressWarnings("unchecked")
		List<TipoDeObra> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test
	public void consultar_por_descricao() {
		TipoDeObra a = criaTipoDeObra();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "FROM TipoDeObra a WHERE a.descricao = :descricao";
		TypedQuery<TipoDeObra> query = dao.getEntityManager().createQuery(sql, TipoDeObra.class);
		query.setParameter("descricao", a.getDescricao());
		TipoDeObra consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		TipoDeObra a = criaTipoDeObra();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		TipoDeObra a = criaTipoDeObra();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		TipoDeObra a = criaTipoDeObra();
		a.setDescricao(criarStringDinamicaPorTamanho(256));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		TipoDeObra a = criaTipoDeObra();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		TipoDeObra a = criaTipoDeObra();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private TipoDeObra criaTipoDeObra() {
		return TipoDeObraFabricaTest.getInstance().criaTipoDeObra();
	}
}