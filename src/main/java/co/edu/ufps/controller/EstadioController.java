package co.edu.ufps.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ufps.entities.Partido;
import co.edu.ufps.entities.Estadio;
import co.edu.ufps.services.EstadioService;

@RestController
@RequestMapping("/estadios")
public class EstadioController {

	@Autowired
	private EstadioService estadioService;

	@GetMapping
	public List<Estadio> list() {
		return estadioService.list();
	}

	@PostMapping
	public Estadio create(@RequestBody Estadio Estadio) {
		return estadioService.create(Estadio);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Estadio> getById(@PathVariable Integer id) {
		Optional<Estadio> estadio = estadioService.getById(id);
		return estadio.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Estadio> update(@PathVariable Integer id, @RequestBody Estadio EstadioDetails) {
		Optional<Estadio> updatedEstadio = estadioService.update(id, EstadioDetails);
		return updatedEstadio.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		boolean deleted = estadioService.delete(id);
		return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/{id}/partidos") 
	public ResponseEntity<List<Partido>> listPartidoes(@PathVariable Integer id) {
		List<Partido> partidos = estadioService.getListPartidos(id);
		if (partidos.isEmpty()) {
			return ResponseEntity.notFound().build(); // Retorna un 404 si no hay partidos para ese estadio
		}
		return ResponseEntity.ok(partidos); // Retorna un 200 con la lista de partidos
	}

	@PostMapping("/{id}/add_partidos/{partidoId}")
	public Estadio create(@PathVariable Integer id, @PathVariable Integer partidoId) {
		Estadio nuevaEstadio = estadioService.addPartido(id, partidoId);
		return nuevaEstadio;
	}

}
