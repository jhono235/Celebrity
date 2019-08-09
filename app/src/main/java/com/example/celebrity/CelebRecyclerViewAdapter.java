package com.example.celebrity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static androidx.core.content.ContextCompat.startActivity;

public class CelebRecyclerViewAdapter extends RecyclerView.Adapter<CelebRecyclerViewAdapter.ViewHolder> {
    ArrayList<Celebs> celebsArrayList = new ArrayList<>();


    public CelebRecyclerViewAdapter(ArrayList<Celebs> celebList) {
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
        holder.setCeleb(celebBringRendered);

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

        Celebs celebs;
        private String firstName;


        public ViewHolder(final View itemView) {
            super(itemView);
            tvFirstName = itemView.findViewById(R.id.tvFirstName);
            tvLastName = itemView.findViewById(R.id.tvLastName);
            ibFavorite = itemView.findViewById(R.id.ibFavorite);


            ibDelete = itemView.findViewById(R.id.ibDelete);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), EditCeleb.class);
                    intent.putExtra("firstNameToEdit", tvFirstName.getText().toString());
                    intent.putExtra("lastNameToEdit", tvLastName.getText().toString());
                    intent.putExtra("favOrNot", Celebs.isFavorite());
                    view.getContext().startActivity(intent);


                }
            });

            ibDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CelebDatabaseHelper helper = new CelebDatabaseHelper(view.getContext());
                    helper.deleteCeleb(firstName);
                    celebsArrayList = helper.getAllCelebs();
                    notifyDataSetChanged();
                    Toast.makeText(view.getContext(), "Deleted from List", Toast.LENGTH_LONG).show();


                }
            });

            ibFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!Celebs.isFavorite()) {
                        Celebs.setFavorite(true);
                        new CelebDatabaseHelper(view.getContext()).updateCelebIntoDatabase(celebs);
                        notifyDataSetChanged();
                        Toast.makeText(view.getContext(), "Added to Favorites", Toast.LENGTH_LONG).show();
                    } else {
                        Celebs.setFavorite(false);
                        new CelebDatabaseHelper(view.getContext()).updateCelebIntoDatabase(celebs);
                        notifyDataSetChanged();
                        Toast.makeText(view.getContext(), "Removed to favorites", Toast.LENGTH_LONG).show();
                    }


                }
            });


        }


        void setCeleb(Celebs celeb) {
            this.celebs = celeb;
        }

        public void setFirstName(String first) {
            this.firstName = first;
        }
    }
}
