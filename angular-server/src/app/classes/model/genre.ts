export class Genre {
    constructor(private _id: bigint = BigInt(0), private _nom: string) {}

    get id(): bigint {
        return this._id;
    }

    set id(_id: bigint) {
        this._id = _id;
    }

    get nom(): string {
        return this._nom;
    }

    set nom(_nom: string ) {
        this._nom = _nom;
    }
}
