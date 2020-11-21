import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { FormularioComponent } from './components/formulario/formulario.component';
import { ResultadoComponent } from './components/resultado/resultado.component';
import {FormsModule} from '@angular/forms';
import {PoemaService} from './services/poema.service';
import {HttpClientModule} from '@angular/common/http';
import {NgxLoadingModule} from 'ngx-loading';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'
import {MatSnackBarModule} from '@angular/material/snack-bar'
import {SnackbarCustomComponent} from "./components/util/snackbar-custom/snackbar-custom.component";
import {MatIconModule} from "@angular/material/icon";
import {SnackbarCustomService} from "./services/snackbar-custom.service";


@NgModule({
  declarations: [
    AppComponent,
    FormularioComponent,
    ResultadoComponent,
    SnackbarCustomComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgxLoadingModule.forRoot({}),
    BrowserAnimationsModule,
    MatSnackBarModule,
    MatIconModule
  ],
  providers: [PoemaService,SnackbarCustomService],
  bootstrap: [AppComponent]
})
export class AppModule { }
