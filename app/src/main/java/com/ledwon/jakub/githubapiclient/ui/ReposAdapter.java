package com.ledwon.jakub.githubapiclient.ui;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ledwon.jakub.githubapiclient.R;
import com.ledwon.jakub.githubapiclient.databinding.RepoItemBinding;
import com.ledwon.jakub.githubapiclient.repository.data.Repo;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class ReposAdapter extends ListAdapter<Repo, ReposAdapter.ReposHolder> {
    private static final String TAG = "ReposAdapter";
    
    private ReposAdapter.onItemClickListener mListener;
    private RepoItemBinding mRepoItemBinding;

    public ReposAdapter(){
        super(new DiffUtil.ItemCallback<Repo>() {
            @Override
            public boolean areItemsTheSame(@NonNull Repo oldItem, @NonNull Repo newItem) {
                return oldItem.getName().equals(newItem.getName());
            }

            @Override
            public boolean areContentsTheSame(@NonNull Repo oldItem, @NonNull Repo newItem) {
                return oldItem.getName().equals(newItem.getName());
            }
        });
    }

    @NonNull
    @Override
    public ReposHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");
        mRepoItemBinding = DataBindingUtil.bind(LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_item, parent, false));
        return new ReposHolder(mRepoItemBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ReposHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: ");
        mRepoItemBinding.setRepo(getItem(position));
    }


    class ReposHolder extends RecyclerView.ViewHolder {

        public ReposHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (mListener != null && position != RecyclerView.NO_POSITION) {
                        mListener.onItemClick(getItem(position));
                    }
                }
            });

        }
    }

    public interface onItemClickListener {
        void onItemClick(Repo repo);
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        this.mListener = listener;
    }
}
