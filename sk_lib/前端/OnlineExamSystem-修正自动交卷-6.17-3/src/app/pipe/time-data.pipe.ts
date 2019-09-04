import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'timeData'
})
export class TimeDataPipe implements PipeTransform {

  transform(value: any, args?: any): any {
    let hours = Math.floor(value / 3600) < 0 ? "0" : Math.floor(value / 3600);
    let minutes = Math.floor(value % 3600 / 60) < 0 ? "0" : Math.floor(value % 3600 / 60);
    let seconds = Math.floor(value % 3600 % 60);
    return hours + "小时" + minutes + "分钟" + seconds + "秒";
  }

}
