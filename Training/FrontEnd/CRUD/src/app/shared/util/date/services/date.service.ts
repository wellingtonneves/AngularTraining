import { Injectable } from '@angular/core';
import { DateConfiguration } from '../model/DateConfiguration.vm';
import * as moment from 'moment';

@Injectable({
  providedIn: 'root'
})
export class DateService {

  private _config: DateConfiguration;

    public config(): DateConfiguration {
        this._config = DateConfiguration.ToBrazil();
        return this._config;
    }

    public default(): DateConfiguration {
        this._config = DateConfiguration.ToEnglish();
        return this._config;
    }

    constructor() { }

    public formatDate(date?: Date): string {
        return (date) ? moment(date).format(this._config.DateFormat) : null;
    }

    public changeLanguage(option: number): void {

    }

    public isValidTime(value: string): boolean {
        if (!value) return false;

        const timeTokens = value.split(':');
        const data = new Date(1970, 0, 1, parseInt(timeTokens[0]), parseInt(timeTokens[1])
        )
        const momento = moment(data).isValid();
        return momento;
    }
}
