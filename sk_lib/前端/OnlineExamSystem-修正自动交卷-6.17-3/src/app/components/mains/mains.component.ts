import {Component, OnInit} from '@angular/core';
import {NzNotificationService, NzMessageService, NzModalService} from 'ng-zorro-antd';
import {DepartmentManageServerService} from '../../serve/information-manage/department-manage-server.service';
import {MajorManageServerService} from '../../serve/information-manage/major-manage-server.service';
import {StudentManageServerService} from '../../serve/information-manage/student-manage-server.service';
import {ClassManageServerService} from '../../serve/information-manage/class-manage-server.service';
import {MessageServiceService} from '../../serve/message-service.service';
import {interval} from 'rxjs/observable/interval';

@Component({
  selector: 'app-mains',
  templateUrl: './mains.component.html',
  styleUrls: ['./mains.component.css']
})
export class MainsComponent implements OnInit {

  department;
  departments;
  major;
  majors;
  class;
  classes;
  student;
  students;
  content;
  message;
  _class2;
  classes2;

  status;

  _root = false;
  _admin = false;
  _teacher = false;
  _student = false;

  serachShow = true;
  isVisible = false;
  /*tab添加修改窗口*/
  _current = 1;
  _pageSize = 10;
  _total = 1;
  _dataSet = [];
  _loading = true;
  _sortValue = null;

  examTime: any;
  time: any;

  constructor(private _notification: NzNotificationService,
              private departmentManageServerService: DepartmentManageServerService,
              private majorManageServerService: MajorManageServerService,
              private studentManageServerService: StudentManageServerService,
              private messageServiceService: MessageServiceService,
              private _message: NzMessageService,
              private confirmServ: NzModalService,
              private classManageServerService: ClassManageServerService,) {
  }


  ngOnInit() {

    this.time = 1523088834000;
    // this.examTime = this.resetTime(7200);
    // this.examTime = setInterval(() => {
    //   this.time = this.time - 10000;
    //   console.log(this.time)
    // }, 1000);


    this.status = sessionStorage.getItem('roleValue');

    if (this.status == '1') {
      this._root = true;
      this._admin = false;
      this._teacher = false;
      this._student = false;

    } else if (this.status == '2') {
      this._root = false;
      this._admin = true;
      this._teacher = false;
      this._student = false;
    } else if (this.status == '3') {
      this._root = false;
      this._admin = false;
      this._teacher = true;
      this._student = false;
    } else {
      this._root = false;
      this._admin = false;
      this._teacher = false;
      this._student = true;
    }

    if (this.status == '4') {
      this.createBasicNotification();
    }
    this.departmentManageServerService.getDepartmentFromTeacher().subscribe((data: any) => {

      console.log(data);
      this.departments = data.data;
    });

    this.refrashData(true);

  }

  refrashData(reset = false) {
    if (reset) {
      this._current = 1;
    }
    this._loading = true;
    // var status = sessionStorage.getItem("roleValue");
    if (this.status == '3') {
      this.messageServiceService.getMessage2SBfromMe({
        'page': this._current,
        'size': this._pageSize
      }).subscribe((data: any) => {
        console.log('getMessageSendMe');
        console.log(data);
        this._loading = false;
        this._total = data.data.total;
        this._dataSet = data.data.list;

      });
    }
    if (this.status == '4') {
      this.messageServiceService.getMessageSendMe({
        'page': this._current,
        'size': this._pageSize
      }).subscribe((data: any) => {
        console.log('getMessageSendMe');
        console.log(data);
        this._loading = false;
        this._total = data.data.total;
        this._dataSet = data.data.list;

      });
    }

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
      this.classes2 = data.data.list;
    });
  }

  //教师根据班级获得学生
  queryStudent(cls) {
    this.studentManageServerService.getStudentFromTeacher({
      'classId': cls,
    }).subscribe((data: any) => {

      console.log(data);
      this.students = data.data;
    });
  }

  send() {
    const body = {
      content: this.content,
      toUserId: this.student
    };
    this.messageServiceService.sendMessage2SB(body).subscribe((data: any) => {
      console.log('推送');
      console.log(body);
      this._message.create('success', data.message);
    });
    this.refrashData(true);
  }

  sendClass() {
    let str = [];
    for (let i of this._class2) {
      str.push({'id': i});
    }
    const body = {
      content: this.content,
      schoolClasses: str
    };
    this.messageServiceService.sendSelectClass(body).subscribe((data: any) => {
      console.log('推送给班级');
      console.log(body);
      this._message.create('success', data.message);
    });
    this.refrashData(true);
  }

  // showModal = () => {
  //   this.isVisible = true;
  // }

  showMessage(data) {
    // this.isVisible = true;
    // this.message = this.content;
    if (this.status == '4') {
      const body = {
        id: data.id,
      };
      this.messageServiceService.updateMessageStatus(data.id, body).subscribe((data: any) => {
        console.log('已阅');
      });
      const modal = this.confirmServ.success({
        title: '来自' + data.fromUserName + '老师的消息',
        content: '内容:' + data.content
      });
    }
    if (this.status == '3') {
      const modal = this.confirmServ.success({
        title: '推送给' + data.toUserName + '的消息',
        content: '内容:' + data.content
      });
    }
    this.refrashData(true);
  }

  handleOk = (e) => {
    console.log('点击了确定');
    this.isVisible = false;
  };

  handleCancel = (e) => {
    console.log(e);
    this.isVisible = false;
  };

  createBasicNotification = () => {
    // this._notification.blank('这是标题', '我不会自动关闭，我不会自动关闭，我不会自动关闭，我不会自动关闭，我不会自动关闭，我不会自动关闭，我不会自动关闭', {nzDuration: 0});
  };

  isRoot() {
    return this._root;
  }

  isAdmin() {
    return this._admin;
  }

  isTeacher() {
    return this._teacher;
  }

  isStudent() {
    return this._student;
  }

  isRootAdmin() {
    return this._root || this._admin;
  }

  isTeacherStudent() {
    return this._teacher || this._student;
  }

  resetTime(time: number) {
    function countdown() {
      var s = time % 60;
      var m = Math.floor((time / 60)) % 60;
      var msg = `${(m < 10 ? '0' : '') + m}分钟${(s < 10 ? '0' : '') + s}秒`;
      if (--time > 0) {
        setTimeout(countdown, 1000);
      } else {
        // 做结束的事
      }
    }

    countdown();
  }

}
