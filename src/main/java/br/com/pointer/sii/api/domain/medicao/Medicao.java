package br.com.pointer.sii.api.domain.medicao;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="medicao")
@Entity(name="Medicao")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medicao {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate data;
	private LocalTime hora;
	private Double valor;
	private Double valor_litros;
	private Double valor_metros_cubicos;
	private Integer ponto_de_coleta_id;
	private Integer cliente;
	

	public Long getId() {
		return id;
	}

	public LocalDate getData() {
		return data;
	}

	public LocalTime getHora() {
		return hora;
	}

	public Double getValor() {
		return valor;
	}

	public Integer getPonto_de_coleta_id() {
		return ponto_de_coleta_id;
	}

	public Double getValor_litros() {
		return valor_litros;
	}

	public void setValor_litros(Double valor_litros) {
		this.valor_litros = valor_litros;
	}

	public Double getValor_metros_cubicos() {
		return valor_metros_cubicos;
	}

	public void setValor_metros_cubicos(Double valor_metros_cubicos) {
		this.valor_metros_cubicos = valor_metros_cubicos;
	}

	public Integer getCliente() {
		return cliente;
	}

	public Medicao(LocalDate data, LocalTime hora, Double valor, Double valor_litros, Double valor_metros_cubicos,
			Integer ponto_de_coleta_id, Integer cliente) {		
		
	}

	public Medicao(DadosCadastroMedicao dados) {
		this.data = dados.data();
		this.hora = dados.hora();
		this.valor = dados.valor();
		this.valor_litros = dados.valor_litros();
		this.valor_metros_cubicos = dados.valor_metros_cubicos();
		this.ponto_de_coleta_id = dados.ponto_de_coleta_id();
		this.cliente = dados.cliente();
	}
	
	

}
