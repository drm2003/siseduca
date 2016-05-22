package br.com.cdan.negocio.teste.pedagogico.curso;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pedagogico.curso.Investimento;
import br.com.cdan.negocio.pedagogico.curso.InvestimentoDao;
import br.com.cdan.negocio.pedagogico.curso.factory.InvestimentoFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class InvestimentoDaoTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(InvestimentoDaoTeste.class);
	InvestimentoDao dao;

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
		dao = new InvestimentoDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Investimento a = criaInvestimento();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Investimento consulta = dao.find(Investimento.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		Investimento a = criaInvestimento();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setDescricaoPlano("complemento");
		dao.merge(a);
		Investimento consulta = dao.find(Investimento.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		Investimento a = criaInvestimento();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Investimento consulta = dao.find(Investimento.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Investimento.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		Investimento a1 = criaInvestimento();
		dao.persist(a1);
		Investimento a2 = criaInvestimento();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Investimento a";
		TypedQuery<Investimento> query = dao.getEntityManager().createQuery(sql, Investimento.class);
		//
		List<Investimento> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricaoPlano_nulo() {
		Investimento a = criaInvestimento();
		a.setDescricaoPlano(null);
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_descricaoPlano_vazio() {
		Investimento a = criaInvestimento();
		a.setDescricaoPlano("");
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test
	public void consultar_por_descricaoPlano() {
		Investimento a = criaInvestimento();
		dao.persist(a);
		String sql = "SELECT a FROM Investimento a WHERE a.descricaoPlano = :descricaoPlano";
		TypedQuery<Investimento> query = dao.getEntityManager().createQuery(sql, Investimento.class);
		query.setParameter("descricaoPlano", a.getDescricaoPlano());
		List<Investimento> lista = query.getResultList();
		Assert.assertTrue(lista.contains(a));
	}

	@Test(expected = ConstraintViolationException.class)
	public void descricaoPlano_menor_que_tamanho_minimo_permitido() {
		Investimento a = criaInvestimento();
		a.setDescricaoPlano(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		Investimento a = criaInvestimento();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private Investimento criaInvestimento() {
		return InvestimentoFabricaTest.getInstance().criaInvestimento(getEntityManager());
	}
}