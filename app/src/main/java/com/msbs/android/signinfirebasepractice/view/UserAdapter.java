package com.msbs.android.signinfirebasepractice.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.msbs.android.signinfirebasepractice.R;
import com.msbs.android.signinfirebasepractice.model.AppExecutors;
import com.msbs.android.signinfirebasepractice.model.NewDatabase;
import com.msbs.android.signinfirebasepractice.model.User;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.StoryViewHolder> {
    private View mEmptyView;

    // Constant for date format
    private static final String DATE_FORMAT = "MM/dd/yyy";

    // Member variable to handle item clicks
    // final private ItemClickListener mItemClickListener;
    // Class variables for the List that holds task data and the Context
    private List<User> mUserEntries;
    private Context mContext;
    // UserEditViewModel userModel;
    private int mTaskId = DEFAULT_TASK_ID;
    // Constant for default task id to be used when not in update mode
    private static final int DEFAULT_TASK_ID = -1;
    // Member variable for the Database
    private NewDatabase mDb;


    // Date formatter
    private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());

    /**
     * Constructor for the TaskAdapter that initializes the Context.
     *
     * @param context  the current Context
     *
     */
    public UserAdapter(Context context) {
        mContext = context;
        //mItemClickListener = listener;
    }

    /**
     * Called when ViewHolders are created to fill a RecyclerView.
     *
     * @return A new TaskViewHolder that holds the view for each task
     */
    @Override
    public StoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the task_layout to a view
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.user_display_details_list, parent, false);

        return new StoryViewHolder(view);
    }

    /**
     * Called by the RecyclerView to display data at a specified position in the Cursor.
     *
     * @param holder   The ViewHolder to bind Cursor data to
     * @param position The position of the data in the Cursor
     */
    @Override
    public void onBindViewHolder(final StoryViewHolder holder, final int position) {
        // Determine the values of the wanted data
        final User users = mUserEntries.get(position);
        final String email= users.getEmail();
        final String firstname = users.getFirstname();
        final String lastname = users.getLastname();
        final String city = users.getCity();
        final String state = users.getState();
        final String country = users.getCountry();
        final String phone = users.getPhone();
        final String userid = users.getUserId();
        final String displayname = users.getDisplayname();
        final String displayemail = users.getDisplayemail();

        mDb = NewDatabase.getInstance(mContext);

        //Handle Editing Database Entry
        holder.editStoryDetails.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                String elementId = mUserEntries.get(position).getUserId();
                Intent intent = new Intent(mContext, UserEditDetailsActivity.class);
                intent.putExtra(UserEditDetailsActivity.EXTRA_TASK_ID, elementId);
                // intent.putExtra("title", title);
//                String elementTitle =  mUserEntries.get(position).getAudiotitle();
//                intent.putExtra("Stories",  elementTitle);
//
//                String elementUrl =  mStoryEntries.get(position).getAudioUrl();
//                intent.putExtra("StoriesLink",  elementUrl);
//                mContext.startActivity(intent);
            }
        });




        //Set values
        holder.user_display_email.setText(email);
        holder.user_display_firstname.setText(firstname);
        holder.user_display_lastname.setText(lastname);
        holder.user_display_city.setText(city);
        holder.user_display_state.setText(state);
        holder.user_display_country.setText(country);
        holder.user_display_phone.setText(phone);

        holder.user_display_displayname.setText(displayname);
        holder.user_display_displayemail.setText(displayemail);


    }




    /*


    /**
     * Returns the number of items to display.
     */
    @Override
    public int getItemCount() {
        if (mUserEntries == null) {
            return 0;
        }
        return mUserEntries.size();
    }

    public List<User> getTasks() {
        return mUserEntries;
    }

    /**
     * When data changes, this method updates the list of taskEntries
     * and notifies the adapter to use the new values on it
     */
    public void setTasks(List<User> storyEntries) {
        mUserEntries = storyEntries;
        notifyDataSetChanged();
    }

    // Inner class for creating ViewHolders
    class StoryViewHolder extends RecyclerView.ViewHolder {

        // Class variables for the task description and priority TextViews
        TextView user_display_email;
        TextView user_display_firstname;
        TextView user_display_lastname;
        TextView user_display_city;
        TextView user_display_state;
        TextView user_display_country;
        TextView user_display_phone;
        TextView user_display_displayname;
        TextView user_display_displayemail;


        Button editStoryDetails;



        /**
         * Constructor for the TaskViewHolders.
         *
         * @param itemView The view inflated in onCreateViewHolder
         */
        public StoryViewHolder(View itemView) {
            super(itemView);



            user_display_email= itemView.findViewById(R.id.user_display_email);
            user_display_firstname = itemView.findViewById(R.id.user_display_firstname);
            user_display_lastname=itemView.findViewById(R.id.user_display_lastname);
            user_display_city =itemView.findViewById(R.id.user_display_city);
            user_display_state=itemView.findViewById(R.id.user_display_state);
            user_display_country=itemView.findViewById(R.id.user_display_country);
            user_display_phone= itemView.findViewById(R.id.user_display_phone);
            user_display_displayname = itemView.findViewById(R.id.user_display_displayname);
            user_display_displayemail=itemView.findViewById(R.id.user_display_displayemail);
            editStoryDetails=itemView.findViewById(R.id.edit_saved_audio);




        }

    }



}
