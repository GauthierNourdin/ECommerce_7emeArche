export abstract class Personne {
    constructor(private _id: bigint = BigInt(0), private _civilite: string | null, private _nom: string, private _prenom: string | null) {}

    get id(): bigint {
        return this._id;
    }

    set id(_id: bigint) {
        this._id = _id;
    }

    get civilite(): string | null {
        return this._civilite;
    }
    
    set civilite(_civilite: string | null) {
        this._civilite = _civilite;
    }

    get nom(): string {
        return this._nom;
    }

    set nom(_nom: string) {
        this._nom = _nom;
    }

    get prenom(): string | null {
        return this._prenom;
    }

    set prenom(_prenom: string | null) {
        this._prenom = _prenom;
    }

}
