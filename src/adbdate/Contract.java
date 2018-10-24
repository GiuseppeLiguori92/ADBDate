package adbdate;

public interface Contract {
    interface Presenter {
        void initialise();
        void addHour();
        void addDay();
        void addWeek();
        void reset();

        void sendBootBroadcast();
    }

    interface View {
        void showDate(String date);
    }
}
