package ittouchbd.com.smartpillbox.extension;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class OffLineFirebaseEnable extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
