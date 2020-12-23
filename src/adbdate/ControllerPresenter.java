package adbdate;

import adbdate.Contract.Presenter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import java.util.ArrayList;
import java.util.List;

public class ControllerPresenter implements Presenter {
    private Contract.View view;
    private CommandExecutor commandExecutor;
    private List<String> installedPackages = new ArrayList<>();

    public ControllerPresenter(Contract.View view) {
        this.view = view;
        commandExecutor = new CommandExecutor();
        commandExecutor.resetDate();
        root();
        getStatus();
    }

    private boolean isRooted(String root) {
        return root.equals("adbd is already running as root") ||
                root.equals("restarting adbd as root");
    }

    @Override
    public void initialise() {
        view.showDate(commandExecutor.readDate());
        view.showDevices(commandExecutor.getDevices());
        getInstalledPackages();
    }

    @Override
    public void addDay() {
        commandExecutor.addDay();
        view.showDate(commandExecutor.readDate());
    }

    @Override
    public void addMinute() {
        commandExecutor.addMinute();
        view.showDate(commandExecutor.readDate());
    }

    @Override
    public void addMinutes(long minutes) {
        commandExecutor.addMinutes(minutes);
        view.showDate(commandExecutor.readDate());
    }

    @Override
    public void addHour() {
        commandExecutor.addHour();
        view.showDate(commandExecutor.readDate());
    }

    @Override
    public void addWeek() {
        commandExecutor.addWeek();
        view.showDate(commandExecutor.readDate());
    }

    @Override
    public void reset() {
        commandExecutor.resetDate();
        view.showDate(commandExecutor.readDate());
    }

    @Override
    public void root() {
        String root = commandExecutor.root();
        boolean rooted = isRooted(root);
        if (rooted) {
            view.showIsRooted();
        } else {
            view.showIsNotRooted();
        }
    }

    @Override
    public void dozeMode() {
        commandExecutor.dozeMode();
        getStatus();
    }

    @Override
    public void normalMode() {
        commandExecutor.normalMode();
        getStatus();
    }

    @Override
    public void getStatus() {
        String status = commandExecutor.getStatus();
        view.showStatus("Doze: " + status);
    }

    @Override
    public void sendBootBroadcast() {
        commandExecutor.sendBootBroadcast();
    }

    @Override
    public void getInstalledPackages() {
        installedPackages = commandExecutor.getInstalledPackages();
        view.showInstalledPackages(installedPackages);
    }

    @Override
    public void filterInstalledPackages(String filter) {
        ObservableList<String> data = FXCollections.observableArrayList();
        data.addAll(installedPackages);
        FilteredList<String> filteredInstalledPackages = new FilteredList<>(data, s -> s.contains(filter));
        view.showInstalledPackages(filteredInstalledPackages);
    }
}
