package br.com.cdan.negocio.pedagogico.pessoa.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.pessoa.Aluno;
import br.com.cdan.model.pessoa.Documento;
import br.com.cdan.model.pessoa.DocumentoPendente;
import br.com.cdan.model.pessoa.Funcionario;
import br.com.cdan.negocio.pedagogico.pessoa.AlunoDao;
import br.com.cdan.negocio.pedagogico.pessoa.DocumentoDao;
import br.com.cdan.negocio.pedagogico.pessoa.DocumentoPendenteDao;
import br.com.cdan.negocio.pedagogico.pessoa.FuncionarioDao;

public class DocumentoPendenteFabricaTest {
	private static DocumentoPendenteFabricaTest instance = null;

	public static synchronized DocumentoPendenteFabricaTest getInstance() {
		if (instance == null) {
			instance = new DocumentoPendenteFabricaTest();
		}
		return instance;
	}

	public DocumentoPendente criaDocumentoPendente() {
		DocumentoPendente d = new DocumentoPendente();
		d.setAtivo(Boolean.TRUE);
		d.setAluno(AlunoFabricaTest.getInstance().criaAluno());
		//
		Set<Documento> documentos = new LinkedHashSet<>();
		documentos.add(DocumentoFabricaTest.getInstance().criaDocumento());
		documentos.add(DocumentoFabricaTest.getInstance().criaDocumento());
		d.setDocumentos(documentos);
		//
		d.setFuncionario(FuncionarioFabricaTest.getInstance().criaFuncionario());
		d.setObservacoes("observacoes");
		return d;
	}

	public DocumentoPendente criaDocumentoPendentePersistido(EntityManager em) {
		DocumentoPendente d = criaDocumentoPendente();
		DocumentoPendenteDao dao = new DocumentoPendenteDao(em);
		//
		AlunoDao alunoDao = new AlunoDao(em);
		Aluno aluno = d.getAluno();
		alunoDao.persist(aluno);
		d.setAluno(aluno);
		//
		Set<Documento> documentos = new LinkedHashSet<>();
		DocumentoDao documentoDao = new DocumentoDao(em);
		d.getDocumentos().forEach(documento -> {
			documentoDao.persist(documento);
			documentos.add(documento);
		});
		d.setDocumentos(documentos);
		//
		FuncionarioDao funcionarioDao = new FuncionarioDao(em);
		Funcionario funcionario = d.getFuncionario();
		funcionarioDao.persist(funcionario);
		d.setFuncionario(funcionario);
		//
		dao.persist(d);
		return d;
	}

}
