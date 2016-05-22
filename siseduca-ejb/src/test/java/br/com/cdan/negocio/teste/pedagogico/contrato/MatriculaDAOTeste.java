package br.com.cdan.negocio.teste.pedagogico.contrato;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.contrato.Matricula;
import br.com.cdan.negocio.pedagogico.contrato.MatriculaDao;
import br.com.cdan.negocio.pedagogico.contrato.factory.MatriculaFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class MatriculaDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(MatriculaDAOTeste.class);
	MatriculaDao dao;

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
		dao = new MatriculaDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Matricula a = criaMatricula();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Matricula consulta = dao.find(Matricula.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		Matricula a = criaMatricula();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(Boolean.FALSE);
		dao.merge(a);
		Matricula consulta = dao.find(Matricula.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		Matricula a = criaMatricula();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Matricula consulta = dao.find(Matricula.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Matricula.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		Matricula a1 = criaMatricula();
		dao.persist(a1);
		Matricula a2 = criaMatricula();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Matricula a";
		TypedQuery<Matricula> query = dao.getEntityManager().createQuery(sql, Matricula.class);
		//
		List<Matricula> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_numeroContrato_nulo() {
		Matricula a = criaMatricula();
		a.setNumeroContrato(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_numeroContrato_vazio() {
		Matricula a = criaMatricula();
		a.setNumeroContrato("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void numeroContrato_menor_que_tamanho_minimo_permitido() {
		Matricula a = criaMatricula();
		a.setNumeroContrato(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		Matricula a = criaMatricula();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private Matricula criaMatricula() {
		return MatriculaFabricaTest.getInstance().criaMatricula(getEntityManager());
	}
}