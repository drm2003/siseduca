package br.com.cdan.negocio.teste.pedagogico.curso;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.curso.Turma_Disciplina;
import br.com.cdan.negocio.pedagogico.curso.Turma_DisciplinaDao;
import br.com.cdan.negocio.pedagogico.curso.factory.Turma_DisciplinaFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class Turma_DisciplinaDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(Turma_DisciplinaDAOTeste.class);
	Turma_DisciplinaDao dao;

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
		dao = new Turma_DisciplinaDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Turma_Disciplina a = criaTurma_Disciplina();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Turma_Disciplina consulta = dao.find(Turma_Disciplina.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		Turma_Disciplina a = criaTurma_Disciplina();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		Turma_Disciplina consulta = dao.find(Turma_Disciplina.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		Turma_Disciplina a = criaTurma_Disciplina();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Turma_Disciplina consulta = dao.find(Turma_Disciplina.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Turma_Disciplina.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		Turma_Disciplina a1 = criaTurma_Disciplina();
		dao.persist(a1);
		Turma_Disciplina a2 = criaTurma_Disciplina();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Turma_Disciplina a";
		TypedQuery<Turma_Disciplina> query = dao.getEntityManager().createQuery(sql, Turma_Disciplina.class);
		//
		List<Turma_Disciplina> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = AssertionError.class)
	public void nao_deve_permitir_ativo_nulo() {
		Turma_Disciplina a = criaTurma_Disciplina();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private Turma_Disciplina criaTurma_Disciplina() {
		return Turma_DisciplinaFabricaTest.getInstance().criaTurma_Disciplina(getEntityManager());
	}
}