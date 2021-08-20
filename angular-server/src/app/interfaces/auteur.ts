import { Article } from "./article";

export interface Auteur {

    id?: bigint;
	civilite?: string;
    nom?: string;
    prenom?: string;
    livres?: Article[];

}
