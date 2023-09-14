package br.com.pointer.sii.api.domain.modelomedidor;

public record DadosDetalhamentoModeloMedidor(Long id, String descricao, double diametro_polegada, double fator_impulso, String tipo) {

	public DadosDetalhamentoModeloMedidor(ModeloMedidor modelomedidor) {
		this(modelomedidor.getId(), modelomedidor.getDescricao(), modelomedidor.getDiametro_polegada(), modelomedidor.getFator_impulso(), modelomedidor.getTipo());
	}
}
