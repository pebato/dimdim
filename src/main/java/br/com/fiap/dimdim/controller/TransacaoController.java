package br.com.fiap.dimdim.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.fiap.dimdim.model.Transacao;
import br.com.fiap.dimdim.repository.TransacaoRepository;

@Controller
public class TransacaoController {
	
	@Autowired
	private TransacaoRepository repository;

	@GetMapping("/transacao")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("transacoes");
		List<Transacao> transacoes = repository.findAll();
		modelAndView.addObject("transacoes", transacoes);
		return modelAndView;
	}
	
	@RequestMapping("/transacao/new")
	public String create(Transacao transacao) {		
		return "transacao-form";
	}
	
	@PostMapping("/transacao")
	public String save(@Valid Transacao transacao, BindingResult result) {
		if(result.hasErrors()) {
			return "transacao-form";
		}
		repository.save(transacao);
		return "transacoes";
	}
}
