package co.edu.ufps.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name="resultado")
public class Resultado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="goles")
	private Integer goles;
	
	@Column(name="amarillas")
	private Integer amarillas;
	
	@Column(name="rojas")
	private Integer rojas;
	
	@ManyToOne
	@JoinColumn(name="seleccion_id")
	private Seleccion seleccion;
	
	@ManyToOne
	@JoinColumn(name="partido_id")
	private Partido partido;
}