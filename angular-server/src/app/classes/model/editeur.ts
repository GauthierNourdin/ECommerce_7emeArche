import { Adresse } from "./adresse";

export class Editeur {
    constructor(private _id: bigint = BigInt(0), private _nom: string, private _adresse: Adresse) {}
    
    get id(): bigint {
        return this._id;
    }

    set id(_id: bigint) {
        this._id = _id;
    }

    get nom(): string {
        return this._nom;
    }

    set nom(_nom: string) {
        this._nom = _nom;
    }

    get adresse(): Adresse {
        return this._adresse;
    }

    set adresse(_adresse: Adresse) {
        this._adresse = _adresse;
    }

}
