package android.thaihn.androidadvance.recycleview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.thaihn.androidadvance.R;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    public static final int TYPE_ITEM = 0;
    public static final int TYPE_LOADING = 1;

    protected ViewHolder viewHolder;

    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;
    private ILoadMore iLoadMore;
    private IClickListener mListener;


    private ArrayList<ContactObject> mListContact;
    private Context mContext;


    public SparseIntArray layoutContainer = new SparseIntArray() {
        {
            put(TYPE_ITEM, R.layout.item_contact);
            put(TYPE_LOADING, R.layout.item_loading);
        }
    };

    @Override
    public int getItemViewType(int position) {
        return mListContact.get(position) != null ? TYPE_ITEM : TYPE_LOADING;
    }

    /**
     * Constructor
     *
     * @param context
     * @param list
     */
    public ContactAdapter(Context context, ArrayList<ContactObject> list, RecyclerView mRecycle) {
        this.mListContact = list;
        this.mContext = context;

        if (mRecycle.getLayoutManager() instanceof LinearLayoutManager) {
            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mRecycle.getLayoutManager();
            mRecycle.addOnScrollListener(new RecyclerView.OnScrollListener() {

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    totalItemCount = linearLayoutManager.getItemCount();
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    if (!loading
                            && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                        // End has been reached
                        // Do something
                        if (iLoadMore != null) {
                            iLoadMore.onLoadMore();
                        }
                        loading = true;
                    }

                }
            });
        }

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_ITEM: {
                viewHolder = new ItemViewHolder(LayoutInflater.from(parent.getContext()).
                        inflate(layoutContainer.get(viewType), parent, false));
                return viewHolder;
            }
            case TYPE_LOADING: {
                viewHolder = new ProgressViewHolder(LayoutInflater.from(parent.getContext()).
                        inflate(layoutContainer.get(viewType), parent, false));
                return viewHolder;
            }
            default: {
                return onCreateViewHolder(parent, viewType);
            }
        }

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder instanceof ProgressViewHolder) {
            ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
        } else if (holder instanceof ItemViewHolder) {
            final ContactObject contactObject = mListContact.get(position);
            ((ItemViewHolder) holder).textEmail.setText(contactObject.getmEmail());
            ((ItemViewHolder) holder).textName.setText(contactObject.getmName());
            ((ItemViewHolder) holder).cardContact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onClick(contactObject);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mListContact.size();
    }

    // sub ViewHolder
    public class ItemViewHolder extends ViewHolder {

        public ImageView imageAvatar;
        public TextView textName, textEmail;
        public CardView cardContact;

        public ItemViewHolder(View v) {
            super(v);
            imageAvatar = v.findViewById(R.id.image_avatar);
            textName = v.findViewById(R.id.text_name);
            textEmail = v.findViewById(R.id.text_email);
            cardContact = v.findViewById(R.id.card_contact);
        }
    }

    public class ProgressViewHolder extends ViewHolder {

        public ProgressBar progressBar;

        public ProgressViewHolder(View v) {
            super(v);
            progressBar = v.findViewById(R.id.progressbar);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View view) {
            super(view);
        }
    }

    public void setLoaded() {
        loading = false;
    }

    public void setListContact(ArrayList<ContactObject> mListContact) {
        this.mListContact = mListContact;
    }

    public ArrayList<ContactObject> getListContact() {
        return mListContact;
    }

    public interface IClickListener {
        void onClick(ContactObject contactObject);
    }

    public void setListener(IClickListener mListener) {
        this.mListener = mListener;
    }

    public void setLoadMore(ILoadMore iLoadMore) {
        this.iLoadMore = iLoadMore;
    }
}
