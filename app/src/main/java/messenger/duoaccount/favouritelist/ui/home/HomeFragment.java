package messenger.duoaccount.favouritelist.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import messenger.duoaccount.favouritelist.CoffeItem;
import messenger.duoaccount.favouritelist.CoffeeAdapter;
import messenger.duoaccount.favouritelist.R;

public class HomeFragment extends Fragment {

    private ArrayList<CoffeItem> coffeItems = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new CoffeeAdapter(coffeItems, getActivity()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        coffeItems.add(new CoffeItem(R.drawable.girl1, "Ani", "0", "0"));
        coffeItems.add(new CoffeItem(R.drawable.girl2, "Mani", "1", "0"));
        coffeItems.add(new CoffeItem(R.drawable.girl3, "Alla", "2", "0"));
        coffeItems.add(new CoffeItem(R.drawable.girl4, "Karine", "3", "0"));
        coffeItems.add(new CoffeItem(R.drawable.girl5, "Smbi", "4", "0"));
        coffeItems.add(new CoffeItem(R.drawable.girl6   , "Monika", "5", "0"));
        coffeItems.add(new CoffeItem(R.drawable.girl7, "Vanuhi", "6", "0"));
        coffeItems.add(new CoffeItem(R.drawable.girl8, "Nare", "7", "0"));
        coffeItems.add(new CoffeItem(R.drawable.girl9, "Katrin", "8", "0"));
        coffeItems.add(new CoffeItem(R.drawable.girl10, "Jasmin", "9", "0"));
        return root;
    }
}