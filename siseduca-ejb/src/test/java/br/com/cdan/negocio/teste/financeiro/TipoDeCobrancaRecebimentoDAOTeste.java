package br.com.cdan.negocio.teste.financeiro;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.financeiro.TipoDeCobrancaRecebimento;
import br.com.cdan.negocio.financeiro.TipoDeCobrancaRecebimentoDao;
import br.com.cdan.negocio.financeiro.factory.TipoDeCobrancaRecebimentoFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class TipoDeCobrancaRecebimentoDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(TipoDeCobrancaRecebimentoDAOTeste.class);
	TipoDeCobrancaRecebimentoDao dao;

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
		dao = new TipoDeCobrancaRecebimentoDao(getEntityManager());
	}

	@Test
	public void inserir() {
		TipoDeCobrancaRecebimento a = criaTipoDeCobrancaRecebimento();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		TipoDeCobrancaRecebimento consulta = dao.find(TipoDeCobrancaRecebimento.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		TipoDeCobrancaRecebimento a = criaTipoDeCobrancaRecebimento();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setDescricao(criarStringDinamicaPorTamanho(50));
		dao.merge(a);
		TipoDeCobrancaRecebimento consulta = dao.find(TipoDeCobrancaRecebimento.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		TipoDeCobrancaRecebimento a = criaTipoDeCobrancaRecebimento();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		TipoDeCobrancaRecebimento consulta = dao.find(TipoDeCobrancaRecebimento.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(TipoDeCobrancaRecebimento.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		TipoDeCobrancaRecebimento a1 = criaTipoDeCobrancaRecebimento();
		dao.persist(a1);
		TipoDeCobrancaRecebimento a2 = criaTipoDeCobrancaRecebimento();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM TipoDeCobrancaRecebimento a";
		Query query = dao.getEntityManager().createQuery(sql, TipoDeCobrancaRecebimento.class);
		//
		@SuppressWarnings("unchecked")
		List<TipoDeCobrancaRecebimento> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test
	public void consultar_por_descricao() {
		TipoDeCobrancaRecebimento a = criaTipoDeCobrancaRecebimento();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "FROM TipoDeCobrancaRecebimento a WHERE a.descricao = :descricao";
		TypedQuery<TipoDeCobrancaRecebimento> query = dao.getEntityManager().createQuery(sql,
				TipoDeCobrancaRecebimento.class);
		query.setParameter("descricao", a.getDescricao());
		TipoDeCobrancaRecebimento consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		TipoDeCobrancaRecebimento a = criaTipoDeCobrancaRecebimento();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		TipoDeCobrancaRecebimento a = criaTipoDeCobrancaRecebimento();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		TipoDeCobrancaRecebimento a = criaTipoDeCobrancaRecebimento();
		a.setDescricao(criarStringDinamicaPorTamanho(251));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		TipoDeCobrancaRecebimento a = criaTipoDeCobrancaRecebimento();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		TipoDeCobrancaRecebimento a = criaTipoDeCobrancaRecebimento();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private TipoDeCobrancaRecebimento criaTipoDeCobrancaRecebimento() {
		return TipoDeCobrancaRecebimentoFabricaTest.getInstance().criaTipoDeCobrancaRecebimento();
	}
}