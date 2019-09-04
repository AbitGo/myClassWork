import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {HttpHeaders} from "@angular/common/http";

@Injectable()
export class MessageServiceService {

  constructor(private http: HttpClient) {
  }

  //发送给某人
  sendMessage2SB(body) {
    return this.http.post('/apis/api/message', body, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=UTF-8'),
      withCredentials: true,
    });
  }

  //删除记录
  removeMessage(id) {
    return this.http.delete("/apis/api/message/" + id, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
    });
  }

  //修改已读状态
  updateMessageStatus(id, body) {
    return this.http.put('/apis/api/message/read/' + id, body, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=UTF-8'),
      withCredentials: true,
    });
  }

  //记录个人
  getMessageNum(param) {
    return this.http.get('/apis/api/message/countsendme', {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
      params: param
    });
  }

  //我发送给某人的列表
  getMessage2SBfromMe(param) {
    return this.http.get('/apis/api/message/isSend', {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
      params: param
    });
  }

  //发送给我的列表
  getMessageSendMe(param) {
    return this.http.get('/apis/api/message/sendme', {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
      params: param
    });
  }

  //列出没有阅读的消息
  getMessageSendMeNotRead(param) {
    return this.http.get('/apis/api/message/sendmenotread', {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
      params: param
    });
  }

  //消息详细信息
  getMessageDetail(id) {
    return this.http.get('/apis/api/message/' + id, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=utf-8'),
      withCredentials: true,
    });
  }


  //发送给指定班级
  sendSelectClass(body) {
    return this.http.post('/apis/api/message/sendSelectClass', body, {
      headers: new HttpHeaders().set('Content-Type', 'application/json;charset=UTF-8'),
      withCredentials: true,
    });
  }


}
