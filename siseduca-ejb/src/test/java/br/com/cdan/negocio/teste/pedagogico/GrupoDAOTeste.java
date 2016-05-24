package br.com.cdan.negocio.teste.pedagogico;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.Grupo;
import br.com.cdan.negocio.pedagogico.GrupoDao;
import br.com.cdan.negocio.pedagogico.factory.GrupoFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class GrupoDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(GrupoDAOTeste.class);
	GrupoDao dao;

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
		dao = new GrupoDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Grupo a = criaGrupo();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Grupo consulta = dao.find(Grupo.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		Grupo a = criaGrupo();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		Grupo consulta = dao.find(Grupo.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		Grupo a = criaGrupo();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Grupo consulta = dao.find(Grupo.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Grupo.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		Grupo a1 = criaGrupo();
		dao.persist(a1);
		Grupo a2 = criaGrupo();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Grupo a";
		TypedQuery<Grupo> query = dao.getEntityManager().createQuery(sql, Grupo.class);
		//
		List<Grupo> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		Grupo a = criaGrupo();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		Grupo a = criaGrupo();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		Grupo a = criaGrupo();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		Grupo a = criaGrupo();
		a.setDescricao(criarStringDinamicaPorTamanho(1001));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_descricao() {
		Grupo a = criaGrupo();
		dao.persist(a);
		//
		String sql = "SELECT a FROM Grupo a WHERE a.descricao = :descricao";
		TypedQuery<Grupo> query = dao.getEntityManager().createQuery(sql, Grupo.class);
		query.setParameter("descricao", a.getDescricao());
		//
		Grupo m = query.getSingleResult(); //
		//
		Assert.assertEquals(a.hashCode(), m.hashCode());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		Grupo a = criaGrupo();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private Grupo criaGrupo() {
		return GrupoFabricaTest.getInstance().criaGrupo();
	}
}