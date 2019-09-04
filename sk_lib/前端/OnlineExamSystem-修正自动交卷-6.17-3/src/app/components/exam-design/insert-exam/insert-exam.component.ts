import {Component, OnInit} from '@angular/core';
import {NzMessageService} from 'ng-zorro-antd';
import {InsertExamService} from '../../../serve/exam-design/insert-exam.service';
import {DesignPaperService} from '../../../serve/exam-design/design-paper.service';
import {DepartmentManageServerService} from '../../../serve/information-manage/department-manage-server.service';
import {MajorManageServerService} from '../../../serve/information-manage/major-manage-server.service';
import {ClassManageServerService} from '../../../serve/information-manage/class-manage-server.service';

@Component({
  selector: 'app-insert-exam',
  templateUrl: './insert-exam.component.html',
  styleUrls: ['./insert-exam.component.css']
})
export class InsertExamComponent implements OnInit {

  _date = null;
  serachShow = false;

  _examName;
  _startTime = null;
  _endTime = null;
  _duration;
  _examPaper;
  examPapers;

  departments;
  department;
  class;
  classes;
  major;
  majors;
  classArray;

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

  constructor(private _message: NzMessageService,
              private designPaperService: DesignPaperService,
              private departmentManageServerService: DepartmentManageServerService,
              private majorManageServerService: MajorManageServerService,
              private classManageServerService: ClassManageServerService,
              private insertExamService: InsertExamService) {
  }

  ngOnInit() {
    this.serachShow = true;

    this.designPaperService.getExampaper({
      // 'page': 1,
      // 'size': 20,
      'subjectId': sessionStorage.getItem('selectedSubject'),
    }).subscribe((data: any) => {

      console.log(data);
      this.examPapers = data.data.list;

    });

    this.searchExam();

    this.departmentManageServerService.getDepartmentFromTeacher().subscribe((data: any) => {

      console.log(data);
      this.departments = data.data;
    });


  }

  //查询数据
  searchExam() {
    // console.log(this.schollsId);
    this.serachShow = true;
    this.refreshData(true);
  }

  changeTime(time) {
    let year = time.getFullYear() < 10 ? ('0' + time.getFullYear()) : time.getFullYear();
    let month = (time.getMonth() + 1) < 10 ? ('0' + (time.getMonth() + 1)) : (time.getMonth() + 1);
    let day = time.getDate() < 10 ? ('0' + time.getDate()) : time.getDate();
    let hours = time.getHours() < 10 ? ('0' + time.getHours()) : time.getHours();
    let minutes = (time.getMinutes()) < 10 ? ('0' + (time.getMinutes())) : (time.getMinutes());
    let mis = time.getSeconds() < 10 ? ('0' + time.getSeconds()) : time.getSeconds();
    return year + '-' + month + '-' + day + ' ' + hours + ':' + minutes + ':' + mis;
  }

  submit() {
    if (this._examName && this._startTime && this._duration) {
      console.log(this._examName + ' ' + new Date(this._startTime).getHours() + ' ' + (this._endTime).toISOString() + ' ' + this._duration + ' ' + this._examPaper);
      console.log(new Date().toISOString());
      let subjectId = sessionStorage.getItem('selectedSubject');
      let time1 = this.changeTime(new Date(this._startTime));
      let time2 = this.changeTime(new Date(this._endTime));

      let str = [];
      for (let i of this.class) {
        str.push({'classId': i});
      }
      this.classArray = str;

      const body = {
        startTime: time1,
        endTime: time2,
        duration: this._duration,
        paperId: this._examPaper,
        subjectId: subjectId,
        name: this._examName,
        eoExamClassList: this.classArray,
      };
      this.insertExamService.insertExam(body).subscribe((data: any) => {
        console.log('添加');
        console.log(body);
        console.log(data);

        if (data.message == "SUCCESS") {
          this._message.create('success', data.message);
          this.refreshData(true);
          this.reset();
        }

      });
    } else {
      this._message.create('error', '不能为空!!!');
    }
  }

  reset() {
    this._examName = null;
    this.department = null;
    this.major = null;
    this.class = null;
    this._startTime = null;
    this._endTime = null;
    this._duration = null;
    this._examPaper = null;
  }


  /*表格数据操作*/
  refreshData(reset = false) {
    if (reset) {
      this._current = 1;
    }

    this._loading = true;
    const selectedGender = this._filterGender.filter(item => item.value).map(item => item.name);
    this.insertExamService.getExam({
      'page': this._current,
      'size': this._pageSize,
      'subjectId': sessionStorage.getItem('selectedSubject'),
    }).subscribe((data: any) => {

      console.log(data);
      this._loading = false;
      this._total = data.data.total;
      this._dataSet = data.data.list;

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

  cancel() {
    // this.message.info('click cancel');
  }

  confirm(id) {
    console.log("删除！" + id);
    this.insertExamService.deleteExam(id).subscribe((data: any) => {
      // console.log(data)
      if (data.message == 'SUCCESS') {
        this._message.create('success', data.message);
        this.searchExam();
      } else {
        this._message.create('error', "失败");
      }
    });
  }


}


