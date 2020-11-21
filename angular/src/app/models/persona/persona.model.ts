export class PersonaModel {

  primerNombre: string;
  segundoNombre: string;
  apellidoPaterno: string;
  apellidoMaterno: string;
  edad: number;
  fechaNacimiento: string;

  constructor(primerNombre?: string, segundoNombre?: string, apellidoPaterno?: string,
              apellidoMaterno?: string, edad?: number, fechaNacimiento?: string) {

    this.primerNombre = primerNombre || '';
    this.segundoNombre = segundoNombre || '';
    this.apellidoPaterno = apellidoPaterno || '';
    this.apellidoMaterno = apellidoMaterno || '';
    this.edad = edad || 0;
    this.fechaNacimiento = fechaNacimiento || '';
  }

}
