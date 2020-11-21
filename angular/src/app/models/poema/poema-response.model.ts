import {PersonaModel} from '../persona/persona.model';

export class PoemaResponseModel {

  persona: PersonaModel;
  diasRestantesCumpleanos: number;
  poema: string;
  mensajeFelicitacion: string;

  constructor(persona?: PersonaModel, diasRestantesCumpleanos?: number, poema?: string, mensajeFelicitacion?: string) {
    this.persona = persona || new PersonaModel();
    this.diasRestantesCumpleanos = diasRestantesCumpleanos || 0;
    this.poema = poema || '';
    this.mensajeFelicitacion = mensajeFelicitacion || '';
  }
}
