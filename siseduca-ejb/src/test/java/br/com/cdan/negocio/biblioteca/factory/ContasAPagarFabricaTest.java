package br.com.cdan.negocio.biblioteca.factory;

import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import br.com.cdan.model.empresa.Empresa;
import br.com.cdan.model.financeiro.ContasAPagar;
import br.com.cdan.model.pessoa.Aluno;
import br.com.cdan.model.pessoa.Funcionario;

public class ContasAPagarFabricaTest {
	private static ContasAPagarFabricaTest instance = null;

	public static synchronized ContasAPagarFabricaTest getInstance() {
		if (instance == null) {
			instance = new ContasAPagarFabricaTest();
		}
		return instance;
	}

	public ContasAPagar criaContasAPagar() {
		ContasAPagar c = new ContasAPagar();
		// Alunos
		Set<Aluno> alunos = new LinkedHashSet<>();
		alunos.add(AlunoFabricaTest.getInstance().criaAluno());
		alunos.add(AlunoFabricaTest.getInstance().criaAluno());
		c.setAlunos(alunos);
		c.setAtivo(Boolean.TRUE);
		c.setComplementoPlanoDeConta("complemento plano de conta");
		c.setConta(ContaFabricaTest.getInstance().criaConta());
		c.setDataCompetenciaPlanoDeConta(Calendar.getInstance());
		c.setDataVencimento(Calendar.getInstance());
		c.setDocumentoPlanoDeConta("documentoPlanoDeConta");
		// Empresas
		Set<Empresa> empresas = new LinkedHashSet<>();
		empresas.add(EmpresaFabricaTest.getInstance().criaEmpresa());
		c.setEmpresas(empresas);
		// Funcionários
		Set<Funcionario> funcionarios = new LinkedHashSet<>();
		funcionarios.add(FuncionarioTest.getInstance().criaFuncionario());
		funcionarios.add(FuncionarioTest.getInstance().criaFuncionario());
		c.setFuncionarios(funcionarios);
		return c;
	}

}
