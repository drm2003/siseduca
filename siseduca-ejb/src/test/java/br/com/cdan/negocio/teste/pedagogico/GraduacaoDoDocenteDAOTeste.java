package br.com.cdan.negocio.teste.pedagogico;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.GraduacaoDoDocente;
import br.com.cdan.negocio.pedagogico.GraduacaoDoDocenteDao;
import br.com.cdan.negocio.pedagogico.factory.GraduacaoDoDocenteFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class GraduacaoDoDocenteDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(GraduacaoDoDocenteDAOTeste.class);
	GraduacaoDoDocenteDao dao;

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
		dao = new GraduacaoDoDocenteDao(getEntityManager());
	}

	@Test
	public void inserir() {
		GraduacaoDoDocente a = criaGraduacaoDoDocente();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		GraduacaoDoDocente consulta = dao.find(GraduacaoDoDocente.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		GraduacaoDoDocente a = criaGraduacaoDoDocente();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		GraduacaoDoDocente consulta = dao.find(GraduacaoDoDocente.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		GraduacaoDoDocente a = criaGraduacaoDoDocente();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		GraduacaoDoDocente consulta = dao.find(GraduacaoDoDocente.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(GraduacaoDoDocente.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		GraduacaoDoDocente a1 = criaGraduacaoDoDocente();
		dao.persist(a1);
		GraduacaoDoDocente a2 = criaGraduacaoDoDocente();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM GraduacaoDoDocente a";
		TypedQuery<GraduacaoDoDocente> query = dao.getEntityManager().createQuery(sql, GraduacaoDoDocente.class);
		//
		List<GraduacaoDoDocente> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		GraduacaoDoDocente a = criaGraduacaoDoDocente();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		GraduacaoDoDocente a = criaGraduacaoDoDocente();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		GraduacaoDoDocente a = criaGraduacaoDoDocente();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		GraduacaoDoDocente a = criaGraduacaoDoDocente();
		a.setDescricao(criarStringDinamicaPorTamanho(1001));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_descricao() {
		GraduacaoDoDocente a = criaGraduacaoDoDocente();
		dao.persist(a);
		//
		String sql = "SELECT a FROM GraduacaoDoDocente a WHERE a.descricao = :descricao";
		TypedQuery<GraduacaoDoDocente> query = dao.getEntityManager().createQuery(sql, GraduacaoDoDocente.class);
		query.setParameter("descricao", a.getDescricao());
		//
		GraduacaoDoDocente m = query.getSingleResult(); //
		//
		Assert.assertEquals(a.hashCode(), m.hashCode());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		GraduacaoDoDocente a = criaGraduacaoDoDocente();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private GraduacaoDoDocente criaGraduacaoDoDocente() {
		return GraduacaoDoDocenteFabricaTest.getInstance().criaGraduacaoDoDocente();
	}
}