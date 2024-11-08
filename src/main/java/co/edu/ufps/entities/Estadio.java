package co.edu.ufps.entities;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name="estadio")
public class Estadio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="nombre", length =100)
	private String nombre;
	
	@Column(name="capacidad")
	private Integer capacidad;
	
	@OneToMany(mappedBy="estadio", cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Partido> partidos;
	
	public void addPartido(Partido partido) {
		this.partidos.add(partido);
	}

	public void removePartido(Partido partido) {
		this.partidos.remove(partido);
		
	}
}