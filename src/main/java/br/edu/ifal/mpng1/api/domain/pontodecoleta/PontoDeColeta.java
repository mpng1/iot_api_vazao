package br.edu.ifal.mpng1.api.domain.pontodecoleta;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.edu.ifal.mpng1.api.domain.modelomedidor.ModeloMedidor;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="ponto_de_coleta")
@Entity(name="PontoDeColeta")
@EqualsAndHashCode(of = "id")
public class PontoDeColeta {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String localizacao;
	private String setor;
	@ManyToOne
	@JoinColumn(name="modelo_medidor_id")
	private ModeloMedidor modelo_medidor_id;
	private String tipo;
	private BigDecimal limite_vazao;
	private AbreOuFecha estado;
	
	public AbreOuFecha getEstado() {
		return estado;
	}

	public void setEstado(AbreOuFecha estado) {
		this.estado = estado;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private boolean ativo;

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

	public ModeloMedidor getModelo_medidor_id() {
		return modelo_medidor_id;
	}

	public void setModelo_medidor_id(ModeloMedidor modelo_medidor_id) {
		this.modelo_medidor_id = modelo_medidor_id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	public PontoDeColeta() {
		
	}

	public PontoDeColeta(Long id, String localizacao, String setor, ModeloMedidor modelo_medidor_id,
		 String tipo, BigDecimal limite_vazao, AbreOuFecha estado, boolean ativo) {
		this.id = id;
		this.localizacao = localizacao;
		this.setor = setor;
		this.modelo_medidor_id = modelo_medidor_id;
		this.tipo = tipo;
		this.limite_vazao = limite_vazao;
		this.estado = estado;
		this.ativo = ativo;
	}

	public PontoDeColeta(DadosCadastroPontoDeColeta dados) {
		this.ativo = true;
		this.localizacao = dados.localizacao();
		this.setor = dados.setor();
		this.modelo_medidor_id = dados.modelo_medidor_id();
		this.tipo = dados.tipo();		
		this.limite_vazao = dados.limite_vazao();
		this.estado = dados.estado().ABRE;
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
		if (dados.limite_vazao() != null) {
			this.limite_vazao = dados.limite_vazao();
		}
		if (dados.estado() != null) {
			this.estado = dados.estado();
		}
		
	}
	public void excluir() {
		this.ativo = false;
	}

	public BigDecimal getLimite_vazao() {
		return limite_vazao;
	}

	public void setLimite_vazao(BigDecimal limite_vazao) {
		this.limite_vazao = limite_vazao;
	}

	@Override
	public String toString() {
		return "PontoDeColeta [id=" + id + ", localizacao=" + localizacao + ", setor=" + setor + ", modelo_medidor_id="
				+ modelo_medidor_id + ", tipo=" + tipo + ", limite_vazao=" + limite_vazao + ", ativo=" + ativo + "]";
	}
	
	public PontoDeColeta(Long id) {
		this.id = id;
	}
	
	
}
