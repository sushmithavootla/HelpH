package com.e.helph;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link symptonsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class symptonsFragment extends Fragment {
    TextView s,sm;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public symptonsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment symptonsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static symptonsFragment newInstance(String param1, String param2) {
        symptonsFragment fragment = new symptonsFragment();
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
        View v= inflater.inflate(R.layout.fragment_symptons, container, false);
        s=v.findViewById(R.id.symptom_heading);
        sm=v.findViewById(R.id.message_symptom);
        s.setText("SYMPTOMS");
        sm.setText("** Most common symptoms:"+"\n\n"+
        "o fever"+"\n"+
        "o dry cough"+"\n"+
        "o tiredness"+"\n\n"+
        "** Less common symptoms:"+"\n\n"+
        "o aches and pains"+"\n"+
        "o sore throat"+"\n"+
        "o diarrhoea "+"\n"+
                "o conjunctivitis"+"\n"+
                "o headache"+"\n"+
        "o loss of taste or smell"+"\n"+
        "o a rash on skin, or discolouration of fingers or toes"+"\n\n"+
        "** Serious symptoms:"+"\n\n"+
        "o difficulty breathing or shortness of breath"+"\n"+
        "o chest pain or pressure"+"\n"+
        "o loss of speech or movement");
        return v;
    }
}