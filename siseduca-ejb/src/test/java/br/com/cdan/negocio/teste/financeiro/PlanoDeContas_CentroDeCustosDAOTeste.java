package br.com.cdan.negocio.teste.financeiro;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.financeiro.PlanoDeContas_CentroDeCustos;
import br.com.cdan.negocio.financeiro.PlanoDeContas_CentroDeCustosDao;
import br.com.cdan.negocio.financeiro.factory.PlanoDeContas_CentroDeCustosFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class PlanoDeContas_CentroDeCustosDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(PlanoDeContas_CentroDeCustosDAOTeste.class);
	PlanoDeContas_CentroDeCustosDao dao;

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
		dao = new PlanoDeContas_CentroDeCustosDao(getEntityManager());
	}

	@Test
	public void inserir() {
		PlanoDeContas_CentroDeCustos a = criaPlanoDeContas_CentroDeCustos();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		PlanoDeContas_CentroDeCustos consulta = dao.find(PlanoDeContas_CentroDeCustos.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		PlanoDeContas_CentroDeCustos a = criaPlanoDeContas_CentroDeCustos();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setPercentual(BigDecimal.valueOf(Double.parseDouble(criarStringDinamicaPorTamanho(10))));
		dao.merge(a);
		PlanoDeContas_CentroDeCustos consulta = dao.find(PlanoDeContas_CentroDeCustos.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		PlanoDeContas_CentroDeCustos a = criaPlanoDeContas_CentroDeCustos();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		PlanoDeContas_CentroDeCustos consulta = dao.find(PlanoDeContas_CentroDeCustos.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(PlanoDeContas_CentroDeCustos.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		PlanoDeContas_CentroDeCustos a1 = criaPlanoDeContas_CentroDeCustos();
		dao.persist(a1);
		PlanoDeContas_CentroDeCustos a2 = criaPlanoDeContas_CentroDeCustos();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM PlanoDeContas_CentroDeCustos a";
		Query query = dao.getEntityManager().createQuery(sql, PlanoDeContas_CentroDeCustos.class);
		//
		@SuppressWarnings("unchecked")
		List<PlanoDeContas_CentroDeCustos> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = AssertionError.class)
	public void nao_deve_permitir_ativo_nulo() {
		PlanoDeContas_CentroDeCustos a = criaPlanoDeContas_CentroDeCustos();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private PlanoDeContas_CentroDeCustos criaPlanoDeContas_CentroDeCustos() {
		return PlanoDeContas_CentroDeCustosFabricaTest.getInstance()
				.criaPlanoDeContas_CentroDeCustos(getEntityManager());
	}
}