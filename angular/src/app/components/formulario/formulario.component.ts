import {Component, OnInit} from '@angular/core';
import {PoemaService} from '../../services/poema.service';
import {Router} from '@angular/router';
import {SnackbarCustomService} from "../../services/snackbar-custom.service";
import {SnackTypesEnum} from "../../enums/snack-types.enum";
import {Subject, Subscription} from "rxjs";
import {debounceTime, distinctUntilChanged, map} from 'rxjs/operators';


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
  camposValidos = false ;
  formularioValido = new Map<string, boolean>();
  subjectFormularioValido = new Subject();
  public keyUp = new Subject<any>();
  private subscription: Subscription;

  constructor(private poemaService: PoemaService, private router: Router,
              private snackbar: SnackbarCustomService) {

    this.subscription = this.keyUp.pipe(
      map((event:any) => event ),
      debounceTime(700),
      distinctUntilChanged()
    ).subscribe(res => {
      this.validacion(res.tipo, res.nombreCampo)
    });

  }

  ngOnInit(): void {
    this.suscripcionValidarFormulario();
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

  validacion(tipo: string, nombreCampo: string) {

    const regexpTexto = new RegExp('((^[A-Za-zÀ-ÖØ-öø-ÿ]*)(\\s?)([A-Za-zÀ-ÖØ-öø-ÿ]*?))$');
    const regexpFecha = new RegExp('^\\d{2}-\\d{2}-\\d{4}$');
    const regexp = nombreCampo === 'fechaNacimiento' ? regexpFecha : regexpTexto;

    // @ts-ignore
    if (this[nombreCampo] !== undefined
    // @ts-ignore
    && this[nombreCampo] !== ''
    // @ts-ignore
    && regexp.test(this[nombreCampo])) {

      this.subjectFormularioValido.next({tipo: tipo, valor: true})
    } else {
      this.snackbar.openSnackBar(tipo + ' no tiene un formato correcto','', SnackTypesEnum.ERROR);
      this.subjectFormularioValido.next({tipo: tipo, valor: false})
    }
  }

  private suscripcionValidarFormulario() {
    this.subjectFormularioValido
    .subscribe( (res: any) => {
      this.formularioValido.set(res.tipo, res.valor);
      if (this.formularioValido.size === 3) {
        const valores = Array.from(this.formularioValido.values());
        this.camposValidos = !(valores.filter(valor => valor === false).length > 0);
      } else {
        this. camposValidos = false
      }
    });
  }




}
