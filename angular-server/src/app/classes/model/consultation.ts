import { Article } from "./article";

export class Consultation {
    constructor(private _id: bigint = BigInt(0), private _dateEnregistrement: Date | null, private _article: Article) {}

    get id(): bigint {
        return this._id;
    }

    set id(_id: bigint) {
        this._id = _id;
    }

    get dateEnregistrement(): Date | null {
        return this._dateEnregistrement;
    }

    set dateEnregistrement(_dateEnregistrement: Date | null) {
        this._dateEnregistrement = _dateEnregistrement;
    }

    get article(): Article {
        return this._article;
    }

    set article(_article: Article) {
        this._article = _article;
    } 
}
