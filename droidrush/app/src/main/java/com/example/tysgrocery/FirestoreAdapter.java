package com.example.tysgrocery;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FirestoreAdapter extends FirestoreRecyclerAdapter<ProductModel, FirestoreAdapter.ProductViewHolder> implements Filterable {

    private OnListItemClick onListItemClick;
    List<OnListItemClick> l;


    public FirestoreAdapter(@NonNull FirestoreRecyclerOptions<ProductModel> options, OnListItemClick onListItemClick) {
        super(options);
        this.onListItemClick=onListItemClick;
    }

    @Override
    protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull ProductModel model) {

        holder.list_name.setText(model.getProduct());
        holder.list_price.setText(model.getPrice());
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_product,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

              l = new ArrayList<>();
//            if(constraint.toString().isEmpty()){
                l.addAll((Collection<? extends OnListItemClick>) onListItemClick);
//            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = l;


            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            notifyDataSetChanged();

        }
    };

    public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView list_name;
        private TextView list_price;


        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            list_name = itemView.findViewById(R.id.product);
            list_price = itemView.findViewById(R.id.price);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onListItemClick.onItemClick(getItem(getAdapterPosition()),getAdapterPosition());
        }
    }

    public interface OnListItemClick{
        void onItemClick(ProductModel a, int position);
    }
}
