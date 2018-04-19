package android.thaihn.androidadvance.urlconnection;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.thaihn.androidadvance.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.ViewHolder> {

    private ArrayList<RepositoryObject> mListRepo;
    private Context mContext;


    public RepoAdapter(Context context, ArrayList<RepositoryObject> list) {
        this.mContext=context;
        this.mListRepo=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder vh = new ViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_repos, parent, false));
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RepositoryObject repositoryObject = mListRepo.get(position);
        holder.textBranch.setText(repositoryObject.getDefault_branch());
        holder.textLanguage.setText(repositoryObject.getLanguage());
        holder.textName.setText(repositoryObject.getName());
        holder.textRepo.setText(repositoryObject.getSsh_url());
        holder.textDesc.setText(repositoryObject.getDescription());
        holder.text_name_owner.setText(repositoryObject.getOwner().getLogin());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mListRepo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public CardView cardView;
        public TextView textName, textRepo, textDesc, textLanguage, textBranch,
                text_name_owner;

        public ViewHolder(View v) {
            super(v);
            cardView = (CardView) v.findViewById(R.id.card_repo);
            textName = (TextView) v.findViewById(R.id.text_name);
            textRepo = (TextView) v.findViewById(R.id.text_repo);
            textDesc = (TextView) v.findViewById(R.id.text_desc);
            textLanguage = (TextView) v.findViewById(R.id.text_language);
            textBranch = (TextView) v.findViewById(R.id.text_branch);
            text_name_owner = v.findViewById(R.id.text_name_owner);
        }
    }

    public ArrayList<RepositoryObject> getListRepo() {
        return mListRepo;
    }

    public void setListRepo(ArrayList<RepositoryObject> mListRepo) {
        this.mListRepo = mListRepo;
    }
}
