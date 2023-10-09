package br.edu.ifal.mpng1.api.domain.medicao;

import java.time.LocalDate;
import java.time.LocalTime;

import br.edu.ifal.mpng1.api.domain.pontodecoleta.PontoDeColeta;

public record DadosCadastroMedicao(Long id, LocalDate data, LocalTime hora, Double valor, PontoDeColeta pontoDeColeta, Double valor_litros) {

}
