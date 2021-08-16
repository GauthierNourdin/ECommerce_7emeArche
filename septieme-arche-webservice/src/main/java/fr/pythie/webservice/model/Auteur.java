package fr.pythie.webservice.model;

import java.util.ArrayList;

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
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString(of = { "id" }) 
@EqualsAndHashCode(callSuper = true)
@Entity
public class Auteur extends Personne {
	/** Classe pour les auteurs */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "livres")
	ArrayList<Livre> livres = new ArrayList<Livre>();

}
