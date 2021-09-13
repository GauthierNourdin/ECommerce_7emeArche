import { Personne } from "./personne";

export class Auteur extends Personne{
    constructor(_id: bigint, _civilite: string | null, _nom: string, _prenom: string | null) {
        super(_id, _civilite, _nom, _prenom);
    }
}
