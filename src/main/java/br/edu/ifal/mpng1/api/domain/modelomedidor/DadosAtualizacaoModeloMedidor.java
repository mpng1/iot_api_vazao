package br.edu.ifal.mpng1.api.domain.modelomedidor;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoModeloMedidor(
		@NotNull
		Long id, 
		String descricao, 
		double diametro_polegada, 
		double fator_impulso, 
		String tipo) {

}
