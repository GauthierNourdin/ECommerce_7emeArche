import { Article } from "./article";

export class LignePanier {
    constructor(private _id: bigint = BigInt(0), private _quantiteSouhaitee: bigint, private _article: Article) {}

    get id(): bigint {
        return this._id;
    }

    set id(_id: bigint) {
        this._id = _id;
    }

    get quantiteSouhaitee(): bigint {
        return this._quantiteSouhaitee;
    }

    set quantiteSouhaitee(_quantiteSouhaitee :bigint) {
        this._quantiteSouhaitee = _quantiteSouhaitee;
    }

    get article(): Article {
        return this._article;
    }

    set article(_article: Article) {
        this._article = _article;
    }

}
