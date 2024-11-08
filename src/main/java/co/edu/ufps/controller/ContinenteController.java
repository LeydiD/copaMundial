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

import co.edu.ufps.entities.Seleccion;
import co.edu.ufps.entities.Continente;
import co.edu.ufps.services.ContinenteService;

@RestController
@RequestMapping("/continentes")
public class ContinenteController {

	@Autowired
	private ContinenteService continenteService;

	@GetMapping
	public List<Continente> list() {
		return continenteService.list();
	}

	@PostMapping
	public Continente create(@RequestBody Continente Continente) {
		return continenteService.create(Continente);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Continente> getById(@PathVariable Integer id) {
		Optional<Continente> continente = continenteService.getById(id);
		return continente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Continente> update(@PathVariable Integer id, @RequestBody Continente ContinenteDetails) {
		Optional<Continente> updatedContinente = continenteService.update(id, ContinenteDetails);
		return updatedContinente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		boolean deleted = continenteService.delete(id);
		return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/{id}/seleccions") 
	public ResponseEntity<List<Seleccion>> listSelecciones(@PathVariable Integer id) {
		List<Seleccion> seleccions = continenteService.getListSelecciones(id);
		if (seleccions.isEmpty()) {
			return ResponseEntity.notFound().build(); // Retorna un 404 si no hay seleccions para ese continente
		}
		return ResponseEntity.ok(seleccions); // Retorna un 200 con la lista de seleccions
	}

	@PostMapping("/{id}/add_seleccions/{seleccionId}")
	public Continente create(@PathVariable Integer id, @PathVariable Integer seleccionId) {
		Continente nuevaContinente = continenteService.addSeleccion(id, seleccionId);
		return nuevaContinente;
	}

}
