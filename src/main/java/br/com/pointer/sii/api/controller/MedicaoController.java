package br.com.pointer.sii.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.pointer.sii.api.domain.medicao.DadosCadastroMedicao;
import br.com.pointer.sii.api.domain.medicao.DadosDetalhamentoMedicao;
import br.com.pointer.sii.api.domain.medicao.DadosListagemMedicao;
import br.com.pointer.sii.api.domain.medicao.Medicao;
import br.com.pointer.sii.api.domain.medicao.MedicaoRepository;
import br.com.pointer.sii.api.domain.modelomedidor.ModeloMedidorService;
import br.com.pointer.sii.api.domain.pontodecoleta.DadosCadastroPontoDeColeta;
import br.com.pointer.sii.api.domain.pontodecoleta.DadosDetalhamentoPontoDeColeta;
import br.com.pointer.sii.api.domain.pontodecoleta.PontoDeColeta;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("medicao")
public class MedicaoController {
	
	@Autowired
	private ModeloMedidorService modeloMedidorService;
	
	@Autowired
	private MedicaoRepository repository;

	@GetMapping
	public ResponseEntity<Page<DadosListagemMedicao>> listar(@PageableDefault(size = 10, sort = {"id"}) Pageable paginacao) {
		
		modeloMedidorService.updateMedicaoValorLitrosAndValorMetrosCubicos();
		
		var page = repository.findAll(paginacao).map(DadosListagemMedicao::new);
		
		return ResponseEntity.ok(page);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedicao dados, UriComponentsBuilder uriBuilder) {
		
		var medicao = new Medicao(dados);
		
		repository.save(medicao);
		
		var uri = uriBuilder.path("/medicao/{id}").buildAndExpand(medicao.getId()).toUri();
		
		
		return ResponseEntity.created(uri).body(new DadosDetalhamentoMedicao(medicao));
		
	}
	
}
