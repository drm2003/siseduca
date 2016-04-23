package br.com.cdan.negocio.biblioteca.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import br.com.cdan.model.acesso.AcessoDiasDaSemana;
import br.com.cdan.model.empresa.Empresa;
import br.com.cdan.model.pedagogico.HorarioDeAula;
import br.com.cdan.model.pedagogico.curso.Turma;

public class HorarioDeAulaFabricaTest {
	private static HorarioDeAulaFabricaTest instance = null;

	public static synchronized HorarioDeAulaFabricaTest getInstance() {
		if (instance == null) {
			instance = new HorarioDeAulaFabricaTest();
		}
		return instance;
	}

	public HorarioDeAula criaHorarioDeAula() {
		HorarioDeAula h = new HorarioDeAula();
		// Acessos Dias da semana
		Set<AcessoDiasDaSemana> acessosDiasDaSemana = new LinkedHashSet<>();
		acessosDiasDaSemana.add(AcessoDiasDaSemanaFabricaTest.getInstance().criaAcessoDiaDaSemana());
		acessosDiasDaSemana.add(AcessoDiasDaSemanaFabricaTest.getInstance().criaAcessoDiaDaSemana());
		h.setAcessoDiasDaSemana(acessosDiasDaSemana);
		//
		h.setAtivo(Boolean.TRUE);
		h.setDescricao("descricao");
		// Empresas
		Set<Empresa> empresas = new LinkedHashSet<>();
		empresas.add(EmpresaFabricaTest.getInstance().criaEmpresa());
		empresas.add(EmpresaFabricaTest.getInstance().criaEmpresa());
		h.setEmpresas(empresas);
		//
		h.setQuantidadeDeAula(Long.valueOf("10"));
		// Turmas
		Set<Turma> turmas = new LinkedHashSet<>();
		turmas.add(TurmaFabricaTest.getInstance().criaTurma());
		turmas.add(TurmaFabricaTest.getInstance().criaTurma());
		h.setTurmas(turmas);
		//
		return h;
	}

}
