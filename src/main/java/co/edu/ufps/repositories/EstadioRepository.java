package co.edu.ufps.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.ufps.entities.Estadio;


@Repository
public interface EstadioRepository extends JpaRepository<Estadio,Integer>{

}