export class Facture {
    constructor(private _id: bigint = BigInt(0), private _numero: string | null, private _status: string) {}

    get id(): bigint {
        return this._id;
    }

    set id(_id: bigint) {
        this._id = _id;
    }

    get numero(): string | null {
        return this._numero;
    }

    set numero(_numero: string | null) {
        this._numero = _numero;
    }

    get status(): string {
        return this._status;
    }

    set status(_status: string) {
        this._status = _status;
    }

}
