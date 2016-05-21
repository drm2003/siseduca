package br.com.cdan.negocio.teste.geral;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.geral.TipoDeResponsavel;
import br.com.cdan.negocio.geral.TipoDeResponsavelDao;
import br.com.cdan.negocio.geral.factory.TipoDeResponsavelFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class TipoDeResponsavelDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(TipoDeResponsavelDAOTeste.class);
	TipoDeResponsavelDao dao;

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
		dao = new TipoDeResponsavelDao(getEntityManager());
	}

	@Test
	public void inserir() {
		TipoDeResponsavel a = criaTipoDeResponsavel();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		TipoDeResponsavel consulta = dao.find(TipoDeResponsavel.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		TipoDeResponsavel a = criaTipoDeResponsavel();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setDescricao("complemento");
		dao.merge(a);
		TipoDeResponsavel consulta = dao.find(TipoDeResponsavel.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		TipoDeResponsavel a = criaTipoDeResponsavel();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		TipoDeResponsavel consulta = dao.find(TipoDeResponsavel.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(TipoDeResponsavel.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		TipoDeResponsavel a1 = criaTipoDeResponsavel();
		dao.persist(a1);
		TipoDeResponsavel a2 = criaTipoDeResponsavel();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM TipoDeResponsavel a";
		TypedQuery<TipoDeResponsavel> query = dao.getEntityManager().createQuery(sql, TipoDeResponsavel.class);
		//
		List<TipoDeResponsavel> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		TipoDeResponsavel a = criaTipoDeResponsavel();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		TipoDeResponsavel a = criaTipoDeResponsavel();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		TipoDeResponsavel a = criaTipoDeResponsavel();
		a.setDescricao(criarStringDinamicaPorTamanho(151));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_descricao() {
		TipoDeResponsavel a = criaTipoDeResponsavel();
		dao.persist(a);
		String sql = "SELECT a FROM TipoDeResponsavel a WHERE a.descricao = :descricao";
		TypedQuery<TipoDeResponsavel> query = dao.getEntityManager().createQuery(sql, TipoDeResponsavel.class);
		query.setParameter("descricao", a.getDescricao());
		List<TipoDeResponsavel> lista = query.getResultList();
		Assert.assertTrue(lista.contains(a));
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		TipoDeResponsavel a = criaTipoDeResponsavel();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		TipoDeResponsavel a = criaTipoDeResponsavel();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private TipoDeResponsavel criaTipoDeResponsavel() {
		return TipoDeResponsavelFabricaTest.getInstance().criaTipoDeResponsavel();
	}
}