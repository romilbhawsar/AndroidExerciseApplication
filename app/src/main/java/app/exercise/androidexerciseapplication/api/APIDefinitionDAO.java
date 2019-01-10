package app.exercise.androidexerciseapplication.api;

import app.exercise.androidexerciseapplication.beans.FactsBean;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * This class is being used to define API definition.
 */
public interface APIDefinitionDAO {

    @GET("facts.json")
    public Call<FactsBean> getFactsData();

}
