import {Injectable} from '@angular/core';
import {PoemaRequestModel} from '../models/poema/poema-request.model';
import {Observable} from 'rxjs';
import {PoemaResponseModel} from '../models/poema/poema-response.model';
import {HttpClient, HttpParams} from '@angular/common/http';

@Injectable()
export class PoemaService {

  poemaRS: PoemaResponseModel = new PoemaResponseModel();

  constructor(private http: HttpClient) {
  }


  crearRequest(nombres: string, apellidos: string, fechaNacimiento: string): PoemaRequestModel {
    const poemaRQ = new PoemaRequestModel(nombres, apellidos, fechaNacimiento);
    return poemaRQ;
  }

  get(data: PoemaRequestModel): Observable<PoemaResponseModel> {

    const params = new HttpParams()
                  .append('nombres', data.nombres)
                  .append('apellidos', data.apellidos)
                  .append('fechaNacimiento', data.fechaNacimiento);

    return this.http.get<PoemaResponseModel>('http://localhost:8090/poema', {params});
  }

  guardarRespuesta(res: PoemaResponseModel): void {
    this.poemaRS = res;
  }

}
