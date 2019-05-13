import { Pipe, PipeTransform } from '@angular/core';
import { DateService } from '../shared/util/date/services/date.service';

@Pipe({
  name: 'dateFormat'
})
export class DateFormatPipe implements PipeTransform {

  constructor(private dateService: DateService) { }

  transform(value: any, args?: any): any {
    if (!value) return null;
    else
      return this.dateService.formatDate(value);
  }
}
