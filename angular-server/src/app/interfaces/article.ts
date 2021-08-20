import { Consultation } from "./consultation";
import { Genre } from "./genre";
import { LigneCommande } from "./ligne-commande";
import { Auteur } from "./auteur";
import { Editeur } from "./editeur";

export interface Article {
    id?: bigint;
    titre?: string;
    resume?: string;
    prixHT?: bigint;
    prixTTC?: bigint;
    consultations?: Consultation[];
    lignesCommande?: LigneCommande[];

	isbn13?: string;
	titreLivre?: string;
	format?: string;
	lienImage?: string;
	nombrePages?: bigint;
	dateDepotLegal?: Date
	genres?: Genre[];
	auteurs?: Auteur[];
	editeur?: Editeur;

	quantiteStock?: bigint;
	dateFinTirage?: Date
	dateReimpression?: Date
	poids?: number;
	unitePoids?: string;
	longueur?: number;
	largeur?: number;
	epaisseur?: number;
	uniteLongueur?: string;

    espace?: number;
	unite?: string
}
