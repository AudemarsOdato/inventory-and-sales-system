package utilities;

import java.time.LocalDate;
import java.time.LocalTime;

public class FormattedDateTime{
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        private String date;
        private String time;

        public FormattedDateTime() {
                this.date = localDate.getMonth() + "-" + localDate.getDayOfMonth() + "-" + localDate.getYear();
                this.time = localTime.getHour() + "-" + localTime.getMinute() + "-" + localTime.getSecond();
        }

        public String getDateTime() {
                return  date + " " + time;
        }
}