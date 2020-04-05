package com.cybercrypt.sandvmi.ui.homescreen;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.cybercrypt.sandvmi.R;
import com.cybercrypt.sandvmi.adapter.CustomHomeVMListAdapter;
import com.cybercrypt.sandvmi.databinding.FragmentHomeBinding;
import com.cybercrypt.sandvmi.model.VMListItem;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ArrayList<VMListItem> dataList;

    private String dummytitle1,dummytitle2,lv1,lv2;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        setDummys();
        dataList = new ArrayList<VMListItem>();
        dataList.add(new VMListItem(dummytitle1, lv1,R.drawable.ic_screen_lock));
        dataList.add(new VMListItem(dummytitle1,lv2, R.drawable.ic_screen_lock));

        final CustomHomeVMListAdapter cAdapter=new CustomHomeVMListAdapter(
                getActivity(),
                R.layout.custom_home_vm_item,
                dataList);
        binding.listVm.setAdapter(cAdapter);

        binding.listVm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Dialog filterDialog = new Dialog(getActivity(), android.R.style.Theme_Black_NoTitleBar_Fullscreen);
                filterDialog.setContentView(R.layout.custom_loading_screen);
                filterDialog.setCancelable(true);
                filterDialog.show();
            }
        });


        return binding.getRoot();
    }

    private void setDummys(){
        dummytitle1="Virtual Device No.1";
        dummytitle2="Virtual Device No.2";
        lv1="Last visit: 14/02/20 at 15:46";
        lv2="Last visit: 10/01/20 at 09:35";
    }

}