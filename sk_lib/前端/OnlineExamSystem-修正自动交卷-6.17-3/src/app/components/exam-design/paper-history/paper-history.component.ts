import {Component, OnInit} from '@angular/core';
import {Paper} from "../paper-detail/paper";
import {DesignPaperService} from "../../../serve/exam-design/design-paper.service";

const PAPERS: Paper[] = [
  {id: 1, name: '2018年国家公务员考试', content: "《中华人民共和国公务员法》规定录用担任主任科员以下及其他相当职务层次的非领导职务公务员，采取公开考试、严格考察、平等竞争、择优录取的办法。"},
  {
    id: 2,
    name: '2018年全国研究生入学考试',
    content: "全国硕士研究生统一招生考试（简称考研）指教育主管部门或招生机构为选拔研究生而组织的相关考试的总称，由国家考试主管部门和招生单位组织的初试和复试组成。"
  },
  {
    id: 3,
    name: '2018年工商管理硕士考试',
    content: "MBA即工商管理硕士（Master of Business Administration，简称MBA）是商业界普遍认为晋身管理阶层的一块踏脚石。现时不少学校为了开源，都与世界知名的大学商学院合作，销售他们的工商管理硕士课程。通常报考MBA都需要有GMAT资历。"
  },
  {
    id: 4,
    name: '2018年普通高等学校招生全国统一考试',
    content: "普通高等学校招生全国统一考试（The National College Entrance Examination）简称高考，是中华人民共和国（不包括香港、澳门、台湾）合格的高中毕业生或具有同等学力的考生参加的高等学校招生选拔性考试。"
  },
  {
    id: 5,
    name: '2018年全国计算机技术与软件专业技术资格考试',
    content: "由国家人事部和信息产业部领导下的国家级考试，其目的是，科学、公正地对全国计算机与软件专业技术人员进行职业资格、专业技术资格认定和专业技术水平测试。"
  },
];

@Component({
  selector: 'app-paper-history',
  templateUrl: './paper-history.component.html',
  styleUrls: ['./paper-history.component.css']
})
export class PaperHistoryComponent implements OnInit {

  // papers = PAPERS;
  papers = [];

  constructor(private designPaperService: DesignPaperService,) {
  }

  ngOnInit() {
    this.designPaperService.getExampaper({
      // 'page': 1,
      // 'size': 20,
      'subjectId': sessionStorage.getItem("selectedSubject"),
    }).subscribe((data: any) => {

      console.log(data)
      this.papers = data.data.list;

    });
  }

}
