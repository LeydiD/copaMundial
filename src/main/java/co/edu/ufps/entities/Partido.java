package co.edu.ufps.entities;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name="partido")
public class Partido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="fecha")
	private Date fecha;
	
	@OneToMany(mappedBy="partido", cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Resultado> resultados;
	
	@ManyToOne
	@JoinColumn(name="estadio_id")
	private Estadio estadio;
	
	
	public void addResultado(Resultado resultado) {
		this.resultados.add(resultado);
	}

	public void removeResultado(Resultado resultado) {
		this.resultados.remove(resultado);
		
	}
}