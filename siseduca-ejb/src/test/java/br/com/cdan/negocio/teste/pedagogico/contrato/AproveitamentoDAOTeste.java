package br.com.cdan.negocio.teste.pedagogico.contrato;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.contrato.Aproveitamento;
import br.com.cdan.negocio.pedagogico.contrato.AproveitamentoDao;
import br.com.cdan.negocio.pedagogico.contrato.factory.AproveitamentoFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class AproveitamentoDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(AproveitamentoDAOTeste.class);
	AproveitamentoDao dao;

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
		dao = new AproveitamentoDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Aproveitamento a = criaAproveitamento();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Aproveitamento consulta = dao.find(Aproveitamento.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		Aproveitamento a = criaAproveitamento();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setNomeCurso("complemento");
		dao.merge(a);
		Aproveitamento consulta = dao.find(Aproveitamento.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		Aproveitamento a = criaAproveitamento();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Aproveitamento consulta = dao.find(Aproveitamento.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Aproveitamento.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		Aproveitamento a1 = criaAproveitamento();
		dao.persist(a1);
		Aproveitamento a2 = criaAproveitamento();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Aproveitamento a";
		TypedQuery<Aproveitamento> query = dao.getEntityManager().createQuery(sql, Aproveitamento.class);
		//
		List<Aproveitamento> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_NomeCurso_vazio() {
		Aproveitamento a = criaAproveitamento();
		a.setNomeCurso("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void NomeCurso_maior_que_tamanho_maximo_permitido() {
		Aproveitamento a = criaAproveitamento();
		a.setNomeCurso(criarStringDinamicaPorTamanho(151));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void NomeCurso_menor_que_tamanho_minimo_permitido() {
		Aproveitamento a = criaAproveitamento();
		a.setNomeCurso(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_NomeCurso_nulo() {
		Aproveitamento a = criaAproveitamento();
		a.setNomeCurso(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_nomeCurso() {
		Aproveitamento a = criaAproveitamento();
		dao.persist(a);
		//
		String sql = "SELECT a FROM Aproveitamento a WHERE a.nomeCurso = :nomeCurso";
		TypedQuery<Aproveitamento> query = dao.getEntityManager().createQuery(sql, Aproveitamento.class);
		query.setParameter("nomeCurso", a.getNomeCurso());
		//
		List<Aproveitamento> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a));
	}

	/**
	 * 
	 * 
	 * NOMEDISCIPLINA
	 * 
	 */

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_NomeDisciplina_vazio() {
		Aproveitamento a = criaAproveitamento();
		a.setNomeDisciplina("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void NomeDisciplina_maior_que_tamanho_maximo_permitido() {
		Aproveitamento a = criaAproveitamento();
		a.setNomeDisciplina(criarStringDinamicaPorTamanho(151));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void NomeDisciplina_menor_que_tamanho_minimo_permitido() {
		Aproveitamento a = criaAproveitamento();
		a.setNomeDisciplina(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_NomeDisciplina_nulo() {
		Aproveitamento a = criaAproveitamento();
		a.setNomeDisciplina(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_nomeDisciplina() {
		Aproveitamento a = criaAproveitamento();
		dao.persist(a);
		//
		String sql = "SELECT a FROM Aproveitamento a WHERE a.nomeDisciplina = :nomeDisciplina";
		TypedQuery<Aproveitamento> query = dao.getEntityManager().createQuery(sql, Aproveitamento.class);
		query.setParameter("nomeDisciplina", a.getNomeDisciplina());
		//
		List<Aproveitamento> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a));
	}

	/**
	 * Parecer coordenador
	 * 
	 */
	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_parecerCoordenador_vazio() {
		Aproveitamento a = criaAproveitamento();
		a.setParecerCoordenador("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void parecerCoordenador_menor_que_tamanho_minimo_permitido() {
		Aproveitamento a = criaAproveitamento();
		a.setParecerCoordenador(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_parecerCoordenador_nulo() {
		Aproveitamento a = criaAproveitamento();
		a.setParecerCoordenador(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_parecerCoordenador() {
		Aproveitamento a = criaAproveitamento();
		dao.persist(a);
		//
		String sql = "SELECT a FROM Aproveitamento a WHERE a.parecerCoordenador = :parecerCoordenador";
		TypedQuery<Aproveitamento> query = dao.getEntityManager().createQuery(sql, Aproveitamento.class);
		query.setParameter("parecerCoordenador", a.getParecerCoordenador());
		//
		List<Aproveitamento> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a));
	}

	/*
	 * 
	 */
	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_necessarioExameProficiencia_nulo() {
		Aproveitamento a = criaAproveitamento();
		a.setNecessarioExameProficiencia(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_confirmaCredito_nulo() {
		Aproveitamento a = criaAproveitamento();
		a.setConfirmaCredito(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		Aproveitamento a = criaAproveitamento();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private Aproveitamento criaAproveitamento() {
		return AproveitamentoFabricaTest.getInstance().criaAproveitamento(getEntityManager());
	}
}