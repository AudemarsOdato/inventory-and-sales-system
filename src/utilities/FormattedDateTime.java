package utilities;

import models.Sale;

public class FormattedDateTime{
        private String date;
        private String time;

        public FormattedDateTime(Sale sale) {
                this.date = sale.getDate().toString();
                this.time = sale.getTime().toString();
        }

        public String getDateTime() {
                return  date + " " + time;
        }
}