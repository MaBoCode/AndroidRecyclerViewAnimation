package fr.example.androidrecyclerviewanimation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.transition.MaterialContainerTransform;

import fr.example.androidrecyclerviewanimation.databinding.FrgDestinationBinding;

public class DestinationFragment extends Fragment {

    @SuppressLint("NewApi")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FrgDestinationBinding binding = FrgDestinationBinding.inflate(inflater, container, false);

        MaterialToolbar tlbMain = binding.tlbMain;
        tlbMain.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigateUp();
            }
        });

        Destination destination = DestinationFragmentArgs.fromBundle(getArguments()).getDestination();
        binding.setDestination(destination);

        Interpolator interpolator = AnimationUtils.loadInterpolator(getContext(), R.interpolator.fast_out_slow_in);

        MaterialContainerTransform enterTransition = new MaterialContainerTransform();
        enterTransition.setInterpolator(interpolator);
        enterTransition.setDrawingViewId(R.id.destinationScrollView);
        enterTransition.setDuration(400L);

        MaterialContainerTransform returnTransition = new MaterialContainerTransform();
        returnTransition.setInterpolator(interpolator);
        returnTransition.setDuration(300L);

        setSharedElementEnterTransition(enterTransition);
        setSharedElementReturnTransition(returnTransition);

        return binding.getRoot();
    }

}
