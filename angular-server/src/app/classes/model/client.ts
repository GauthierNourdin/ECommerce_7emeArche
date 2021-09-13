import { ReactiveFormsModule } from "@angular/forms";
import { Adresse } from "./adresse";
import { Commande } from "./commande";
import { Consultation } from "./consultation";
import { Personne } from "./personne";

export class Client extends Personne{
    constructor(_id: bigint, _civilite: string | null, _nom: string, _prenom: string | null, private _email: string, private _motDePasse: string, private _numeroCarte: string | null, private _dateDeValidite: string | null, private _cvc: string | null, private _adresseFacturation : Adresse, private _adresseLivraison: Adresse, private _consultations: Consultation[] | null, private _commandes: Commande[] | null) {
        super(_id, _civilite, _nom, _prenom);
    }

    get email(): string {
        return this._email;
    }

    set email(_email : string) {
        this._email = _email;
    }

    get motDePasse(): string {
        return this._motDePasse;
    }

    set motDePasse(_motDePasse : string) {
        this._motDePasse = _motDePasse;
    }

    get numeroCarte(): string | null {
        return this._numeroCarte;
    }

    set numeroCarte(_numeroCarte : string | null ) {
        this._numeroCarte = _numeroCarte;
    }

    get dateDeValidite(): string | null {
        return this._dateDeValidite;
    }

    set dateDeValidite(_dateDeValidite: string | null) {
        this._dateDeValidite = _dateDeValidite;
    }

    get cvc(): string | null {
        return this._cvc;
    }

    set cvc(_cvc: string | null) {
        this._cvc = _cvc
    }

    get adresseFacturation() : Adresse {
        return this._adresseFacturation;
    }

    set adresseFacturation(_adresseFacturation: Adresse) {
        this._adresseFacturation = _adresseFacturation;
    }

    get adresseLivraison(): Adresse {
        return this._adresseLivraison;
    }
    
    set adresseLivraison(_adresseLivraison: Adresse) {
        this._adresseLivraison = this._adresseLivraison;
    }

    get consultations(): Consultation[] | null {
        return this._consultations;
    }

    set consultations(_consultations: Consultation[] | null) {
        this._consultations = _consultations;
    }

    get commandes(): Commande[] | null {
        return this._commandes;
    }

    set commandes(_commandes: Commande[] | null) {
        this._commandes = _commandes;
    }

}
