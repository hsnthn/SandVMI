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
import com.cybercrypt.sandvmi.model.ProfileItem;

import java.util.List;

public class CustomProfileListAdapter extends ArrayAdapter<ProfileItem> {

    private Context context;
    private List<ProfileItem> drawerItemList;
    private int layoutResID;

    public CustomProfileListAdapter(Context context, int layoutResourceID, List<ProfileItem> listItems) {
        super(context, layoutResourceID, listItems);
        this.context = context;
        this.drawerItemList = listItems;
        this.layoutResID = layoutResourceID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        CustomProfileListAdapter.ProfileItemHolder profileHolder;
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            profileHolder = new CustomProfileListAdapter.ProfileItemHolder();

            view = inflater.inflate(layoutResID, parent, false);
            profileHolder.ItemName = (TextView)view.findViewById(R.id.txt_profile_list_text);
            profileHolder.icon = (ImageView) view.findViewById(R.id.im_profile_list_icon);

            view.setTag(profileHolder);

        } else {
            profileHolder = (CustomProfileListAdapter.ProfileItemHolder) view.getTag();
        }

        ProfileItem pItem = (ProfileItem) this.drawerItemList.get(position);

        profileHolder.icon.setImageDrawable(view.getResources().getDrawable(
                pItem.getImgResID()));
        profileHolder.ItemName.setText(pItem.getProfileItem());

        return view;
    }

    private static class ProfileItemHolder {
        TextView ItemName;
        ImageView icon;
    }
}