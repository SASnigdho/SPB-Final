package ittouchbd.com.smartpillbox.helper;

import android.content.Context;
import android.support.v4.app.Fragment;

import java.util.Calendar;

public interface ChangeFragment {
        void changeFragment(Fragment fragment, String title, String id);
        void setAlarm(Calendar calendar);
        void openAlertDialog();
}
