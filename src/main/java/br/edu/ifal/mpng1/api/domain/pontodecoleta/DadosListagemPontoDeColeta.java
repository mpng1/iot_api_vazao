package br.edu.ifal.mpng1.api.domain.pontodecoleta;

import java.math.BigDecimal;

import br.edu.ifal.mpng1.api.domain.modelomedidor.ModeloMedidor;

public record DadosListagemPontoDeColeta(Long id, String localizacao, String setor, ModeloMedidor modelo_medidor_id, String tipo, BigDecimal limite_vazao, AbreOuFecha estado) {
	
	public DadosListagemPontoDeColeta(PontoDeColeta pontodecoleta) {
		this(pontodecoleta.getId(), pontodecoleta.getLocalizacao(), pontodecoleta.getSetor(), pontodecoleta.getModelo_medidor_id(), pontodecoleta.getTipo(), pontodecoleta.getLimite_vazao(), pontodecoleta.getEstado());
	}
}
