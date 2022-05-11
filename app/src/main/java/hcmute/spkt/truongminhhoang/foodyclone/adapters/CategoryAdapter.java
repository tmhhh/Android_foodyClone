package hcmute.spkt.truongminhhoang.foodyclone.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hcmute.spkt.truongminhhoang.foodyclone.R;
import hcmute.spkt.truongminhhoang.foodyclone.classes.Category;


public class CategoryAdapter extends BaseAdapter {
    List<Category> listCate;
    private LayoutInflater layoutInflater;
    private Context context;

    public CategoryAdapter(Context aContext, List<Category> listCate) {
        this.context = aContext;
        this.listCate = listCate;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listCate.size();
    }

    @Override
    public Object getItem(int position) {
        return listCate.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        RestaurantListAdapter.ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.category_item, null);
            holder = new RestaurantListAdapter.ViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.ivCateImage);
            holder.name = (TextView) convertView.findViewById(R.id.tvCateName);

            convertView.setTag(holder);
        } else {
            holder = (RestaurantListAdapter.ViewHolder) convertView.getTag();
        }
        Category cate = this.listCate.get(position);
        holder.name.setText(cate.getName());
        int imageId = this.getMipmapResIdByName(cate.getImage());
        holder.image.setImageResource(imageId);
        return convertView;
    }

    // Find Image ID corresponding to the name of the image (in the directory mipmap).
    public int getMipmapResIdByName(String resName) {
        String pkgName = context.getPackageName();
        // Return 0 if not found.
        int resID = context.getResources().getIdentifier(resName, "drawable", pkgName);
        return resID;
    }

    static class ViewHolder {
        ImageView image;
        TextView name;
    }
}
