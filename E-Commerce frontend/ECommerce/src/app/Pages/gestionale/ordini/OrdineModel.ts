export interface Ordine {
    id: number;
    product: {
      id: number;
      nome: string;
      descrizione: string;
      foto: string;
      codice_articolo: string;
      categoria: string;
      prezzo: number;
      colore: string;
      taglia: string;
      genere: string;
      accessorio: boolean;
      peso: number;
      quantita: number;
      dataInserimento: string;
    };
    quantita: number;
    user: {
      id: number;
      email: string;
      fullName: string;
      role: string;
    };
    stato: string;
    prezzo: number;
  }