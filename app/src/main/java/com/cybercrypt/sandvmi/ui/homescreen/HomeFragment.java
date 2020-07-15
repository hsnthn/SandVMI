package com.cybercrypt.sandvmi.ui.homescreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.cybercrypt.sandvmi.R;
import com.cybercrypt.sandvmi.data.local.model.VMListItem;
import com.cybercrypt.sandvmi.databinding.FragmentHomeBinding;
import com.cybercrypt.sandvmi.ui.MainActivity;
import com.cybercrypt.sandvmi.ui.VMIActivity;
import com.cybercrypt.sandvmi.ui.adapter.CustomHomeVMListAdapter;
import com.cybercrypt.sandvmi.ui.util.BaseFragment;

import java.util.ArrayList;

public class HomeFragment extends BaseFragment {

    private FragmentHomeBinding binding;
    private ArrayList<VMListItem> dataList;

    private String dummytitle1,dummytitle2,lv1,lv2,url1,url2;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        setToolbarTitle(getResources().getString(R.string.menu_home));

        setDummys();
        dataList = new ArrayList<VMListItem>();
        dataList.add(new VMListItem(dummytitle1, lv1,url1,R.drawable.ic_screen_lock));
        dataList.add(new VMListItem(dummytitle2, lv1,url2,R.drawable.ic_screen_lock));

        final CustomHomeVMListAdapter cAdapter=new CustomHomeVMListAdapter(
                getActivity(),
                R.layout.custom_home_vm_item,
                dataList);
        binding.listVm.setAdapter(cAdapter);

        binding.listVm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                VMListItem selectedItem = dataList.get(position);
                Intent intent = new Intent(getContext(), VMIActivity.class);
                intent.putExtra("vmiUrl",selectedItem.getVmUrl());
                startActivity(intent);
            }
        });

        return binding.getRoot();
    }

    private void setDummys(){
        dummytitle1="Demo";
        url1="http://185.165.169.244:8081/guacamole";
        url2="http://172.17.0.4:8080/guacamole";
        dummytitle2="Caner Local";
        lv1="Last visit: 14/02/20 at 15:46";
        lv2="Last visit: 10/01/20 at 09:35";
    }

    @Override
    public void onNavigationIconClick() {
        ((MainActivity)getActivity()).drawerOpen();
    }
}