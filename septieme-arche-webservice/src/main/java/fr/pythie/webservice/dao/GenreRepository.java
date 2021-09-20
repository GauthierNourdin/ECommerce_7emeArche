package fr.pythie.webservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.pythie.webservice.model.Genre;

/**
 * 
 * @author Gauthier Nourdin.
 *
 */
public interface GenreRepository extends JpaRepository<Genre, Long>{

}
