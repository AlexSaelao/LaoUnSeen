package com.as.androidunseen.in.lao.laounseen.utility;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.as.androidunseen.in.lao.laounseen.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder> {

    private Context context;
    private ArrayList<String> photoStringArrayList, nameStringArrayList, postStringArrayList;
    private LayoutInflater layoutInflater;

    public ServiceAdapter(Context context, ArrayList<String> photoStringArrayList, ArrayList<String> nameStringArrayList, ArrayList<String> postStringArrayList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.photoStringArrayList = photoStringArrayList;
        this.nameStringArrayList = nameStringArrayList;
        this.postStringArrayList = postStringArrayList;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.recycle_view_service, parent, false);
        ServiceViewHolder serviceViewHolder = new ServiceViewHolder(view);

        return serviceViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {

        String urlPathString = photoStringArrayList.get(position);
        String nameString = nameStringArrayList.get(position);
        String postString = postStringArrayList.get(position);

        holder.nameTextView.setText(nameString);
        holder.postTextView.setText(postString);

        Picasso.get().load(urlPathString)
                .resize(150,150)
                .into(holder.circleImageView);
    }

    @Override
    public int getItemCount() {
        return nameStringArrayList.size();
    }


    public class ServiceViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView circleImageView;
        private TextView nameTextView, postTextView;

        public ServiceViewHolder(View itemView) {
            super(itemView);

            circleImageView = itemView.findViewById(R.id.circlePhoto);
            nameTextView = itemView.findViewById(R.id.txtName);
            postTextView = itemView.findViewById(R.id.txtPost);

        }


    } // Service Class






} // Main Class
