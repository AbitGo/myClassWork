import {Component, OnInit} from '@angular/core';
import {TableServiceService} from '../../../serve/table-service.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AdminManageServerService} from '../../../serve/information-manage/admin-manage-server.service';
import {HttpHeaders} from '@angular/common/http';
import {HttpClient} from '@angular/common/http';
import {HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {UtilsServiceService} from '../../../serve/utils-service.service';
import {NzMessageService} from 'ng-zorro-antd';

@Component({
  selector: 'app-admin-manage',
  templateUrl: './admin-manage.component.html',
  styleUrls: ['./admin-manage.component.css']
})

export class AdminManageComponent implements OnInit {
  validateForm: FormGroup;

  statusShow = false;//状态
  statusRole = false;//角色权限状态

  result: Observable<any>;

  id;
  /*删除的id*/
  tabTitle = '';
  /*弹窗标题*/
  scholls;
  schollsId;

  loginName;
  realName;
  password;
  gender;


  serachShow = false;
  /*控制是否生成table*/
  isVisible = false;
  /*tab添加修改窗口*/
  _current = 1;
  _pageSize = 10;
  _total = 1;
  _dataSet = [];
  _loading = true;
  _sortValue = null;
  _filterGender = [
    {name: 'male', value: false},
    {name: 'female', value: false}
  ];

  constructor(private fb: FormBuilder,
              private adminManageServerService: AdminManageServerService,
              private _message: NzMessageService,
              private http: HttpClient) {
  }

  ngOnInit() {

    var status = sessionStorage.getItem('roleValue');
    if (status == '1') {
      this.statusRole = true;
    } else if (status == '2') {
      this.statusRole = true;
    } else if (status == '3') {
      this.statusRole = false;
    } else {
      this.statusRole = false;
    }

    // this.genders = [{value: 'jack', label: 'Jack'},
    //   {value: 'lucy', label: 'Lucy'},
    //   {value: 'disabled', label: 'Disabled', disabled: true}];
    this.validateForm = this.fb.group({

      loginName: ['', [Validators.required]],
      realName: ['', [Validators.required]],
      gender: ['', [Validators.required]],
      password: ['', [Validators.required]]
    });
    this.searchAdmin();
  }

  //查询数据
  searchAdmin() {
    // console.log(this.schollsId);
    this.serachShow = true;
    this.refreshData(true);
  }


  /*弹窗*/
  operateData(strs) {
    this.isVisible = true;
    if (strs == 'add') {
      this.tabTitle = '添加用户数据';
      this.statusShow = false;
    } else {
      this.tabTitle = '修改用户数据';
      this.statusShow = true;
      this.id = strs.id;
      this.loginName = strs.loginName;
      this.realName = strs.realName;
      this.gender = strs.gender;
    }
    // console.log(strs);
  }

  //添加管理员
  submit() {
    // if (this.validateForm.valid) {
    /*此处提交*/


    // const body = this.validateForm;
    if (this.statusShow == true) {
      const body = {
        id: this.id,
        loginName: this.loginName,
        password: this.password,
        realName: this.realName,
        gender: this.gender
      };
      this.adminManageServerService.updateAdmin(body).subscribe((data: any) => {
        console.log('更新');
        if (data.message == 'SUCCESS') {
          this._message.create('success', data.message);
          this.refreshData(true);
        } else {
          this._message.create('error', "失败");
        }
        console.log(body);
      });
    } else {
      const body = {loginName: this.loginName, password: this.password, realName: this.realName, gender: this.gender};
      this.adminManageServerService.addAdmin(body).subscribe((data: any) => {

        console.log('添加');
        console.log(data);
        if (data.message == 'SUCCESS') {
          this._message.create('success', data.message);
          this.refreshData(true);
        } else {
          this._message.create('error', data.message);
        }
        console.log(body);
      });
    }
    // console.log(this.validateForm.value);
    this.validateForm.reset();
    // console.log(this.validateForm.value)
    this.isVisible = false;
    // this.refreshData(true);
    this.searchAdmin();
    /*刷新table*/

    // }
  }

  //关闭窗口
  handleCancel() {
    this.isVisible = false;
  }

  sort(value) {
    this._sortValue = value;
    this.refreshData(true);
  }

  reset() {
    this._filterGender.forEach(item => {
      item.value = false;
    });
    this.refreshData(true);
  }


  //表格数据操作
  refreshData(reset) {
    if (reset) {
      this._current = 1;
    }
    this._loading = true;
    const selectedGender = this._filterGender.filter(item => item.value).map(item => item.name);
    let urlSearchParams = new URLSearchParams();
    urlSearchParams.append('page', `${this._current}`);
    urlSearchParams.append('size', `${this._pageSize}`);
    let param = urlSearchParams.toString();

    this.adminManageServerService.getAdmin({
      'page': this._current,
      'size': this._pageSize
    }, 'name', this._sortValue, selectedGender, '/apis/api/root/user/listrooter').subscribe((data: any) => {

      // console.log(data.data.list)
      this._loading = false;
      this._total = data.data.total;
      this._dataSet = data.data.list;

    });


  }

  /*删除提醒操作*/
  cancel = function () {
    this.alertTab = false;
  };

  confirm = function (id) {
    /*删除数据请求*/
    // console.log(id);
    this.adminManageServerService.deleteAdmin({id: id}, '/apis/api/root/user/deleteroot').subscribe((data: any) => {
      if (data.message == 'SUCCESS') {
        this._message.create('success', data.message);
        this.searchAdmin();
      } else {
        this._message.create('error', "失败");
      }

      // console.log(data)
    });
  };

}
