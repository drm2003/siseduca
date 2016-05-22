package br.com.cdan.negocio.teste.pedagogico;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.AvaliacaoPadrao;
import br.com.cdan.negocio.pedagogico.AvaliacaoPadraoDao;
import br.com.cdan.negocio.pedagogico.factory.AvaliacaoPadraoFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class AvaliacaoPadraoDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(AvaliacaoPadraoDAOTeste.class);
	AvaliacaoPadraoDao dao;

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
		dao = new AvaliacaoPadraoDao(getEntityManager());
	}

	@Test
	public void inserir() {
		AvaliacaoPadrao a = criaAvaliacaoPadrao();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		AvaliacaoPadrao consulta = dao.find(AvaliacaoPadrao.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		AvaliacaoPadrao a = criaAvaliacaoPadrao();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		AvaliacaoPadrao consulta = dao.find(AvaliacaoPadrao.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		AvaliacaoPadrao a = criaAvaliacaoPadrao();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		AvaliacaoPadrao consulta = dao.find(AvaliacaoPadrao.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(AvaliacaoPadrao.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		AvaliacaoPadrao a1 = criaAvaliacaoPadrao();
		dao.persist(a1);
		AvaliacaoPadrao a2 = criaAvaliacaoPadrao();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM AvaliacaoPadrao a";
		TypedQuery<AvaliacaoPadrao> query = dao.getEntityManager().createQuery(sql, AvaliacaoPadrao.class);
		//
		List<AvaliacaoPadrao> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		AvaliacaoPadrao a = criaAvaliacaoPadrao();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		AvaliacaoPadrao a = criaAvaliacaoPadrao();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		AvaliacaoPadrao a = criaAvaliacaoPadrao();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		AvaliacaoPadrao a = criaAvaliacaoPadrao();
		a.setDescricao(criarStringDinamicaPorTamanho(1001));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_descricao() {
		AvaliacaoPadrao a = criaAvaliacaoPadrao();
		dao.persist(a);
		//
		String sql = "SELECT a FROM AvaliacaoPadrao a WHERE a.descricao = :descricao";
		TypedQuery<AvaliacaoPadrao> query = dao.getEntityManager().createQuery(sql, AvaliacaoPadrao.class);
		query.setParameter("descricao", a.getDescricao());
		//
		AvaliacaoPadrao m = query.getSingleResult(); //
		//
		Assert.assertEquals(a.hashCode(), m.hashCode());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		AvaliacaoPadrao a = criaAvaliacaoPadrao();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private AvaliacaoPadrao criaAvaliacaoPadrao() {
		return AvaliacaoPadraoFabricaTest.getInstance().criaAvaliacaoPadrao(getEntityManager());
	}
}