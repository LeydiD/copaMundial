package co.edu.ufps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.ufps.entities.Resultado;


@Repository
public interface ResultadoRepository extends JpaRepository<Resultado,Integer>{

}