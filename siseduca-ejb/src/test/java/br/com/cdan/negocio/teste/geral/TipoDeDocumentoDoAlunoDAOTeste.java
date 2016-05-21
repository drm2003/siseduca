package br.com.cdan.negocio.teste.geral;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.geral.TipoDeDocumentoDoAluno;
import br.com.cdan.negocio.geral.TipoDeDocumentoDoAlunoDao;
import br.com.cdan.negocio.geral.factory.TipoDeDocumentoDoAlunoFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class TipoDeDocumentoDoAlunoDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(TipoDeDocumentoDoAlunoDAOTeste.class);
	TipoDeDocumentoDoAlunoDao dao;

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
		dao = new TipoDeDocumentoDoAlunoDao(getEntityManager());
	}

	@Test
	public void inserir() {
		TipoDeDocumentoDoAluno a = criaTipoDeDocumentoDoAluno();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		TipoDeDocumentoDoAluno consulta = dao.find(TipoDeDocumentoDoAluno.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		TipoDeDocumentoDoAluno a = criaTipoDeDocumentoDoAluno();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setDescricao("complemento");
		dao.merge(a);
		TipoDeDocumentoDoAluno consulta = dao.find(TipoDeDocumentoDoAluno.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		TipoDeDocumentoDoAluno a = criaTipoDeDocumentoDoAluno();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		TipoDeDocumentoDoAluno consulta = dao.find(TipoDeDocumentoDoAluno.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(TipoDeDocumentoDoAluno.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		TipoDeDocumentoDoAluno a1 = criaTipoDeDocumentoDoAluno();
		dao.persist(a1);
		TipoDeDocumentoDoAluno a2 = criaTipoDeDocumentoDoAluno();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM TipoDeDocumentoDoAluno a";
		TypedQuery<TipoDeDocumentoDoAluno> query = dao.getEntityManager().createQuery(sql,
				TipoDeDocumentoDoAluno.class);
		//
		List<TipoDeDocumentoDoAluno> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		TipoDeDocumentoDoAluno a = criaTipoDeDocumentoDoAluno();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		TipoDeDocumentoDoAluno a = criaTipoDeDocumentoDoAluno();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		TipoDeDocumentoDoAluno a = criaTipoDeDocumentoDoAluno();
		a.setDescricao(criarStringDinamicaPorTamanho(151));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		TipoDeDocumentoDoAluno a = criaTipoDeDocumentoDoAluno();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		TipoDeDocumentoDoAluno a = criaTipoDeDocumentoDoAluno();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private TipoDeDocumentoDoAluno criaTipoDeDocumentoDoAluno() {
		return TipoDeDocumentoDoAlunoFabricaTest.getInstance().criaTipoDeDocumentoDoAluno();
	}
}