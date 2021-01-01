package fr.example.androidrecyclerviewanimation;

import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import java.io.Serializable;

public class Destination implements Serializable {

    public Drawable drawable;
    public String title;
    public int availableApartments;

    public Destination(Drawable drawable, String title, int availableApartments) {
        this.drawable = drawable;
        this.title = title;
        this.availableApartments = availableApartments;
    }

    public static class DestinationDiff extends DiffUtil.ItemCallback<Destination> {

        @Override
        public boolean areItemsTheSame(@NonNull Destination oldItem, @NonNull Destination newItem) {
            return oldItem.title.equals(newItem.title);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Destination oldItem, @NonNull Destination newItem) {
            return oldItem == newItem;
        }
    }
}
