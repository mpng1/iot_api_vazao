package br.com.pointer.sii.api.domain.modelomedidor;

import br.com.pointer.sii.api.domain.pontodecoleta.DadosCadastroPontoDeColeta;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="modelo_medidor")
@Entity(name="ModeloMedidor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ModeloMedidor {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String descricao;
	@NotBlank
	private String tipo;
	private boolean ativo;
	
	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getTipo() {
		return tipo;
	}

	public double getDiametro_polegada() {
		return diametro_polegada;
	}

	public double getFator_impulso() {
		return fator_impulso;
	}

	@NotNull
	private double diametro_polegada;
	@NotNull
	private double fator_impulso;
	
	public ModeloMedidor(DadosCadastroModeloMedidor dados) {
		this.descricao = dados.descricao();
		this.diametro_polegada = dados.diametro_polegada();
		this.fator_impulso = dados.fator_impulso();
		this.tipo = dados.tipo();
		this.ativo = true;
	}

	public void atualizarInformacoes(@Valid DadosAtualizacaoModeloMedidor dados) {
		if (dados.descricao() != null) {
			this.descricao = dados.descricao();
		}
		if (dados.diametro_polegada() != 0.0) {
			this.diametro_polegada = dados.diametro_polegada();
		}
		if (dados.fator_impulso() != 0.0) {
			this.fator_impulso = dados.fator_impulso();
		}
		if (dados.tipo() != null) {
			this.tipo = dados.tipo();
		}
		
		
	}

	public void excluir() {
		this.ativo = false;
		
	}
	
}
