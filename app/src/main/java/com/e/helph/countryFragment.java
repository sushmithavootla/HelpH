package com.e.helph;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link countryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class countryFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    TextView tvSummary;
    EditText etSearch;
    CasesAdapter myDataAdapter;
    ArrayList<CasesDO> casesDOS = new ArrayList<>();
    ProgressDialog dialog;
    RecyclerView recyclerView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public countryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment countryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static countryFragment newInstance(String param1, String param2) {
        countryFragment fragment = new countryFragment();
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
        View v=inflater.inflate(R.layout.fragment_country, container, false);

        recyclerView = v.findViewById(R.id.my_recycler_view);
        ImageView ivClearSearch = v.findViewById(R.id.ivClearSearch);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);
        ivClearSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(casesDOS!=null &&casesDOS.size()>0){
                    myDataAdapter = new CasesAdapter(getContext(), casesDOS);
                    recyclerView.setAdapter(myDataAdapter);

                }
            }
        });



        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);

        etSearch = v.findViewById(R.id.etSearch);
        dialog = new ProgressDialog(getContext());
        dialog.setCancelable(false);
        dialog.setTitle("Retrieving data..please wait..");
        dialog.show();

        convertCurrency();
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etSearch.getText().toString().equalsIgnoreCase("")) {
                    if (casesDOS != null && casesDOS.size() > 0) {



                    } else {
                        Toast.makeText(getContext(), "No Countrys found", Toast.LENGTH_SHORT).show();
                    }
                } else if (etSearch.getText().toString().length() > 3) {
                    filter(etSearch.getText().toString());
                }
            }
        });
return v;

    }

    private ArrayList<CasesDO> filter(String a) {
        ArrayList casesDoS = new ArrayList<CasesDO>();
        for (int i = 0; i < casesDOS.size(); i++) {
            if (casesDOS.get(i).name.toLowerCase().contains(a)) {
                casesDoS.add(casesDOS.get(i));
            }
        }
        if (casesDoS!=null&&casesDoS.size() > 0) {

//            myDataAdapter = new CasesAdapter(getApplicationContext(), casesDOS);
            myDataAdapter.refreshAdapter(casesDoS);
        } else {
            Toast.makeText(getContext(), "No Data found", Toast.LENGTH_SHORT).show();
        }
        return casesDoS;
    }

    public void convertCurrency() {
        String url = "https://api.covid19api.com/summary";


        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .header("Content-Type", "application/json")
                .build();


        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                String mMessage = e.getMessage().toString();
                dialog.cancel();
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
                            JSONArray jsonArray = obj.getJSONArray("Countries");
                            casesDOS = new ArrayList<>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                CasesDO casesDO = new CasesDO();
                                casesDO.name = jsonObject.getString("Country");
                                casesDO.newConfirmed = jsonObject.getInt("NewConfirmed");
                                casesDO.totalConfirmed = jsonObject.getInt("TotalConfirmed");
                                casesDO.newDeaths = jsonObject.getInt("NewDeaths");
                                casesDO.totalDeaths = jsonObject.getInt("TotalDeaths");
                                casesDO.newRecovered = jsonObject.getInt("NewRecovered");
                                casesDO.totalRecovered = jsonObject.getInt("TotalRecovered");
                                casesDOS.add(casesDO);
                            }


                            myDataAdapter = new CasesAdapter(getContext(), casesDOS);
                            recyclerView.setAdapter(myDataAdapter);
                            dialog.cancel();
                            dialog.dismiss();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });


            }


        });
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}