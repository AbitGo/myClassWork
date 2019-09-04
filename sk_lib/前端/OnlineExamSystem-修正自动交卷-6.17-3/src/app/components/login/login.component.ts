import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {LoginServeService} from '../../serve/login-serve.service';
import {passwordValid, usernameValid} from '../../validator/validators';
import {Router} from '@angular/router';
import {SessionStorageService, SessionStorage} from 'ngx-webstorage';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  validateForm: FormGroup;
  userCate = [];
  errorInfo = false;

  username;
  password;
  roleId;

  @SessionStorage()
  public boundValue;

  constructor(private fb: FormBuilder,
              private loginServeService: LoginServeService,
              private sessionSt: SessionStorageService,
              private router: Router) {
  }

  ngOnInit() {
    this.validateForm = this.fb.group({
      username: [null, [Validators.required]],
      adminCate: [null, [Validators.required]],
      password: [null, [Validators.required]],
      // remember: [true],
    });

    setTimeout(() => {
      this.userCate = [
        {value: '1', label: '超级管理员'},
        {value: '2', label: '学校管理员'},
        {value: '3', label: '教师'},
        {value: '4', label: '学生'}
      ];
    });
  }

  fouces() {
    this.errorInfo = false;
  }

  _submitForm() {
    console.log(this.validateForm.controls);
    if (this.validateForm.valid) {
      // console.log(this.validateForm.value)
      let urlSearchParams = new URLSearchParams();
      urlSearchParams.append('username', this.username);
      urlSearchParams.append('password', this.password);
      urlSearchParams.append('roleId', this.roleId.value);
      let param = urlSearchParams.toString();
      this.loginServeService.login(param, data => {
        // if (data.success) {
        console.log(data);
        if (data.message == 'SUCCESS') {
          // this.sessionSt.store('roleValue', data.data.sysUser.roleId);
          sessionStorage.setItem('roleValue', data.data.sysUser.roleId);
          sessionStorage.setItem('roleId', data.data.sysUser.id);
          // console.log(this.sessionSt.retrieve('roleValue'));
          console.log(sessionStorage.getItem('roleValue'));
          console.log(sessionStorage.getItem('roleId'));

          this.router.navigate(['main']);
        } else {
          this.errorInfo = true;
        }
      });
    }
  }
}
