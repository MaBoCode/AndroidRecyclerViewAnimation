package fr.example.androidrecyclerviewanimation;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fr.example.androidrecyclerviewanimation.databinding.FrgMainBinding;

public class MainFragment extends Fragment implements DestinationAdapter.DestinationCardViewClick {

    protected FrgMainBinding mainFragmentBinding;

    protected List<Destination> destinations;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainFragmentBinding = FrgMainBinding.inflate(inflater, container, false);

        initDestinations();

        DestinationAdapter adapter = new DestinationAdapter(new Destination.DestinationDiff(), this);
        adapter.submitList(destinations);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mainFragmentBinding.destCardsRecyclerView.setHasFixedSize(true);
        mainFragmentBinding.destCardsRecyclerView.setLayoutManager(layoutManager);
        mainFragmentBinding.destCardsRecyclerView.setAdapter(adapter);

        //SnapHelper snapHelper = new LinearSnapHelper();
        //snapHelper.attachToRecyclerView(recyclerView);

        return mainFragmentBinding.getRoot();
    }

    @SuppressLint("NewApi")
    @Override
    public void onCardClick(View view, Destination destination) {
        MainFragmentDirections.ToDestinationFragment action = MainFragmentDirections.toDestinationFragment();
        action.setDestination(destination);

        FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder()
                .addSharedElement(view, "card_transition")
                .build();

        Navigation.findNavController(view).navigate(action, extras);
    }

    public void initDestinations() {
        Resources resources = getResources();

        Drawable greece = ResourcesCompat.getDrawable(resources, R.drawable.greece, null);
        Drawable portugal = ResourcesCompat.getDrawable(resources, R.drawable.lisbon, null);
        Drawable city = ResourcesCompat.getDrawable(resources, R.drawable.city, null);
        Drawable miami = ResourcesCompat.getDrawable(resources, R.drawable.miami, null);

        destinations = new ArrayList<>();
        destinations.add(new Destination(greece, "Santorini, Greece", 23));
        destinations.add(new Destination(portugal, "Lisbon, Portugal", 78));
        destinations.add(new Destination(city, "City, Country", 12));
        destinations.add(new Destination(miami, "Miami, USA", 94));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mainFragmentBinding = null;
    }

}
