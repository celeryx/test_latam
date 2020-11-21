import {Injectable} from '@angular/core';
import {MatSnackBar} from '@angular/material/snack-bar';
import {SnackbarCustomComponent} from "../components/util/snackbar-custom/snackbar-custom.component";
import {SnackTypesEnum} from "../enums/snack-types.enum";

@Injectable({
  providedIn: 'root'
})
export class SnackbarCustomService {

  constructor(private snackBar: MatSnackBar) {}


  public openSnackBar(message: string, action: string, snackType?: SnackTypesEnum) {

    const _snackType: SnackTypesEnum = snackType !== undefined ? snackType : SnackTypesEnum.SUCCESS;

    this.snackBar.openFromComponent(SnackbarCustomComponent, {
      duration: 3000,
      data: { message: message, snackType: _snackType }
    });
  }
}
