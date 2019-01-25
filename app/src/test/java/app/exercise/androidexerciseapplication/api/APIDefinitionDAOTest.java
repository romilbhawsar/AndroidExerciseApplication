package app.exercise.androidexerciseapplication.api;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import app.exercise.androidexerciseapplication.beans.FactsBean;
import retrofit2.Response;
import retrofit2.Retrofit;


public class APIDefinitionDAOTest {

    private Retrofit retrofit;
    private APIDefinitionDAO apiConfig;

    @Before
    public void setUp()
    {
        retrofit = APIConfig.getRetrofit();

        apiConfig = APIConfig.getRetrofit().create(APIDefinitionDAO.class);
    }


    @Test
    public void test()
    {
        Response<FactsBean> response = null;

        try {
            response = apiConfig.getFactsData().execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(response);

        Assert.assertTrue(response.isSuccessful());
    }

}