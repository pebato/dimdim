package br.com.fiap.dimdim.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.fiap.dimdim.model.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long>{

	List<Transacao> findByTitleLike(String title);


}
