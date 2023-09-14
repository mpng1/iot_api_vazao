package br.com.pointer.sii.api.domain.pontodecoleta;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="ponto_de_coleta")
@Entity(name="PontoDeColeta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class PontoDeColeta {

	public String getLocalizacao() {
		return localizacao;
	}

	public Long getId() {
		return id;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public Integer getModelo_medidor_id() {
		return modelo_medidor_id;
	}

	public void setModelo_medidor_id(Integer modelo_medidor_id) {
		this.modelo_medidor_id = modelo_medidor_id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String localizacao;
	@NotBlank
	private String setor;
	@NotNull
	private Integer modelo_medidor_id;
	@NotBlank
	private String tipo;
	private boolean ativo;
	
	public PontoDeColeta(DadosCadastroPontoDeColeta dados) {
		this.ativo = true;
		this.localizacao = dados.localizacao();
		this.setor = dados.setor();
		this.modelo_medidor_id = dados.modelo_medidor_id();
		this.tipo = dados.tipo();
		
	}

	public void atualizarInformacoes(@Valid DadosAtualizacaoPontoDeColeta dados) {
		if (dados.localizacao() != null) {
			this.localizacao = dados.localizacao();
		}
		if(dados.setor() != null) {
			this.setor = dados.setor();
		}
		if (dados.modelo_medidor_id() != null) {
			this.modelo_medidor_id = dados.modelo_medidor_id();
		}
		if (dados.tipo() != null) {
			this.tipo = dados.tipo();
		}
		
	}

	public void excluir() {
		this.ativo = false;
	}
}
