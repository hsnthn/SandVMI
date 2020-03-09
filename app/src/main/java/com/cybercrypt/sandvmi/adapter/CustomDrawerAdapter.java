package com.cybercrypt.sandvmi.adapter;

import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;
import androidx.core.widget.TextViewCompat;

import com.cybercrypt.sandvmi.R;
import com.cybercrypt.sandvmi.model.DrawerItem;

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
            drawerHolder.icon.setImageDrawable(view.getResources().getDrawable(
                    dItem.getImgResID()));
            TextViewCompat.setTextAppearance(drawerHolder.ItemName, R.style.NormalTextViewStyle);
            drawerHolder.ll.setBackground(view.getResources().getDrawable(R.drawable.bg_drawer_item_selected));
        }else{
            TextViewCompat.setTextAppearance(drawerHolder.ItemName, R.style.LightTextViewStyle);
            drawerHolder.ll.setBackgroundColor(view.getResources().getColor(android.R.color.transparent));

        }

        return view;
    }

    private static class DrawerItemHolder {
        TextView ItemName;
        ImageView icon;
        LinearLayout ll;
    }
}