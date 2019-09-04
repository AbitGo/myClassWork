import {Component, OnInit} from '@angular/core';
import {DepartmentManageServerService} from '../../../serve/information-manage/department-manage-server.service';
import {MajorManageServerService} from '../../../serve/information-manage/major-manage-server.service';
import {StudentManageServerService} from '../../../serve/information-manage/student-manage-server.service';
import {ClassManageServerService} from '../../../serve/information-manage/class-manage-server.service';
import {GradeQueryServerService} from '../../../serve/information-query/grade-query-server.service';

@Component({
  selector: 'app-student-query',
  templateUrl: './student-query.component.html',
  styleUrls: ['./student-query.component.css']
})
export class StudentQueryComponent implements OnInit {

  department;
  departments;
  major;
  majors;
  class;
  classes;

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


  constructor(private departmentManageServerService: DepartmentManageServerService,
              private majorManageServerService: MajorManageServerService,
              private studentManageServerService: StudentManageServerService,
              private gradeQueryServerService: GradeQueryServerService,
              private classManageServerService: ClassManageServerService,) {
  }

  ngOnInit() {
    this.departmentManageServerService.getDepartmentFromTeacher().subscribe((data: any) => {

      console.log(data);
      this.departments = data.data;
    });
  }

  //教师根据学院查询专业
  queryMajor(department) {
    this.majorManageServerService.getMajorFromTeacher({
      // 'page': 1,
      // 'size': 10,
      'departmentId': department,
    }).subscribe((data: any) => {

      console.log(data.data);
      this.majors = data.data;
    });
  }

  //教师根据学院和专业查询班级
  queryClass(major) {
    this.classManageServerService.getClassFromTeacher({
      // 'page': 1,
      // 'size': 10,
      'departmentId': this.department,
      'majorId': major,
    }).subscribe((data: any) => {

      console.log(data.data.list);
      this.classes = data.data.list;
    });
  }

  submit() {
    this.serachShow = true;
    this.refreshData();
  }

  /*表格数据操作*/
  refreshData(reset = false) {
    if (reset) {
      this._current = 1;
    }
    this.gradeQueryServerService.getScoreFromTeacher({
      'page': this._current,
      'size': this._pageSize,
      'subjectId': sessionStorage.getItem('selectedSubject'),
      'classId': this.class,
    }).subscribe((data: any) => {
      console.log(data);
      this._loading = false;
      this._total = data.data.total;
      this._dataSet = data.data.list;

    });

  }


}
