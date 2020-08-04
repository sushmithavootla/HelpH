package com.e.helph;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link globalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class globalFragment extends Fragment {
    TextView t,t1,t2,t3,t4,t5;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public globalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment globalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static globalFragment newInstance(String param1, String param2) {
        globalFragment fragment = new globalFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_global, container, false);
        t=v.findViewById(R.id.tvsummary);
        t1=v.findViewById(R.id.tv1);
        t2=v.findViewById(R.id.tv2);
        t3=v.findViewById(R.id.tv3);
        t4=v.findViewById(R.id.tv4);
        t5=v.findViewById(R.id.tv5);
        coronaCases();
        return v;
    }

    public void coronaCases(){
        String url ="https://api.covid19api.com/summary";




        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .header("Content-Type", "application/json")
                .build();


        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                String mMessage = e.getMessage().toString();
                Log.w("failure Response", mMessage);
                Toast.makeText(getContext(), mMessage, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                final String mMessage = response.body().string();
                Handler mHandler = new Handler(Looper.getMainLooper());

// anywhere else in your code
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject obj = new JSONObject(mMessage);
                            JSONObject b = obj.getJSONObject("Global");
                            int newConfirmed = b.getInt("NewConfirmed");
                            int totalConfirmed = b.getInt("TotalConfirmed");
                            int newDeaths = b.getInt("NewDeaths");
                            int totalDeaths = b.getInt("TotalDeaths");
                            int newRecovered = b.getInt("NewRecovered");
                            int totalRecovered = b.getInt("TotalRecovered");


                            ;
//
//                            double output = euroVlaue * Double.valueOf(val);


                            t.setText("New Confirmed     :  "+newConfirmed);
                            t1.setText( "Total Confirmed : "+totalConfirmed);
                            t2.setText("New Deaths            :  "+newDeaths);
                            t3.setText("Total Deaths        :  "+totalDeaths);
                            t4.setText( "New Recovered    :  "+newRecovered);
                            t5.setText( "Total Recovered :  "+totalRecovered);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });


            }


        });
    }
}