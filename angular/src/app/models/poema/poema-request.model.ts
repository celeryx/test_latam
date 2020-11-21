export class PoemaRequestModel {

  nombres: string;
  apellidos: string;
  fechaNacimiento: string;

  constructor(nombres?: string, apellidos?: string, fechaNacimiento?: string) {
    this.nombres = nombres || '';
    this.apellidos = apellidos || '';
    this.fechaNacimiento = fechaNacimiento || '';
  }

}
