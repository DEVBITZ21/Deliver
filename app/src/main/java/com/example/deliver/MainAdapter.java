package com.example.deliver;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainAdapter<DialogPlus> extends FirebaseRecyclerAdapter <MainModel,MainAdapter.myViewHolder>{


    private myViewHolder Holder;

    public MainAdapter(FirebaseRecyclerOptions<MainModel> mainModelFirebaseRecyclerOptions) {

    }

    protected void  onBindViewHolder(@NonNull myViewHolder holder,final int position, @NonNull MainModel model) {
        holder.address.setText(model.getAddress());
        holder.email.setText(model.getEmail());
        holder.id.setText(model.getId());
        holder.name.setText(model.getName());
        holder.tpno.setText(model.getTpno());

        Glide.with(holder.img.getContext())
                .load(model.getImage())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);


        holder.btnEdit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                   final DialogPlus dialogPlus;
                dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContenHolder(new RecyclerView.ViewHolder(R.layout.update_popup))
                        .setExpanded(true,1200)
                        .create();

                //dialogPlus.show();
                View view = dialogPlus.getHolderView();

                EditText address = view.findViewById(R.id.addtext);
                EditText email= view.findViewById(R.id.mailtext);
                EditText id=view.findViewById(R.id.idno);
                EditText image=view.findViewById(R.id.img1);
                EditText name =view.findViewById(R.id.nametext);
                EditText tpno =view.findViewById(R.id.tpno);

                Button btnUpdate = view.findViewById(R.id.btnUpdate);
                address.setText(model.getAddress());
                email.setText(model.getEmail());
                id.setText(model.getId());
                image.setText(model.getImage());
                name.setText(model.getName());
                tpno.setText(model.getTpno());

                dialogPlus.show();


                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map =new HashMap<>();
                        map.put("address",address.getText().toString());
                        map.put("email",email.getText().toString());
                        map.put("id",id.getText().toString());
                        map.put("image",image.getText().toString());
                        map.put("name",name.getText().toString());
                        map.put("tpno",tpno.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("deliverpersons")
                                .child(getRef(position).getKey).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.address.getContext(),"Data Updated Success",Toast.LENGTH_SHORT.show());
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(Exception e) {
                                        Toast.makeText(holder.address.getContext(),"Data Updated Unsuccess",Toast.LENGTH_SHORT.show());
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });

            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Holder.address.getContext());
                builder.setTitle("Are you Sure?");
                builder.setMessage("Deleted data can't be Undo");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseReference deliverpersons = FirebaseDatabase.getInstance().getReference().child("deliverpersons")
                                .child(getRef(position.getKey()).removeValue();

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.address.getContext(),"Cancelled.",Toast.LENGTH_SHORT).show();

                    }
                });
                builder.show();
            }
        });
    }


@NonNull
public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType){
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item,parent,false);
    return new myViewHolder(view);
}

    public void startListening() {
    }

    public void stopListening() {
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        CircleImageView img;
        TextView address,email,id,name,tpno;

        Button btnEdit,btnDelete;


        public myViewHolder(@NonNull  View itemView) {
            super(itemView);

            img = (CircleImageView)itemView.findViewById(R.id.img1);
            address=(TextView)itemView.findViewById(R.id.addtext);
            email=(TextView)itemView.findViewById(R.id.mailtext);
            id=(TextView)itemView.findViewById(R.id.idno);
            name=(TextView)itemView.findViewById(R.id.nametext);
            tpno=(TextView)itemView.findViewById(R.id.tpno);



            btnEdit =(Button)itemView.findViewById(R.id.btnEdit);
            btnDelete=(Button)itemView.findViewById(R.id.btnDelete);

        }
    }
}
