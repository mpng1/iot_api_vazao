package br.edu.ifal.mpng1.api.domain.medicao;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.edu.ifal.mpng1.api.domain.pontodecoleta.PontoDeColeta;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="medicao")
@Entity(name="Medicao")
@EqualsAndHashCode(of = "id")
public class Medicao {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate data;
	private LocalTime hora;
	private Double valor;
	private Double valor_litros;
	@ManyToOne
	@JoinColumn(name="ponto_de_coleta_id")
	private PontoDeColeta pontoDeColeta;
	
	public Medicao() {
		
	}

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

	public PontoDeColeta getPontoDeColeta() {
		return pontoDeColeta;
	}

	public Double getValor_litros() {
		return valor_litros;
	}

	public void setValor_litros(Double valor_litros) {
		this.valor_litros = valor_litros;
	}


	public Medicao(LocalDate data, LocalTime hora, Double valor, Double valor_litros,
			Long ponto_de_coleta_id, Integer cliente) {		
		
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public void setPontoDeColeta(PontoDeColeta pontoDeColeta) {
		this.pontoDeColeta = pontoDeColeta;
	}


	public Medicao(DadosCadastroMedicao dados) {
		this.data = dados.data();
		this.hora = dados.hora();
		this.valor = dados.valor();
		this.valor_litros = dados.valor_litros();
		this.pontoDeColeta = dados.pontoDeColeta();
	}

	

}
