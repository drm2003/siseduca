package br.com.cdan.model.pedagogico.contrato;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "MotivoDeTransferencia")
public class MotivoDeTransferencia implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@NotNull
	@NotEmpty
	@Column(name = "descricao", nullable = false, unique = true)
	private String descricao;

	@OneToMany(mappedBy = "motivoDeTransferencia")
	private Set<Transferencia> tranferencias;

	@Column(name = "ativo")
	private Boolean ativo;
}
