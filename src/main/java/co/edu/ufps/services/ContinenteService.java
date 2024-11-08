package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Continente;
import co.edu.ufps.entities.Seleccion;
import co.edu.ufps.repositories.ContinenteRepository;
import co.edu.ufps.repositories.SeleccionRepository;

@Service
public class ContinenteService {

	@Autowired
	ContinenteRepository continenteRepository;
	
	@Autowired
	SeleccionRepository seleccionRepository;

	public List<Continente> list() {
		return continenteRepository.findAll();
	}

	public Continente create(Continente continente) {
		return continenteRepository.save(continente);
	}

	public Optional<Continente> update(Integer id, Continente continenteDetails) {
		Optional<Continente> optionalcontinente = continenteRepository.findById(id);
		if (!optionalcontinente.isPresent()) {
			return Optional.empty();
		}

		Continente continente = optionalcontinente.get();
		continente.setNombre(continenteDetails.getNombre());
		return Optional.of(continenteRepository.save(continente));
	}

	public Optional<Continente> getById(Integer id) {
		return continenteRepository.findById(id);
	}

	public boolean delete(Integer id) {
		if (!continenteRepository.existsById(id)) {
			return false;
		}

		continenteRepository.deleteById(id);
		return true;
	}
	
	
	public Continente addSeleccion(Integer id, Integer seleccionId) {
		Optional<Continente> continenteOpt = continenteRepository.findById(id);
		if (continenteOpt.isPresent()) {
			Continente continente = continenteOpt.get();
			Optional<Seleccion> seleccionOpt = seleccionRepository.findById(seleccionId);
			if (seleccionOpt.isPresent()) {
				continente.addSeleccion(seleccionOpt.get());
			}
			return continenteRepository.save(continente);
		}
		return null;
	}
	
	public List<Seleccion> getListSelecciones(Integer id) {
		Optional<Continente> continenteOpt = continenteRepository.findById(id);
		return continenteOpt.map(Continente::getSelecciones).orElse(Collections.emptyList());
	}
	

}
