package com.e.helph;

/**
 * Created by sandy on 2/7/2018.
 */

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CasesAdapter extends RecyclerView.Adapter<CasesAdapter.MyViewHolder> {

    private ArrayList<CasesDO> casesDOS;
        private Context context;
        CasesDO casesDO;

        public void refreshAdapter(@NotNull ArrayList<CasesDO>  casesDOS) {
            this.casesDOS = casesDOS;
            notifyDataSetChanged();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView  tvCountryName;

            public MyViewHolder(View view) {
                super(view);
                tvCountryName = itemView.findViewById(R.id.tvCountryName);



            }
        }


        public CasesAdapter(Context context, ArrayList<CasesDO> countryDOS) {
            this.context = context;
            this.casesDOS = countryDOS;

        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.countrys_data, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {
            holder.tvCountryName.setText(casesDOS.get(position).name);
            casesDO=casesDOS.get(position);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(context,CaronaDetailsScreen.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);

                    intent.putExtra("newConfirmed",casesDO.newConfirmed);
                    intent.putExtra("totalConfirmed",casesDO.totalConfirmed);
                    intent.putExtra("newDeaths",casesDO.newDeaths);
                    intent.putExtra("totalDeaths",casesDO.totalDeaths);
                    intent.putExtra("newRecovered",casesDO.newRecovered);
                    intent.putExtra("totalRecovered",casesDO.totalRecovered);

                    context.startActivity(intent);

                }
            });


        }

        @Override
        public int getItemCount() {
            return casesDOS.size();
//        return 10;

        }

    }
