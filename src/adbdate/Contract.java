package adbdate;

import java.util.List;

public interface Contract {
    interface Presenter {
        void initialise();
        void addHour();
        void addMinute();
        void addMinutes(long minutes);
        void addDay();
        void addWeek();
        void reset();
        void root();
        void dozeMode();
        void normalMode();
        void getStatus();
        void sendBootBroadcast();
        void getInstalledPackages();
        void filterInstalledPackages(String newValue);
    }

    interface View {
        void showDevices(String devices);
        void showDate(String date);
        void showStatus(String status);
        void showIsRooted();
        void showIsNotRooted();
        void showInstalledPackages(List<String> installedPackages);
    }
}
