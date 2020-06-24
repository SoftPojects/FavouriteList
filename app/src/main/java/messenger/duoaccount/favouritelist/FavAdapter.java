package messenger.duoaccount.favouritelist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.ViewHolder> {

    private Context mContext;
    private List<FavItem> favItemList;
    private FavDB favDB;

    public FavAdapter(Context context, List<FavItem> favItemList) {
        this.mContext = context;
        this.favItemList = favItemList;
    }

    @NonNull
    @Override
    public FavAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_item,
                parent, false);
        favDB = new FavDB(mContext);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavAdapter.ViewHolder holder, int position) {
        holder.fav_text_view.setText(favItemList.get(position).getItemTitle());
        holder.fav_image_view.setImageResource(favItemList.get(position).getItem_image());
    }

    @Override
    public int getItemCount() {
        return favItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView fav_text_view;
        Button fav_btn;
        ImageView fav_image_view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fav_text_view = itemView.findViewById(R.id.fav_text_view);
            fav_btn = itemView.findViewById(R.id.fav_btn2);
            fav_image_view = itemView.findViewById(R.id.fav_image_view);

            //remove fav after click
            fav_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final FavItem favItem = favItemList.get(position);
                    favDB.removeFav(favItem.getKey_id());
                    removeItemPosition(position);
                }
            });
        }

        private void removeItemPosition(int position) {
            favItemList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, favItemList.size());
        }
    }
}
