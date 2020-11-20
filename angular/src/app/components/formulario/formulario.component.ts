import { Component, OnInit } from '@angular/core';
import {PoemaService} from '../../services/poema.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-formulario',
  templateUrl: './formulario.component.html',
  styleUrls: ['./formulario.component.css']
})
export class FormularioComponent implements OnInit {

  fechaNacimiento: string;
  apellidos: string;
  nombres: string;
  loading = false;

  constructor(private poemaService: PoemaService, private router: Router) { }

  ngOnInit(): void {
  }

  cambiarModo() {
    console.log('cambiar MODO');
    const toggle = document.querySelector('.js-change-theme');
    const body = document.querySelector('body');
    const profile = document.getElementById('profile');
    const boton = document.getElementById('enviarPoema');

    if (body !== null && body.classList.contains('text-gray-900')) {
      if (toggle !== null) {
        toggle.innerHTML = '☀️';
      }
      if (body !== null) {
        body.classList.remove('text-gray-900');
        body.classList.add('text-gray-100');
      }
      if (profile !== null) {
        profile.classList.remove('bg-white');
        profile.classList.add('bg-gray-900');
      }
      if (boton !== null) {

        boton.classList.add('bg-white');
        boton.classList.add('bg-opacity-25');
        boton.classList.remove('bg-gray-900');
      }
    } else
    {
      if (toggle !== null){
        toggle.innerHTML = '🌙';
      }
      if (body !== null) {
        body.classList.remove('text-gray-100');
        body.classList.add('text-gray-900');
      }
      if (profile !== null) {
        profile.classList.remove('bg-gray-900');
        profile.classList.add('bg-white');
      }
      if (boton !== null) {

        boton.classList.add('bg-gray-900');
        boton.classList.add('text-white');
        boton.classList.remove('bg-white');

      }

    }
  }

  enviarFormulario() {
    const dataRQ = this.poemaService.crearRequest(this.nombres, this.apellidos , this.fechaNacimiento);
    this.loading = true;
    this.poemaService.get(dataRQ).subscribe(res => {
      this.loading = false;
      this.poemaService.guardarRespuesta(res);
      this.router.navigate(['/resultado']);
    }, error => {
      this.loading = false;
    });
  }
}
