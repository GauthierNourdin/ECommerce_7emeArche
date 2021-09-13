import { Article } from "./article";
import { Auteur } from "./auteur";
import { Editeur } from "./editeur";
import { Genre } from "./genre";

export abstract class Livre extends Article {
    constructor(_id: bigint = BigInt(0), _titre: string, _resume: string, _prixHT: bigint = BigInt(0), _prixTTC: bigint = BigInt(0), private _isbn13: string, private _titreLivre: string, private _format: string, private _lienImage: string, private _nombrePages: bigint | null, private _dateDepotLegal: Date, private _genres: Genre[], private _auteurs: Auteur[], private _editeur: Editeur) {
        super(_id, _titre, _resume, _prixHT, _prixTTC);
    }

    get isbn13(): string {
        return this._isbn13;
    }

    set isbn13(_isbn13: string) {
        this._isbn13 = _isbn13;
    }

    get titreLivre(): string {
        return this._titreLivre;
    }

    set titreLivre(_titreLivre: string) {
        this._titreLivre = _titreLivre;
    }

    get format(): string {
        return this._format;
    }

    set format(_format: string) {
        this._format = _format;
    }

    get lienImage(): string {
        return this._lienImage;
    }

    set lienImage(_lienImage: string) {
        this._lienImage = _lienImage;
    }

    get nombrePages(): bigint | null {
        return this._nombrePages;
    }

    set nombrePages(_nombrePages: bigint | null) {
        this._nombrePages = _nombrePages;
    }

    get dateDepotLegal(): Date {
        return this._dateDepotLegal;
    }

    set dateDepotLegal(_dateDepotLegal: Date) {
        this._dateDepotLegal = _dateDepotLegal;
    }

    get genres(): Genre[] {
        return this._genres;
    }

    set genres(_genres: Genre[]) {
        this._genres = _genres;
    }

    get auteurs(): Auteur[] {
        return this._auteurs;
    }

    set auteurs(_auteurs: Auteur[]) {
        this._auteurs = _auteurs;
    }

    get editeur(): Editeur {
        return this._editeur;
    }

    set editeur(_editeur: Editeur) {
        this._editeur;
    }
 
}
