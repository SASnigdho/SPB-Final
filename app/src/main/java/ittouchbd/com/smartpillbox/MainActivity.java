package ittouchbd.com.smartpillbox;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ittouchbd.com.smartpillbox.Models.DoctorInformation;
import ittouchbd.com.smartpillbox.Models.Prescription;
import ittouchbd.com.smartpillbox.Models.User;
import ittouchbd.com.smartpillbox.activitys.AccountInfoActivity;
import ittouchbd.com.smartpillbox.activitys.LogInActivity;
import ittouchbd.com.smartpillbox.fragments.HomeFM;
import ittouchbd.com.smartpillbox.fragments.InputPrescriptionFM;
import ittouchbd.com.smartpillbox.fragments.inventory.InventoryFM;
import ittouchbd.com.smartpillbox.fragments.LowMedicineFM;
import ittouchbd.com.smartpillbox.fragments.ViewPrescriptionListFM;
import ittouchbd.com.smartpillbox.helper.AlarmHelper;
import ittouchbd.com.smartpillbox.helper.ChangeFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ChangeFragment {

    public Toolbar toolbar;

    //Fire Base
    private FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        DoctorInformation doctorInformation = new DoctorInformation("name","Dhanmondi", "0124954648","doc@emaiul.com","asdads", "ajhsdaodaodosadoad");
        List<Prescription> prescriptions = new ArrayList<Prescription>();
        //prescriptions.add(new PrescriptionInfo().setDoctorInformation(doctorInformation));

        User user = new User();
        //user.setPrescriptions();


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, new HomeFM());
        fragmentTransaction.commit();
        toolbar.setTitle("Home");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_account_info) {
            startActivity(new Intent(getApplicationContext(), AccountInfoActivity.class));
        }

        if (item.getItemId() == R.id.action_log_out) {
            FirebaseAuth.getInstance().signOut();
            finish();
            startActivity(new Intent(getApplicationContext(), LogInActivity.class));
        }

        if (item.getItemId() == R.id.action_settings) {
            Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        switch (item.getItemId()){
            case R.id.nav_home:
                changeFragment(new HomeFM(), "Home", null);
                break;

            case R.id.nav_prescription:
                changeFragment(new InputPrescriptionFM(), "Add PrescriptionInfo", null);
                break;

            case R.id.nav_inventory:
                changeFragment(new InventoryFM(), "Inventory", null);
                break;

            case R.id.nav_low_medicine:
                changeFragment(new LowMedicineFM(), "Low Medicine", null);
                break;

            case R.id.nav_view_all:
                changeFragment(new ViewPrescriptionListFM(), "View All", null);
                break;

            case R.id.nav_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_send:
                Toast.makeText(this, "Send", Toast.LENGTH_SHORT).show();
                break;

            default:break;
        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void changeFragment(Fragment fragment, String title, String id) {

        if (id==null){
            id="";
        }

        Bundle bundle = new Bundle();
        bundle.putString("prescriptionId", id);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragment_container, fragment).commit();
        fragmentTransaction.addToBackStack(title);
        toolbar.setTitle(title);
    }

    @Override
    public void setAlarm(Calendar calendar) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getApplicationContext(), AlarmHelper.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 1, intent, 0);

        if (calendar.before(Calendar.getInstance())) {
            calendar.add(Calendar.DATE, 1);
        }

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

    @Override
    public void openAlertDialog() {

        //ViewGroup viewGroup = findViewById(android.R.id.content);

//        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//        builder.setTitle("Take Your Medicine");
//
//        LayoutInflater layoutInflater =  getLayoutInflater();
//        View view = layoutInflater.inflate(R.layout.alarm_layout, null);
//
//        builder.setView(view);
//
//        TextView medicineNamesTV = view.findViewById(R.id.alarm_tv_Id);
//        Button snoozeBT = view.findViewById(R.id.alarm_snooze_btn_Id);
//        Button okBT = view.findViewById(R.id.alarm_ok_btn_Id);
//
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();

//        View dialogView = LayoutInflater.from(this).inflate(R.layout.alarm_layout, viewGroup, false);
//
//
//        //Now we need an AlertDialog.Builder object
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//        //setting the view of the builder to our custom view that we already inflated
//        builder.setView(dialogView);
//
//        //finally creating the alert dialog and displaying it
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();

        // custom dialog
//        final Dialog dialog = new Dialog(MainActivity.this);
//        dialog.setContentView(R.layout.activity_alarm);
//        Button okBT = dialog.findViewById(R.id.aa_ok_btn_Id);
//        // if button is clicked, close the custom dialog
//        okBT.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//                Toast.makeText(getApplicationContext(),"Dismissed..!!",Toast.LENGTH_SHORT).show();
//            }
//        });
//        dialog.show();
    }
}
