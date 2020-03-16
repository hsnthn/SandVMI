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
import com.cybercrypt.sandvmi.model.SettingsItem;

import java.util.List;

public class CustomSettingsListAdapter extends ArrayAdapter<SettingsItem> {

    private Context context;
    private List<SettingsItem> drawerItemList;
    private int layoutResID;

    public CustomSettingsListAdapter(Context context, int layoutResourceID, List<SettingsItem> listItems) {
        super(context, layoutResourceID, listItems);
        this.context = context;
        this.drawerItemList = listItems;
        this.layoutResID = layoutResourceID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        CustomSettingsListAdapter.SettingItemHolder settingHolder;
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            settingHolder = new CustomSettingsListAdapter.SettingItemHolder();

            view = inflater.inflate(layoutResID, parent, false);
            settingHolder.ItemName = (TextView)view.findViewById(R.id.txt_settings_title);
            settingHolder.icon = (ImageView) view.findViewById(R.id.im_setting_icon);
            settingHolder.itemdef = (TextView) view.findViewById(R.id.txt_settings_info);

            view.setTag(settingHolder);

        } else {
            settingHolder = (CustomSettingsListAdapter.SettingItemHolder) view.getTag();
        }

        SettingsItem sItem = (SettingsItem) this.drawerItemList.get(position);

        settingHolder.icon.setImageDrawable(view.getResources().getDrawable(
                sItem.getImgResID()));
        settingHolder.ItemName.setText(sItem.getSettingItem());
        settingHolder.itemdef.setText(sItem.getSettingdef());


        return view;
    }

    private static class SettingItemHolder {
        TextView ItemName;
        TextView itemdef;
        ImageView icon;
    }
}