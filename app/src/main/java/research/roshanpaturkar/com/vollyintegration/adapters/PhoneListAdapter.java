package research.roshanpaturkar.com.vollyintegration.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import research.roshanpaturkar.com.vollyintegration.R;
import research.roshanpaturkar.com.vollyintegration.models.PhoneDetails;

public class PhoneListAdapter extends RecyclerView.Adapter<PhoneListAdapter.ViewHolder> {
    private List<PhoneDetails> listItems;
    private Context context;

    public PhoneListAdapter(List<PhoneDetails> listItems, Context context)
    {
        this.listItems = listItems;
        this.context = context;
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.phonedetails,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        PhoneDetails listItem = listItems.get(position);
        holder.phonename.setText(listItem.getPhonename());
        holder.model.setText(listItem.getModel());
        holder.date.setText(listItem.getDate());
        holder.phonetype.setText(listItem.getPhonetype());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView phonename, model, date, phonetype;

        public ViewHolder(View itemView)
        {
            super(itemView);
            phonename = (TextView) itemView.findViewById(R.id.phoneName);
            model = (TextView) itemView.findViewById(R.id.phoneModel);
            date = (TextView) itemView.findViewById(R.id.phoneDate);
            phonetype = (TextView) itemView.findViewById(R.id.phoneType);
        }
    }
}
