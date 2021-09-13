import { Auteur } from "./auteur";
import { Editeur } from "./editeur";
import { Genre } from "./genre";
import { Livre } from "./livre";

export class LivreNumerique extends Livre{
    constructor(_id: bigint = BigInt(0), _titre: string, _resume: string, _prixHT: bigint = BigInt(0), _prixTTC: bigint = BigInt(0), _isbn13: string, _titreLivre: string, _format: string, _lienImage: string, _nombrePages: bigint | null, _dateDepotLegal: Date, _genres: Genre[], _auteurs: Auteur[], _editeur: Editeur, private _espace: number | null, private _unite: string | null) {
        super(_id, _titre, _resume, _prixHT, _prixTTC, _isbn13, _titreLivre, _format, _lienImage, _nombrePages, _dateDepotLegal, _genres, _auteurs, _editeur);
    }

    get espace(): number | null {
        return this._espace;
    }
    
    set espace(_espace: number | null) {
        this._espace = _espace;
    }

    get unite(): string | null {
        return this._unite;
    }

    set unite(_unite: string | null) {
        this._unite = _unite;
    }

}
