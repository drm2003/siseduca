package br.com.cdan.negocio.teste.geral;

import java.util.List;

import javax.persistence.Query;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.geral.Equipamento;
import br.com.cdan.negocio.geral.EquipamentoDao;
import br.com.cdan.negocio.geral.factory.EquipamentoFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class EquipamentoDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(EquipamentoDAOTeste.class);
	EquipamentoDao dao;

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
		dao = new EquipamentoDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Equipamento a = criaEquipamento();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Equipamento consulta = dao.find(Equipamento.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		Equipamento a = criaEquipamento();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setNome("nome" + Math.random() * 10000);
		a.setDescricao("complemento");
		dao.merge(a);
		Equipamento consulta = dao.find(Equipamento.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		Equipamento a = criaEquipamento();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Equipamento consulta = dao.find(Equipamento.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Equipamento.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		Equipamento a1 = criaEquipamento();
		dao.persist(a1);
		Equipamento a2 = criaEquipamento();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Equipamento a";
		Query query = dao.getEntityManager().createQuery(sql, Equipamento.class);
		//
		@SuppressWarnings("unchecked")
		List<Equipamento> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_nome_vazio() {
		Equipamento a = criaEquipamento();
		a.setNome("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nome_maior_que_tamanho_maximo_permitido() {
		Equipamento a = criaEquipamento();
		a.setNome(criarStringDinamicaPorTamanho(151));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nome_menor_que_tamanho_minimo_permitido() {
		Equipamento a = criaEquipamento();
		a.setNome(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_nome_nulo() {
		Equipamento a = criaEquipamento();
		a.setNome(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	/**
	 * 
	 * DESCRIÇÃO
	 * 
	 */
	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_vazio() {
		Equipamento a = criaEquipamento();
		a.setDescricao("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_maior_que_tamanho_maximo_permitido() {
		Equipamento a = criaEquipamento();
		a.setDescricao(criarStringDinamicaPorTamanho(151));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricao_menor_que_tamanho_minimo_permitido() {
		Equipamento a = criaEquipamento();
		a.setDescricao(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricao_nulo() {
		Equipamento a = criaEquipamento();
		a.setDescricao(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		Equipamento a = criaEquipamento();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private Equipamento criaEquipamento() {
		return EquipamentoFabricaTest.getInstance().criaEquipamento(getEntityManager());
	}
}