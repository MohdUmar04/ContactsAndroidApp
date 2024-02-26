package com.example.recyclerviewexample;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class RecyclerContactAdapter extends RecyclerView.Adapter<RecyclerContactAdapter.ViewHolder> {


    private Context context = null;
    private List<ContactModel> array;

    public RecyclerContactAdapter(Context context, List<ContactModel> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contact_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ContactModel contact = array.get(holder.getAdapterPosition());
        holder.img.setImageResource(contact.img);
        holder.name.setText(contact.name);
        holder.phno.setText(contact.number);

        holder.editInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);

                dialog.setContentView(R.layout.add_update_contact_layout);

                EditText editName, editNumber, editCountry;
                editName = dialog.findViewById(R.id.NewName);
                editNumber = dialog.findViewById(R.id.NewContactNo);
                MaterialButton save = dialog.findViewById(R.id.SaveNewContact);
                TextView heading = dialog.findViewById(R.id.addUpdateHeading);
                heading.setText("UPDATE CONTACT");
                save.setText("UPDATE");

                editName.setText(array.get(holder.getAdapterPosition()).name);
                editNumber.setText(array.get(holder.getAdapterPosition()).number);

                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = "", num = "" , country = "";
                        boolean flag = true;
                        if (!editName.getText().toString().equals(""))
                            name = editName.getText().toString();
                        else {
                            flag = false;
                            Toast.makeText(context, "Invalid Name", Toast.LENGTH_SHORT).show();
                        }
                        if (!editNumber.getText().toString().equals(""))
                            num = editNumber.getText().toString();
                        else {
                            flag = false;
                            Toast.makeText(context, "Invalid Number", Toast.LENGTH_SHORT).show();
                        }



                        array.set(holder.getAdapterPosition(),new ContactModel(name,num));

                        notifyItemChanged(holder.getAdapterPosition());
                        dialog.dismiss();
                    }
                });
                dialog.show();

            }
        });

        holder.contactview.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle("DELETE CONTACT?")
                        .setIcon(R.drawable.deleteicon)
                        .setMessage("Are you sure ?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                array.remove(holder.getAdapterPosition());
                                notifyItemRemoved(holder.getAdapterPosition());
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                builder.show();

                return true;
            }
        });


        animate(holder.contactview, holder.getPosition());
    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, phno;
        ImageView img;
        MaterialButton editInfo;
        CardView contactview;


        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.contact_Name);
            phno = itemView.findViewById(R.id.contact_Number);
            img = itemView.findViewById(R.id.contact_img);
            editInfo = itemView.findViewById((R.id.SaveNewContact));
            contactview = itemView.findViewById(R.id.card_View);
        }

    }
    private void animate(View toAnimate, int position) {
        Animation slidein = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
        toAnimate.startAnimation(slidein);
    }
}

