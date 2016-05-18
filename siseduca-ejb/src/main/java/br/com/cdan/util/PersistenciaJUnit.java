package br.com.cdan.util;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Classe utilizada para permitir o acesso à camada de persistência para testes
 * JUnit.fos
 * 
 * @author Daniel Monteiro
 */
@RunWith(JUnit4.class)
public class PersistenciaJUnit {
	private static final String PERSISTENCE_UNIT_TESTES = "SisEducaPULocal";
	private static final String DRIVER_CONEXAO = "com.mysql.jdbc.Driver";
	// private static final String URL_CONEXAO_BANCO =
	// "jdbc:oracle:thin:@tjsu690v:1521:ORADES";
	private static final String URL_CONEXAO_BANCO = "jdbc:mysql://localhost:3306/siseduca?useSSL=false";
	private static final String USUARIO_CONEXAO = "root";
	private static final String SENHA_CONEXAO = "admin";
	private static final Logger LOG = Logger.getLogger(PersistenciaJUnit.class);
	private Connection connection;
	private EntityManager em;

	/**
	 * Recupera uma inst?ncia <code>EntityManager</code>.
	 * <p>
	 * O <code>EntityManager</code> está relacionado a um contexto de
	 * persistência.
	 */
	protected EntityManager getEntityManager() {
		if (em == null) {
			criaEntityManagerDoJPA();
		}
		return em;
	}

	/**
	 * 
	 * Classe utilizada para permitir que testes JUnit consigam acesso ao
	 * contexto de persistência fornecido pelo JPA.
	 * <p>
	 * Permite o acesso ao banco de dados sem a necessidade de deploy em um
	 * servidor de aplicação.
	 */
	public PersistenciaJUnit() {
		inicializa();
	}

	/**
	 * Permite o acesso ao banco de dados:
	 * <p>
	 * 1. Estabelece a conexão com o banco de dados.
	 * <p>
	 * 2. Cria uma instância <code>EntityManager</code> para interagir com o
	 * contexto de persistência.
	 * <p>
	 * 3. Inicializa uma transa??o <code>EntityTransaction</code>, que pode ser
	 * usada ser utilizada em s?rie para come?ar e confirmar v?rias transa??es.
	 */
	public void inicializa() {
		estabeleceConexaoComBancoDeDados();
		criaEntityManagerDoJPA();
		inicializaTransacao();
	}

	/**
	 * Tenta estabelecer uma conexão com o banco de dados.
	 * <p>
	 * O <code>DriverManager</code> tenta selecionar o driver apropriado entre
	 * os drivers JDBC disponíveis.
	 */
	private void estabeleceConexaoComBancoDeDados() {
		try {
			try {
				Class.forName(DRIVER_CONEXAO);
			} catch (ClassNotFoundException e) {
				LOG.error(e.getMessage(), e);
				fail("Não encontrou o driver para conectar ao banco de dados.");
			}
			connection = DriverManager.getConnection(URL_CONEXAO_BANCO, USUARIO_CONEXAO, SENHA_CONEXAO);
			LOG.info("JUnit conectou-se ao banco de dados.");
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
			fail("Exceção durante a conexão com o banco de dados.");
		}
	}

	/**
	 * Cria uma inst?ncia <code>EntityManager</code> para interagir com o
	 * contexto de persist?ncia.
	 * <p>
	 * A API <code>EntityManager</code> é utilizada para criar e remover
	 * instâncias de entidades, para consultar entidades por sua chave prim?ria,
	 * e para consultar outras entidades.
	 */
	private void criaEntityManagerDoJPA() {
		try {
			em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_TESTES).createEntityManager();
			em.setFlushMode(FlushModeType.AUTO);
			LOG.info("JUnit inicializou o EntityManager do JPA.");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			fail("Exceção durante a inicialização do EntityManager JPA.");
		}
	}

	/**
	 * Inicializa uma transação <code>EntityTransaction</code>, que pode ser
	 * usada ser utilizada em série para começar e confirmar várias transações.
	 * <p>
	 * Após a inicialização da transação esta é colocada em modo de "roll back",
	 * deste modo a transação será sempre desfeita.
	 */
	private void inicializaTransacao() {
		if (em != null) {
			if (em.getTransaction() == null || !em.getTransaction().isActive()) {
				em.getTransaction().begin();
				em.getTransaction().setRollbackOnly();
			}
		}
	}

	/**
	 * Ap?s a execu??o dos testes JUnit os recursos necess?rios para acesso a
	 * camada de persist?ncia s?o liberados.
	 * <p>
	 * 1. Reverte as altera??es efetuadas (roll back).
	 * <p>
	 * 2. Finaliza o <code>EntityManager</code>.
	 * <p>
	 * 3. Fecha a conex?o com o banco de dados.
	 */
	@After
	public void finaliza() throws Exception {
		encerraTransacaoComRollback();
		finalizaEntityManagerJPA();
		// encerraConexaoComBancoDeDados();
	}

	/**
	 * Reverte as altera??es efetuadas (roll back).
	 */
	private void encerraTransacaoComRollback() {
		LOG.info("Desfaz transação com roll back.");
		if (em.getTransaction() != null && em.getTransaction().isActive()) {
			em.getTransaction().rollback();
		}
	}

	/**
	 * Finaliza o <code>EntityManager</code>.
	 */
	private void finalizaEntityManagerJPA() {
		if (em != null) {
			em.close();
		}
		LOG.info("JUnit fechou camada JPA do Hibernate.");
	}

	/**
	 * Fecha a conexão com o banco de dados.
	 */
	protected void encerraConexaoComBancoDeDados() {
		try {
			connection.createStatement().execute("SHUTDOWN");
		} catch (Exception ex) {
		}
		LOG.info("JUnit encerrou a conexão com o banco de dados.");
	}

	/**
	 * 
	 * @param quantidade
	 * @return
	 * 
	 * 		CRIAR STRING A PARTIR DE UM DETERMINADO TAMANHO SERVE PARA TESTES
	 *         DE CAMPOS
	 */
	public String criarStringDinamicaPorTamanho(Integer quantidade) {
		String s = "";
		int x = 0;
		for (int i = 0; i < quantidade; i++) {
			s += (int) (Math.random() * 10) + "";
			if (x == 10)
				x = 0;
		}
		return s;
	}
}