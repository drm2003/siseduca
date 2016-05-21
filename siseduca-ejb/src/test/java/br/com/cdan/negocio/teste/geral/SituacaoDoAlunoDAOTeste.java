package br.com.cdan.negocio.teste.geral;

import java.util.List;

import javax.persistence.Query;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.geral.SituacaoDoAluno;
import br.com.cdan.negocio.geral.SituacaoDoAlunoDao;
import br.com.cdan.negocio.geral.factory.SituacaoDoAlunoFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class SituacaoDoAlunoDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(SituacaoDoAlunoDAOTeste.class);
	SituacaoDoAlunoDao dao;

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
		dao = new SituacaoDoAlunoDao(getEntityManager());
	}

	@Test
	public void inserir() {
		SituacaoDoAluno a = criaSituacaoDoAluno();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		SituacaoDoAluno consulta = dao.find(SituacaoDoAluno.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		SituacaoDoAluno a = criaSituacaoDoAluno();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setDescricao("complemento");
		dao.merge(a);
		SituacaoDoAluno consulta = dao.find(SituacaoDoAluno.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		SituacaoDoAluno a = criaSituacaoDoAluno();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		SituacaoDoAluno consulta = dao.find(SituacaoDoAluno.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(SituacaoDoAluno.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		SituacaoDoAluno a1 = criaSituacaoDoAluno();
		dao.persist(a1);
		SituacaoDoAluno a2 = criaSituacaoDoAluno();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM SituacaoDoAluno a";
		Query query = dao.getEntityManager().createQuery(sql, SituacaoDoAluno.class);
		//
		@SuppressWarnings("unchecked")
		List<SituacaoDoAluno> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		SituacaoDoAluno a = criaSituacaoDoAluno();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		SituacaoDoAluno a = criaSituacaoDoAluno();
		a.setDescricao(criarStringDinamicaPorTamanho(151));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = AssertionError.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		SituacaoDoAluno a = criaSituacaoDoAluno();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		SituacaoDoAluno a = criaSituacaoDoAluno();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		SituacaoDoAluno a = criaSituacaoDoAluno();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private SituacaoDoAluno criaSituacaoDoAluno() {
		return SituacaoDoAlunoFabricaTest.getInstance().criaSituacaoDoAluno();
	}
}