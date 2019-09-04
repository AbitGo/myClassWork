import {Component, OnInit, Input} from '@angular/core';
import {Paper} from "./paper";
import {DesignPaperService} from "../../../serve/exam-design/design-paper.service";
import {NzMessageService} from "ng-zorro-antd";

@Component({
  selector: 'app-paper-detail',
  templateUrl: './paper-detail.component.html',
  styleUrls: ['./paper-detail.component.css']
})
export class PaperDetailComponent implements OnInit {

  @Input() paper: Paper;

  result;
  isVisible = false;

  constructor(private designPaperService: DesignPaperService,
              private _message: NzMessageService,) {
  }

  ngOnInit() {
  }

  cancel() {
    // this.message.info('click cancel');
  }

  confirm(id) {
    console.log("删除！" + id);
    this.designPaperService.deleteExampaper(id).subscribe((data: any) => {
      // console.log(data)
      if (data.message == 'SUCCESS') {
        this._message.create('success', data.message);
      }  else {
        this._message.create('error', "失败");
      }
    });
  }

  show(paper) {
    this.isVisible = true;
    let str = "";
    let result = [];
    console.log(paper);
    console.log("------------->");

    // for (let i of paper.eoQuestionList) {
    //   console.log(i);
    //   str = "";
    //   str = str + i.id + " " + i.typeId + " " + i.eoKnowleagePointName + " " + i.realTitle + " " + i.selectOptions + " " + i.answoer + " ";
    //   result.push(i);
    //
    // }
    this.result = paper.eoQuestionList;
  }

  handleCancel() {
    this.isVisible = false;
    // this.router.navigate(['main']);
  }

  handleOk() {
    this.isVisible = false;
    // this.router.navigate(['main']);
  }

  optionChange(i) {
    let str = 'A';
    let code = str.charCodeAt(0);
    let str2 = String.fromCharCode(code + i);
    return str2;
  }

}
