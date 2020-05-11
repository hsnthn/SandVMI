package com.cybercrypt.sandvmi.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.widget.TextViewCompat;

import com.cybercrypt.sandvmi.R;
import com.cybercrypt.sandvmi.data.local.model.DrawerItem;

import java.util.List;

public class CustomDrawerAdapter extends ArrayAdapter<DrawerItem> {

    private Context context;
    private List<DrawerItem> drawerItemList;
    private int layoutResID;
    private int mSelectedItem = 0;

    public CustomDrawerAdapter(Context context, int layoutResourceID, List<DrawerItem> listItems) {
        super(context, layoutResourceID, listItems);
        this.context = context;
        this.drawerItemList = listItems;
        this.layoutResID = layoutResourceID;
    }

    public void selectItem(int position) {
        mSelectedItem = position;
        notifyDataSetChanged();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        DrawerItemHolder drawerHolder;
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            drawerHolder = new DrawerItemHolder();

            view = inflater.inflate(layoutResID, parent, false);
            drawerHolder.ItemName = (TextView)view.findViewById(R.id.txt_drawer);
            drawerHolder.icon = (ImageView) view.findViewById(R.id.im_drawer);
            drawerHolder.ll = (LinearLayout) view.findViewById(R.id.ll_drawer);

            view.setTag(drawerHolder);

        } else {
            drawerHolder = (DrawerItemHolder) view.getTag();
        }

        DrawerItem dItem = (DrawerItem) this.drawerItemList.get(position);

        drawerHolder.icon.setImageDrawable(view.getResources().getDrawable(
                dItem.getImgResID()));
        drawerHolder.ItemName.setText(dItem.getItemName());
        drawerHolder.ll.setTag(dItem.getLayout_tag());

        if(mSelectedItem == position) {
            TextViewCompat.setTextAppearance(drawerHolder.ItemName, R.style.SandTextView_RobotoSelected);
            drawerHolder.ItemName.setTextColor(ContextCompat.getColor(context, android.R.color.white));
            drawerHolder.ll.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_drawer_item_selected));
        }else{
            TextViewCompat.setTextAppearance(drawerHolder.ItemName, R.style.SandTextView_RobotoLight_Medium);
            drawerHolder.ll.setBackgroundColor(ContextCompat.getColor(context,android.R.color.transparent));

        }

        return view;
    }

    private static class DrawerItemHolder {
        TextView ItemName;
        ImageView icon;
        LinearLayout ll;
    }
}