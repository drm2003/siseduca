package br.com.cdan.negocio.teste.geral;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.geral.TipoDeServico;
import br.com.cdan.negocio.geral.TipoDeServicoDao;
import br.com.cdan.negocio.geral.factory.TipoDeServicoFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class TipoDeServicoDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(TipoDeServicoDAOTeste.class);
	TipoDeServicoDao dao;

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
		dao = new TipoDeServicoDao(getEntityManager());
	}

	@Test
	public void inserir() {
		TipoDeServico a = criaTipoDeServico();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		TipoDeServico consulta = dao.find(TipoDeServico.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		TipoDeServico a = criaTipoDeServico();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setDescricao("complemento");
		dao.merge(a);
		TipoDeServico consulta = dao.find(TipoDeServico.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		TipoDeServico a = criaTipoDeServico();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		TipoDeServico consulta = dao.find(TipoDeServico.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(TipoDeServico.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		TipoDeServico a1 = criaTipoDeServico();
		dao.persist(a1);
		TipoDeServico a2 = criaTipoDeServico();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM TipoDeServico a";
		TypedQuery<TipoDeServico> query = dao.getEntityManager().createQuery(sql, TipoDeServico.class);
		//
		List<TipoDeServico> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		TipoDeServico a = criaTipoDeServico();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		TipoDeServico a = criaTipoDeServico();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		TipoDeServico a = criaTipoDeServico();
		a.setDescricao(criarStringDinamicaPorTamanho(151));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_descricao() {
		TipoDeServico a = criaTipoDeServico();
		dao.persist(a);
		String sql = "SELECT a FROM TipoDeServico a WHERE a.descricao = :descricao";
		TypedQuery<TipoDeServico> query = dao.getEntityManager().createQuery(sql, TipoDeServico.class);
		query.setParameter("descricao", a.getDescricao());
		List<TipoDeServico> lista = query.getResultList();
		Assert.assertTrue(lista.contains(a));
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		TipoDeServico a = criaTipoDeServico();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		TipoDeServico a = criaTipoDeServico();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private TipoDeServico criaTipoDeServico() {
		return TipoDeServicoFabricaTest.getInstance().criaTipoDeServico(getEntityManager());
	}
}