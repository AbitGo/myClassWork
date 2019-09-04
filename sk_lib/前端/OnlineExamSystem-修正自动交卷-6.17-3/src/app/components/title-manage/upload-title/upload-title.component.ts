import {Component, OnInit, ElementRef, ViewChild} from '@angular/core';
import {KnowledageManageServiceService} from '../../../serve/title-manage/knowledage-manage-service.service';
import {TitleManageServerService} from '../../../serve/title-manage/title-manage-server.service';
import {NzMessageService} from 'ng-zorro-antd';
import {Router} from '@angular/router';

@Component({
  selector: 'app-upload-title',
  templateUrl: './upload-title.component.html',
  styleUrls: ['./upload-title.component.css']
})
export class UploadTitleComponent implements OnInit {
  @ViewChild('equation') equation: ElementRef;

  eoKnowleagePoint;
  title_type;
  degree;
  title_text;
  answer_text;
  title_nickname;
  title_option;

  degree_value;
  eoKnowleagePoints;
  title_types;

  model_text;
  title = '$$ x_i $$';

  constructor(private knowledageManageServiceService: KnowledageManageServiceService,
              private _message: NzMessageService,
              private router: Router,
              private titleManageServerService: TitleManageServerService) {

  }

  ngOnInit() {
    this.title_types = [{value: '1', label: '单选题'},
      {value: '2', label: '多选题'},
      {value: '3', label: '判断题'},
      {value: '4', label: '填空题'},
      {value: '5', label: '完型填空'},
    ];

    this.searchKnowledage();
  }

  searchKnowledage() {

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

  // contentChange($event) {
  //   console.log("contentChange：", $event);
  // }
  //
  // editorReady($event) {
  //   console.log("ready：", $event);
  // }
  //
  // test(text: string): void {
  //   console.log(text);
  // }
  submit() {
    var status = sessionStorage.getItem('roleValue');
    // debugger
    if (!this.title_nickname || !this.eoKnowleagePoint || !this.degree || !this.title_text || !this.answer_text || !this.title_type) {
      this._message.create('error', '不能为空！！！');
    } else {
      if (this.title_type == 3 || this.title_type == 4) {
        if (this.title_option) {
          this._message.create('error', '选项必须为空！！！');
        }
      } else {
        if (!this.title_option) {
          this._message.create('error', '选项不能为空！！！');
        } else {
          const body = {
            name: this.title_nickname,
            knowleagePointId: this.eoKnowleagePoint,
            degree: this.degree,
            title: this.title_text,
            selectOption: this.title_option,
            answoer: this.answer_text,
            typeId: this.title_type,
            subjectId: sessionStorage.getItem('selectedSubject')
          };
          if (status == '3') {
            this.titleManageServerService.addTitle(body).subscribe((data: any) => {
              if (data.message == 'SUCCESS') {
                this.title_nickname = '';
                this.eoKnowleagePoint = '';
                this.degree = '';
                this.title_text = '';
                this.title_option = '';
                this.answer_text = '';
                this.title_type = '';
                this._message.create('success', '添加成功');
                this.router.navigate(['./main/upload-title']);
                this.reset();
              } else {
                this._message.create('error', '添加失败');
              }
              console.log(data);
              console.log('添加');
              console.log(body);
            });
          } else {
            this.titleManageServerService.addTitleFromStudent(body).subscribe((data: any) => {
              if (data.message == 'SUCCESS') {
                this.title_nickname = '';
                this.eoKnowleagePoint = '';
                this.degree = '';
                this.title_text = '';
                this.title_option = '';
                this.answer_text = '';
                this.title_type = '';
                this._message.create('success', '添加成功');
                this.router.navigate(['./main/upload-title']);
                this.reset();
              } else {
                this._message.create('error', '添加失败');
              }
              console.log(data);
              console.log('添加');
              console.log(body);
            });
          }
        }
      }


    }

  }

  reset() {
    this.eoKnowleagePoint = null;
    this.title_type = null;
    this.degree = null;
    this.title_nickname = null;
    this.title_text = null;
    this.title_option = null;
    this.answer_text = "";
  }

  get_degree_value(degree) {
    if (degree <= 2) {
      this.degree_value = '简单';
      this.degree = '0';
    } else if (degree > 2 && this.degree <= 3.5) {
      this.degree_value = '中等';
      this.degree = '1';
    } else {
      this.degree_value = '困难';
      this.degree = '2';
    }
  }

  // // 监听onEditorKeyup事件
  // keyupHandler(event) {
  //   console.log('编辑器的内容：', this.model_text);
  // }

}
