package br.com.pointer.sii.api.domain.medicao;

import java.time.LocalDate;
import java.time.LocalTime;

import br.com.pointer.sii.api.domain.modelomedidor.ModeloMedidor;

public record DadosListagemMedicao(Long id, LocalDate data, LocalTime hora, Double valor, Integer ponto_de_coleta_id, Double valor_litros, Double valor_metros_cubicos, Integer cliente) {

	public DadosListagemMedicao(Medicao medicao) {
		this(medicao.getId(), medicao.getData(), medicao.getHora(), medicao.getValor(), medicao.getPonto_de_coleta_id(), medicao.getValor_litros(), medicao.getValor_metros_cubicos(), medicao.getCliente());
	}
}
