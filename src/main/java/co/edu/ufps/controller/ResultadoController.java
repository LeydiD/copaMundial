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

import co.edu.ufps.entities.Resultado;
import co.edu.ufps.services.ResultadoService;

@RestController
@RequestMapping("/resultados")
public class ResultadoController {

	@Autowired
	private ResultadoService resultadoService;

	@GetMapping
	public List<Resultado> list() {
		return resultadoService.list();
	}

	@PostMapping
	public Resultado create(@RequestBody Resultado Resultado) {
		return resultadoService.create(Resultado);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Resultado> getById(@PathVariable Integer id) {
		Optional<Resultado> resultado = resultadoService.getById(id);
		return resultado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Resultado> update(@PathVariable Integer id, @RequestBody Resultado ResultadoDetails) {
		Optional<Resultado> updatedResultado = resultadoService.update(id, ResultadoDetails);
		return updatedResultado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		boolean deleted = resultadoService.delete(id);
		return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}
	
}
