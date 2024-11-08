package co.edu.ufps.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Estadio;
import co.edu.ufps.entities.Partido;
import co.edu.ufps.repositories.EstadioRepository;
import co.edu.ufps.repositories.PartidoRepository;

@Service
public class EstadioService {

	@Autowired
	EstadioRepository estadioRepository;
	
	@Autowired
	PartidoRepository partidoRepository;

	public List<Estadio> list() {
		return estadioRepository.findAll();
	}

	public Estadio create(Estadio estadio) {
		return estadioRepository.save(estadio);
	}

	public Optional<Estadio> update(Integer id, Estadio estadioDetails) {
		Optional<Estadio> optionalestadio = estadioRepository.findById(id);
		if (!optionalestadio.isPresent()) {
			return Optional.empty();
		}

		Estadio estadio = optionalestadio.get();

		estadio.setNombre(estadioDetails.getNombre());
		estadio.setCapacidad(estadioDetails.getCapacidad());

		return Optional.of(estadioRepository.save(estadio));
	}

	public Optional<Estadio> getById(Integer id) {
		return estadioRepository.findById(id);
	}

	public boolean delete(Integer id) {
		if (!estadioRepository.existsById(id)) {
			return false;
		}

		estadioRepository.deleteById(id);
		return true;
	}
	
	public Estadio addPartido(Integer id, Integer partidoId) {
		Optional<Estadio> estadioOpt = estadioRepository.findById(id);
		if (estadioOpt.isPresent()) {
			Estadio estadio = estadioOpt.get();
			Optional<Partido> partidoOpt = partidoRepository.findById(partidoId);
			if (partidoOpt.isPresent()) {
				estadio.addPartido(partidoOpt.get());
			}
			return estadioRepository.save(estadio);
		}
		return null;
	}
	
	public List<Partido> getListPartidos(Integer id) {
		Optional<Estadio> estadioOpt = estadioRepository.findById(id);
		return estadioOpt.map(Estadio::getPartidos).orElse(Collections.emptyList());
	}
	
	

}
