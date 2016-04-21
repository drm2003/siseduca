package br.com.cdan.negocio.biblioteca.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import br.com.cdan.model.geral.Endereco;
import br.com.cdan.model.geral.Telefone;
import br.com.cdan.model.pessoa.Follow;
import br.com.cdan.model.pessoa.Responsavel;

public class ResponsavelFabricaTest {
	private static ResponsavelFabricaTest instance = null;

	public static synchronized ResponsavelFabricaTest getInstance() {
		if (instance == null) {
			instance = new ResponsavelFabricaTest();
		}
		return instance;
	}

	public Responsavel criaResponsavel() {
		Responsavel r = new Responsavel();
		r.setAluno(AlunoFabricaTest.getInstance().criaAluno());
		r.setAlunoInteressado(AlunoInteressadoFabricaTest.getInstance().criaAlunoInteressado());
		r.setAtivo(Boolean.TRUE);
		r.setEmpresa(EmpresaFabricaTest.getInstance().criaEmpresa());
		// Endereços
		Set<Endereco> enderecos = new LinkedHashSet<>();
		enderecos.add(EnderecoFabricaTest.getInstance().criaEndereco());
		r.setEnderecos(enderecos);
		// Follows
		Set<Follow> follows = new LinkedHashSet<>();
		follows.add(FollowFabricaTest.getInstance().criaFollow());
		r.setFollows(follows);
		//
		r.setLoginPortal("loginPortal");
		r.setObservacoes("observacoes");
		r.setPessoa(PessoaFabricaTest.getInstance().criaPessoa());
		r.setSenhaPortal("senhaPortal");
		// Telefones
		Set<Telefone> telefones = new LinkedHashSet<>();
		telefones.add(TelefoneFabricaTest.getInstance().criaTelefone());
		telefones.add(TelefoneFabricaTest.getInstance().criaTelefone());
		r.setTelefones(telefones);
		//
		r.setTipoDeResponsavel(TipoDeResponsavelFabricaTest.getInstance().criaTipoDeResponsavel());
		return r;
	}
}