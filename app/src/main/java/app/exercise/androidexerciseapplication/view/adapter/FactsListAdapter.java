package app.exercise.androidexerciseapplication.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import app.exercise.androidexerciseapplication.R;
import app.exercise.androidexerciseapplication.beans.FactsBean;
import app.exercise.androidexerciseapplication.databinding.ListItemBinding;
import app.exercise.androidexerciseapplication.utility.CommonMethods;


/**
 * Adapter class for facts list.
 */

public class FactsListAdapter extends RecyclerView.Adapter<FactsListAdapter.FactsListItemViewHolder> {

    private String TAG = "FactsListAdapter";
    private Context context;
    private List<FactsBean.FactsItem> factsList;
    private LayoutInflater layoutInflater;

    public FactsListAdapter(Context context, List<FactsBean.FactsItem> factsList) {
        this.context = context;
        this.factsList = factsList;
    }

    public void setFactsList(List<FactsBean.FactsItem> factsList) {
        this.factsList = factsList;
    }

    @Override
    public FactsListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) layoutInflater = LayoutInflater.from(context);
        ListItemBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.list_item, parent, false);
        return new FactsListItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FactsListItemViewHolder holder, int position) {
        try {
            //Bind data.
            holder.bindData(factsList.get(position));

        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }


    @Override
    public int getItemCount() {
        if (factsList != null)
            return factsList.size();
        return 0;
    }

    /* ViewHolder class for list items.*/
    public class FactsListItemViewHolder extends RecyclerView.ViewHolder {
        private ListItemBinding binding;

        public FactsListItemViewHolder(ListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        // Provide data to view
        public void bindData(FactsBean.FactsItem factsBean) {
            this.binding.setFactsBean(factsBean);

            // Load image
            CommonMethods.loadImage(context, factsBean.getImageHref(), binding.ivListImage);
        }

    }


}
