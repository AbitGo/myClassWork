import {Component, OnInit} from '@angular/core';
import {ErrorTitleService} from '../../../serve/title-manage/error-title.service';
import {Validators, FormBuilder, FormGroup} from '@angular/forms';
import {KnowledageManageServiceService} from '../../../serve/title-manage/knowledage-manage-service.service';
import {Router} from '@angular/router';
import {TitleManageServerService} from "../../../serve/title-manage/title-manage-server.service";

@Component({
  selector: 'app-error-title',
  templateUrl: './error-title.component.html',
  styleUrls: ['./error-title.component.css']
})
export class ErrorTitleComponent implements OnInit {

  validateForm: FormGroup;

  titleType;
  titleTypes;
  knowledgePoint;
  knowledgePoints;

  errorTitle;
  errorOption;

  isVisibleError = false;
  _current = 1;
  _pageSize = 10;
  _total = 1;
  _dataSet = [];
  _loading = true;
  _sortValue = null;

  isVisible = false;

  constructor(private errorTitleService: ErrorTitleService,
              private fb: FormBuilder,
              private titleManageServerService: TitleManageServerService,
              private router: Router,
              private knowledageManageServerService: KnowledageManageServiceService,) {
  }

  ngOnInit() {
    // this.validateForm = this.fb.group({
    //
    //   titleType: ['', [Validators.required]],
    //   knowledgePoint: ['', [Validators.required]]
    // });

    // this.titleTypes = [{value: '1', label: '单选题'},
    //   {value: '2', label: '多选题'},
    //   {value: '3', label: '判断题'},
    //   {value: '4', label: '填空题'},
    //   {value: '5', label: '完型填空'},
    // ];

    // this.knowledageManageServerService.getKnowledage({
    //   'subjectId': sessionStorage.getItem("selectedSubject")
    // }).subscribe((data: any) => {
    //   console.log(data)
    //   if (data.data) {
    //     console.log(data.data.list)
    //     this.knowledages = data.data.list;
    //   }
    // });
    this.refreshData(true);
  }

  confirm(id) {
    this.errorTitleService.removeErrorQuestion(id).subscribe((data: any) => {
      // console.log(data);
      if (data.message == 'SUCCESS') {
        this.refreshData(true);
      }
    });
  }

  show(id) {
   console.log(id);
    this.titleManageServerService.getTitleById({
      'id':id
    },id).subscribe((data: any) => {
      if (data.message=="SUCCESS"){
        console.log(data);
        this.errorTitle = data.data.realTitle;
        this.errorOption = data.data.selectOptions;
        console.log(this.errorOption);
        this.isVisibleError = true;
      }

    });
  }

  practice(data) {
    console.log('111111111111');

    this.errorTitleService.insertErrorTitlePractice({
      'typeId': data.typeId,
      'teacherId': data.teacherId,
      'subjectId': data.subjectId,
      'knowleagePointId': data.knowleagePointId,
    }).subscribe((data: any) => {

      console.log(data);
      // sessionStorage.setItem('PRACTICE', data.data);
      sessionStorage.setItem('PRACTICE', JSON.stringify(data.data.eoQuestionList));
      sessionStorage.setItem('PRACTICEID', data.data.id);
      if (data.message == 'SUCCESS') {
        this.router.navigate(['title-practice']);
      }
    });

    // sessionStorage.setItem('PRACTICE', data);
    // this.router.navigate(['title-practice']);
  }

  operateData(data) {
    this.isVisible = true;
  }

  sort() {
  }

  refreshData(reset = false) {
    if (reset) {
      this._current = 1;
    }
    this.errorTitleService.getErrorQuestion({
      'page': this._current,
      'size': this._pageSize,
      'subjectId': sessionStorage.getItem('selectedSubject')
    }).subscribe((data: any) => {

      console.log(data);
      this._loading = false;
      this._total = data.data.total;
      this._dataSet = data.data.list;

    });
  }

  //关闭窗口
  handleCancel() {
    this.isVisible = false;
  }

  handleCancelError() {
    this.isVisibleError = false;
    // this.router.navigate(['main']);
  }

  handleOkError() {
    this.isVisibleError = false;
    // this.router.navigate(['main']);
  }

  optionChange(i) {
    let str = 'A';
    let code = str.charCodeAt(0);
    let str2 = String.fromCharCode(code + i);
    return str2;
  }

}
