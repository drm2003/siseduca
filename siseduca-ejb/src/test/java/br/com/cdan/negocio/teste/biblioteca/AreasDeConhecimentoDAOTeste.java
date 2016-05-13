package br.com.cdan.negocio.teste.biblioteca;

import java.util.List;

import javax.persistence.Query;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.biblioteca.AreasDeConhecimento;
import br.com.cdan.negocio.biblioteca.AreasDeConhecimentoDao;
import br.com.cdan.negocio.biblioteca.factory.AreasDeConhecimentoFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class AreasDeConhecimentoDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(AreasDeConhecimentoDAOTeste.class);
	AreasDeConhecimentoDao dao;

	/**
	 * <c> Ao criar um teste da camada de persistï¿½ncia utilizando o JUnit ï¿½
	 * preciso ter acesso ao cont\exto de persistï¿½ncia fornecido pelo JPA.
	 * <c> Deste modo, antes da execuï¿½ï¿½o dos testes fornecemos este acesso
	 * ï¿½ camada de persistï¿½ncia por meio de uma instï¿½ncia
	 * <code>EntityManager</code> gerada pela <code>PersistenciaJUnit</code>.
	 * 
	 * @throws Exception
	 * @see br.jus.tjdft.siscor.util.PersistenciaJUnit
	 */
	@Before
	public void setUp() throws Exception {
		LOG.info("Instanciando DAOTest.");
		dao = new AreasDeConhecimentoDao();
		dao.setEntityManager(getEntityManager());
	}

	@Test
	public void inserir() {
		AreasDeConhecimento a = criaAreasDeConhecimento();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		AreasDeConhecimento consulta = dao.find(AreasDeConhecimento.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void alterar() {
		AreasDeConhecimento a = criaAreasDeConhecimento();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setDescricao("alteração");
		a.setCompartilhado(false);
		dao.merge(a);
		AreasDeConhecimento consulta = dao.find(AreasDeConhecimento.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		AreasDeConhecimento a = criaAreasDeConhecimento();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		AreasDeConhecimento consulta = dao.find(AreasDeConhecimento.class, a.getId());// CONSULTA
		consulta.setAtivo(false);
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(AreasDeConhecimento.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		AreasDeConhecimento a1 = criaAreasDeConhecimento();
		dao.persist(a1);
		AreasDeConhecimento a2 = criaAreasDeConhecimento();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM AreasDeConhecimento a";
		Query query = dao.getEntityManager().createQuery(sql, AreasDeConhecimento.class);
		//
		@SuppressWarnings("unchecked")
		List<AreasDeConhecimento> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test
	public void consultar_por_descricao() {
		AreasDeConhecimento a = criaAreasDeConhecimento();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "SELECT a FROM AreasDeConhecimento a WHERE a.descricao = :descricao";
		Query query = dao.getEntityManager().createQuery(sql);
		query.setParameter("descricao", a.getDescricao());
		AreasDeConhecimento consulta = (AreasDeConhecimento) query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nula() {
		AreasDeConhecimento a = criaAreasDeConhecimento();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazia() {
		AreasDeConhecimento a = criaAreasDeConhecimento();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		AreasDeConhecimento a = criaAreasDeConhecimento();
		a.setDescricao(criarStringDinamicaPorTamanho(51));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		AreasDeConhecimento a = criaAreasDeConhecimento();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_compartilhado_nulo() {
		AreasDeConhecimento a = criaAreasDeConhecimento();
		a.setCompartilhado(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private AreasDeConhecimento criaAreasDeConhecimento() {
		return AreasDeConhecimentoFabricaTest.getInstance().criaAreasDeConhecimento();
	}
}