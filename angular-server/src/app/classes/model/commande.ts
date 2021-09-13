import { Facture } from "./facture";
import { LigneCommande } from "./ligne-commande";

export class Commande {
    constructor(private _id: bigint = BigInt(0), private _numero: string | null, private _status: string, private _date: Date | null, private _lignesCommande: LigneCommande[] | null, private _factures: Facture[] | null) {}

    get id(): bigint{
        return this._id ;
    }

    set id(_id: bigint) {
        this._id = _id ;
    }

    get numero(): string | null {
        return this._numero ;
    }

    set numero(_numero: string | null) {
        this._numero = _numero ;
    }

    get status(): string {
        return this._status ;
    }

    set status(_status: string) {
        this._status = _status ;
    }

    get date(): Date | null {
        return this._date ;
    }

    set date(_date: Date | null) {
        this._date = _date ;
    }

    get lignesCommande(): LigneCommande[] | null {
        return this._lignesCommande ;
    }

    set lignesCommande(_lignesCommande: LigneCommande[] | null) {
        this._lignesCommande = _lignesCommande ;
    }

    get factures(): Facture[] | null {
        return this._factures ;
    }

    set factures(_factures: Facture[] | null) {
        this._factures = _factures ;
    }


}
