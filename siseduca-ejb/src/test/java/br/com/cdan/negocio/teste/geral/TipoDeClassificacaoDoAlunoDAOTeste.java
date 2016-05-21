package br.com.cdan.negocio.teste.geral;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.geral.TipoDeClassificacaoDoAluno;
import br.com.cdan.negocio.geral.TipoDeClassificacaoDoAlunoDao;
import br.com.cdan.negocio.geral.factory.TipoDeClassificacaoDoAlunoFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class TipoDeClassificacaoDoAlunoDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(TipoDeClassificacaoDoAlunoDAOTeste.class);
	TipoDeClassificacaoDoAlunoDao dao;

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
		dao = new TipoDeClassificacaoDoAlunoDao(getEntityManager());
	}

	@Test
	public void inserir() {
		TipoDeClassificacaoDoAluno a = criaTipoDeClassificacaoDoAluno();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		TipoDeClassificacaoDoAluno consulta = dao.find(TipoDeClassificacaoDoAluno.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		TipoDeClassificacaoDoAluno a = criaTipoDeClassificacaoDoAluno();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setDescricao("complemento");
		dao.merge(a);
		TipoDeClassificacaoDoAluno consulta = dao.find(TipoDeClassificacaoDoAluno.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		TipoDeClassificacaoDoAluno a = criaTipoDeClassificacaoDoAluno();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		TipoDeClassificacaoDoAluno consulta = dao.find(TipoDeClassificacaoDoAluno.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(TipoDeClassificacaoDoAluno.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		TipoDeClassificacaoDoAluno a1 = criaTipoDeClassificacaoDoAluno();
		dao.persist(a1);
		TipoDeClassificacaoDoAluno a2 = criaTipoDeClassificacaoDoAluno();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM TipoDeClassificacaoDoAluno a";
		TypedQuery<TipoDeClassificacaoDoAluno> query = dao.getEntityManager().createQuery(sql,
				TipoDeClassificacaoDoAluno.class);
		//
		List<TipoDeClassificacaoDoAluno> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		TipoDeClassificacaoDoAluno a = criaTipoDeClassificacaoDoAluno();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		TipoDeClassificacaoDoAluno a = criaTipoDeClassificacaoDoAluno();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		TipoDeClassificacaoDoAluno a = criaTipoDeClassificacaoDoAluno();
		a.setDescricao(criarStringDinamicaPorTamanho(151));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		TipoDeClassificacaoDoAluno a = criaTipoDeClassificacaoDoAluno();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		TipoDeClassificacaoDoAluno a = criaTipoDeClassificacaoDoAluno();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_compartilhado_nulo() {
		TipoDeClassificacaoDoAluno a = criaTipoDeClassificacaoDoAluno();
		a.setCompartilhado(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private TipoDeClassificacaoDoAluno criaTipoDeClassificacaoDoAluno() {
		return TipoDeClassificacaoDoAlunoFabricaTest.getInstance().criaTipoDeClassificacaoDoAluno();
	}
}