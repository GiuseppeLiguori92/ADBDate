package adbdate;

public interface Contract {
    interface Presenter {
        void initialise();
        void addDay();
        void addWeek();
    }

    interface View {
        void showDate(String date);
    }
}
