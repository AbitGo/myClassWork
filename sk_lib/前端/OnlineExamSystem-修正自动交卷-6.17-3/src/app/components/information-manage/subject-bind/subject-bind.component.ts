import {Component, OnInit} from '@angular/core';
import {CourseManageServerService} from '../../../serve/information-manage/course-manage-server.service';
import {DepartmentManageServerService} from '../../../serve/information-manage/department-manage-server.service';
import {MajorManageServerService} from '../../../serve/information-manage/major-manage-server.service';
import {NzMessageService} from 'ng-zorro-antd';

@Component({
  selector: 'app-subject-bind',
  templateUrl: './subject-bind.component.html',
  styleUrls: ['./subject-bind.component.css']
})
export class SubjectBindComponent implements OnInit {

  _subject;
  subjectIds;
  _major;
  subjects;
  majors;
  _department;
  departments;

  serachShow = false;

  _current = 1;
  _pageSize = 10;
  _total = 1;
  _dataSet = [];
  _loading = true;
  _sortValue = null;

  constructor(private courseManageServerService: CourseManageServerService,
              private departmentManageServerService: DepartmentManageServerService,
              private _message: NzMessageService,
              private majorManageServerService: MajorManageServerService) {
  }

  ngOnInit() {

    //校管获取所属学校的所有学院
    this.departmentManageServerService.getDepartmentFromAdmin({
      // 'page': 1,
      // 'size': 10,
    }).subscribe((data: any) => {

      console.log(data.data.list);
      this.departments = data.data.list;

    });

    this.searchBindSubject();


  }

  queryMajorSubject(department) {
    console.log(department);
    this.courseManageServerService.getCourse({
      // 'page': 1,
      // 'size': 10,
      'departmentId': department,
    }).subscribe((data: any) => {

      console.log(data.data.list);
      // this.scholls = data.data.list;
      this.subjects = data.data.list;

    });

    this.majorManageServerService.getAdultMajorFromAdmin({
      // 'page': 1,
      // 'size': 10,
      'departmentId': department
    }).subscribe((data: any) => {

      console.log(data.data.list);
      // this.scholls = data.data.list;
      this.majors = data.data.list;

    });
  }

  insertBind() {

    let str = [];
    for (let i of this._subject) {
      str.push({'id': i});
    }
    this.subjectIds = str;

    const body = {
      majorId: this._major,
      subjectIds: this.subjectIds,
    };
    this.majorManageServerService.bindMajor2Subject(body).subscribe((data: any) => {
      console.log('绑定');
      console.log(body);
      console.log(data);
      if (data.message == 'SUCCESS') {
        this._message.create('success', data.message);
        this.searchBindSubject();
      } else {
        this._message.create('error', "失败");
      }
    });
    this._subject = null;
  }

  //查询数据
  searchBindSubject() {
    // console.log(this.schollsId);
    this.serachShow = true;
    this.refreshData();
  }

  refreshData(reset = false) {
    if (reset) {
      this._current = 1;
    }
    this.majorManageServerService.getBindSubject({
      'page': this._current,
      'size': this._pageSize
    }).subscribe((data: any) => {
      console.log(data);
      this._loading = false;
      this._total = data.data.total;
      this._dataSet = data.data.list;

    });
  }

  sort() {
  }

  /*删除提醒操作*/
  cancel = function () {
    this.alertTab = false;
  };

  confirm = function (id) {
    /*删除数据请求*/
    console.log(id);
    this.majorManageServerService.deleteBindSubject({id: id}, id).subscribe((data: any) => {
      // console.log(data)
      if (data.message == 'SUCCESS') {
        this._message.create('success', data.message);
        this.searchBindSubject();
      } else {
        this._message.create('error', "失败");
      }
    });
    // this.refreshData(true);
  };

}
