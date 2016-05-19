package br.com.cdan.negocio.teste.financeiro;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.financeiro.ModalidadeBaseICMS;
import br.com.cdan.negocio.financeiro.ModalidadeBaseICMSDao;
import br.com.cdan.negocio.financeiro.factory.ModalidadeBaseICMSFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class ModalidadeBaseICMSDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(ModalidadeBaseICMSDAOTeste.class);
	ModalidadeBaseICMSDao dao;

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
		dao = new ModalidadeBaseICMSDao(getEntityManager());
	}

	@Test
	public void inserir() {
		ModalidadeBaseICMS a = criaModalidadeBaseICMS();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		ModalidadeBaseICMS consulta = dao.find(ModalidadeBaseICMS.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		ModalidadeBaseICMS a = criaModalidadeBaseICMS();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setDescricao(criarStringDinamicaPorTamanho(50));
		dao.merge(a);
		ModalidadeBaseICMS consulta = dao.find(ModalidadeBaseICMS.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		ModalidadeBaseICMS a = criaModalidadeBaseICMS();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		ModalidadeBaseICMS consulta = dao.find(ModalidadeBaseICMS.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(ModalidadeBaseICMS.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		ModalidadeBaseICMS a1 = criaModalidadeBaseICMS();
		dao.persist(a1);
		ModalidadeBaseICMS a2 = criaModalidadeBaseICMS();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM ModalidadeBaseICMS a";
		Query query = dao.getEntityManager().createQuery(sql, ModalidadeBaseICMS.class);
		//
		@SuppressWarnings("unchecked")
		List<ModalidadeBaseICMS> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}
	
	@Test
	public void consultar_por_descricao() {
		ModalidadeBaseICMS a = criaModalidadeBaseICMS();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "FROM ModalidadeBaseICMS a WHERE a.descricao = :descricao";
		TypedQuery<ModalidadeBaseICMS> query = dao.getEntityManager().createQuery(sql, ModalidadeBaseICMS.class);
		query.setParameter("descricao", a.getDescricao());
		ModalidadeBaseICMS consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		ModalidadeBaseICMS a = criaModalidadeBaseICMS();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		ModalidadeBaseICMS a = criaModalidadeBaseICMS();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		ModalidadeBaseICMS a = criaModalidadeBaseICMS();
		a.setDescricao(criarStringDinamicaPorTamanho(251));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		ModalidadeBaseICMS a = criaModalidadeBaseICMS();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		ModalidadeBaseICMS a = criaModalidadeBaseICMS();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private ModalidadeBaseICMS criaModalidadeBaseICMS() {
		return ModalidadeBaseICMSFabricaTest.getInstance().criaModalidadeBaseICMS(getEntityManager());
	}
}