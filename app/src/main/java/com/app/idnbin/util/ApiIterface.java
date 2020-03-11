package com.app.idnbin.util;


import com.app.idnbin.MainScreen.Chat.Support.Model.BotReply;
import com.app.idnbin.MainScreen.Chat.Support.Model.SupportRequestBody;
import com.app.idnbin.MainScreen.MarketAnalysis.Newsfeed;
import com.app.idnbin.democall.BidData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiIterface {

    @GET("u/11158660/rss.xml")
    Call<Newsfeed>getnewsdata();

    @GET("device/rss/rss.html")
    Call<Newsfeed> getearningdata();

    @GET("forex-economic-calendar-events")
    Call<Newsfeed> getforexdata();

    @GET("quotes?pairs=EURUSD&api_key=B70qKwu64eVlzwfIwe900xhT2Pn6KvZS")
    Call<ArrayList<BidData>> getBidData();

    @POST("bots/idnbot/converse/{uid}")
    Call<BotReply> getSupportData(@Path("uid") String uid, @Body SupportRequestBody supportRequestBody);
}
