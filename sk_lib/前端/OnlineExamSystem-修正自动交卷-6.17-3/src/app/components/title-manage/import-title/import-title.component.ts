// tslint:disable
import {Component, OnInit} from '@angular/core';
import {NzMessageService, NzNotificationService} from "ng-zorro-antd";
import {FileUploader} from "ng2-file-upload";
import {HttpHeaders} from "@angular/common/http";
import {HttpClient} from "@angular/common/http";
import {RequestOptions} from "@angular/http";
import {Observable} from "rxjs";
import {HttpRequest} from "@angular/common/http";
import {HttpEventType} from "@angular/common/http";
import {HttpResponse} from "@angular/common/http";
import {KnowledageManageServiceService} from "../../../serve/title-manage/knowledage-manage-service.service";


@Component({
  selector: 'app-import-title',
  templateUrl: './import-title.component.html',
  styleUrls: ['./import-title.component.css']
})
export class ImportTitleComponent implements OnInit {

  eoKnowleagePoint;
  eoKnowleagePoints;

  imgText;

  status;
  show = false;

  constructor(private http: HttpClient,
              private knowledageManageServiceService: KnowledageManageServiceService,
              private _notification: NzNotificationService) {
  }

  ngOnInit() {
    this.status = sessionStorage.getItem('roleValue');
    if (this.status == 3) {
      this.show = true;
    } else {
      this.show = false;
    }

    this.knowledageManageServiceService.getKnowledage({
      'subjectId': sessionStorage.getItem('selectedSubject')
    }).subscribe((data: any) => {
      console.log(data);
      if (data.data) {
        console.log(data.data.list);
        this.eoKnowleagePoints = data.data.list;
      }
    });
  }

  upload(file: HTMLInputElement) {
    console.log("1111111111111");

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
    formData.append('subjectId', sessionStorage.getItem('selectedSubject'));
    formData.append('knowleageId', this.eoKnowleagePoint);
    // }

    // const token = localStorage.getItem('token');
    // let headers: HttpHeaders = new HttpHeaders();
    // headers = headers
    //   .set('Cache-Control', 'no-cache')
    //   .set('Authorization', 'Bearer ' + token);
    // // if (file.value.length === 0) {
    // //   console.log("return")
    // //   return;
    // // }
    // const files: FileList = file.files;
    // const fileLength = files.length;
    // const formData: FormData = new FormData();
    // debugger
    // for (let index = 0; index < fileLength; index++) {
    //   const singleFile = files.item(index);
    //   // files 这个名字和spring mvc controller参数的名字要对应
    //   formData.append('excel', singleFile);
    //   formData.append('subjectId', sessionStorage.getItem('selectedSubject'));
    //   formData.append('knowleageId', this.eoKnowleagePoint);
    // }

    const url = '/apis/api/file/import';
    const req = new HttpRequest('POST', url, formData, {
      reportProgress: true, headers: headers,
      withCredentials: true,
    });

    this.http.request(req).subscribe(event => {
      console.log(event);
    });

  }

  uploadImg(file: HTMLInputElement) {
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
    for (let index = 0; index < fileLength; index++) {
      const singleFile = files.item(index);
      // files 这个名字和spring mvc controller参数的名字要对应
      formData.append('file', singleFile);
    }

    const url = '/apis/api/fileupload/ocr';
    const req = new HttpRequest('POST', url, formData, {
      reportProgress: true, headers: headers,
      withCredentials: true,
    });

    this.http.request(req).subscribe(event => {
        console.log("Event call ", event);
        if (event.type === HttpEventType.Response) {
          let str = event.body;
          console.log(str["data"]);
          this.imgText = str["data"]
          // this.createBasicNotification(str["data"]);
          // console.log("response received...", event.body);
        }
      },
      response => {
        console.log("POST call in error", response);
      });

  }

  isTeacher() {
    if (this.status == 3) {
      return true;
    } else {
      return false;
    }
  }

  //下载模板
  download() {
    // this.studentManageServerService.downloadStudentFile({
    // }).subscribe((data: any) => {
    //
    //   console.log(data);
    // });
    window.open('/apis/api/file/download');
  }

  createBasicNotification(str) {
    this._notification.blank('题目内容', str);
  }
}
