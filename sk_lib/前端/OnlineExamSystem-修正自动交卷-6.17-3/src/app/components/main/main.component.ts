import {Component, OnInit} from '@angular/core';
import {CourseManageServerService} from '../../serve/information-manage/course-manage-server.service';
import {InsertExamService} from '../../serve/exam-design/insert-exam.service';
import {NzMessageService} from 'ng-zorro-antd';
import {Router} from '@angular/router';
import {MessageServiceService} from '../../serve/message-service.service';
import {ErrorTitleComponent} from "../title-manage/error-title/error-title.component";
import {ViewChild} from "@angular/core";
import {LoginServeService} from "../../serve/login-serve.service";
import {FormGroup, FormBuilder, Validators} from "@angular/forms";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  validateForm: FormGroup;

  root = false;
  admin = false;
  teacher = false;
  student = false;

  _userId;
  _newPassword;
  _subject;
  _exam;
  exams;
  subjectsStu;
  subjects;
  selectedSubject;
  isCollapsed = false;
  isVisibleMiddle = false;
  isVisiblePassword = false;

  count = 0;

  constructor(private courseManageServerService: CourseManageServerService,
              private insertExamService: InsertExamService,
              private router: Router,
              private fb: FormBuilder,
              private loginServeService: LoginServeService,
              private messageServiceService: MessageServiceService,
              private _message: NzMessageService,) {
  }

  ngOnInit() {
    this.validateForm = this.fb.group({
      _userId: ['', [Validators.required]],
      _newPassword: ['', [Validators.required]],
    });
    this._userId = sessionStorage.getItem('roleId');
    var status = sessionStorage.getItem('roleValue');
    if (status == '1') {
      this.root = true;
      this.admin = false;
      this.teacher = false;
      this.student = false;

    } else if (status == '2') {
      this.root = false;
      this.admin = true;
      this.teacher = false;
      this.student = false;
    } else if (status == '3') {
      this.root = false;
      this.admin = false;
      this.teacher = true;
      this.student = false;
      //教师获取科目
      this.courseManageServerService.getCourseFomTeacher({
        'page': 1,
        'size': 10,
      }).subscribe((data: any) => {

        console.log(data);
        if (data.data) {
          sessionStorage.setItem('teacherSubjects', data.data.list);
          this.subjects = data.data.list;
          if (data.data.list.length > 0) {
            this.selectedSubject = data.data.list[0].id;
            console.log("-----" + this.selectedSubject);
            sessionStorage.setItem('selectedSubject', this.selectedSubject);//把选择的科目保存到sessionStorage中
          }
          // sessionStorage.setItem('selectedSubject', this.selectedSubject);//把选择的科目保存到sessionStorage中
          // console.log("--------------------");
          // console.log(this.selectedSubject);
        }
      });
    } else {
      this.root = false;
      this.admin = false;
      this.teacher = false;
      this.student = true;
      //学生获取科目
      this.courseManageServerService.getSubjectFromStudent({
        'page': 1,
        'size': 10,
      }).subscribe((data: any) => {
        console.log(data);
        if (data.data) {
          console.log(data.data.list);
          this.subjects = data.data.list;
          if (data.data.list.length > 0) {
            this.selectedSubject = data.data.list[0].id;
            console.log("-----" + this.selectedSubject);
            sessionStorage.setItem('selectedSubject', this.selectedSubject);//把选择的科目保存到sessionStorage中
          }
        }
      });
    }

    this.messageServiceService.getMessageSendMe({
      // 'page': this._current,
      // 'size': this._pageSize
    }).subscribe((data: any) => {

      console.log('getMessageSendMe');
      console.log(data);
      // this._loading = false;
      // this._total = data.data.total;
      // this._dataSet = data.data.list;
      for (let entry of data.data.list) {
        if (entry.isRead == '0') {
          this.count = this.count + 1;
        }
      }

    });

  }

  isRoot() {
    return this.root;
  }

  isAdmin() {
    return this.admin;
  }

  isTeacher() {
    return this.teacher;
  }

  isStudent() {
    return this.student;
  }

  isRootAdmin() {
    return this.root || this.admin;
  }

  isTeacherStudent() {
    return this.teacher || this.student;
  }

  toggleCollapsed() {
    this.isCollapsed = !this.isCollapsed;
  }

  selectSubject(selectedSubject) {
    console.log(selectedSubject);
    sessionStorage.setItem('selectedSubject', selectedSubject);//把选择的科目保存到sessionStorage中
    // this.router.renavigate();
  }

  showModalMiddle = () => {
    this.isVisibleMiddle = true;
    this.insertExamService.getExamListFromStudent({
      'subjectId': sessionStorage.getItem('selectedSubject'),
    }).subscribe((data: any) => {

      console.log(data);
      if (data.data) {
        this.exams = data.data;
      }
    });

  };
  handleOkMiddle = (e) => {
    console.log('点击了确定');
    this.isVisibleMiddle = false;
    sessionStorage.setItem('EXAMID', JSON.stringify(this._exam));
    this.insertExamService.getExamFromStudent(this._exam, {
      'subjectId': sessionStorage.getItem('selectedSubject'),
    }).subscribe((data: any) => {

      console.log(data);
      this._message.create('success', data.message);
      if (data.message == 'SUCCESS') {
        sessionStorage.setItem('EXAMDATA', JSON.stringify(data.data.eoExamPaper.eoQuestionList));
        sessionStorage.setItem('EXAMANSWER', JSON.stringify(data.data));
        this.router.navigate(['exam']);
      } else {
        this._message.create('error', '不可进入考试!!!');
      }
    });
  };

  handleCancelMiddle = (e) => {
    console.log(e);
    this.isVisibleMiddle = false;
  };

  logout() {
    let param = [];
    this.loginServeService.logout(param, data => {
      // if (data.success) {
      console.log(data);
      this.router.navigate(['']);
      if (data.message == 'SUCCESS') {
        console.log("成功");
      } else {
        this._message.create('error', "失败");
      }
    });
  }

  changePassword() {
    this.isVisiblePassword = true;
  }

  handleOkPassword = (e) => {
    console.log('点击了确定');
    this.isVisiblePassword = false;

    const body = {
      id: sessionStorage.getItem('roleId'),
      password: this._newPassword
    };
    this.loginServeService.changePassword(body).subscribe((data: any) => {
      console.log('添加');
      if (data.message == 'SUCCESS') {
        this._message.create('success', data.message);
      } else {
        this._message.create('error', "失败");
      }
      console.log(body);
    });


  };

  handleCancelPassword = (e) => {
    console.log(e);
    this.isVisiblePassword = false;
  };

}
