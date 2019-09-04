import {Component, OnInit} from '@angular/core';
import {TableServiceService} from '../../../serve/table-service.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {HttpHeaders} from '@angular/common/http';
import {HttpClient} from '@angular/common/http';
import {AdminManageServerService} from '../../../serve/information-manage/admin-manage-server.service';
import {DepartmentManageServerService} from '../../../serve/information-manage/department-manage-server.service';
import {MajorManageServerService} from '../../../serve/information-manage/major-manage-server.service';
import {ClassManageServerService} from '../../../serve/information-manage/class-manage-server.service';
import {NzMessageService} from 'ng-zorro-antd';

@Component({
  selector: 'app-class-manage',
  templateUrl: './class-manage.component.html',
  styleUrls: ['./class-manage.component.css']
})
export class ClassManageComponent implements OnInit {
  validateForm: FormGroup;

  department;
  major;
  department_modal;
  major_modal;
  class_modal;

  statusShow = false;
  /*状态*/
  id;
  /*删除的id*/
  tabTitle = '';
  /*弹窗标题*/
  scholls;
  departments;
  majors;
  departments_modal;
  majors_modal;
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
              private adminManageServerService: AdminManageServerService,
              private majorManageServerService: MajorManageServerService,
              private classManageServerService: ClassManageServerService,
              private departmentManageServerService: DepartmentManageServerService,
              private _message: NzMessageService,
              private http: HttpClient) {
  }

  ngOnInit() {
    this.departments = [{value: 'jack', label: 'Jack'},
      {value: 'lucy', label: 'Lucy'},
      {value: 'disabled', label: 'Disabled', disabled: true}];
    this.validateForm = this.fb.group({
      department_modal: ['', [Validators.required]],
      major_modal: ['', [Validators.required]],
      class_modal: ['', [Validators.required]],
      is_adult: ['', [Validators.required]]
    });

    //校管获取所属学校的所有学院
    this.departmentManageServerService.getDepartmentFromAdmin({
      // 'page': 1,
      // 'size': 10,
    }).subscribe((data: any) => {

      console.log(data.data.list);
      this.departments = data.data.list;
      this.departments_modal = data.data.list;

    });

  }

  queryMajor(department) {
    console.log(department);
    this.majorManageServerService.getAdultMajorFromAdmin({
      // 'page': 1,
      // 'size': 10,
      'departmentId': department
    }).subscribe((data: any) => {

      console.log(data.data.list);
      this.majors = data.data.list;

    });
  }

  queryMajor_modal(department_modal) {
    console.log(department_modal);
    this.majorManageServerService.getMajorFromAdmin({
      'page': 1,
      'size': 10,
      'departmentId': department_modal
    }).subscribe((data: any) => {
      if (data.data) {
        this.majors_modal = data.data.list;
      }


    });
  }

  /*查询数据*/
  searchClass() {
    this.classManageServerService.getClass({
      'page': this._current,
      'size': this._pageSize,
      'departmentId': this.department,
      'majorId': this.major,
    }).subscribe((data: any) => {

      console.log('----');
      console.log(data);
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
      this.class_modal = strs.name;
      this.department_modal = strs.department_name;
      this.major_modal = strs.major_name;
      this.statusShow = true;
    }
  }


  submit() {
    if (this.statusShow == true) {
      const body = {
        id: this.id,
        name: this.class_modal,
        departmentId: this.department_modal,
        majorId: this.major_modal,
      };
      this.classManageServerService.updateClass(body).subscribe((data: any) => {
        console.log('更新');
        if (data.message == 'SUCCESS') {
          this._message.create('success', data.message);
          this.searchClass();
        } else {
          this._message.create('error', "失败");
        }
        console.log(body);
      });
    } else {
      const body = {
        name: this.class_modal,
        departmentId: this.department_modal,
        majorId: this.major_modal,
      };
      this.classManageServerService.addClass(body).subscribe((data: any) => {
        console.log('添加');
        if (data.message == 'SUCCESS') {
          this._message.create('success', data.message);
          this.searchClass();
        } else {
          this._message.create('error', "失败");
        }
        console.log(body);
      });
    }
    this.validateForm.reset();
    this.searchClass();
    this.isVisible = false;
    /*刷新table*/
  }

  /*关闭窗口*/
  handleCancel() {
    this.isVisible = false;
  }

  sort(value) {
    this._sortValue = value;
  }

  reset() {
    this._filterGender.forEach(item => {
      item.value = false;
    });
  }


  /*删除提醒操作*/
  cancel = function () {
    this.alertTab = false;
  };

  confirm = function (id) {
    /*删除数据请求*/
    console.log(id);
    this.classManageServerService.deleteClass(id).subscribe((data: any) => {
      // console.log(data)
      if (data.message == 'SUCCESS') {
        this._message.create('success', data.message);
        this.searchClass();
      } else {
        this._message.create('error', "失败");
      }
    });
    this.searchClass();
    // this.refreshData(true);
  };

}
