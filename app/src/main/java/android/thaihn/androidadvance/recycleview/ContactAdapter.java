package android.thaihn.androidadvance.recycleview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.thaihn.androidadvance.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private ArrayList<ContactObject> mListContact;
    private Context mContext;


    /**
     * Constructor
     *
     * @param context
     * @param list
     */
    public ContactAdapter(Context context, ArrayList<ContactObject> list) {
        this.mListContact = list;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ContactObject contact = mListContact.get(position);
        holder.textEmail.setText(contact.getmEmail());
        holder.textName.setText(contact.getmName());
    }

    @Override
    public int getItemCount() {
        return mListContact.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageAvatar;
        public TextView textName, textEmail;

        public ViewHolder(View v) {
            super(v);
            imageAvatar = v.findViewById(R.id.image_avatar);
            textName = v.findViewById(R.id.text_name);
            textEmail = v.findViewById(R.id.text_email);
        }
    }

    public void setmListContact(ArrayList<ContactObject> mListContact) {
        this.mListContact = mListContact;
    }

    public ArrayList<ContactObject> getmListContact() {
        return mListContact;
    }
}
