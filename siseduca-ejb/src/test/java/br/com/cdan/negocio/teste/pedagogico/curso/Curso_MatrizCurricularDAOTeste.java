package br.com.cdan.negocio.teste.pedagogico.curso;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.curso.Curso_MatrizCurricular;
import br.com.cdan.negocio.pedagogico.curso.Curso_MatrizCurricularDao;
import br.com.cdan.negocio.pedagogico.curso.factory.Curso_MatrizCurricularFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class Curso_MatrizCurricularDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(Curso_MatrizCurricularDAOTeste.class);
	Curso_MatrizCurricularDao dao;

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
		dao = new Curso_MatrizCurricularDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Curso_MatrizCurricular a = criaCurso_MatrizCurricular();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Curso_MatrizCurricular consulta = dao.find(Curso_MatrizCurricular.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		Curso_MatrizCurricular a = criaCurso_MatrizCurricular();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		Curso_MatrizCurricular consulta = dao.find(Curso_MatrizCurricular.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		Curso_MatrizCurricular a = criaCurso_MatrizCurricular();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Curso_MatrizCurricular consulta = dao.find(Curso_MatrizCurricular.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Curso_MatrizCurricular.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		Curso_MatrizCurricular a1 = criaCurso_MatrizCurricular();
		dao.persist(a1);
		Curso_MatrizCurricular a2 = criaCurso_MatrizCurricular();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Curso_MatrizCurricular a";
		TypedQuery<Curso_MatrizCurricular> query = dao.getEntityManager().createQuery(sql,
				Curso_MatrizCurricular.class);
		//
		List<Curso_MatrizCurricular> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = AssertionError.class)
	public void nao_deve_permitir_ativo_nulo() {
		Curso_MatrizCurricular a = criaCurso_MatrizCurricular();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private Curso_MatrizCurricular criaCurso_MatrizCurricular() {
		return Curso_MatrizCurricularFabricaTest.getInstance().criaCurso_MatrizCurricular(getEntityManager());
	}
}