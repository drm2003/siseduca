package br.com.cdan.negocio.teste.financeiro;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.financeiro.CentroDeCustos;
import br.com.cdan.negocio.financeiro.CentroDeCustosDao;
import br.com.cdan.negocio.financeiro.factory.CentroDeCustosFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class CentroDeCustosDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(CentroDeCustosDAOTeste.class);
	CentroDeCustosDao dao;

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
		dao = new CentroDeCustosDao(getEntityManager());
	}

	@Test
	public void inserir() {
		CentroDeCustos a = criaCentroDeCustos();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		CentroDeCustos consulta = dao.find(CentroDeCustos.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		CentroDeCustos a = criaCentroDeCustos();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setNome("");
		dao.merge(a);
		CentroDeCustos consulta = dao.find(CentroDeCustos.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		CentroDeCustos a = criaCentroDeCustos();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		CentroDeCustos consulta = dao.find(CentroDeCustos.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(CentroDeCustos.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		CentroDeCustos a1 = criaCentroDeCustos();
		dao.persist(a1);
		CentroDeCustos a2 = criaCentroDeCustos();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM CentroDeCustos a";
		Query query = dao.getEntityManager().createQuery(sql, CentroDeCustos.class);
		//
		@SuppressWarnings("unchecked")
		List<CentroDeCustos> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test
	public void consultar_por_nome() {
		CentroDeCustos a = criaCentroDeCustos();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "FROM CentroDeCustos a WHERE a.nome = :nome";
		TypedQuery<CentroDeCustos> query = dao.getEntityManager().createQuery(sql, CentroDeCustos.class);
		query.setParameter("nome", a.getNome());
		CentroDeCustos consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_nome_nulo() {
		CentroDeCustos a = criaCentroDeCustos();
		a.setNome(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_nome_vazio() {
		CentroDeCustos a = criaCentroDeCustos();
		a.setNome("");
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nome_maior_que_tamanho_maximo_permitido() {
		CentroDeCustos a = criaCentroDeCustos();
		a.setNome(criarStringDinamicaPorTamanho(161));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nome_menor_que_tamanho_minimo_permitido() {
		CentroDeCustos a = criaCentroDeCustos();
		a.setNome(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_compartilhado_nulo() {
		CentroDeCustos a = criaCentroDeCustos();
		a.setCompartilhado(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		CentroDeCustos a = criaCentroDeCustos();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private CentroDeCustos criaCentroDeCustos() {
		return CentroDeCustosFabricaTest.getInstance().criaCentroDeCustos();
	}
}