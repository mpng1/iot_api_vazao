package br.com.pointer.sii.api.domain.pontodecoleta;

public record DadosListagemPontoDeColeta(Long id, String localizacao, String setor, Integer modelo_medidor_id, String tipo) {
	
	public DadosListagemPontoDeColeta(PontoDeColeta pontodecoleta) {
		this(pontodecoleta.getId(), pontodecoleta.getLocalizacao(), pontodecoleta.getSetor(), pontodecoleta.getModelo_medidor_id(), pontodecoleta.getTipo());
	}
}
