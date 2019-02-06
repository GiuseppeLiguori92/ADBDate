package adbdate;

import adbdate.Contract.Presenter;

public class ControllerPresenter implements Presenter {
    private Contract.View view;
    private CommandExecutor commandExecutor;

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
    }

    @Override
    public void addDay() {
        commandExecutor.addDay();
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
}
