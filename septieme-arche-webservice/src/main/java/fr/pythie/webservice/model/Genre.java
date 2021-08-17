package fr.pythie.webservice.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@ToString(of = { "id", "nom" }) 
@EqualsAndHashCode
@Entity
public class Genre {
	/** Classe pour sauvegarder les genres des livres */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	@NonNull
	String nom;
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "genres")
	List<Livre> livres;

	// Constructeur complet mis à part l'ID
	public Genre(@NonNull String nom, List<Livre> livres) {
		super();
		this.nom = nom;
		this.livres = livres;
	}
	
}
