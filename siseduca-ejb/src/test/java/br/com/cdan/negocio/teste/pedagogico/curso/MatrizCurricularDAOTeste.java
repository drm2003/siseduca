package br.com.cdan.negocio.teste.pedagogico.curso;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.curso.MatrizCurricular;
import br.com.cdan.negocio.pedagogico.curso.MatrizCurricularDao;
import br.com.cdan.negocio.pedagogico.curso.factory.MatrizCurricularFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class MatrizCurricularDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(MatrizCurricularDAOTeste.class);
	MatrizCurricularDao dao;

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
		dao = new MatrizCurricularDao(getEntityManager());
	}

	@Test
	public void inserir() {
		MatrizCurricular a = criaMatrizCurricular();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		MatrizCurricular consulta = dao.find(MatrizCurricular.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		MatrizCurricular a = criaMatrizCurricular();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		MatrizCurricular consulta = dao.find(MatrizCurricular.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		MatrizCurricular a = criaMatrizCurricular();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		MatrizCurricular consulta = dao.find(MatrizCurricular.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(MatrizCurricular.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		MatrizCurricular a1 = criaMatrizCurricular();
		dao.persist(a1);
		MatrizCurricular a2 = criaMatrizCurricular();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM MatrizCurricular a";
		TypedQuery<MatrizCurricular> query = dao.getEntityManager().createQuery(sql, MatrizCurricular.class);
		//
		List<MatrizCurricular> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_nome_nulo() {
		MatrizCurricular a = criaMatrizCurricular();
		a.setNome(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_nome_vazio() {
		MatrizCurricular a = criaMatrizCurricular();
		a.setNome("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nome_menor_que_tamanho_minimo_permitido() {
		MatrizCurricular a = criaMatrizCurricular();
		a.setNome(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nome_maior_que_tamanho_maximo_permitido() {
		MatrizCurricular a = criaMatrizCurricular();
		a.setNome(criarStringDinamicaPorTamanho(256));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_nome() {
		MatrizCurricular a = criaMatrizCurricular();
		dao.persist(a);
		//
		String sql = "SELECT a FROM MatrizCurricular a WHERE a.nome = :nome";
		TypedQuery<MatrizCurricular> query = dao.getEntityManager().createQuery(sql, MatrizCurricular.class);
		query.setParameter("nome", a.getNome());
		//
		MatrizCurricular m = query.getSingleResult(); //
		//
		Assert.assertEquals(a.hashCode(), m.hashCode());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		MatrizCurricular a = criaMatrizCurricular();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private MatrizCurricular criaMatrizCurricular() {
		return MatrizCurricularFabricaTest.getInstance().criaMatrizCurricular(getEntityManager());
	}
}