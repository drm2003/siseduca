package br.com.cdan.negocio.biblioteca.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.comum.EnumEspecieDesconto;
import br.com.cdan.comum.EnumTipoDeDesconto;
import br.com.cdan.model.empresa.Empresa;
import br.com.cdan.model.financeiro.Bolsa;
import br.com.cdan.model.financeiro.ContasAReceber_Bolsa;

public class BolsaFabricaTest {
	private static BolsaFabricaTest instance = null;

	public static synchronized BolsaFabricaTest getInstance() {
		if (instance == null) {
			instance = new BolsaFabricaTest();
		}
		return instance;
	}

	public Bolsa criaBolsa() {
		Bolsa b = new Bolsa();
		b.setAtivo(Boolean.TRUE);
		// Bolsas
		Set<ContasAReceber_Bolsa> contasAReceber_Bolsa = new LinkedHashSet<>();
		contasAReceber_Bolsa.add(ContasAReceber_BolsaFabricaTest.getInstance().criaContasAReceber_Bolsa());
		contasAReceber_Bolsa.add(ContasAReceber_BolsaFabricaTest.getInstance().criaContasAReceber_Bolsa());
		b.setBolsa(contasAReceber_Bolsa);
		//
		b.setDadoBancario(DadoBancarioFabricaTest.getInstance().criaDadoBancario());
		b.setDescontos(DescontoFabricaTest.getInstance().criaDesconto());
		b.setDescricao("descricao");
		// Empresas
		Set<Empresa> empresas = new LinkedHashSet<>();
		empresas.add(EmpresaFabricaTest.getInstance().criaEmpresa());
		empresas.add(EmpresaFabricaTest.getInstance().criaEmpresa());
		b.setEmpresas(empresas);
		//
		b.setEspecieDesconto(EnumEspecieDesconto.PERCENTUAL);
		b.setPadraoFranquia("padraoFranquia");
		b.setTipoDeDesconto(EnumTipoDeDesconto.BOLSA);
		return b;
	}

	public Bolsa criaBolsaPersistido(EntityManager em) {

}
