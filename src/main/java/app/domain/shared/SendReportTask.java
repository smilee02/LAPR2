package app.domain.shared;

import app.domain.store.NHSReportStore;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 
 */
class SendReportTask {

    private static final int SIMPLE = 1;
    /**
     * Creation of the time variable and assign the time pretended for the task to
     * be executed
     */
    private static final Date time = setTime();

    /**
     * 24h in seconds
     */
    private static final int TIMEBETWEENDAYSINSECONDS = 86400;

    /**
     * Sets the time to 6AM
     *
     * @return The time at 6AM
     */
    public static Date setTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 6);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * Schedules a task to be sent at 6AM every day
     */
    public SendReportTask(Date date1, Date date2, int independentVariable, int historicalPoints, int choice,
            double significanceLevel) {
        Timer timer = new Timer();
        TimerTask task = new SendReport(date1, date2, independentVariable, historicalPoints, choice, significanceLevel); // cria
                                                                                                                         // a
                                                                                                                         // task
                                                                                                                         // e
                                                                                                                         // atribui
                                                                                                                         // os
                                                                                                                         // valores
                                                                                                                         // do
                                                                                                                         // report
                                                                                                                         // (presume-se
                                                                                                                         // q
                                                                                                                         // funcione)
        timer.scheduleAtFixedRate(task, time, TIMEBETWEENDAYSINSECONDS * 1000); // manda instantaneamente e as 6 da
                                                                                // manha
    }
}

/**
 * Contains the task to be sent
 */
class SendReport extends TimerTask {

    private static final NHSReportStore NHS_REPORT_STORE = new NHSReportStore();
    private final Date date1, date2;
    private final int independentVariable, historicalPoints, choice;
    private final double significanceLevel;

    public SendReport(Date date1, Date date2, int independentVariable, int historicalPoints, int choice,
            double significanceLevel) {
        this.date1 = date1;
        this.date2 = date2;
        this.independentVariable = independentVariable;
        this.historicalPoints = historicalPoints;
        this.choice = choice;
        this.significanceLevel = significanceLevel;
    }

    public void run() {
        String report = NHS_REPORT_STORE.createReport(date1, date2, independentVariable, historicalPoints, choice,
                significanceLevel); // criacao do report
        NHS_REPORT_STORE.sendReport(report); // mandar o report
    }
}
