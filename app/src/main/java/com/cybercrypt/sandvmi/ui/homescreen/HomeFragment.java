package com.cybercrypt.sandvmi.ui.homescreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.cybercrypt.sandvmi.R;
import com.cybercrypt.sandvmi.adapter.CustomHomeVMListAdapter;
import com.cybercrypt.sandvmi.model.VMListItem;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private ListView mVMListView;
    private ArrayList<VMListItem> dataList;

    private String dummytitle1,dummytitle2,lv1,lv2;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        setDummys();
        dataList = new ArrayList<VMListItem>();
        dataList.add(new VMListItem(dummytitle1, lv1,R.drawable.ic_screen_lock));
        dataList.add(new VMListItem(dummytitle1,lv2, R.drawable.ic_screen_lock));

        mVMListView = root.findViewById(R.id.list_vm);
        final CustomHomeVMListAdapter cAdapter=new CustomHomeVMListAdapter(
                getActivity(),
                R.layout.custom_home_vm_item,
                dataList);
        mVMListView.setAdapter(cAdapter);

        mVMListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {

            }
        });



        return root;
    }

    private void setDummys(){
        dummytitle1="Virtual Device No.1";
        dummytitle2="Virtual Device No.2";
        lv1="Last visit: 14/02/20 at 15:46";
        lv2="Last visit: 10/01/20 at 09:35";
    }

}