package adbdate;

public interface Contract {
    interface Presenter {
        void initialise();
        void addHour();
        void addDay();
        void addWeek();
        void reset();
    }

    interface View {
        void showDate(String date);
    }
}
