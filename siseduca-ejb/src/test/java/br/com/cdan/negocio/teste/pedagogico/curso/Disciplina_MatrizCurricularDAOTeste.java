package br.com.cdan.negocio.teste.pedagogico.curso;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.curso.Disciplina_MatrizCurricular;
import br.com.cdan.negocio.pedagogico.curso.Disciplina_MatrizCurricularDao;
import br.com.cdan.negocio.pedagogico.curso.factory.Disciplina_MatrizCurricularFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class Disciplina_MatrizCurricularDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(Disciplina_MatrizCurricularDAOTeste.class);
	Disciplina_MatrizCurricularDao dao;

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
		dao = new Disciplina_MatrizCurricularDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Disciplina_MatrizCurricular a = criaDisciplina_MatrizCurricular();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Disciplina_MatrizCurricular consulta = dao.find(Disciplina_MatrizCurricular.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		Disciplina_MatrizCurricular a = criaDisciplina_MatrizCurricular();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		Disciplina_MatrizCurricular consulta = dao.find(Disciplina_MatrizCurricular.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		Disciplina_MatrizCurricular a = criaDisciplina_MatrizCurricular();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Disciplina_MatrizCurricular consulta = dao.find(Disciplina_MatrizCurricular.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Disciplina_MatrizCurricular.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		Disciplina_MatrizCurricular a1 = criaDisciplina_MatrizCurricular();
		dao.persist(a1);
		Disciplina_MatrizCurricular a2 = criaDisciplina_MatrizCurricular();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Disciplina_MatrizCurricular a";
		TypedQuery<Disciplina_MatrizCurricular> query = dao.getEntityManager().createQuery(sql,
				Disciplina_MatrizCurricular.class);
		//
		List<Disciplina_MatrizCurricular> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = AssertionError.class)
	public void nao_deve_permitir_ativo_nulo() {
		Disciplina_MatrizCurricular a = criaDisciplina_MatrizCurricular();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = AssertionError.class)
	public void nao_deve_permitir_descricao_nulo() {
		Disciplina_MatrizCurricular a = criaDisciplina_MatrizCurricular();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = AssertionError.class)
	public void nao_deve_permitir_descricao_vazio() {
		Disciplina_MatrizCurricular a = criaDisciplina_MatrizCurricular();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = AssertionError.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		Disciplina_MatrizCurricular a = criaDisciplina_MatrizCurricular();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = AssertionError.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		Disciplina_MatrizCurricular a = criaDisciplina_MatrizCurricular();
		a.setDescricao(criarStringDinamicaPorTamanho(1001));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_descricao() {
		Disciplina_MatrizCurricular a = criaDisciplina_MatrizCurricular();
		dao.persist(a);
		//
		String sql = "SELECT a FROM Disciplina_MatrizCurricular a WHERE a.descricao = :descricao";
		TypedQuery<Disciplina_MatrizCurricular> query = dao.getEntityManager().createQuery(sql,
				Disciplina_MatrizCurricular.class);
		query.setParameter("descricao", a.getDescricao());
		//
		Disciplina_MatrizCurricular m = query.getSingleResult(); //
		//
		Assert.assertEquals(a.hashCode(), m.hashCode());
	}

	private Disciplina_MatrizCurricular criaDisciplina_MatrizCurricular() {
		return Disciplina_MatrizCurricularFabricaTest.getInstance().criaDisciplina_MatrizCurricular(getEntityManager());
	}
}