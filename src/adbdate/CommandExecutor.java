package adbdate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class CommandExecutor {
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMddHHmmYYYY.ss");

    private long day = 0;
    private long hour = 0;

    private String execute(String command) {
        StringBuilder stringBuilder = new StringBuilder();
        try {

            Process process = Runtime.getRuntime().exec(command);

            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(process.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(process.getErrorStream()));

            String s;
            while ((s = stdInput.readLine()) != null) {
                stringBuilder.append(s);
            }

            while ((s = stdError.readLine()) != null) {
                stringBuilder.append(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }

    public void resetDate() {
        day = 0;
        hour = 0;
        setDate(getNow());
    }

    private LocalDateTime getNow() {
        return LocalDateTime.now();
    }

    public void addHour() {
        hour++;
        addHours(hour);
    }

    public void addDay() {
        day++;
        addDays(day);
    }

    public void addWeek() {
        day += 7;
        addDays(day);
    }

    private void addTime(long days, ChronoUnit days2) {
        LocalDateTime localDateTime = getNow().plus(days, days2);
        setDate(localDateTime);
    }

    private void addDays(long days) {
        addTime(days, ChronoUnit.DAYS);
    }

    private void addHours(long hours) {
        addTime(hours, ChronoUnit.HOURS);
    }

    public String readDate() {
        return execute("adb shell date");
    }

    public String root() {
        return execute("adb root");
    }

    public void sendBootBroadcast() {
       sendBroadcast("android.intent.action.BOOT_COMPLETED");
    }

    private void sendBroadcast(String action) {
        execute("adb shell am broadcast -a " + action);
    }

    public void setDate(LocalDateTime localDateTime) {
        String format = dateTimeFormatter.format(localDateTime);
        System.out.println("CommandExecutor.setDate: " + format + "\n" + localDateTime);
        String command = "adb shell date " + format + " && am broadcast -a android.intent.action.TIME_SET";

        String execute = execute(command);
        if (execute.contains("bad date")) {
            adjustTimezone("CET");
            execute = execute(command);

            if (execute.contains("bad date")) {
                adjustTimezone("CEST");
                execute(command);
            }
        }
    }

    private void adjustTimezone(String timezone) {
        execute("adb shell setprop persist.sys.timezone " + timezone);
    }

    public String getStatus() {
        return execute("adb shell dumpsys deviceidle get deep");
    }

    public String dozeMode() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(execute("adb shell dumpsys battery unplug"));
        stringBuilder.append(execute("adb shell dumpsys deviceidle enable"));
        stringBuilder.append(execute("adb shell dumpsys deviceidle force-idle"));
        return stringBuilder.toString();
    }

    public String normalMode() {
        StringBuilder stringBuilder = new StringBuilder();
        execute("adb shell dumpsys battery reset");
        execute("adb shell dumpsys deviceidle disable");
        return stringBuilder.toString();
    }
}
