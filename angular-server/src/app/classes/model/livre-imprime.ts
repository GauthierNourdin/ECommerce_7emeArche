import { Auteur } from "./auteur";
import { Editeur } from "./editeur";
import { Genre } from "./genre";
import { Livre } from "./livre";

export class LivreImprime extends Livre{
    constructor(_id: bigint = BigInt(0), _titre: string, _resume: string, _prixHT: bigint = BigInt(0), _prixTTC: bigint = BigInt(0), _isbn13: string, _titreLivre: string, _format: string, _lienImage: string, _nombrePages: bigint | null, _dateDepotLegal: Date, _genres: Genre[], _auteurs: Auteur[], _editeur: Editeur, private _quantiteStock: bigint = BigInt(0), private _dateFinTirage: Date, private _dateReimpression: Date | null, private _poids: number | null, private _unitePoids: string | null, private _longueur: number | null, private _largeur: number | null, private _epaisseur: number | null, private _uniteLongueur: string | null) {
        super(_id, _titre, _resume, _prixHT, _prixTTC, _isbn13, _titreLivre, _format, _lienImage, _nombrePages, _dateDepotLegal, _genres, _auteurs, _editeur);
    }

    get quantiteStock(): bigint {
        return this._quantiteStock;
    }

    set quantiteStock(_quantiteStock: bigint) {
        this._quantiteStock = _quantiteStock;
    }

    get dateFinTirage(): Date {
        return this._dateFinTirage;
    }

    set dateFinTirage(_dateFinTirage: Date) {
        this._dateFinTirage = _dateFinTirage;
    }

    get dateReimpression(): Date | null {
        return this._dateReimpression;
    }

    set dateReimpression(_dateReimpression : Date | null) {
        this._dateReimpression = _dateReimpression;
    }

    get poids(): number | null {
        return this._poids;
    }

    set poids(_poids: number | null) {
        this._poids = _poids;
    }

    get unitePoids(): string | null {
        return this._unitePoids;
    }

    set unitePoids(_unitePoids: string | null) {
        this._unitePoids = _unitePoids;
    }

    get longueur(): number | null {
        return this._longueur;
    }

    set longueur(_longueur: number | null) {
        this._longueur = _longueur;
    }

    get largeur(): number | null {
        return this._largeur;
    }

    set largeur(_largeur: number | null) {
        this._largeur = _largeur;
    }

    get epaisseur(): number | null {
        return this._epaisseur;
    }

    set epaisseur(_epaisseur: number | null) {
        this._epaisseur = _epaisseur;
    }

    get uniteLongueur(): string | null {
        return this._uniteLongueur;
    }

    set uniteLongueur(_uniteLongueur: string | null) {
        this._uniteLongueur = _uniteLongueur;
    }
  
}
