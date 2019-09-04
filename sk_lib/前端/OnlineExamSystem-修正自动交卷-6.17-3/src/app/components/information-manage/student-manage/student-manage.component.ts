import {Component, OnInit} from '@angular/core';
import {TableServiceService} from '../../../serve/table-service.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {StudentManageServerService} from '../../../serve/information-manage/student-manage-server.service';
import {DepartmentManageServerService} from '../../../serve/information-manage/department-manage-server.service';
import {MajorManageServerService} from '../../../serve/information-manage/major-manage-server.service';
import {ClassManageServerService} from '../../../serve/information-manage/class-manage-server.service';
import {NzMessageService} from 'ng-zorro-antd';
import {HttpHeaders} from "@angular/common/http";
import {HttpRequest} from "@angular/common/http";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-student-manage',
  templateUrl: './student-manage.component.html',
  styleUrls: ['./student-manage.component.css']
})

export class StudentManageComponent implements OnInit {
  validateForm: FormGroup;

  _department;
  departments;
  _major;
  majors;
  _class;
  classes;

  id_modal;
  login_name_modal;
  real_name_modal;
  password_modal;
  major_modal;
  department_modal;
  class_modal;
  majors_modal;
  departments_modal;
  classes_modal;

  statusShow = false;
  /*状态*/

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
              private http: HttpClient,
              private studentManageServerService: StudentManageServerService,
              private departmentManageServerService: DepartmentManageServerService,
              private majorManageServerService: MajorManageServerService,
              private classManageServerService: ClassManageServerService,
              private _message: NzMessageService,
              private fb: FormBuilder) {
  }

  ngOnInit() {
    this.scholls = [{value: 'jack', label: 'Jack'},
      {value: 'lucy', label: 'Lucy'},
      {value: 'disabled', label: 'Disabled', disabled: true}];
    this.validateForm = this.fb.group({
      login_name_modal: ['', [Validators.required]],
      real_name_modal: ['', [Validators.required]],
      password_modal: ['', [Validators.required]],
      major_modal: ['', [Validators.required]],
      department_modal: ['', [Validators.required]],
      class_modal: ['', [Validators.required]],
      is_adult: ['', [Validators.required]]
    });
    this.searchStudent();
    //校管获取所属学校的所有学院
    this.departmentManageServerService.getDepartmentFromAdmin({
      // 'page': this._current,
      // 'size': this._pageSize,
    }).subscribe((data: any) => {

      console.log(data.data.list);
      this.departments_modal = data.data.list;
      this.departments = data.data.list;

    });
  }

  queryMajor(department_modal) {
    this.majorManageServerService.getMajorFromAdmin({
      // 'page': this._current,
      // 'size': this._pageSize,
      'departmentId': department_modal
    }).subscribe((data: any) => {

      console.log(data.data.list);
      this.majors_modal = data.data.list;
      this.majors = data.data.list;

    });
  }

  queryClass(major_modal) {
    this.classManageServerService.getClass({
      // 'page': this._current,
      // 'size': this._pageSize,
      'departmentId': this._department,
      'majorId': this._major,
    }).subscribe((data: any) => {

      console.log(data.data.list);
      // this.scholls = data.data.list;
      this.classes = data.data.list;

    });
  }

  queryClassFromModal(major_modal) {
    this.classManageServerService.getClass({
      // 'page': this._current,
      // 'size': this._pageSize,
      'departmentId': this.department_modal,
      'majorId': this.major_modal,
    }).subscribe((data: any) => {

      console.log(data.data.list);
      // this.scholls = data.data.list;
      this.classes_modal = data.data.list;

    });
  }

  //查询数据
  searchStudent() {
    // console.log(this.schollsId);
    this.serachShow = true;
    this.refreshData();
  }

  /*弹窗*/
  operateData(strs) {
    this.isVisible = true;
    if (strs == 'add') {
      this.tabTitle = '添加学院数据';
      this.statusShow = false;
    } else {
      this.tabTitle = '修改学院数据';
      this.id_modal = strs.id;
      this.login_name_modal = strs.loginName;
      this.real_name_modal = strs.realName;
      this.password_modal = strs.schoolId;
      this.department_modal = this.scholls[0];
      this.class_modal = this.scholls[0];
      this.statusShow = true;
    }
  }

  submit() {
    if (this.statusShow == true) {
      const body = {
        id: this.id_modal,
        loginName: this.login_name_modal,
        realName: this.real_name_modal,
        password: this.password_modal,
        departmentId: this.department_modal,
        classId: this.class_modal,
      };
      this.studentManageServerService.updateStudent(body).subscribe((data: any) => {
        console.log('更新');
        if (data.message == 'SUCCESS') {
          this._message.create('success', data.message);
          this.searchStudent();
        } else {
          this._message.create('error', "失败");
        }
        console.log(body);
      });
    } else {
      const body = {
        loginName: this.login_name_modal,
        realName: this.real_name_modal,
        password: this.password_modal,
        departmentId: this.department_modal,
        classId: this.class_modal,
      };
      this.studentManageServerService.addStudent(body).subscribe((data: any) => {
        console.log('添加');
        if (data.message == 'SUCCESS') {
          this._message.create('success', data.message);
          this.searchStudent();
        } else {
          this._message.create('error', "失败");
        }
        console.log(body);
      });
    }
    // console.log(this.validateForm.value);
    this.validateForm.reset();
    // console.log(this.validateForm.value)
    this.isVisible = false;
    this.searchStudent();
    // this.searchSchool();
    /*刷新table*/
  }

  //添加数据
  addStudent() {
    if (this.validateForm.valid) {
      /*此处提交*/
      console.log(this.validateForm.value);
      this.validateForm.reset();
      console.log(this.validateForm.value);
      this.isVisible = false;
      this.refreshData(true);
      /*刷新table*/

    }
  }

  //导入学生
  importStudent(file: HTMLInputElement) {
    debugger
    const token = localStorage.getItem('token');
    let headers: HttpHeaders = new HttpHeaders();
    headers = headers
      .set('Cache-Control', 'no-cache')
      .set('Authorization', 'Bearer ' + token);
    if (file.value.length === 0) {
      return;
    }
    const files: FileList = file.files
    const fileLength = files.length;
    const formData: FormData = new FormData();
    // for (let index = 0; index < fileLength; index++) {
    const singleFile = files.item(0);
    // files 这个名字和spring mvc controller参数的名字要对应
    formData.append('excel', singleFile);
    formData.append('departmentId', this._department);
    formData.append('classId', this._class);
    // }

    const url = '/apis/api/file/studentimport';
    const req = new HttpRequest('POST', url, formData, {
      reportProgress: true, headers: headers,
      withCredentials: true,
    });

    // this.http.request(req).subscribe(event => {
    //   console.log("上传");
    //   console.log(event);
    // });
    this.http.request(req).subscribe((data: any)=> {
      console.log("上传");
      console.log(data);
      // debugger
      // if (data.body.message == "SUCCESS") {
      //   this._message.create('success', "成功");
      // }
    });
  }

  //下载模板
  download() {
    // this.studentManageServerService.downloadStudentFile({
    // }).subscribe((data: any) => {
    //
    //   console.log(data);
    // });
    window.open('/apis/api/file/downloadstudent');
  }

  //关闭窗口
  handleCancel() {
    this.isVisible = false;
  }

  sort(value) {
    this._sortValue = value;
    this.refreshData();
  }

  reset() {
    this._filterGender.forEach(item => {
      item.value = false;
    });
    this.refreshData(true);
  }

  search() {
    this.refreshData(true);
  }


  //表格数据操作
  refreshData(reset = false) {
    if (reset) {
      this._current = 1;
    }
    this._loading = true;
    this.studentManageServerService.getStudent({
      'page': this._current,
      'size': this._pageSize,
      'departmentId': this._department == undefined ? "" : this._department,
      'classId': this._class == undefined ? "" : this._class,
    }).subscribe((data: any) => {

      console.log(data);
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
    console.log(id);
    this.studentManageServerService.deleteStudent({id: id}).subscribe((data: any) => {
      // console.log(data)
      if (data.message == 'SUCCESS') {
        this._message.create('success', data.message);
        this.searchStudent();
      } else {
        this._message.create('error', "失败");
      }
    });
    this.searchLeadership();
    // this.refreshData(true);
  };

}
