package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Partido;
import co.edu.ufps.entities.Resultado;
import co.edu.ufps.repositories.PartidoRepository;
import co.edu.ufps.repositories.ResultadoRepository;

@Service
public class PartidoService {

	@Autowired
	PartidoRepository partidoRepository;
	
	@Autowired
	ResultadoRepository resultadoRepository;

	public List<Partido> list() {
		return partidoRepository.findAll();
	}

	public Partido create(Partido partido) {
		return partidoRepository.save(partido);
	}

	public Optional<Partido> update(Integer id, Partido partidoDetails) {
		Optional<Partido> optionalpartido = partidoRepository.findById(id);
		if (!optionalpartido.isPresent()) {
			return Optional.empty();
		}

		Partido partido = optionalpartido.get();
		partido.setFecha(partidoDetails.getFecha());
		partido.setEstadio(partido.getEstadio());
		return Optional.of(partidoRepository.save(partido));
	}

	public Optional<Partido> getById(Integer id) {
		return partidoRepository.findById(id);
	}

	public boolean delete(Integer id) {
		if (!partidoRepository.existsById(id)) {
			return false;
		}

		partidoRepository.deleteById(id);
		return true;
	}
	
	
	public Partido addResultado(Integer id, Integer resultadoId) {

		Optional<Partido> partidoOpt = partidoRepository.findById(id);

		if (partidoOpt.isPresent()) {
			Partido partido = partidoOpt.get();
			Optional<Resultado> resultadoOpt = resultadoRepository.findById(resultadoId);
			if (resultadoOpt.isPresent()) {
				partido.addResultado(resultadoOpt.get());
			}
			return partidoRepository.save(partido);
		}
		return null;
	}
	
	public List<Resultado> getListResultados(Integer id) {
		Optional<Partido> partidoOpt = partidoRepository.findById(id);
		return partidoOpt.map(Partido::getResultados).orElse(Collections.emptyList());
	}
	

}
