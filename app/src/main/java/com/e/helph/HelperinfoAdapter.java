package com.e.helph;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class HelperinfoAdapter extends ArrayAdapter<Helper> {
    private Activity context;
    private List<Helper> helperList;

    public HelperinfoAdapter(Activity context, List<Helper> helperList) {
        super(context, R.layout.list_view, helperList);
        this.context = context;
        this.helperList = helperList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listview = inflater.inflate(R.layout.list_view, null, true);
        TextView userName = (TextView) listview.findViewById(R.id.PersonName);
        TextView userDonation = (TextView) listview.findViewById(R.id.donatione);
        TextView userLocation = (TextView) listview.findViewById(R.id.locatione);
        TextView userDescription = (TextView) listview.findViewById(R.id.descriptione);
        TextView userQuantity = (TextView) listview.findViewById(R.id.quantitye);
        Helper helper = helperList.get(position);
        userName.setText(helper.getUserName());
        userDonation.setText(helper.getUserDonation());
        userLocation.setText(helper.getUserLocation());
        userDescription.setText(helper.getUserDescription());
        userQuantity.setText(helper.getUserQuantity());

        return listview;
    }
}
