package com.example.readexceldata;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
public class MyAdaptor extends RecyclerView.Adapter<MyAdaptor.MyHolder>{
    public static List<RuleRegulation> ruleRegulationList;
    Context context;
    public  MyAdaptor(List<RuleRegulation> ruleRegulationList){
        MyAdaptor.ruleRegulationList = ruleRegulationList;
    }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder,parent,false);
        context=parent.getContext();
        return new MyHolder(rootView);
    }
    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
            holder.tvSerialNumber.setText(String.format("%d", position));
            holder.tvTitle.setText(ruleRegulationList.get(position).ordinance);
    }
    @Override
    public int getItemCount() {
       return ruleRegulationList.size();
    }
    class MyHolder extends RecyclerView.ViewHolder{
    TextView tvSerialNumber, tvTitle;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvSerialNumber=itemView.findViewById(R.id.tvSerialNumber);
            tvTitle=itemView.findViewById(R.id.tvTitle);
            tvTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,ActivityShowInfo.class);
                    intent.putExtra("ordinancePosition",getAdapterPosition());
                    context.startActivity(intent);
                }
            });
        }
    }

}

