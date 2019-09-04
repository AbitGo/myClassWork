import {Component, OnInit} from '@angular/core';
import {TableServiceService} from '../../../serve/table-service.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {SchoolManageServerService} from '../../../serve/information-manage/school-manage-server.service';
import {DepartmentManageServerService} from '../../../serve/information-manage/department-manage-server.service';
import {NzMessageService} from 'ng-zorro-antd';

@Component({
  selector: 'app-department-manage',
  templateUrl: './department-manage.component.html',
  styleUrls: ['./department-manage.component.css']
})
export class DepartmentManagementComponent implements OnInit {
  validateForm: FormGroup;

  root = false;
  admin = false;

  schoolId;//主界面学校ID
  school_id;//添加学院界面学校ID
  name;

  statusShow = false;
  /*状态*/
  flag = false;

  id;
  /*删除的id*/
  tabTitle = '';
  /*弹窗标题*/
  scholls;
  schollsId;
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

  constructor(private _randomUser: TableServiceService,
              private fb: FormBuilder,
              private schoolManageServerService: SchoolManageServerService,
              private _message: NzMessageService,
              private departmentManageServerService: DepartmentManageServerService) {
  }

  ngOnInit() {
    var status = sessionStorage.getItem('roleValue');
    if (status == '1') {
      this.root = true;
      this.admin = false;

    } else {
      this.root = false;
      this.admin = true;
    }

    this.validateForm = this.fb.group({
      school_id: ['', [Validators.required]],
      department_id: ['', [Validators.required]],
      name: ['', [Validators.required]],
      is_adult: ['', [Validators.required]]
    });

    //获取学校
    this.schoolManageServerService.getSchool({
      // 'page': 1,
      // 'size': 10,
    }).subscribe((data: any) => {

      console.log(data.data.list);
      this.scholls = data.data.list;

    });

  }

  isRoot() {
    return this.root;
  }

  isAdmin() {
    return this.admin;
  }

  //获取学校
  querySchool(value) {
    console.log(value);
    this.schoolId = value;

  }

  //查询数据
  searchDepartment() {
    console.log(this.schollsId);
    this.departmentManageServerService.getDepartment({
      'page': this._current,
      'size': this._pageSize,
      'schoolId': this.schoolId
    }).subscribe((data: any) => {

      console.log(data.data.list);
      // this.scholls = data.data.list;
      this._loading = false;
      this._total = data.data.total;
      this._dataSet = data.data.list;

    });
    this.serachShow = true;
    // this.refreshData(true);
  }

  searchDepartmentFromAdmin() {
    this.departmentManageServerService.getDepartmentFromAdmin({
      'page': this._current,
      'size': this._pageSize,
    }).subscribe((data: any) => {

      console.log(data.data.list);
      // this.scholls = data.data.list;
      this._loading = false;
      this._total = data.data.total;
      this._dataSet = data.data.list;

    });
    this.serachShow = true;
  }

  /*弹窗*/
  operateData(strs) {
    this.isVisible = true;
    if (strs == 'add') {
      this.tabTitle = '添加学院数据';
      this.statusShow = false;
    } else {
      this.tabTitle = '修改学院数据';
      this.id = strs.id;
      this.name = strs.name;
      // this.schoolId = this.scholls[0];
      this.statusShow = true;
    }
  }

  submit() {
    if (this.statusShow == true) {
      const body = {
        id: this.id,
        name: this.name,
        schoolId: this.school_id,
      };
      this.departmentManageServerService.updateDepartment(body).subscribe((data: any) => {
        console.log('更新');
        if (data.message == 'SUCCESS') {
          this._message.create('success', data.message);
          this.searchDepartment();
        }   else {
          this._message.create('error', "失败");
        }
        console.log(body);
      });
    } else {
      console.log(this.school_id + '->>');
      const body = {name: this.name, schoolId: this.school_id};
      this.departmentManageServerService.addDepartment(body).subscribe((data: any) => {
        console.log('添加');
        if (data.message == 'SUCCESS') {
          this._message.create('success', data.message);
          this.searchDepartment();
        }   else {
          this._message.create('error', "失败");
        }
        console.log(body);
      });
    }
    // console.log(this.validateForm.value);
    this.validateForm.reset();
    // console.log(this.validateForm.value)
    this.isVisible = false;
    this.searchDepartment();
    // this.refreshData(true);
    // this.searchSchool();
    /*刷新table*/
  }


  //关闭窗口
  handleCancel() {
    this.isVisible = false;
  }

  sort(value) {
    this._sortValue = value;
    this.searchDepartment();
  }

  reset() {
    this._filterGender.forEach(item => {
      item.value = false;
    });
    // this.refreshData(true);
  }


  /*删除提醒操作*/
  cancel = function () {
    this.alertTab = false;
  };

  confirm = function (id) {
    /*删除数据请求*/
    console.log(id);
    this.departmentManageServerService.deleteDepartment(id).subscribe((data: any) => {
      // console.log(data)
      if (data.message == 'SUCCESS') {
        this._message.create('success', data.message);
        this.searchDepartment();
      }   else {
        this._message.create('error', "失败");
      }
    });
    this.refreshData(true);
    // this.refreshData(true);
  };

}
