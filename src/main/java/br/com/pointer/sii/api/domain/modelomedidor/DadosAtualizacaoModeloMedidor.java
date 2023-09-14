package br.com.pointer.sii.api.domain.modelomedidor;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoModeloMedidor(
		@NotNull
		Long id, 
		String descricao, 
		double diametro_polegada, 
		double fator_impulso, 
		String tipo) {

}
