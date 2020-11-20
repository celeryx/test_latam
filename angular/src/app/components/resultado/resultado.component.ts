import { Component, OnInit } from '@angular/core';
import {PoemaResponseModel} from '../../models/poema/poema-response.model';
import {PoemaService} from '../../services/poema.service';

@Component({
  selector: 'app-resultado',
  templateUrl: './resultado.component.html',
  styleUrls: ['./resultado.component.css']
})
export class ResultadoComponent implements OnInit {

  poemaRS: PoemaResponseModel;

  constructor(private poemaService: PoemaService) {
    console.log('AKA');
  }

  ngOnInit(): void {
    console.log('AKA');
    this.poemaRS = this.poemaService.poemaRS;
    console.log(this.poemaRS);
  }

  cambiarModo() {

  }
}
