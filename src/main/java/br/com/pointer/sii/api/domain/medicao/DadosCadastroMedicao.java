package br.com.pointer.sii.api.domain.medicao;

import java.time.LocalDate;
import java.time.LocalTime;

public record DadosCadastroMedicao(LocalDate data, LocalTime hora, Double valor, Integer ponto_de_coleta_id, Double valor_litros, Double valor_metros_cubicos, Integer cliente) {

}
