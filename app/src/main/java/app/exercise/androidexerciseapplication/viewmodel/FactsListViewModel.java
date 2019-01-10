package app.exercise.androidexerciseapplication.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import app.exercise.androidexerciseapplication.BuildConfig;
import app.exercise.androidexerciseapplication.R;
import app.exercise.androidexerciseapplication.api.APIConfig;
import app.exercise.androidexerciseapplication.beans.FactsBean;
import app.exercise.androidexerciseapplication.utility.CommonMethods;

/**
 * ViewModel for FactsListFragment
 */
public class FactsListViewModel extends AndroidViewModel {

    private MutableLiveData<String> message;

    private MutableLiveData<FactsBean> factsBeanLiveData;

    public FactsListViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<String> getMessage() {

        if (message == null) {
            message = new MutableLiveData<String>();
        }

        return message;
    }

    public MutableLiveData<FactsBean> getFactsList() {

        if (factsBeanLiveData == null) {
            factsBeanLiveData = new MutableLiveData<FactsBean>();
            loadData();
        }

        return factsBeanLiveData;
    }

    /**
     * Call API to get data
     */
    public void loadData() {
        if (CommonMethods.isInternetConnected(getApplication())) {
            APIConfig.callAPI(BuildConfig.API_FACTS, new APIConfig.APICallResponseListener() {
                @Override
                public void onResponseReceived(FactsBean factsBean, String message) {

                    if (factsBean != null) {
                        factsBeanLiveData.setValue(factsBean);
                    } else {
                        if (message != null && !message.isEmpty())
                            FactsListViewModel.this.message.setValue(message);
                    }

                }
            });

        } else {
            message.setValue(getApplication().getString(R.string.msg_no_internet_connection));
        }

    }

}
