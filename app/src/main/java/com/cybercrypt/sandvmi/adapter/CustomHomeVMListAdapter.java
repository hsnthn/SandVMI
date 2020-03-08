package com.cybercrypt.sandvmi.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cybercrypt.sandvmi.R;
import com.cybercrypt.sandvmi.model.VMListItem;

import java.util.List;

public class CustomHomeVMListAdapter extends ArrayAdapter<VMListItem> {

    private Context context;
    private List<VMListItem> wmItemList;
    private int layoutResID;
    private int mSelectedItem = 0;

    public CustomHomeVMListAdapter(Context context, int layoutResourceID, List<VMListItem> listItems) {
        super(context, layoutResourceID, listItems);
        this.context = context;
        this.wmItemList = listItems;
        this.layoutResID = layoutResourceID;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        CustomHomeVMListAdapter.VMItemHolder drawerHolder;
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            drawerHolder = new CustomHomeVMListAdapter.VMItemHolder();

            view = inflater.inflate(layoutResID, parent, false);
            drawerHolder.vmName = (TextView)view.findViewById(R.id.txt_vm_title);
            drawerHolder.icon = (ImageView) view.findViewById(R.id.im_vm_icon);
            drawerHolder.vmLastVisited = (TextView) view.findViewById(R.id.txt_vm_lastvisited);

            view.setTag(drawerHolder);

        } else {
            drawerHolder = (CustomHomeVMListAdapter.VMItemHolder) view.getTag();
        }

        VMListItem vItem = (VMListItem) this.wmItemList.get(position);

        drawerHolder.icon.setImageDrawable(view.getResources().getDrawable(
                vItem.getImResID()));
        drawerHolder.vmName.setText(vItem.getVmName());
        drawerHolder.vmLastVisited.setText(vItem.getLastVisited());

        return view;
    }

    private static class VMItemHolder {
        TextView vmName;
        TextView vmLastVisited;
        ImageView icon;
    }
}