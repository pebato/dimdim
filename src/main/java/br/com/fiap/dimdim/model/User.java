package br.com.fiap.dimdim.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name="TB_USER")
public class User {
	
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
	@SequenceGenerator(name="UserSeq",sequenceName="USER_SEQ", allocationSize=5) 
	private Long id;
	
	@NotBlank(message = "Email inválido")
	private String email;
	
	@NotBlank(message = "Nome obrigatório")
	private String name;
	
	@Size(min=8, message = "Senha deve ter pelo menos 8 caracteres.")
	private String password;
	
}
