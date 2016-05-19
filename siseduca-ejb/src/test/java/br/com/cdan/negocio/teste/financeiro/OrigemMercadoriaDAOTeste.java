package br.com.cdan.negocio.teste.financeiro;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.financeiro.OrigemMercadoria;
import br.com.cdan.negocio.financeiro.OrigemMercadoriaDao;
import br.com.cdan.negocio.financeiro.factory.OrigemMercadoriaFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class OrigemMercadoriaDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(OrigemMercadoriaDAOTeste.class);
	OrigemMercadoriaDao dao;

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
		dao = new OrigemMercadoriaDao(getEntityManager());
	}

	@Test
	public void inserir() {
		OrigemMercadoria a = criaOrigemMercadoria();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		OrigemMercadoria consulta = dao.find(OrigemMercadoria.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		OrigemMercadoria a = criaOrigemMercadoria();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setDescricao(criarStringDinamicaPorTamanho(50));
		dao.merge(a);
		OrigemMercadoria consulta = dao.find(OrigemMercadoria.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		OrigemMercadoria a = criaOrigemMercadoria();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		OrigemMercadoria consulta = dao.find(OrigemMercadoria.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(OrigemMercadoria.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		OrigemMercadoria a1 = criaOrigemMercadoria();
		dao.persist(a1);
		OrigemMercadoria a2 = criaOrigemMercadoria();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM OrigemMercadoria a";
		Query query = dao.getEntityManager().createQuery(sql, OrigemMercadoria.class);
		//
		@SuppressWarnings("unchecked")
		List<OrigemMercadoria> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test
	public void consultar_por_descricao() {
		OrigemMercadoria a = criaOrigemMercadoria();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "FROM OrigemMercadoria a WHERE a.descricao = :descricao";
		TypedQuery<OrigemMercadoria> query = dao.getEntityManager().createQuery(sql, OrigemMercadoria.class);
		query.setParameter("descricao", a.getDescricao());
		OrigemMercadoria consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		OrigemMercadoria a = criaOrigemMercadoria();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		OrigemMercadoria a = criaOrigemMercadoria();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		OrigemMercadoria a = criaOrigemMercadoria();
		a.setDescricao(criarStringDinamicaPorTamanho(251));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		OrigemMercadoria a = criaOrigemMercadoria();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		OrigemMercadoria a = criaOrigemMercadoria();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private OrigemMercadoria criaOrigemMercadoria() {
		return OrigemMercadoriaFabricaTest.getInstance().criaOrigemMercadoria();
	}
}