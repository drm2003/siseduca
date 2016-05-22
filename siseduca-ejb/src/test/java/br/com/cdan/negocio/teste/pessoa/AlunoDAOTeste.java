package br.com.cdan.negocio.teste.pessoa;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pessoa.Aluno;
import br.com.cdan.negocio.pedagogico.pessoa.AlunoDao;
import br.com.cdan.negocio.pedagogico.pessoa.factory.AlunoFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class AlunoDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(AlunoDAOTeste.class);
	AlunoDao dao;

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
		dao = new AlunoDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Aluno a = criaAluno();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Aluno consulta = dao.find(Aluno.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		Aluno a = criaAluno();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setNumeroMatricula(criarStringDinamicaPorTamanho(10));
		dao.merge(a);
		Aluno consulta = dao.find(Aluno.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		Aluno a = criaAluno();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Aluno consulta = dao.find(Aluno.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Aluno.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		Aluno a1 = criaAluno();
		dao.persist(a1);
		Aluno a2 = criaAluno();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Aluno a";
		TypedQuery<Aluno> query = dao.getEntityManager().createQuery(sql, Aluno.class);
		//
		List<Aluno> lista = query.getResultList();
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test
	public void consultar_por_numeroMatricula() {
		Aluno a = criaAluno();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "FROM Aluno a WHERE a.numeroMatricula = :numeroMatricula";
		TypedQuery<Aluno> query = dao.getEntityManager().createQuery(sql, Aluno.class);
		query.setParameter("numeroMatricula", a.getNumeroMatricula());
		Aluno consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_numeroMatricula_vazio() {
		Aluno a = criaAluno();
		a.setNumeroMatricula("");
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		Aluno a = criaAluno();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private Aluno criaAluno() {
		return AlunoFabricaTest.getInstance().criaAluno(getEntityManager());
	}
}