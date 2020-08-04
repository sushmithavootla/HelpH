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
 * Use the {@link precautionsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class precautionsFragment extends Fragment {
TextView p,m;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public precautionsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment precautionsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static precautionsFragment newInstance(String param1, String param2) {
        precautionsFragment fragment = new precautionsFragment();
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
        View v= inflater.inflate(R.layout.fragment_precautions, container, false);
        p=v.findViewById(R.id.precaution_heading);
        m=v.findViewById(R.id.message_window);
        p.setText("PRECAUTIONS");
        m.setText("\n"+"** Clean your hands often. Use soap and water, or an alcohol-based hand rub."+"\n\n"+
                "** Maintain a safe distance from anyone who is coughing or sneezing."+"\n\n"+
                "** Don’t touch your eyes, nose or mouth."+"\n\n"+
                "** Cover your nose and mouth with your bent elbow or a tissue when you cough or sneeze."+"\n\n"+
                "** Stay home if you feel unwell."+"\n\n"+
                "** If you have a fever, cough and difficulty breathing, seek medical attention. Call in advance."+"\n\n"+
                "** Follow the directions of your local health authority."+"\n\n"+
                "** Avoiding unneeded visits to medical facilities allows healthcare systems to operate more effectively, therefore protecting you and others.");


        return v;
    }
}