package hcmute.spkt.truongminhhoang.foodyclone.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hcmute.spkt.truongminhhoang.foodyclone.R;
import hcmute.spkt.truongminhhoang.foodyclone.classes.Food;

public class FoodListAdapter extends BaseAdapter {
    private List<Food> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public FoodListAdapter(Context aContext, List<Food> listData) {
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
            convertView = layoutInflater.inflate(R.layout.food_list_item_layout, null);
            holder = new ViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.foodImage);
            holder.name = (TextView) convertView.findViewById(R.id.foodNameTv);
            holder.category = (TextView) convertView.findViewById(R.id.foodCategoryTv);
            holder.price = (TextView) convertView.findViewById(R.id.foodPriceTv);
            Button addToCart = (Button) convertView.findViewById(R.id.addToCartBtn);
            addToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Food food = this.listData.get(position);
        holder.name.setText(food.getName());
        holder.category.setText(food.getCategory());
        holder.price.setText(food.getPrice() + "$");

        int imageId = this.getMipmapResIdByName(food.getImage());

        holder.image.setImageResource(imageId);

        return convertView;
    }

    // Find Image ID corresponding to the name of the image (in the directory mipmap).
    public int getMipmapResIdByName(String resName)  {
        String pkgName = context.getPackageName();
        // Return 0 if not found.
        int resID = context.getResources().getIdentifier(resName , "drawable", pkgName);
        Log.i("FoodListView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }

    static class ViewHolder {
        ImageView image;
        TextView name;
        TextView category;
        TextView price;
    }
}
