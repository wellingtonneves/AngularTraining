export class DateConfiguration {
    public Language: string;
    public DateFormat: string;
    public Locale: string;

   public static ToBrazil(): DateConfiguration {
        const date = new DateConfiguration();
        date.Language = 'pt-br';
        date.DateFormat = 'dd/mm/yyyy';
        date.Locale= 'ptbr';
        
        return date;
    }

    public static ToEnglish(): DateConfiguration {
        const date = new DateConfiguration();
        date.Language = 'en-us';
        date.DateFormat = 'MM/DD/YYYY';
        
        return date;
    }
}

export class DateConfigurationExport {
    public DateFormatExport: string;


    public static ToExport(): DateConfigurationExport {
        const date = new DateConfigurationExport();
        date.DateFormatExport = 'dd/mm/yyyy';
        return date;
    }
}