package br.com.cdan.negocio.teste.pedagogico.contrato;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.contrato.DisciplinaMatricula;
import br.com.cdan.negocio.pedagogico.contrato.DisciplinaMatriculaDao;
import br.com.cdan.negocio.pedagogico.contrato.factory.DisciplinaMatriculaFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class DisciplinaMatriculaDaoTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(DisciplinaMatriculaDaoTeste.class);
	DisciplinaMatriculaDao dao;

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
		dao = new DisciplinaMatriculaDao(getEntityManager());
	}

	@Test
	public void inserir() {
		DisciplinaMatricula a = criaDisciplinaMatricula();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		DisciplinaMatricula consulta = dao.find(DisciplinaMatricula.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		DisciplinaMatricula a = criaDisciplinaMatricula();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setNome("complemento");
		dao.merge(a);
		DisciplinaMatricula consulta = dao.find(DisciplinaMatricula.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		DisciplinaMatricula a = criaDisciplinaMatricula();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		DisciplinaMatricula consulta = dao.find(DisciplinaMatricula.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(DisciplinaMatricula.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		DisciplinaMatricula a1 = criaDisciplinaMatricula();
		dao.persist(a1);
		DisciplinaMatricula a2 = criaDisciplinaMatricula();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM DisciplinaMatricula a";
		TypedQuery<DisciplinaMatricula> query = dao.getEntityManager().createQuery(sql, DisciplinaMatricula.class);
		//
		List<DisciplinaMatricula> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_nome_nulo() {
		DisciplinaMatricula a = criaDisciplinaMatricula();
		a.setNome(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_nome_vazio() {
		DisciplinaMatricula a = criaDisciplinaMatricula();
		a.setNome("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nome_maior_que_tamanho_maximo_permitido() {
		DisciplinaMatricula a = criaDisciplinaMatricula();
		a.setNome(criarStringDinamicaPorTamanho(151));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_nome() {
		DisciplinaMatricula a = criaDisciplinaMatricula();
		dao.persist(a);
		String sql = "SELECT a FROM DisciplinaMatricula a WHERE a.nome = :nome";
		TypedQuery<DisciplinaMatricula> query = dao.getEntityManager().createQuery(sql, DisciplinaMatricula.class);
		query.setParameter("nome", a.getNome());
		List<DisciplinaMatricula> lista = query.getResultList();
		Assert.assertTrue(lista.contains(a));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nome_menor_que_tamanho_minimo_permitido() {
		DisciplinaMatricula a = criaDisciplinaMatricula();
		a.setNome(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	/**
	 * 
	 * SIGLA
	 */

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_sigla_nulo() {
		DisciplinaMatricula a = criaDisciplinaMatricula();
		a.setSigla(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_sigla_vazio() {
		DisciplinaMatricula a = criaDisciplinaMatricula();
		a.setSigla("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void sigla_maior_que_tamanho_maximo_permitido() {
		DisciplinaMatricula a = criaDisciplinaMatricula();
		a.setSigla(criarStringDinamicaPorTamanho(151));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_sigla() {
		DisciplinaMatricula a = criaDisciplinaMatricula();
		dao.persist(a);
		String sql = "SELECT a FROM DisciplinaMatricula a WHERE a.sigla = :sigla";
		TypedQuery<DisciplinaMatricula> query = dao.getEntityManager().createQuery(sql, DisciplinaMatricula.class);
		query.setParameter("sigla", a.getSigla());
		List<DisciplinaMatricula> lista = query.getResultList();
		Assert.assertTrue(lista.contains(a));
	}

	@Test(expected = ConstraintViolationException.class)
	public void sigla_menor_que_tamanho_minimo_permitido() {
		DisciplinaMatricula a = criaDisciplinaMatricula();
		a.setSigla(criarStringDinamicaPorTamanho(1));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_compartilhado_nulo() {
		DisciplinaMatricula a = criaDisciplinaMatricula();
		a.setCompartilhado(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		DisciplinaMatricula a = criaDisciplinaMatricula();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private DisciplinaMatricula criaDisciplinaMatricula() {
		return DisciplinaMatriculaFabricaTest.getInstance().criaDisciplinaMatricula(getEntityManager());
	}
}