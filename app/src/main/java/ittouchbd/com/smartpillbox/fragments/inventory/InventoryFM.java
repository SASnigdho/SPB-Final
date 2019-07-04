package ittouchbd.com.smartpillbox.fragments.inventory;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ittouchbd.com.smartpillbox.MainActivity;
import ittouchbd.com.smartpillbox.R;
import ittouchbd.com.smartpillbox.fragments.inventory.InventoryDoctorNameFM;
import ittouchbd.com.smartpillbox.helper.ChangeFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class InventoryFM extends Fragment {

    private ChangeFragment changeFragment;
    private Button purchaseBT;

    public InventoryFM() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inventory, container, false);

        findViewById(view);

        return view;
    }

    private void findViewById(View view) {
        changeFragment = (MainActivity)getActivity();

        purchaseBT = view.findViewById(R.id.if_purchase_btn_Id);
        purchaseBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment.changeFragment(new InventoryDoctorNameFM(),"Doctor's", null);
            }
        });
    }

}
