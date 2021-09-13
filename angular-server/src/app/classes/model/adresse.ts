export class Adresse {
    constructor(private _id: bigint = BigInt(0), private _numeroRue: string, private _nomRue: string, private _codePostal: string, private _ville: string, private _pays: string | null , private _complement: string | null) { }

    get id(): bigint {
        return this._id;
    }

    set id(_id: bigint) {
        this._id = _id;
    }

    get numeroRue(): string {
        return this._numeroRue;
    }

    set numeroRue(_numeroRue : string) {
        this._numeroRue = _numeroRue;
    }

    get nomRue(): string  {
        return this._nomRue;
    }

    set nomRue(_nomRue : string) {
        this._nomRue = _nomRue;
    } 

    get codePostal(): string {
        return this._codePostal;
    }

    set codePostal(_codePostal: string) {
        this._codePostal = _codePostal;
    }

    get ville(): string {
        return this._ville;
    }

    set ville(_ville: string) {
        this._ville = _ville;
    }

    get pays(): string | null {
        return this._pays;
    }

    set pays(_pays: string |null) {
        this._pays = _pays;
    }

    get complement(): string | null {
        return this._complement;
    }

    set complement(_complement: string |null) {
        this._complement = _complement;
    }

}
