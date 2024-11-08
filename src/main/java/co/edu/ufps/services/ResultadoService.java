package co.edu.ufps.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.entities.Partido;
import co.edu.ufps.entities.Resultado;
import co.edu.ufps.repositories.ResultadoRepository;

@Service
public class ResultadoService {

	@Autowired
	ResultadoRepository resultadoRepository;

	public List<Resultado> list() {
		return resultadoRepository.findAll();
	}

	public Resultado create(Resultado resultado) {
		return resultadoRepository.save(resultado);
	}

	public Optional<Resultado> update(Integer id, Resultado resultadoDetails) {
		Optional<Resultado> optionalresultado = resultadoRepository.findById(id);
		if (!optionalresultado.isPresent()) {
			return Optional.empty();
		}

		Resultado resultado = optionalresultado.get();

		resultado.setGoles(resultadoDetails.getGoles());
		resultado.setAmarillas(resultadoDetails.getAmarillas());
		resultado.setRojas(resultadoDetails.getRojas());
		resultado.setSeleccion(resultadoDetails.getSeleccion());
		resultado.setPartido(resultadoDetails.getPartido());
		return Optional.of(resultadoRepository.save(resultado));
	}

	public Optional<Resultado> getById(Integer id) {
		return resultadoRepository.findById(id);
	}


	public boolean delete(Integer id) {
		if (!resultadoRepository.existsById(id)) {
			return false;
		}

		resultadoRepository.deleteById(id);
		return true;
	}
	

}
