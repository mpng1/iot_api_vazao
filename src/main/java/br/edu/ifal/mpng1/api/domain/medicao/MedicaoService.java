package br.edu.ifal.mpng1.api.domain.medicao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifal.mpng1.api.domain.pontodecoleta.AbreOuFecha;
import br.edu.ifal.mpng1.api.domain.pontodecoleta.PontoDeColeta;
import br.edu.ifal.mpng1.api.domain.pontodecoleta.PontoDeColetaRepository;

@Service
public class MedicaoService {
	
	@Autowired
	private MedicaoRepository repository;

    @Autowired
    private PontoDeColetaRepository pontoDeColetaRepository;

    public Medicao cadastrarMedicao(DadosCadastroMedicao dadosCadastro) throws Exception {
        var medicao = new Medicao(dadosCadastro);
        
        Long pontoDeColetaId = dadosCadastro.pontoDeColeta().getId();
        PontoDeColeta pontoDeColeta = pontoDeColetaRepository.findById(pontoDeColetaId).orElse(null);

        if (pontoDeColeta == null) {
            throw new Exception("Ponto de coleta not found for ID: " + pontoDeColetaId);
        }

        medicao.setPontoDeColeta(pontoDeColeta);

        double valorLitros = calcularValorLitros(medicao.getValor(), pontoDeColeta.getModelo_medidor_id().getFator_impulso());
        medicao.setValor_litros(valorLitros);

        if (valorLitros > pontoDeColeta.getLimite_vazao().doubleValue()) {
            pontoDeColeta.setEstado(AbreOuFecha.FECHA);
        } else {
            pontoDeColeta.setEstado(AbreOuFecha.ABRE);
        }

        pontoDeColetaRepository.save(pontoDeColeta);

        return repository.save(medicao);
    }

    public double calcularValorLitros(double valor, double fatorImpulso) {
        if (fatorImpulso != 0) {
            return valor / fatorImpulso;
        }
        return 0;
    }
}

