package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Seleccion;
import co.edu.ufps.entities.Resultado;
import co.edu.ufps.repositories.SeleccionRepository;
import co.edu.ufps.repositories.ResultadoRepository;

@Service
public class SeleccionService {

	@Autowired
	SeleccionRepository seleccionRepository;
	
	@Autowired
	ResultadoRepository resultadoRepository;

	public List<Seleccion> list() {
		return seleccionRepository.findAll();
	}

	public Seleccion create(Seleccion seleccion) {
		return seleccionRepository.save(seleccion);
	}

	public Optional<Seleccion> update(Integer id, Seleccion seleccionDetails) {
		Optional<Seleccion> optionalseleccion = seleccionRepository.findById(id);
		if (!optionalseleccion.isPresent()) {
			return Optional.empty();
		}

		Seleccion seleccion = optionalseleccion.get();
		seleccion.setNombre(seleccionDetails.getNombre());
		seleccion.setGrupo(seleccion.getGrupo());
		seleccion.setContinente(seleccion.getContinente());
		return Optional.of(seleccionRepository.save(seleccion));
	}

	public Optional<Seleccion> getById(Integer id) {
		return seleccionRepository.findById(id);
	}

	public boolean delete(Integer id) {
		if (!seleccionRepository.existsById(id)) {
			return false;
		}

		seleccionRepository.deleteById(id);
		return true;
	}
	
	
	public Seleccion addResultado(Integer id, Integer resultadoId) {

		Optional<Seleccion> seleccionOpt = seleccionRepository.findById(id);

		if (seleccionOpt.isPresent()) {
			Seleccion seleccion = seleccionOpt.get();
			Optional<Resultado> resultadoOpt = resultadoRepository.findById(resultadoId);
			if (resultadoOpt.isPresent()) {
				seleccion.addResultado(resultadoOpt.get());
			}
			return seleccionRepository.save(seleccion);
		}
		return null;
	}
	
	public List<Resultado> getListResultados(Integer id) {
		Optional<Seleccion> seleccionOpt = seleccionRepository.findById(id);
		return seleccionOpt.map(Seleccion::getResultados).orElse(Collections.emptyList());
	}
	
	public List<Seleccion>getSeleccionesPorGrupo(String grupo){
		return seleccionRepository.findByGrupo(grupo);
	}
	

}
