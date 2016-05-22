package br.com.cdan.negocio.teste.pedagogico.curso;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.curso.TipoDeInvestimento;
import br.com.cdan.negocio.pedagogico.curso.TipoDeInvestimentoDao;
import br.com.cdan.negocio.pedagogico.curso.factory.TipoDeInvestimentoFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class TipoDeInvestimentoDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(TipoDeInvestimentoDAOTeste.class);
	TipoDeInvestimentoDao dao;

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
		dao = new TipoDeInvestimentoDao(getEntityManager());
	}

	@Test
	public void inserir() {
		TipoDeInvestimento a = criaTipoDeInvestimento();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		TipoDeInvestimento consulta = dao.find(TipoDeInvestimento.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		TipoDeInvestimento a = criaTipoDeInvestimento();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		TipoDeInvestimento consulta = dao.find(TipoDeInvestimento.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		TipoDeInvestimento a = criaTipoDeInvestimento();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		TipoDeInvestimento consulta = dao.find(TipoDeInvestimento.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(TipoDeInvestimento.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		TipoDeInvestimento a1 = criaTipoDeInvestimento();
		dao.persist(a1);
		TipoDeInvestimento a2 = criaTipoDeInvestimento();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM TipoDeInvestimento a";
		TypedQuery<TipoDeInvestimento> query = dao.getEntityManager().createQuery(sql, TipoDeInvestimento.class);
		//
		List<TipoDeInvestimento> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		TipoDeInvestimento a = criaTipoDeInvestimento();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		TipoDeInvestimento a = criaTipoDeInvestimento();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		TipoDeInvestimento a = criaTipoDeInvestimento();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		TipoDeInvestimento a = criaTipoDeInvestimento();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_descricao() {
		TipoDeInvestimento a = criaTipoDeInvestimento();
		dao.persist(a);
		//
		String sql = "SELECT a FROM TipoDeInvestimento a WHERE a.descricao = :descricao";
		TypedQuery<TipoDeInvestimento> query = dao.getEntityManager().createQuery(sql, TipoDeInvestimento.class);
		query.setParameter("descricao", a.getDescricao());
		//
		TipoDeInvestimento m = query.getSingleResult(); //
		//
		Assert.assertEquals(a.hashCode(), m.hashCode());
	}

	private TipoDeInvestimento criaTipoDeInvestimento() {
		return TipoDeInvestimentoFabricaTest.getInstance().criaTipoDeInvestimento();
	}
}