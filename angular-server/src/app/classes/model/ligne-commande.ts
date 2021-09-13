import { Article } from "./article";

export class LigneCommande {
    constructor(private _id: bigint = BigInt(0), private _quantiteCommandee: bigint, private _prixHT: bigint, private _prixTTC: bigint, private _article: Article) {}

    get id(): bigint {
        return this._id;
    }

    set id(_id: bigint) {
        this._id = _id;
    }

    get quantiteCommandee(): bigint {
        return this._quantiteCommandee;
    }

    set quantiteCommandee(_quantiteCommandee :bigint) {
        this._quantiteCommandee = _quantiteCommandee;
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

    get article(): Article {
        return this._article;
    }

    set article(_article: Article) {
        this._article = _article;
    }

}
