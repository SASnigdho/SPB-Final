package ittouchbd.com.smartpillbox.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import ittouchbd.com.smartpillbox.R;
import ittouchbd.com.smartpillbox.fragments.inventory.InventoryFM;


public class HomeFM extends Fragment {

    private Toolbar toolbar;
    private Button addPrescriptionBT, inventoryBT, lowMedicineBT, viewAllBT;


    public HomeFM() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        findViewById(view);

        return view;
    }

    private void findViewById(View view) {

        toolbar = view.findViewById(R.id.toolbar);

        addPrescriptionBT = view.findViewById(R.id.addPrescription_btn_Id);
        inventoryBT = view.findViewById(R.id.inventory_btn_Id);
        lowMedicineBT = view.findViewById(R.id.lowMedicineList_btn_Id);
        viewAllBT = view.findViewById(R.id.viewAll_btn_Id);

        addPrescriptionBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passFragment(new InputPrescriptionFM(), "Add PrescriptionInfo");
            }
        });

        inventoryBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passFragment(new InventoryFM(), "Inventory");
            }
        });

        lowMedicineBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passFragment(new LowMedicineFM(), "Low Medicine List");
            }
        });

        viewAllBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passFragment(new ViewPrescriptionListFM(), "All PrescriptionInfo");
            }
        });
    }


    public void passFragment(Fragment fragment, String title){

        FragmentManager fragmentManager = getFragmentManager();
        assert fragmentManager != null;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment).commit();
        fragmentTransaction.addToBackStack(title);
        //toolbar.setTitle(title);
    }

}
