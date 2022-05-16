package hcmute.spkt.truongminhhoang.foodyclone.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import hcmute.spkt.truongminhhoang.foodyclone.R;
import hcmute.spkt.truongminhhoang.foodyclone.classes.CartItem;
import hcmute.spkt.truongminhhoang.foodyclone.services.CartService;



public class CartListAdapter extends BaseAdapter {
    private List<CartItem> listData;
    private LayoutInflater layoutInflater;
    private Context context;
    CartAdapterItemCallback callback;

    public interface CartAdapterItemCallback {
        void updateTotal();
    }

    public CartListAdapter(Context aContext, List<CartItem> listData, CartAdapterItemCallback callback) {
        this.context = aContext;
        this.listData = listData;
        this.callback = callback;
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
            convertView = layoutInflater.inflate(R.layout.cart_list_item_layout, null);
            holder = new ViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.cartItemImage);
            holder.name = (TextView) convertView.findViewById(R.id.cartItemNameTv);
            holder.price = (TextView) convertView.findViewById(R.id.cartItemPriceTv);
            holder.quantity = (EditText) convertView.findViewById(R.id.cartItemQuantityEt);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        CartItem item = this.listData.get(position);

        // Handler remove cart item
        ImageButton trashBtn = (ImageButton) convertView.findViewById(R.id.cartTrashBtn);
        trashBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CartService.removeCart(item)) {
                    Toast.makeText(context, item.getFood().getName() + " removed", Toast.LENGTH_SHORT).show();
                    callback.updateTotal();
                    notifyDataSetChanged();
                }
            }
        });

        // Handler increase quantity
        ImageButton plusBtn = (ImageButton) convertView.findViewById(R.id.cartPlusBtn);
        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CartService.updateCart(item, item.getQuantity() + 1)) {
                    Toast.makeText(context, item.getFood().getName() + " quantity increased", Toast.LENGTH_SHORT).show();
                    callback.updateTotal();
                    notifyDataSetChanged();
                }
            }
        });

        // Handler decrease quantity
        ImageButton minusBtn = (ImageButton) convertView.findViewById(R.id.cartMinusBtn);
        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (item.getQuantity() != 1){
                    if (CartService.updateCart(item, item.getQuantity() - 1)) {
                        Toast.makeText(context, item.getFood().getName() + " quantity decreased", Toast.LENGTH_SHORT).show();
                        callback.updateTotal();
                        notifyDataSetChanged();
                    }
                }
            }
        });

//        EditText quantityEditView = (EditText) convertView.findViewById(R.id.cartItemQuantityEt);

        holder.name.setText(item.getFood().getName());
        holder.price.setText(Integer.toString(item.getFood().getPrice()) + "$");
        holder.quantity.setText(Integer.toString(item.getQuantity()));

        int imageId = this.getMipmapResIdByName(item.getFood().getImage());

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
        TextView price;
        EditText quantity;
    }
}
