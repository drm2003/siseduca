package br.com.cdan.negocio.teste.geral;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.geral.Utilizacao;
import br.com.cdan.negocio.geral.UtilizacaoDao;
import br.com.cdan.negocio.geral.factory.UtilizacaoFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class UtilizacaoDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(UtilizacaoDAOTeste.class);
	UtilizacaoDao dao;

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
		dao = new UtilizacaoDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Utilizacao a = criaUtilizacao();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Utilizacao consulta = dao.find(Utilizacao.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		Utilizacao a = criaUtilizacao();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setDescricao("complemento");
		dao.merge(a);
		Utilizacao consulta = dao.find(Utilizacao.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		Utilizacao a = criaUtilizacao();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Utilizacao consulta = dao.find(Utilizacao.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Utilizacao.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		Utilizacao a1 = criaUtilizacao();
		dao.persist(a1);
		Utilizacao a2 = criaUtilizacao();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Utilizacao a";
		TypedQuery<Utilizacao> query = dao.getEntityManager().createQuery(sql, Utilizacao.class);
		//
		List<Utilizacao> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		Utilizacao a = criaUtilizacao();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		Utilizacao a = criaUtilizacao();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_descricao() {
		Utilizacao a = criaUtilizacao();
		dao.persist(a);
		String sql = "SELECT a FROM Utilizacao a WHERE a.descricao = :descricao";
		TypedQuery<Utilizacao> query = dao.getEntityManager().createQuery(sql, Utilizacao.class);
		query.setParameter("descricao", a.getDescricao());
		List<Utilizacao> lista = query.getResultList();
		Assert.assertTrue(lista.contains(a));
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		Utilizacao a = criaUtilizacao();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		Utilizacao a = criaUtilizacao();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private Utilizacao criaUtilizacao() {
		return UtilizacaoFabricaTest.getInstance().criaUtilizacao(getEntityManager());
	}
}