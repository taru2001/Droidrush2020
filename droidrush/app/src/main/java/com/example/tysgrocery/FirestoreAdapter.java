//package com.example.tysgrocery;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
//import com.firebase.ui.firestore.FirestoreRecyclerOptions;
//
//public class FirestoreAdapter extends FirestoreRecyclerAdapter<ProductModel, FirestoreAdapter.ProductViewHolder> {
//
//
//    public FirestoreAdapter(@NonNull FirestoreRecyclerOptions<ProductModel> options) {
//        super(options);
//    }
//
//    @Override
//    protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull ProductModel model) {
//
//        holder.list_name.setText(model.getQuantity());
//    }
//
//    @NonNull
//    @Override
//    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_product,parent,false);
//        return new Beverages.ProductViewHolder(view);
//    }
//
//    private class ProductViewHolder extends RecyclerView.ViewHolder {
//
//        private TextView list_name;
//
//
//        public ProductViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            list_name = itemView.findViewById(R.id.productlist);
//        }
//    }
//}
