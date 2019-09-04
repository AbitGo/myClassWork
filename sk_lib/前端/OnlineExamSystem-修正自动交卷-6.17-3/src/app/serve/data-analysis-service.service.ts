import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {HttpHeaders} from "@angular/common/http";

@Injectable()
export class DataAnalysisServiceService {

  constructor(private http: HttpClient) {
  }

  //班级多次考试学生成绩分布
  showAnalysisFromClassStudentManyTest(params, callback) {
    this.http.post('/apis/api/teacher/analysis/1', params, {
      headers: new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8'),
      withCredentials: true,
    }).subscribe(data => {
      callback(data);
    })
  }

  //班级一次考试学生成绩分布
  showAnalysisFromClassStudentsTest(params, callback) {
    this.http.post('/apis/api/teacher/analysis/2', params, {
      headers: new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8'),
      withCredentials: true,
    }).subscribe(data => {
      callback(data);
    })
  }

  //一个学生多次考试成绩分布
  showAnalysisFromStudentManyTest(params, callback) {
    this.http.post('/apis/api/teacher/analysis/3', params, {
      headers: new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8'),
      withCredentials: true,
    }).subscribe(data => {
      callback(data);
    })
  }

  //一个学生一次考试知识点分析情况
  showAnalysisFromStudentKnowledgePointTest(params, callback) {
    this.http.post('/apis/api/teacher/analysis/4', params, {
      headers: new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8'),
      withCredentials: true,
    }).subscribe(data => {
      callback(data);
    })
  }

  //一个学生历史考试知识点得分情况
  showAnalysisFromStudentKnowledgePointHistoryTest(params, callback) {
    this.http.post('/apis/api/teacher/analysis/5', params, {
      headers: new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8'),
      withCredentials: true,
    }).subscribe(data => {
      callback(data);
    })
  }

  //一次考试一个班级知识点分布情况
  showAnalysisFromClassKnowledgePointTest(params, callback) {
    this.http.post('/apis/api/teacher/analysis/7', params, {
      headers: new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8'),
      withCredentials: true,
    }).subscribe(data => {
      callback(data);
    })
  }

  //多个个知识点历史得分情况，取错误率前10
  showAnalysisFromKnowledgePointHistoryResult(params, callback) {
    this.http.post('/apis/api/teacher/analysis/8', params, {
      headers: new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8'),
      withCredentials: true,
    }).subscribe(data => {
      callback(data);
    })
  }

  //多次考试整个班级分数情况
  showAnalysisFromClassManyTestResult(params, callback) {
    this.http.post('/apis/api/teacher/analysis/9', params, {
      headers: new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8'),
      withCredentials: true,
    }).subscribe(data => {
      callback(data);
    })
  }

  //一次考试所有题目情况
  showAnalysisFromTestAllTitle(params, callback) {
    this.http.post('/apis/api/teacher/analysis/10', params, {
      headers: new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8'),
      withCredentials: true,
    }).subscribe(data => {
      callback(data);
    })
  }

  //一个题目历史得分情况
  showAnalysisFromTitleHistoryResult(params, callback) {
    this.http.post('/apis/api/teacher/analysis/11', params, {
      headers: new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8'),
      withCredentials: true,
    }).subscribe(data => {
      callback(data);
    })
  }

  getDataAnalysisOption1() {
    let option1 = {
      title: {
        text: '成绩分布',
        left: 'left'
      },
      tooltip: {
        trigger: 'axis',
        axisPointer: {
          type: 'cross',
          crossStyle: {
            color: '#999'
          }
        }
      },
      toolbox: {
        feature: {
          dataView: {show: true, readOnly: false},
          magicType: {show: true, type: ['line', 'bar']},
          restore: {show: true},
          saveAsImage: {show: true}
        }
      },
      legend: {
        data: ['大二上', '大二下', '大三上']
      },
      xAxis: [
        {
          type: 'category',
          data: ['不及格', '及格', '中等', '良好', '优秀'],
          axisPointer: {
            type: 'shadow'
          }
        }
      ],
      yAxis: [
        {
          type: 'value',
          name: '分数',
          min: 0,
          max: 100,
          interval: 10,
          axisLabel: {
            formatter: '{value} 分'
          }
        },
        {
          type: 'value',
          name: '人数',
          min: 0,
          max: 25,
          interval: 5,
          axisLabel: {
            formatter: '{value} 人'
          }
        }
      ],
      series: [
        {
          name: '大二上',
          type: 'bar',
          data: [2.0, 4.9, 7.0, 23.2, 25.6,]
        },
        {
          name: '大二下',
          type: 'bar',
          data: [2.6, 5.9, 9.0, 26.4, 28.7,]
        },
        {
          name: '大三上',
          type: 'bar',
          yAxisIndex: 1,
          data: [2.0, 2.2, 3.3, 4.5, 6.3, 10.2,]
        }
      ]
    };
    return option1;
  }

  getDataAnalysisOption2() {
    let option2 = {
      title: {
        text: '对数轴示例',
        left: 'center'
      },
      tooltip: {
        trigger: 'item',
        formatter: '{a} <br/>{b} : {c}'
      },
      legend: {
        left: 'left',
        data: ['2的指数', '3的指数']
      },
      xAxis: {
        type: 'category',
        name: 'x',
        splitLine: {show: false},
        data: ['一', '二', '三', '四', '五', '六', '七', '八', '九']
      },
      grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
      },
      yAxis: {
        type: 'log',
        name: 'y'
      },
      series: [
        {
          name: '3的指数',
          type: 'line',
          data: [1, 33, 24, 27, 81, 247, 741, 2223, 6669]
        },
        {
          name: '2的指数',
          type: 'line',
          data: [1, 2, 4, 50, 16, 32, 64, 128, 256]
        },

      ]
    };
    return option2;
  }

  getDataAnalysisOption3() {
    let option3 = {
      title: {
        text: '班级一次考试学生成绩分布情况',
        subtext: '考试',
        x: 'center'
      },
      tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
      },
      legend: {
        orient: 'vertical',
        left: 'left',
        data: ['不及格', '及格', '中等', '良好', '优秀']
      },
      series: [
        {
          name: '访问来源',
          type: 'pie',
          radius: '55%',
          center: ['50%', '60%'],
          data: [
            {value: 10, name: '不及格'},
            {value: 40, name: '及格'},
            {value: 30, name: '中等'},
            {value: 20, name: '良好'},
            {value: 10, name: '优秀'}
          ],
          itemStyle: {
            emphasis: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }
      ]
    };
    return option3;
  }

  getDataAnalysisOption4() {
    let option4 = {
      title: {
        text: '班级科目均衡性'
      },
      tooltip: {},
      legend: {
        data: ['年级平均', '班级平均']
      },
      radar: {
        // shape: 'circle',
        name: {
          textStyle: {
            color: '#fff',
            backgroundColor: '#999',
            borderRadius: 3,
            padding: [3, 5]
          }
        },
        indicator: [
          {name: '语文', max: 100},
          {name: '数学', max: 100},
          {name: '英语', max: 100},
          {name: '物理', max: 100},
          {name: '化学', max: 100},
          {name: '政治', max: 100},
          {name: '历史', max: 100},
          {name: '地理', max: 100},
        ]
      },
      series: [{
        name: '年级平均 vs 班级平均',
        type: 'radar',
        // areaStyle: {normal: {}},
        data: [
          {
            value: [70, 68, 60, 75, 80, 81, 72, 91],
            name: '年级平均'
          },
          {
            value: [77, 88, 99, 79, 96, 85, 90, 93],
            name: '班级平均'
          }
        ]
      }]
    };
    return option4;
  }

}
