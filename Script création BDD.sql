CREATE SCHEMA Utilisateurs;
CREATE SCHEMA Locations;
CREATE SCHEMA Agences;
CREATE SCHEMA Messages;

CREATE TABLE Utilisateurs.Utilisateur (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nom VARCHAR(255) NOT NULL,
    prenom VARCHAR(255) NOT NULL,
    date_naissance DATE NOT NULL,
    adresse_postale TEXT NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    telephone VARCHAR(20) NOT NULL,
    mot_de_passe TEXT NULL,
    type_utilisateur VARCHAR(20) NOT NULL CHECK (type_utilisateur IN ('client', 'employe')),
    cree_le TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modifie_le TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Utilisateurs.InformationsBancaires (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    id_utilisateur UUID UNIQUE NOT NULL REFERENCES Utilisateurs.Utilisateur(id) ON DELETE CASCADE,
    numero_cb VARCHAR(16) NOT NULL,
    date_expiration DATE NOT NULL,
    cree_le TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modifie_le TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Locations.Location (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    id_agence UUID NOT NULL REFERENCES Agences.Agence(id) ON DELETE CASCADE,
    id_utilisateur UUID NULL REFERENCES Utilisateurs.Utilisateur(id) ON DELETE SET NULL,
    ville_depart VARCHAR(255) NOT NULL,
    ville_retour VARCHAR(255) NOT NULL,
    date_heure_depart TIMESTAMP NOT NULL,
    date_heure_retour TIMESTAMP NOT NULL,
    categorie_acriss VARCHAR(10) NOT NULL,
    tarif DECIMAL(10,2) NOT NULL,
    cree_le TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modifie_le TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Locations.MatriceVehicule (
    categorie VARCHAR(10) PRIMARY KEY,
    type VARCHAR(50) NOT NULL,
    transmission VARCHAR(50) NOT NULL,
    consommable_air_conditionne VARCHAR(50) NOT NULL
);

CREATE TABLE Agences.Agence (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nom VARCHAR(255) NOT NULL,
    adresse_postale TEXT NOT NULL,
    telephone VARCHAR(20) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    cree_le TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modifie_le TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Messages.MessageSynchrone (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    id_expediteur UUID NOT NULL REFERENCES Utilisateurs.Utilisateur(id) ON DELETE CASCADE,
    id_destinataire UUID NOT NULL REFERENCES Utilisateurs.Utilisateur(id) ON DELETE CASCADE,
    contenu TEXT NOT NULL,
    cree_le TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modifie_le TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Messages.MessageAsynchrone (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    id_expediteur UUID NOT NULL REFERENCES Utilisateurs.Utilisateur(id) ON DELETE CASCADE,
    id_destinataire UUID NOT NULL REFERENCES Utilisateurs.Utilisateur(id) ON DELETE CASCADE,
    contenu TEXT NOT NULL,
    cree_le TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modifie_le TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);