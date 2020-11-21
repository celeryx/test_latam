import {Injectable} from '@angular/core';
import {PoemaRequestModel} from '../models/poema/poema-request.model';
import {Observable, Subject, Subscription} from 'rxjs';
import {PoemaResponseModel} from '../models/poema/poema-response.model';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';

@Injectable()
export class PoemaService {

  poemaRSSubject: Subject<PoemaResponseModel> = new Subject<PoemaResponseModel>();
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
    const headers = new HttpHeaders().append('header', 'value');
    return this.http.get<PoemaResponseModel>('http://localhost:8090/poema', {headers, params});
  }

  guardarRespuesta(res: PoemaResponseModel): void {
    this.poemaRSSubject.next(res);
    this.poemaRS = res;
  }

  obtenerRespuesta(): Observable<PoemaResponseModel> {
    return  this.poemaRSSubject.asObservable();
  }
}
