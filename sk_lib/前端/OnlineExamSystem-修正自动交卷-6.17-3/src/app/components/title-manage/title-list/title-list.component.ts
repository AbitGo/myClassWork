import {Component, OnInit} from '@angular/core';
import {KatexOptions} from 'ng-katex/src/ng-katex.options';
import {TitleManageServerService} from '../../../serve/title-manage/title-manage-server.service';
import {DesignPaperService} from '../../../serve/exam-design/design-paper.service';
import {KnowledageManageServiceService} from '../../../serve/title-manage/knowledage-manage-service.service';
import {NzMessageService, NzModalService} from 'ng-zorro-antd';

@Component({
  selector: 'app-title-list',
  templateUrl: './title-list.component.html',
  styleUrls: ['./title-list.component.css']

})
export class TitleListComponent implements OnInit {

  // sum: string;
  // result: any;
  // fractionString: any;

  _titleType;
  titleTypes;
  _knowledage;
  knowledages;

  adultId;

  serachShow = false;
  isVisible = false;

  answer: any;

  _current = 1;
  _pageSize = 10;
  _total = 1;
  _dataSet = [];
  _loading = true;
  _sortValue = null;

  constructor(private titleManageServerService: TitleManageServerService,
              private _message: NzMessageService,
              private confirmServ: NzModalService,
              private knowledageManageServerService: KnowledageManageServiceService) {
  }

  ngOnInit() {
    this.titleTypes = [{value: '1', label: '单选题'},
      {value: '2', label: '多选题'},
      {value: '3', label: '判断题'},
      {value: '4', label: '填空题'},
      {value: '5', label: '完型填空'},
    ];

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

  queryQuestion() {
    this.refreshData(true)
  }

  //表格数据操作
  refreshData(reset = false) {
    if (reset) {
      this._current = 1;
    }
    this._loading = true;
    this.titleManageServerService.getTitle({
      'page': this._current,
      'size': this._pageSize,
      'subjectId': sessionStorage.getItem('selectedSubject'),
      'typeId': this._titleType == undefined ? "" : this._titleType,
      'knowleagePointId': this._knowledage == undefined ? "" : this._knowledage
    }).subscribe((data: any) => {
      console.log(data);
      if (data.data) {
        console.log(data.data.list);
        console.log(data);
        this._loading = false;
        this._total = data.data.total;
        this._dataSet = data.data.list;
      }
    });
    this.serachShow = true;
  }

  showMessage(data) {
    // this.isVisible = true;
    // this.message = this.content;
    const modal = this.confirmServ.success({
      title: '题目' + data.realTitle,
      content: '选项:' + data.selectOptions + '\n 答案：' + data.answoer,
    });
    this.adultId = data.id;
  }

  handleOk = (e) => {
    console.log('点击了确定');
    this.titleManageServerService.adultTitle({
      'id': this.adultId,
      'isAdult': 1
    }, this.adultId).subscribe((data: any) => {
      console.log(data);
      if (data.data) {
        console.log(data.data.list);
        this._message.create('success', data.message);
      }
    });
    this.isVisible = false;
    this.queryQuestion();
  };

  handleCancel = (e) => {
    console.log(e);
    this.isVisible = false;
  };

  /*删除提醒操作*/
  cancel = function () {
    this.alertTab = false;
  };

  confirm = function (id) {
    /*删除数据请求*/
    console.log(id);
    this.titleManageServerService.deleteTitle(id).subscribe((data: any) => {
      // console.log(data)
      this._message.create('success', data.message);
      this.queryQuestion();
    });

    // this.refreshData(true);
  };


  // value: string = '这是一个数学公式&\sum_{i=1}^nx_i&求解';
  // title: string = 'ng-katex';
  // url: string = 'https://github.com/garciparedes/ng-katex';
  // equation: string = 'c = \\pm\\sqrt{a^2 + b^2}';
  // options: KatexOptions = {
  //   displayMode: true,
  // };

}
