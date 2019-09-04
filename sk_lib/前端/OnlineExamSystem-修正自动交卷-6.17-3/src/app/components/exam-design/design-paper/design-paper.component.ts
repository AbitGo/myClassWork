import {Component, OnInit} from '@angular/core';
import {KnowledageManageServiceService} from '../../../serve/title-manage/knowledage-manage-service.service';
import {DesignPaperService} from '../../../serve/exam-design/design-paper.service';
import {CreatePaperInput} from './create-paper-input-model';
import {NzMessageService} from 'ng-zorro-antd';


@Component({
  selector: 'app-design-paper',
  templateUrl: './design-paper.component.html',
  styleUrls: ['./design-paper.component.css']
})
export class DesignPaperComponent implements OnInit {

  public createPaperInput: CreatePaperInput;

  degree;
  knowledage;
  degrees;
  knowledages;
  title_nickname;

  knowledageArray;

  radioNum;
  radioGrade;
  selectNum;
  selectGrade;
  judgeNum;
  judgeGrade;
  fillNum;
  fillGrade;
  // multipleNum;
  // multipleGrade;

  constructor(private knowledageManageServerService: KnowledageManageServiceService,
              private _message: NzMessageService,
              private designPaperService: DesignPaperService) {
  }

  ngOnInit() {
    this.degrees = [
      {label: '1星难度', value: '0.1'},
      {label: '2星难度', value: '0.2'},
      {label: '3星难度', value: '0.3'},
      {label: '4星难度', value: '0.4'},
      {label: '5星难度', value: '0.5'},
      {label: '6星难度', value: '0.6'},
      {label: '7星难度', value: '0.7'},
      {label: '8星难度', value: '0.8'},
      {label: '9星难度', value: '0.9'},
      {label: '10星难度', value: '1.0'},
      {value: 'disabled', label: 'Disabled', disabled: true}];

    this.knowledageManageServerService.getKnowledage({
      'subjectId': sessionStorage.getItem('selectedSubject')
    }).subscribe((data: any) => {
      console.log(data);
      if (data.data) {
        console.log(data.data.list);
        this.knowledages = data.data.list;
      }
    });

  }

  submit() {
    let str = [];
    for (let i of this.knowledage) {
      str.push({'id': i});
    }
    this.knowledageArray = str;
    const queTypeAndNumbers = [
      {
        'number': this.radioNum,
        'grade': this.radioGrade,
        'type': 1
      },
      {
        'number': this.selectNum,
        'grade': this.selectGrade,
        'type': 2
      },
      {
        'number': this.judgeNum,
        'grade': this.judgeGrade,
        'type': 3
      },
      {
        'number': this.fillNum,
        'grade': this.fillGrade,
        'type': 4
      },
      {
        'number': 0,
        'grade': 0,
        'type': 5
      }
    ];
    this.createPaperInput = new CreatePaperInput();
    this.createPaperInput.degree = this.degree;
    this.createPaperInput.knowleagePoints = this.knowledageArray;
    this.createPaperInput.queTypeAndNumbers = queTypeAndNumbers;

    let subjectId = sessionStorage.getItem('selectedSubject');
    if (subjectId) {
      const body = {
        createPaperInput: this.createPaperInput,
        subjectId: subjectId,
        name: this.title_nickname,
      };
      this.designPaperService.addExampaper(body).subscribe((data: any) => {
        console.log('添加');
        console.log(body);
        console.log(data);
        this._message.create('success', data.message);
        if (data.message == "SUCCESS") {
          this.reset();
        }
      });
    } else {
      this._message.create('error', '科目不能为空!!!');
    }

  }

  //重置数据
  reset() {
    this.title_nickname = null;
    this.degree = null;
    this.knowledage = null;
    this.radioNum = null;
    this.radioGrade = null;
    this.selectNum = null;
    this.selectGrade = null;
    this.judgeNum = null;
    this.judgeGrade = null;
    this.fillNum = null;
    this.fillGrade = null;
    // this.multipleNum = null;
    // this.multipleGrade = null;
  }

}
