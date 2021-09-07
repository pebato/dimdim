package br.com.fiap.dimdim.controller.api;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.dimdim.model.Transacao;
import br.com.fiap.dimdim.repository.TransacaoRepository;

@RestController
@RequestMapping("/api/transacao")
public class ApiTransacaoController {

	@Autowired
	private TransacaoRepository repository;

	@GetMapping
	@Cacheable("transacoes")
	public Page<Transacao> index(@RequestParam(required = false) String title, @PageableDefault Pageable pageable) {
		if (title == null) {
			return repository.findAll(pageable);
		}
		return repository.findAll(pageable);
	}

	@PostMapping
	@CacheEvict(value = "transacoes", allEntries = true)
	public ResponseEntity<Transacao> create(@RequestBody @Valid Transacao transacao, UriComponentsBuilder uriBuilder) {
		repository.save(transacao);
		URI uri = uriBuilder.path("/api/transacao/{id}").buildAndExpand(transacao.getId()).toUri();
		return ResponseEntity.created(uri).body(transacao);
	}

	@GetMapping("{id}")
	public ResponseEntity<Transacao> show(@PathVariable Long id) {
		return ResponseEntity.of(repository.findById(id));
	}

	@DeleteMapping("{id}")
	@CacheEvict(value = "transacoes", allEntries = true)
	public ResponseEntity<Transacao> destroy(@PathVariable Long id) {
		Optional<Transacao> transacao = repository.findById(id);

		if (!transacao.isPresent())
			return ResponseEntity.notFound().build();

		repository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("{id}")
	@CacheEvict(value = "transacoes", allEntries = true)
	public ResponseEntity<Transacao> update(@PathVariable Long id, @RequestBody @Valid Transacao newTransacao) {
		Optional<Transacao> optional = repository.findById(id);

		if (!optional.isPresent())
			return ResponseEntity.notFound().build();
		
		Transacao transacao = optional.get();
		transacao.setTitle(newTransacao.getTitle());
		transacao.setDescription(newTransacao.getDescription());
		transacao.setPoints(newTransacao.getPoints());
		
		repository.save(transacao);
		
		return ResponseEntity.ok(transacao);
	}
	
	
}
