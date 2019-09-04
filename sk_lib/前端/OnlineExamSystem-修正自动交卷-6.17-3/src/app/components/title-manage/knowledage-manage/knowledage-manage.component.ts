import {Component, OnInit} from '@angular/core';
import {TableServiceService} from '../../../serve/table-service.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import {SchoolManageServerService} from '../../../serve/information-manage/school-manage-server.service';
import {KnowledageManageServiceService} from '../../../serve/title-manage/knowledage-manage-service.service';
import {CourseManageServerService} from '../../../serve/information-manage/course-manage-server.service';
import {NzMessageService} from 'ng-zorro-antd';

@Component({
  selector: 'app-knowledage-manage',
  templateUrl: './knowledage-manage.component.html',
  styleUrls: ['./knowledage-manage.component.css']
})
export class KnowledageManageComponent implements OnInit {

  validateForm: FormGroup;

  id_modal;
  loginName_modal;
  realName_modal;
  password_modal;
  school_modal;
  gender_modal;

  knowledge_modal;
  subject_modal;

  statusShow = false;
  /*状态*/

  id;
  /*删除的id*/
  tabTitle = '';
  /*弹窗标题*/
  subjects;
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
              private knowledageManageServerService: KnowledageManageServiceService,
              private courseManageServerService: CourseManageServerService,
              private _message: NzMessageService,
              private http: HttpClient) {
  }

  ngOnInit() {
    this.subjects = [{value: 'jack', label: 'Jack'},
      {value: 'lucy', label: 'Lucy'},
      {value: 'disabled', label: 'Disabled', disabled: true}];
    this.validateForm = this.fb.group({
      knowledge_modal: ['', [Validators.required]],
      // subject_modal: ['', [Validators.required]],
      is_adult: ['', [Validators.required]]
    });
    //教师获取科目
    this.courseManageServerService.getCourseFomTeacher({
      // 'page': 1,
      // 'size': 10,
    }).subscribe((data: any) => {

      console.log(data);
      if (data.data) {
        this.subjects = data.data.list;
      }
    });
    this.searchKnowledage();
  }

  //查询数据
  searchKnowledage() {
    // console.log(this.subjectsId);
    this.serachShow = true;
    this.refreshData(true);
  }

  /*弹窗*/
  operateData(strs) {
    this.isVisible = true;
    if (strs == 'add') {
      this.tabTitle = '添加知识点';
      this.statusShow = false;
    } else {
      this.tabTitle = '修改知识点';
      this.id_modal = strs.id;
      this.loginName_modal = strs.loginName;
      this.realName_modal = strs.realName;
      this.school_modal = strs.schoolId;
      this.gender_modal = strs.gender;
      // this.schoolId = this.subjects[0];
      this.statusShow = true;
    }
  }

  submit() {
    if (this.statusShow == true) {
      const body = {
        id: this.id_modal,
        name: this.knowledge_modal,
        subjectId: sessionStorage.getItem('selectedSubject')
      };
      this.knowledageManageServerService.updateKnowledage(body).subscribe((data: any) => {
        console.log('更新');
        console.log(body);
        if (data.message == 'SUCCESS') {
          this._message.create('success', data.message);
          this.searchKnowledage();
        }
      });
    } else {
      const body = {
        name: this.knowledge_modal,
        subjectId: sessionStorage.getItem('selectedSubject')
      };
      this.knowledageManageServerService.addKnowledage(body).subscribe((data: any) => {
        console.log('添加');
        console.log(body);
        if (data.message == 'SUCCESS') {
          this._message.create('success', data.message);
          this.searchKnowledage();
        }

      });
    }
    // console.log(this.validateForm.value);
    this.validateForm.reset();
    // console.log(this.validateForm.value)
    this.isVisible = false;

    // this.searchSchool();
    /*刷新table*/
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
  refreshData(reset = false) {
    if (reset) {
      this._current = 1;
    }
    this._loading = true;
    this.knowledageManageServerService.getKnowledage({
      'page': this._current,
      'size': this._pageSize,
      'subjectId': sessionStorage.getItem('selectedSubject')
    }).subscribe((data: any) => {
      console.log(data);
      if (data.data) {
        console.log(data.data.list);
        this._loading = false;
        this._total = data.data.total;
        this._dataSet = data.data.list;
      }
    });
  }

  /*删除提醒操作*/
  cancel = function () {
    this.alertTab = false;
  };

  confirm = function (id) {
    /*删除数据请求*/
    console.log(id);
    this.knowledageManageServerService.deleteKnowledage(id).subscribe((data: any) => {
      // console.log(data)
      if (data.message == 'SUCCESS') {
        this._message.create('success', data.message);
        this.searchKnowledage();
      }
    });
    // this.refreshData(true);
  };

}
