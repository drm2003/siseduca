package br.com.cdan.negocio.teste.empresa;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.cdan.model.empresa.Empresa;
import br.com.cdan.negocio.empresa.EmpresaDao;
import br.com.cdan.negocio.empresa.factory.EmpresaFabricaTest;
import br.com.cdan.util.PersistenciaJUnit;

public class EmpresaDAOTeste extends PersistenciaJUnit {
	private static final Logger LOG = Logger.getLogger(EmpresaDAOTeste.class);
	EmpresaDao dao;

	/**
	 * <c> Ao criar um teste da camada de persistï¿½ncia utilizando o JUnit ï¿½
	 * preciso ter acesso ao cont\exto de persistï¿½ncia fornecido pelo JPA.
	 * <c> Deste modo, antes da execuï¿½ï¿½o dos testes fornecemos este acesso
	 * ï¿½ camada de persistï¿½ncia por meio de uma instï¿½ncia
	 * <code>EntityManager</code> gerada pela <code>PersistenciaJUnit</code>.
	 * 
	 * @throws Exception
	 * @see br.jus.tjdft.siscor.util.PersistenciaJUnit
	 */
	@Before
	public void setUp() throws Exception {
		LOG.info("Instanciando DAOTest.");
		dao = new EmpresaDao(getEntityManager());
	}

	@Test
	public void inserir() {
		Empresa a = criaEmpresa();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Empresa consulta = dao.find(Empresa.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERÇÃO
	}

	@Test
	public void alterar() {
		Empresa a = criaEmpresa();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		a.setAtivo(false);
		a.setNome("");
		dao.merge(a);
		Empresa consulta = dao.find(Empresa.class, a.getId());// CONSULTA
		Assert.assertSame(a, consulta);// VERIFICA INSERï¿½ï¿½O
	}

	@Test
	public void excluir() {
		Empresa a = criaEmpresa();
		dao.persist(a);// INSERE
		Assert.assertNotNull(a.getId());
		Empresa consulta = dao.find(Empresa.class, a.getId());// CONSULTA
		dao.remove(a);
		Assert.assertSame(consulta, dao.find(Empresa.class, a.getId()));
	}

	@Test
	public void consultar_todos() {
		Empresa a1 = criaEmpresa();
		dao.persist(a1);
		Empresa a2 = criaEmpresa();
		dao.persist(a2);
		//
		String sql = "SELECT a FROM Empresa a";
		Query query = dao.getEntityManager().createQuery(sql, Empresa.class);
		//
		@SuppressWarnings("unchecked")
		List<Empresa> lista = query.getResultList(); //
		//
		Assert.assertTrue(lista.contains(a1));
		Assert.assertTrue(lista.contains(a2));
	}

	@Test
	public void consultar_por_nome() {
		Empresa a = criaEmpresa();
		dao.persist(a);
		Assert.assertNotNull(a);
		String sql = "FROM Empresa a WHERE a.nome = :nome";
		TypedQuery<Empresa> query = dao.getEntityManager().createQuery(sql, Empresa.class);
		query.setParameter("nome", a.getNome());
		Empresa consulta = query.getSingleResult();
		Assert.assertSame(a, consulta);
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_nome_nulo() {
		Empresa a = criaEmpresa();
		a.setNome(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_nome_vazio() {
		Empresa a = criaEmpresa();
		a.setNome("");
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nome_maior_que_tamanho_maximo_permitido() {
		Empresa a = criaEmpresa();
		a.setNome(criarStringDinamicaPorTamanho(101));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nome_menor_que_tamanho_minimo_permitido() {
		Empresa a = criaEmpresa();
		a.setNome(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void razaoSocial_menor_que_tamanho_minimo_permitido() {
		Empresa a = criaEmpresa();
		a.setRazaoSocial(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void razaoSocial_maior_que_tamanho_maximo_permitido() {
		Empresa a = criaEmpresa();
		a.setRazaoSocial(criarStringDinamicaPorTamanho(256));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_razaoSocial_nula() {
		Empresa a = criaEmpresa();
		a.setRazaoSocial(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_razaoSocial_vazia() {
		Empresa a = criaEmpresa();
		a.setRazaoSocial("");
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void sigla_menor_que_tamanho_minimo_permitido() {
		Empresa a = criaEmpresa();
		a.setSigla(criarStringDinamicaPorTamanho(2));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void sigla_maior_que_tamanho_maximo_permitido() {
		Empresa a = criaEmpresa();
		a.setSigla(criarStringDinamicaPorTamanho(11));
		dao.persist(a);
		Assert.assertNull(a.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	public void nao_deve_permitir_ativo_nulo() {
		Empresa a = criaEmpresa();
		a.setAtivo(null);
		dao.persist(a);
		Assert.assertNotNull(a.getId());
	}

	private Empresa criaEmpresa() {
		return EmpresaFabricaTest.getInstance().criaEmpresa(getEntityManager());
	}
}