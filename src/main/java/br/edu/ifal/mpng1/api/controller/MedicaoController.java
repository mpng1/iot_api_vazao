package br.edu.ifal.mpng1.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.edu.ifal.mpng1.api.domain.medicao.DadosCadastroMedicao;
import br.edu.ifal.mpng1.api.domain.medicao.DadosDetalhamentoMedicao;
import br.edu.ifal.mpng1.api.domain.medicao.DadosListagemMedicao;
import br.edu.ifal.mpng1.api.domain.medicao.Medicao;
import br.edu.ifal.mpng1.api.domain.medicao.MedicaoRepository;
import br.edu.ifal.mpng1.api.domain.medicao.MedicaoService;
import br.edu.ifal.mpng1.api.domain.modelomedidor.ModeloMedidorService;
import br.edu.ifal.mpng1.api.domain.pontodecoleta.PontoDeColeta;
import br.edu.ifal.mpng1.api.domain.pontodecoleta.PontoDeColetaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("medicao")
public class MedicaoController {
	
	@Autowired
	private ModeloMedidorService modeloMedidorService;
	
	@Autowired
	private PontoDeColetaRepository pontoDeColetaRepository;
	
	@Autowired
	private MedicaoService medicaoService;
	
	@Autowired
	private MedicaoRepository repository;

	@GetMapping
	public ResponseEntity<Page<DadosListagemMedicao>> listar(@PageableDefault(size = 10, sort = {"id"}) Pageable paginacao) {
		
		var page = repository.findAll(paginacao).map(DadosListagemMedicao::new);
		
		return ResponseEntity.ok(page);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DadosDetalhamentoMedicao> detalhar(@PathVariable Long id) {
		var medicao = repository.getReferenceById(id);
		
		return ResponseEntity.ok(new DadosDetalhamentoMedicao(medicao));
	}

	@PostMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoMedicao> cadastrar(@RequestBody @Valid DadosCadastroMedicao dadosCadastro, UriComponentsBuilder uriBuilder) throws Exception {
		
		Medicao medicao = medicaoService.cadastrarMedicao(dadosCadastro);

	    DadosDetalhamentoMedicao detalhamentoMedicao = new DadosDetalhamentoMedicao(medicao);
	    
	    var uri = uriBuilder.path("/medicao/{id}").buildAndExpand(medicao.getId()).toUri();
	    
	    return ResponseEntity.created(uri).body(detalhamentoMedicao);
	}
	
}
