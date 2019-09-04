import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable()
export class TestService {

  constructor(private http: HttpClient) { }

  getTestSelectData(params) {
    return this.http.post('http://localhost:3000/api/test', params)
          .map(data => data[0]['data']['eoExamPaper']['eoQuestionList'])
          .catch(err => {throw err; });

  }

}
