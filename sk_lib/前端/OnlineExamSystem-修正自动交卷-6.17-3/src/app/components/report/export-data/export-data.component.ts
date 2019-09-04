import {Component, OnInit} from '@angular/core';
import {InsertExamService} from '../../../serve/exam-design/insert-exam.service';
import {ExportDataServerService} from '../../../serve/report/export-data-server.service';
import {NzMessageService} from 'ng-zorro-antd';

@Component({
  selector: 'app-export-data',
  templateUrl: './export-data.component.html',
  styleUrls: ['./export-data.component.css']
})
export class ExportDataComponent implements OnInit {

  _exam;
  exams;

  constructor(private insertExamService: InsertExamService,
              private _message: NzMessageService,
              private exportDataServerService: ExportDataServerService) {
  }

  ngOnInit() {
    this.insertExamService.getExam({
      'subjectId': sessionStorage.getItem('selectedSubject'),
    }).subscribe((data: any) => {

      console.log(data);
      this.exams = data.data.list;

    });
  }

  exportTitle() {
    window.open('/apis/api/file/export/question?subjectId=' + sessionStorage.getItem('selectedSubject') + '&teacherId=' + sessionStorage.getItem('roleId'));

    // this.exportDataServerService.exportTitle({
    //   'subjectId': sessionStorage.getItem("selectedSubject"),
    //   'teacherId': sessionStorage.getItem('roleId'),
    // }).subscribe((data: any) => {
    //
    //   console.log(data)
    //   this._message.create("success", data.message);
    //
    // });

  }

  exportScore() {
    window.open('/apis/api/file/export/score?examId=' + this._exam + '&teacherId=' + sessionStorage.getItem('roleId'));
    // this.exportDataServerService.exportScore({
    //   'examId': this._exam,
    //   'teacherId': sessionStorage.getItem('roleId'),
    // }).subscribe((data: any) => {
    //
    //   console.log(data)
    //   this._message.create("success", data.message);
    //
    // });

  }

}
