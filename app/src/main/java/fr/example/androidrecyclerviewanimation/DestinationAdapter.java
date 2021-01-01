package fr.example.androidrecyclerviewanimation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import fr.example.androidrecyclerviewanimation.databinding.DestinationCardItemBinding;

public class DestinationAdapter extends ListAdapter<Destination, DestinationAdapter.ViewHolder> {

    protected DestinationCardViewClick listener;

    public DestinationAdapter(@NonNull DiffUtil.ItemCallback<Destination> diffCallback, DestinationCardViewClick listener) {
        super(diffCallback);
        this.listener = listener;
    }

    public DestinationAdapter(@NonNull AsyncDifferConfig<Destination> config, DestinationCardViewClick listener) {
        super(config);
        this.listener = listener;
    }

    public interface DestinationCardViewClick {
        void onCardClick(View view, Destination destination);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final DestinationCardItemBinding binding;

        public ViewHolder(DestinationCardItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(final Destination destination, final DestinationCardViewClick listener) {
            binding.setDestination(destination);
            binding.setDestinationClick(listener);
            binding.executePendingBindings();
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DestinationCardItemBinding binding = DestinationCardItemBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Destination destination = getItem(position);
        holder.bind(destination, listener);
    }

}
