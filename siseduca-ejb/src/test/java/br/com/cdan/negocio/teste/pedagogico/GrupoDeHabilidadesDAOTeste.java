package br.com.cdan.negocio.teste.pedagogico;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.GrupoDeHabilidades;
import br.com.cdan.negocio.pedagogico.GrupoDeHabilidadesDao;
import br.com.cdan.negocio.pedagogico.factory.GrupoDeHabilidadesFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class GrupoDeHabilidadesDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(GrupoDeHabilidadesDAOTeste.class);
	GrupoDeHabilidadesDao dao;

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
		dao = new GrupoDeHabilidadesDao(getEntityManager());
	}

	@Test
	public void inserir() {
		GrupoDeHabilidades a = criaGrupoDeHabilidades();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		GrupoDeHabilidades consulta = dao.find(GrupoDeHabilidades.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		GrupoDeHabilidades a = criaGrupoDeHabilidades();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		GrupoDeHabilidades consulta = dao.find(GrupoDeHabilidades.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		GrupoDeHabilidades a = criaGrupoDeHabilidades();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		GrupoDeHabilidades consulta = dao.find(GrupoDeHabilidades.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(GrupoDeHabilidades.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		GrupoDeHabilidades a1 = criaGrupoDeHabilidades();
		dao.persist(a1);
		GrupoDeHabilidades a2 = criaGrupoDeHabilidades();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM GrupoDeHabilidades a";
		TypedQuery<GrupoDeHabilidades> query = dao.getEntityManager().createQuery(sql, GrupoDeHabilidades.class);
		//
		List<GrupoDeHabilidades> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = AssertionError.class)
	public void nao_deve_permitir_descricao_nulo() {
		GrupoDeHabilidades a = criaGrupoDeHabilidades();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = AssertionError.class)
	public void nao_deve_permitir_descricao_vazio() {
		GrupoDeHabilidades a = criaGrupoDeHabilidades();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = AssertionError.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		GrupoDeHabilidades a = criaGrupoDeHabilidades();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = AssertionError.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		GrupoDeHabilidades a = criaGrupoDeHabilidades();
		a.setDescricao(criarStringDinamicaPorTamanho(1001));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_descricao() {
		GrupoDeHabilidades a = criaGrupoDeHabilidades();
		dao.persist(a);
		//
		String sql = "SELECT a FROM GrupoDeHabilidades a WHERE a.descricao = :descricao";
		TypedQuery<GrupoDeHabilidades> query = dao.getEntityManager().createQuery(sql, GrupoDeHabilidades.class);
		query.setParameter("descricao", a.getDescricao());
		//
		GrupoDeHabilidades m = query.getSingleResult(); //
		//
		Assert.assertEquals(a.hashCode(), m.hashCode());
	}

	@Test(expected = AssertionError.class)
	public void nao_deve_permitir_ativo_nulo() {
		GrupoDeHabilidades a = criaGrupoDeHabilidades();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private GrupoDeHabilidades criaGrupoDeHabilidades() {
		return GrupoDeHabilidadesFabricaTest.getInstance().criaGrupoDeHabilidadesPersistido(getEntityManager());
	}
}