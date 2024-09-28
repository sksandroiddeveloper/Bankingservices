package com.digitalfintech.customer.transactionst;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.digitalfintech.customer.R;

import org.w3c.dom.Text;

import java.util.List;

public class Adaptertransac extends RecyclerView.Adapter<Adaptertransac.MyViewHolder> {

    private Context mContext;
    private List<Tranasc> transactions;

    public Adaptertransac(Context mContext, List<Tranasc> transactions) {
        this.mContext = mContext;
        this.transactions = transactions;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        v = layoutInflater.inflate(R.layout.paymentrecived, parent, false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

//        holder.name.setText(transactions.get(position).amount);
//        holder.name.setText(transactions.get(position).purpose);//cash_deposit
        if (transactions.get(position).getType().equalsIgnoreCase("DEBIT"))
        {
            holder.date.setText(transactions.get(position).created_on);
            holder.type.setText(transactions.get(position).type);
            holder.name.setText(transactions.get(position).getPurpose());
            holder.type.setTextColor(Color.RED);
            holder.amount.setText("Rs "+transactions.get(position).amount);

            if (transactions.get(position).getStatus().equalsIgnoreCase("success") ) {
                holder.imageViewp.setVisibility(View.INVISIBLE);
                holder.imageViewf.setVisibility(View.INVISIBLE);
                holder.imageViews.setVisibility(View.VISIBLE);
            }
          else if (transactions.get(position).getStatus().equalsIgnoreCase("failure") ) {
                holder.imageViewp.setVisibility(View.INVISIBLE);
                holder.imageViewf.setVisibility(View.VISIBLE);
                holder.imageViews.setVisibility(View.INVISIBLE);
            }
            else if (transactions.get(position).getStatus().equalsIgnoreCase("pending") ) {
                holder.imageViewp.setVisibility(View.INVISIBLE);
                holder.imageViewf.setVisibility(View.VISIBLE);
                holder.imageViews.setVisibility(View.INVISIBLE);
            }
        }
        else

        {
            holder.date.setText(transactions.get(position).created_on);
            holder.type.setText(transactions.get(position).type);
            holder.name.setText(transactions.get(position).getPurpose());
            holder.amount.setText("Rs "+transactions.get(position).amount);
            holder.type.setTextColor(Color.BLUE);

            if (transactions.get(position).getStatus().equalsIgnoreCase("success") ) {
                holder.imageViewp.setVisibility(View.INVISIBLE);
                holder.imageViewf.setVisibility(View.INVISIBLE);
                holder.imageViews.setVisibility(View.VISIBLE);
            }

            else if (transactions.get(position).getStatus().equalsIgnoreCase("failure") ) {
                holder.imageViewp.setVisibility(View.INVISIBLE);
                holder.imageViewf.setVisibility(View.VISIBLE);
                holder.imageViews.setVisibility(View.INVISIBLE);
            }
            else if (transactions.get(position).getStatus().equalsIgnoreCase("pending") ) {
                holder.imageViewp.setVisibility(View.VISIBLE);
                holder.imageViewf.setVisibility(View.INVISIBLE);
                holder.imageViews.setVisibility(View.INVISIBLE);
            }

//        } else if (transactions.get(position).getType().equalsIgnoreCase("CREDIT") && (transactions.get(position).getPurpose().equalsIgnoreCase("cash_deposit"))) {
//
//            holder.date.setText(transactions.get(position).created_on);
//            holder.type.setText(transactions.get(position).type);
//            holder.amount.setText(transactions.get(position).amount);

        }


//        {
//            holder.name.setText(transactions.get(position).getDetails().rmtr_full_name);
//            holder.date.setText(transactions.get(position).getDetails().created_at);
//            holder.type.setText(transactions.get(position).getDetails().payment_type);
//            holder.amount.setText(transactions.get(position).getDetails().amount);
//
//            if (transactions.get(position).getStatus().equalsIgnoreCase("success") ) {
//                holder.imageViewp.setVisibility(View.INVISIBLE);
//                holder.imageViewf.setVisibility(View.INVISIBLE);
//                holder.imageViews.setVisibility(View.VISIBLE);
//            }
//else
//            {
//                Toast.makeText(mContext, "There is Problem", Toast.LENGTH_SHORT).show();
//            }
//
//            //
////
//        }
    }

    @Override
    public int getItemCount() {

        return transactions.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView date;
        TextView type;
        TextView amount;
        ImageView imageViewf, imageViews, imageViewp;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            date = itemView.findViewById(R.id.times);
            type = itemView.findViewById(R.id.type);
            amount = itemView.findViewById(R.id.amount);
            imageViewf = itemView.findViewById(R.id.fail);
            imageViewp = itemView.findViewById(R.id.pend);
            imageViews = itemView.findViewById(R.id.suc);
        }
    }

}
