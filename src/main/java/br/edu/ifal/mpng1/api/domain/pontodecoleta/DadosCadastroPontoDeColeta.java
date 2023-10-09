package br.edu.ifal.mpng1.api.domain.pontodecoleta;

import java.math.BigDecimal;

import br.edu.ifal.mpng1.api.domain.modelomedidor.ModeloMedidor;

public record DadosCadastroPontoDeColeta(String localizacao, String setor, ModeloMedidor modelo_medidor_id, String tipo, BigDecimal limite_vazao, AbreOuFecha estado) {

}
