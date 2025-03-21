export interface Message {
    id: string;
    idExpediteur: string;
    idDestinataire: string;
    contenu: string;
    creeLe: Date;
    modifieLe: Date;
  }