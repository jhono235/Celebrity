package com.example.celebrity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CelebRecyclerViewAdapter extends RecyclerView.Adapter<CelebRecyclerViewAdapter.ViewHolder> {
    ArrayList<Celebs> celebsArrayList = new ArrayList<>();



    public CelebRecyclerViewAdapter(ArrayList<Celebs> celebList){
        this.celebsArrayList = celebList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.celeb_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Celebs celebBringRendered = celebsArrayList.get(position);
        holder.tvFirstName.setText(celebBringRendered.getFirstName());
        holder.tvLastName.setText(celebBringRendered.getLastName());
        holder.setFirstName(celebBringRendered.getFirstName());

    }

    @Override
    public int getItemCount() {
        return celebsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvFirstName;
        TextView tvLastName;
        ImageButton ibFavorite;
        ImageButton ibDelete;
        private String firstName;


        public ViewHolder(final View itemView){
            super(itemView);
            tvFirstName = itemView.findViewById(R.id.tvFirstName);
            tvLastName = itemView.findViewById(R.id.tvLastName);
            ibFavorite = itemView.findViewById(R.id.ibFavorite);
            ibDelete = itemView.findViewById(R.id.ibDelete);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    CelebDatabaseHelper helper = new CelebDatabaseHelper(view.getContext());
                    helper.deleteCeleb(firstName);
                    celebsArrayList = helper.getAllCelebs();
                    notifyDataSetChanged();


                }
            });

            ibDelete.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    CelebDatabaseHelper helper = new CelebDatabaseHelper(view.getContext());
                    helper.deleteCeleb(firstName);
                    celebsArrayList = helper.getAllCelebs();
                    notifyDataSetChanged();

                }
            });


        }

        public void setFirstName(String first){
            this.firstName = first;
        }
    }
}
