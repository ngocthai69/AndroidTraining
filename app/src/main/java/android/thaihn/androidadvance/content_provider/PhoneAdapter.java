package android.thaihn.androidadvance.content_provider;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.thaihn.androidadvance.R;
import android.thaihn.androidadvance.recycleview.CircleImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.ViewHolder> {

    private ArrayList<PhoneObject> mListPhone;
    private Context mContext;
    private ClickPhone mCLickPhone;

    public PhoneAdapter(Context context, ArrayList<PhoneObject> mList) {
        this.mListPhone = mList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder vh = new ViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_phone, parent, false));
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final PhoneObject phoneObject = mListPhone.get(position);
        holder.mTextName.setText(phoneObject.getName());
        holder.mTextPhone.setText(phoneObject.getNumber());
        holder.mCardPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCLickPhone.onClick(phoneObject);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mListPhone.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView mImageAvatar;
        private TextView mTextName, mTextPhone;
        private CardView mCardPhone;


        public ViewHolder(View v) {
            super(v);
            mImageAvatar = v.findViewById(R.id.image_avatar);
            mTextName = v.findViewById(R.id.text_name);
            mTextPhone = v.findViewById(R.id.text_phone);
            mCardPhone = v.findViewById(R.id.card_phone);

        }
    }

    public void setCLickPhone(ClickPhone mCLickPhone) {
        this.mCLickPhone = mCLickPhone;
    }

    public ArrayList<PhoneObject> getListPhone() {
        return mListPhone;
    }

    public void setListPhone(ArrayList<PhoneObject> mListPhone) {
        this.mListPhone = mListPhone;
    }

    public interface ClickPhone {
        void onClick(PhoneObject phone);
    }
}
