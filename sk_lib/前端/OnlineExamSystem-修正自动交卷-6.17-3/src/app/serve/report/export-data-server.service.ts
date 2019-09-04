import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {HttpHeaders} from "@angular/common/http";
import {ResponseContentType} from "@angular/http";

@Injectable()
export class ExportDataServerService {

  constructor(private http: HttpClient) {
  }

  //导出题目
  exportTitle(param) {
    return this.http.get('/apis/api/teacher/file/export/question', {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
      params: param
    });
  }

  //导出成绩
  exportScore(param) {
    return this.http.get('/apis/api/teacher/file/export/score', {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
      params: param
    });
  }
}
