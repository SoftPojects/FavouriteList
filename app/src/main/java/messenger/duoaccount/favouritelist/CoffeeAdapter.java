package messenger.duoaccount.favouritelist;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CoffeeAdapter extends RecyclerView.Adapter<CoffeeAdapter.ViewHolder> {
    private ArrayList<CoffeItem> coffeeItems;
    private Context mContext;
    private FavDB favDB;

    public CoffeeAdapter(ArrayList<CoffeItem> coffeeItems, Context context) {
        this.coffeeItems = coffeeItems;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        favDB = new FavDB(mContext);
        //create table on first
        SharedPreferences prefs = mContext.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", true);
        if (firstStart) {
            createTableOnFirstStart();
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final CoffeItem coffeItem = coffeeItems.get(position);

        readCursorData(coffeItem, holder);
        holder.imageView.setImageResource(coffeItem.getImageResources());
        holder.titleTextView.setText(coffeItem.getTitle());
    }

    @Override
    public int getItemCount() {
        return coffeeItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView titleTextView;
        private Button favBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            favBtn = itemView.findViewById(R.id.fav_btn1);

            // add to fav btn
            favBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    CoffeItem coffeItem = coffeeItems.get(position);
                    if (coffeItem.getFavStatus().equals("0")) {
                        coffeItem.setFavStatus("1");
                        favDB.insertDataIntoDatabase(coffeItem.getTitle(), coffeItem.getImageResources(),
                                coffeItem.getKey_id(), coffeItem.getFavStatus());
                        favBtn.setBackgroundResource(R.drawable.ic_favorite_red_24);
                    } else {
                        coffeItem.setFavStatus("0");
                        favDB.removeFav(coffeItem.getKey_id());
                        favBtn.setBackgroundResource(R.drawable.ic_favorite_shadow_24);
                    }
                }
            });
        }
    }

    private void createTableOnFirstStart() {
        favDB.insertEmpty();

        SharedPreferences prefs = mContext.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }

    private void readCursorData(CoffeItem coffeItem, ViewHolder viewHolder) {
        Cursor cursor = favDB.readAllData(coffeItem.getKey_id());
        SQLiteDatabase db = favDB.getReadableDatabase();
        try {
            while (cursor.moveToNext()) {
                String item_fav_status = cursor.getString(cursor.getColumnIndex(FavDB.FAVOURITE_STATUS));
                coffeItem.setFavStatus(item_fav_status);

                //check fav Status
                if (item_fav_status != null && item_fav_status.equals("1")) {
                    viewHolder.favBtn.setBackgroundResource(R.drawable.ic_favorite_red_24);
                } else if (item_fav_status != null && item_fav_status.equals("0")) {
                    viewHolder.favBtn.setBackgroundResource(R.drawable.ic_favorite_shadow_24);
                }
            }
        } finally {
            if (cursor != null && cursor.isClosed())
                cursor.close();
            db.close();
        }
    }
}
