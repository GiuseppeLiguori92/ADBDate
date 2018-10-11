package adbdate;

import adbdate.Contract.Presenter;

public class ControllerPresenter implements Presenter {
    private Contract.View view;
    private CommandExecutor commandExecutor;

    public ControllerPresenter(Contract.View view) {
        this.view = view;
        commandExecutor = new CommandExecutor();
        commandExecutor.resetDate();
    }

    @Override
    public void initialise() {
        view.showDate(commandExecutor.readDate());
    }

    @Override
    public void addDay() {
        commandExecutor.addDay();
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
}
