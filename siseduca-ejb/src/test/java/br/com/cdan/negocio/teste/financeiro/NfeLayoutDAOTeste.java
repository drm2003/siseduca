package br.com.cdan.negocio.teste.financeiro;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.financeiro.NfeLayout;
import br.com.cdan.negocio.financeiro.NfeLayoutDao;
import br.com.cdan.negocio.financeiro.factory.NfeLayoutFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class NfeLayoutDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(NfeLayoutDAOTeste.class);
	NfeLayoutDao dao;

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
		dao = new NfeLayoutDao(getEntityManager());
	}

	@Test
	public void inserir() {
		NfeLayout a = criaNfeLayout();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		NfeLayout consulta = dao.find(NfeLayout.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		NfeLayout a = criaNfeLayout();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setDescricao(criarStringDinamicaPorTamanho(50));
		dao.merge(a);
		NfeLayout consulta = dao.find(NfeLayout.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		NfeLayout a = criaNfeLayout();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		NfeLayout consulta = dao.find(NfeLayout.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(NfeLayout.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		NfeLayout a1 = criaNfeLayout();
		dao.persist(a1);
		NfeLayout a2 = criaNfeLayout();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM NfeLayout a";
		Query query = dao.getEntityManager().createQuery(sql, NfeLayout.class);
		//
		@SuppressWarnings("unchecked")
		List<NfeLayout> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test
	public void consultar_por_descricao() {
		NfeLayout a = criaNfeLayout();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "FROM NfeLayout a WHERE a.descricao = :descricao";
		TypedQuery<NfeLayout> query = dao.getEntityManager().createQuery(sql, NfeLayout.class);
		query.setParameter("descricao", a.getDescricao());
		NfeLayout consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		NfeLayout a = criaNfeLayout();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		NfeLayout a = criaNfeLayout();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		NfeLayout a = criaNfeLayout();
		a.setDescricao(criarStringDinamicaPorTamanho(251));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		NfeLayout a = criaNfeLayout();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		NfeLayout a = criaNfeLayout();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private NfeLayout criaNfeLayout() {
		return NfeLayoutFabricaTest.getInstance().criaNfeLayout(getEntityManager());
	}
}