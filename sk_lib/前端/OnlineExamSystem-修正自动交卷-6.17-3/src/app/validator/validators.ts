import {FormControl} from "@angular/forms";
/**
 * Created by wuzhi on 2018/1/3.
 */
export function usernameValid(control: FormControl): any {
  let myreg = /[0-9]a-zA-Z/g;
  let valid = myreg.test(control.value);
  return valid ? null : {valid: true};
}
export function passwordValid(control: FormControl): any {
  let myreg = /[0-9]a-zA-Z/g;
  let valid = myreg.test(control.value);
  return valid ? null : {valid: true};
}
