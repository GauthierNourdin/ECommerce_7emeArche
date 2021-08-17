package fr.pythie.webservice.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString(of = { "id", "date" }) 
@EqualsAndHashCode
@Entity
public class Consultation {
	/** Classe pour les consultations d'articles */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	@NonNull
	LocalDateTime date = LocalDateTime.now();
	@JsonIgnore
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	Client client;
	@NonNull
	@JsonIgnoreProperties( "consultations" )
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	Article article;

	// Constructeur complet mis à part l'ID
	public Consultation(@NonNull LocalDateTime date, Client client, @NonNull Article article) {
		super();
		this.date = date;
		this.client = client;
		this.article = article;
	}
	
}
