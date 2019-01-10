package app.exercise.androidexerciseapplication.view.fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.exercise.androidexerciseapplication.R;
import app.exercise.androidexerciseapplication.beans.FactsBean;
import app.exercise.androidexerciseapplication.databinding.FragmentFactsListBinding;
import app.exercise.androidexerciseapplication.utility.CommonMethods;
import app.exercise.androidexerciseapplication.view.activities.MainActivity;
import app.exercise.androidexerciseapplication.view.adapter.FactsListAdapter;
import app.exercise.androidexerciseapplication.viewmodel.FactsListViewModel;


/**
 * A Fragment class is being used to render list of some items.
 */
public class FactsListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private Context context;
    private FragmentFactsListBinding binding;
    private boolean isViewCreated;

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView factsListRV;
    private FactsListAdapter factsListAdapter;

    private FactsListViewModel viewModel;

    public FactsListFragment(){
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (binding == null) {
            // Inflate the layout for this fragment
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_facts_list, container, false);
        }
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*Initialize views after fragment loaded*/
        if (!isViewCreated) {
            isViewCreated = true;
            initViews();
        }

    }

    /**
     * Use this method to initialize views.
     */
    public void initViews() {
        /*Initialization of list view*/
        factsListRV = binding.rvFactsList;
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        factsListRV.setLayoutManager(mLayoutManager);
        factsListAdapter = new FactsListAdapter(context, null);
        factsListRV.setAdapter(factsListAdapter);

        /*Initialization of Swipe to refresh*/
        swipeRefreshLayout = binding.layoutSwipeToRefresh;
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));

        viewModel = ViewModelProviders.of(this.getActivity()).get(FactsListViewModel.class);

        /*Observer to notify message data changed*/
        viewModel.getMessage().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String message) {

                setRefreshingOff();

                if (message != null && !message.isEmpty())
                    CommonMethods.showAlertDialog(context, getString(R.string.alert), message);

            }
        });

        // Show progress bar while data loading
        swipeRefreshLayout.setRefreshing(true);

        /*Observer to notify list data changed*/
        viewModel.getFactsList().observe(this.getActivity(), new Observer<FactsBean>() {
            @Override
            public void onChanged(@Nullable FactsBean factsBean) {

                if (factsBean != null) {
                    // Recycler view setup
                    if (factsListAdapter != null) {
                        factsListAdapter.setFactsList(factsBean.getFactsList());
                        factsListAdapter.notifyDataSetChanged();
                    }

                    // Set actionbar title
                    if (factsBean.getTitle() != null && !factsBean.getTitle().isEmpty()) {
                        if (((MainActivity) getActivity()) != null)
                            ((MainActivity) getActivity()).setActionBarTitle(factsBean.getTitle());
                    }

                }

                setRefreshingOff();

            }
        });
    }

    @Override
    public void onRefresh() {

        if (viewModel != null) {
            viewModel.loadData();
        }

    }

    /**
     * This method is used to dismiss progress bar.
     */
    public void setRefreshingOff() {
        if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

}
