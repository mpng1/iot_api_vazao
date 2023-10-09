package br.edu.ifal.mpng1.api.domain.medicao;

import java.time.LocalDate;
import java.time.LocalTime;

import br.edu.ifal.mpng1.api.domain.pontodecoleta.PontoDeColeta;

public record DadosDetalhamentoMedicao(Long id, LocalDate data, LocalTime hora, Double valor,PontoDeColeta pontoDeColeta, Double valor_litros) {

    public DadosDetalhamentoMedicao(Medicao medicao) {
        this(medicao.getId(), medicao.getData(), medicao.getHora(), medicao.getValor(), medicao.getPontoDeColeta(), medicao.getValor_litros());
    }
    
    
		
}
