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
        setDate(getNow());
    }

    private LocalDateTime getNow() {
        return LocalDateTime.now();
    }

    public void addDay() {
        day++;
        addDays(day);
    }

    public void addWeek() {
        day += 7;
        addDays(day);
    }

    private void addDays(long days) {
        LocalDateTime localDateTime = getNow().plus(days, ChronoUnit.DAYS);
        setDate(localDateTime);
    }


    public String readDate() {
        return execute("adb shell date");
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
}
