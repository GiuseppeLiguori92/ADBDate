package adbdate;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller implements Contract.View {
    private Contract.Presenter presenter;

    @FXML
    private Label lblDate;
    @FXML
    private Button btPlusDay;
    @FXML
    private Button btPlusWeek;

    @FXML
    private void initialize() {
        presenter = new ControllerPresenter(this);
        presenter.initialise();

        btPlusDay.setOnAction(event -> presenter.addDay());
        btPlusWeek.setOnAction(event -> presenter.addWeek());
    }

    @Override
    public void showDate(String date) {
        lblDate.setText(date);
    }
}
