export abstract class Article {
    constructor(private _id: bigint = BigInt(0), private _titre: string, private _resume: string, private _prixHT: bigint = BigInt(0), private _prixTTC: bigint = BigInt(0)) { }

    get id(): bigint{
        return this._id;
    }

    set id(_id: bigint) {
        this._id = _id;
    }

    get titre(): string {
        return this._titre;
    }

    set titre(_titre: string) {
        this._titre = _titre;
    }

    get resume(): string {
        return this._resume;
    }

    set resume(_resume: string) {
        this._resume = _resume;
    }

    get prixHT(): bigint {
        return this._prixHT;
    }

    set prixHT(_prixHT: bigint) {
        this._prixHT = _prixHT;
    }

    get prixTTC(): bigint {
        return this._prixTTC;
    }

    set prixTTC(_prixTTC: bigint) {
        this._prixTTC = _prixTTC;
    }

}
