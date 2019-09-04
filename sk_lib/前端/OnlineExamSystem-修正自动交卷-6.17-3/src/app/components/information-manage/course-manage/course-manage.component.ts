import {Component, OnInit} from '@angular/core';
import {TableServiceService} from '../../../serve/table-service.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {DepartmentManageServerService} from '../../../serve/information-manage/department-manage-server.service';
import {CourseManageServerService} from '../../../serve/information-manage/course-manage-server.service';
import {NzMessageService} from 'ng-zorro-antd';

@Component({
  selector: 'app-course-department-manage',
  templateUrl: './course-manage.component.html',
  styleUrls: ['./course-manage.component.css']
})

export class CourseManageComponent implements OnInit {
  validateForm: FormGroup;

  department;
  departments;

  course_modal;
  department_modal;
  departments_modal;

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
              private departmentManageServerService: DepartmentManageServerService,
              private courseManageServerService: CourseManageServerService,
              private _message: NzMessageService,
              private fb: FormBuilder) {
  }

  ngOnInit() {
    this.validateForm = this.fb.group({
      course_modal: ['', [Validators.required]],
      department_modal: ['', [Validators.required]],
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

  //查询数据
  searchCourse() {
    // console.log(this.schollsId);
    this.courseManageServerService.getCourse({
      'page': this._current,
      'size': this._pageSize,
      'departmentId': this.department,
    }).subscribe((data: any) => {

      console.log(data.data.list);
      // this.scholls = data.data.list;
      this._loading = false;
      this._total = data.data.total;
      this._dataSet = data.data.list;

    });
    this.serachShow = true;
    this.refreshData(true);
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
      this.course_modal = strs.name;
      this.department_modal = strs.department_name;
      this.statusShow = true;
    }
  }

  submit() {
    if (this.statusShow == true) {
      const body = {
        id: this.id,
        name: this.course_modal,
        departmentId: this.department_modal,
      };
      this.courseManageServerService.updateCourse(body).subscribe((data: any) => {
        console.log('更新');
        if (data.message == 'SUCCESS') {
          this._message.create('success', data.message);
          this.searchCourse();
        }  else {
          this._message.create('error', "失败");
        }
        console.log(body);
      });
    } else {
      const body = {
        name: this.course_modal,
        departmentId: this.department_modal,
      };
      this.courseManageServerService.addCourse(body).subscribe((data: any) => {
        console.log('添加');
        if (data.message == 'SUCCESS') {
          this._message.create('success', data.message);
          this.searchCourse();
        }  else {
          this._message.create('error', "失败");
        }
        console.log(body);
      });
    }
    this.validateForm.reset();
    this.searchCourse();
    this.isVisible = false;
    /*刷新table*/

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


  //表格数据操作
  refreshData(reset = false) {
    if (reset) {
      this._current = 1;
    }
    // this._loading = true;
    // const selectedGender = this._filterGender.filter(item => item.value).map(item => item.name);
    // this._randomUser.getUsers(this._current, this._pageSize, 'name', this._sortValue, selectedGender, '/api/course').subscribe((data: any) => {
    //   this._loading = false;
    //   this._total = data[0].info.total;
    //   this._dataSet = data[0].results;
    //   console.log(data)
    // })
  }

  /*删除提醒操作*/
  cancel = function () {
    this.alertTab = false;
  };

  confirm = function (id) {
    /*删除数据请求*/
    console.log(id);
    this.courseManageServerService.deleteCourse(id).subscribe((data: any) => {
      // console.log(data)
      if (data.message == 'SUCCESS') {
        this._message.create('success', data.message);
        this.searchCourse();
      }   else {
        this._message.create('error', "失败");
      }
    });
    this.searchCourse();
    // this.refreshData(true);
  };

}
