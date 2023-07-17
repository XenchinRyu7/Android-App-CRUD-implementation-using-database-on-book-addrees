package com.indraazimi.bukualamat.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.indraazimi.bukualamat.data.DataAlamat;
import com.indraazimi.bukualamat.databinding.ItemListBinding;

import java.util.List;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData> {

    private final List<DataAlamat> listData;
    private final LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    public AdapterData(Context context, List<DataAlamat> listData) {
        this.listData = listData;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListBinding binding = ItemListBinding.inflate(inflater, parent, false);
        return new HolderData(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataAlamat data = listData.get(position);
        holder.bind(data);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public void setData(List<DataAlamat> data) {
        listData.clear();
        listData.addAll(data);
        notifyDataSetChanged();
    }


    public class HolderData extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private final ItemListBinding binding;

        public HolderData(@NonNull ItemListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void bind(DataAlamat data) {
            binding.textViewName.setText(data.getName());
            binding.textViewAddress.setText(data.getAddress());
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(getAdapterPosition());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (onItemLongClickListener != null) {
                onItemLongClickListener.onItemLongClick(getAdapterPosition());
                return true;
            }
            return false;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(int position);
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.onItemLongClickListener = listener;
    }
}
