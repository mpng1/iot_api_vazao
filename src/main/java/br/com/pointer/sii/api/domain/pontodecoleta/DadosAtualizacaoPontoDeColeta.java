package br.com.pointer.sii.api.domain.pontodecoleta;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPontoDeColeta(
		@NotNull
		Long id, 
		String localizacao, 
		String setor, 
		Integer modelo_medidor_id, 
		String tipo) {

}
