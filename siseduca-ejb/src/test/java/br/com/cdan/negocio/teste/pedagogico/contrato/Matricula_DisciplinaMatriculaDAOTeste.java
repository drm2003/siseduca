package br.com.cdan.negocio.teste.pedagogico.contrato;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.contrato.Matricula_DisciplinaMatricula;
import br.com.cdan.negocio.pedagogico.contrato.Matricula_DisciplinaMatriculaDao;
import br.com.cdan.negocio.pedagogico.contrato.factory.Matricula_DisciplinaMatriculaFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class Matricula_DisciplinaMatriculaDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(Matricula_DisciplinaMatriculaDAOTeste.class);
	Matricula_DisciplinaMatriculaDao dao;

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
		dao = new Matricula_DisciplinaMatriculaDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Matricula_DisciplinaMatricula a = criaMatricula_DisciplinaMatricula();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Matricula_DisciplinaMatricula consulta = dao.find(Matricula_DisciplinaMatricula.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		Matricula_DisciplinaMatricula a = criaMatricula_DisciplinaMatricula();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		Matricula_DisciplinaMatricula consulta = dao.find(Matricula_DisciplinaMatricula.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		Matricula_DisciplinaMatricula a = criaMatricula_DisciplinaMatricula();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Matricula_DisciplinaMatricula consulta = dao.find(Matricula_DisciplinaMatricula.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Matricula_DisciplinaMatricula.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		Matricula_DisciplinaMatricula a1 = criaMatricula_DisciplinaMatricula();
		dao.persist(a1);
		Matricula_DisciplinaMatricula a2 = criaMatricula_DisciplinaMatricula();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Matricula_DisciplinaMatricula a";
		TypedQuery<Matricula_DisciplinaMatricula> query = dao.getEntityManager().createQuery(sql,
				Matricula_DisciplinaMatricula.class);
		//
		List<Matricula_DisciplinaMatricula> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = AssertionError.class)
	public void nao_deve_permitir_ativo_nulo() {
		Matricula_DisciplinaMatricula a = criaMatricula_DisciplinaMatricula();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	private Matricula_DisciplinaMatricula criaMatricula_DisciplinaMatricula() {
		return Matricula_DisciplinaMatriculaFabricaTest.getInstance()
				.criaMatricula_DisciplinaMatricula(getEntityManager());
	}
}