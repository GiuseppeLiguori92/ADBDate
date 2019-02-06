package adbdate;

public interface Contract {
    interface Presenter {
        void initialise();
        void addHour();
        void addDay();
        void addWeek();
        void reset();
        void root();
        void dozeMode();
        void normalMode();
        void getStatus();

        void sendBootBroadcast();
    }

    interface View {
        void showDevices(String devices);
        void showDate(String date);
        void showStatus(String status);
        void showIsRooted();
        void showIsNotRooted();
    }
}
