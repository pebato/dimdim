package br.com.fiap.dimdim.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.dimdim.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
}
