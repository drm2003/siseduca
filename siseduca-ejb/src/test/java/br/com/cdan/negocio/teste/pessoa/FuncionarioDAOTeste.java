package br.com.cdan.negocio.teste.pessoa;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.pessoa.Funcionario;
import br.com.cdan.negocio.pedagogico.pessoa.FuncionarioDao;
import br.com.cdan.negocio.pedagogico.pessoa.factory.FuncionarioFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class FuncionarioDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(FuncionarioDAOTeste.class);
	FuncionarioDao dao;

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
		dao = new FuncionarioDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Funcionario a = criaFuncionario();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Funcionario consulta = dao.find(Funcionario.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		Funcionario a = criaFuncionario();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setNomeApelido(criarStringDinamicaPorTamanho(10));
		dao.merge(a);
		Funcionario consulta = dao.find(Funcionario.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		Funcionario a = criaFuncionario();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Funcionario consulta = dao.find(Funcionario.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Funcionario.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		Funcionario a1 = criaFuncionario();
		dao.persist(a1);
		Funcionario a2 = criaFuncionario();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Funcionario a";
		TypedQuery<Funcionario> query = dao.getEntityManager().createQuery(sql, Funcionario.class);
		//
		List<Funcionario> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		Funcionario a = criaFuncionario();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private Funcionario criaFuncionario() {
		return FuncionarioFabricaTest.getInstance().criaFuncionario(getEntityManager());
	}
}