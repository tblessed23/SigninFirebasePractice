package com.msbs.android.signinfirebasepractice;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.msbs.android.signinfirebasepractice.model.User;

import java.util.ArrayList;
import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder> {

    //The Layout for this file is favorite_layout

    // Class variables for the List that holds task data and the Context
    private List<User> mTaskEntries;
    private Context mContext;


    /**
     * Constructor for the TaskAdapter that initializes the Context.
     *
     * @param context  the current Context
     * @param Reviews the ItemClickListener
     */
    public FavoritesAdapter(Context context, ArrayList<User> Reviews) {
        mContext = context;
        mTaskEntries = Reviews;
    }

    /**
     * Called when ViewHolders are created to fill a RecyclerView.
     *
     * @return A new FavoritesViewHolder that holds the view for each task
     */
    @Override
    public FavoritesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the task_layout to a view
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.favorite_layout, parent, false);

        return new FavoritesViewHolder(view);
    }

    /**
     * Called by the RecyclerView to display data at a specified position in the Cursor.
     *
     * @param holder   The ViewHolder to bind Cursor data to
     * @param position The position of the data in the Cursor
     */
    @Override
    public void onBindViewHolder(FavoritesViewHolder holder, int position) {
        // Determine the values of the wanted data
        User taskEntry = mTaskEntries.get(position);
        String titleFavorities = taskEntry.getEmail();
        String id = taskEntry.getUserId();



        //Set values
        holder.favoriteTitle.setText(titleFavorities);
        holder.favoriteId.setText(id);


    }



    /**
     * Returns the number of items to display.
     */
    @Override
    public int getItemCount() {
        if (mTaskEntries == null) {
            return 0;
        }
        return mTaskEntries.size();
    }

    /**Used for uodating/deleting database information**/
    public List<User> getTasks() {
        return mTaskEntries;
    }

    /**
     * When data changes, this method updates the list of taskEntries
     * and notifies the adapter to use the new values on it
     */
    public void setTasks(List<User> taskEntries) {
        mTaskEntries = taskEntries;
        notifyDataSetChanged();
    }


    // Inner class for creating ViewHolders
    class FavoritesViewHolder extends RecyclerView.ViewHolder {

        // Class variables for the task description and priority TextViews
        TextView favoriteTitle;
        TextView favoriteId;
        TextView favoriteUrl;
        ImageButton arrow;


        /**
         * Constructor for the FavoritesViewHolders.
         *
         * @param itemView The view inflated in onCreateViewHolder
         */
        public FavoritesViewHolder(View itemView) {
            super(itemView);

            favoriteTitle = itemView.findViewById(R.id.favorite_title);
            favoriteId = itemView.findViewById(R.id.favorite_id);

        }
    }
}

