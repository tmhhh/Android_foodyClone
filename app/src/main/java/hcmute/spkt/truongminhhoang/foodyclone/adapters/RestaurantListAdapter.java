package hcmute.spkt.truongminhhoang.foodyclone.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hcmute.spkt.truongminhhoang.foodyclone.R;
import hcmute.spkt.truongminhhoang.foodyclone.classes.Restaurant;

public class RestaurantListAdapter extends BaseAdapter {
    private List<Restaurant> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public RestaurantListAdapter(Context aContext,  List<Restaurant> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.restaurant_list_item_layout, null);
            holder = new ViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.restaurantImage);
            holder.name = (TextView) convertView.findViewById(R.id.restaurantNameTv);
//            holder.category = (ImageView) convertView.findViewById(R.id.restaurantCate);
            holder.avgPrice = (TextView) convertView.findViewById(R.id.restaurantAvgPriceTv);
            holder.address = (TextView) convertView.findViewById(R.id.restaurantAddressTv);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Restaurant restaurant = this.listData.get(position);
        holder.name.setText(restaurant.getName());
        holder.avgPrice.setText("~" + restaurant.getAvgPrice()+" VND");
        holder.address.setText(restaurant.getAddress());

        int imageId = this.getMipmapResIdByName(restaurant.getImage());
//        int cateImage=this.getMipmapResIdByName(restaurant.getCategory());
//        holder.category.setImageResource(cateImage);
        holder.image.setImageResource(imageId);

        return convertView;
    }

    // Find Image ID corresponding to the name of the image (in the directory mipmap).
    public int getMipmapResIdByName(String resName)  {
        String pkgName = context.getPackageName();
        // Return 0 if not found.
        int resID = context.getResources().getIdentifier(resName , "drawable", pkgName);
        Log.i("RestaurantListView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }

    static class ViewHolder {
        ImageView image;
        TextView name;
//        ImageView category;
        TextView avgPrice;
        TextView address;
    }
}
