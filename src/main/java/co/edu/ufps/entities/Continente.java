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
@Table(name="continente")
public class Continente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="nombre", length =50)
	private String nombre;
	
	@OneToMany(mappedBy="continente", cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Seleccion> selecciones;
	
	public void addSeleccion(Seleccion seleccion) {
		this.selecciones.add(seleccion);
	}

	public void removeEmpleado(Seleccion seleccion) {
		this.selecciones.remove(seleccion);
		
	}
	
}
