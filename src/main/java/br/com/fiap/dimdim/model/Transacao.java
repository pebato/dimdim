package br.com.fiap.dimdim.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name="TB_TRANSACAO")
public class Transacao {
	
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRANS_SEQ")
	@SequenceGenerator(name="TransSeq",sequenceName="TRANS_SEQ", allocationSize=5) 
	private Long id;
	
	@NotBlank(message = "Título obrigatório.")
	private String title;
	
	@Size(min=10, message = "Descrição deve ter pelo menos 10 caracteres.")
	private String description;
	
	@Min(value=10, message = "A pontuação mínima é 10")
	@Max(value=500, message = "A pontuação máxima é 500")
	private int points;
}
