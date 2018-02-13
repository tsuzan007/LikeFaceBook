package com.example.macbookpro.MovieBook.Model;

import com.example.macbookpro.MovieBook.POJOClasses.User_WithFacebook;
import com.example.macbookpro.MovieBook.Presenter.MVPContracts;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;


public class Model implements MVPContracts.RequestModel {


    private static User_WithFacebook user_withFacebook;

    @Override
    public void getUserInfo() {
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "me?fields=id,name,email,birthday",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        if (response.getError() == null) {
                            JSONObject jsonObject = response.getJSONObject();
                            try {
                                String id = jsonObject.getString("id");
                                String name = jsonObject.getString("name");
                                String email = jsonObject.getString("email");
                                String birthday = jsonObject.getString("birthday");
                                user_withFacebook = new User_WithFacebook(name, email, id, birthday);
                                EventBus.getDefault().post(user_withFacebook);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }

                    }
                }
        ).executeAsync();
        EventBus.getDefault().post("Request done.");

    }


    public void getFeedsList() {
//        new GraphRequest(
//                AccessToken.getCurrentAccessToken(),
//                "me?fields=feed{full_picture,story,created_time,id}",
//                null,
//                HttpMethod.GET,
//                new GraphRequest.Callback() {
//                    public void onCompleted(GraphResponse response) {
//                        if (response.getError() == null) {
//                            JSONObject jsonObject = response.getJSONObject();
//                            try {
//                                JSONObject feedmasterobject = jsonObject.getJSONObject("feed");
//                                JSONArray data_feed = feedmasterobject.getJSONArray("data");
//                                for (int x = 0; x < data_feed.length(); x++) {
//                                    JSONObject feed_object = data_feed.getJSONObject(x);
//                                    //  Log.e("....",feed_object.toString());
//                                    String story;
//                                    String url;
//                                    try {
//                                        story = feed_object.getString("story");
//
//
//                                    } catch (JSONException ex) {
//
//                                            story = "";
//
//                                    }
//                                    try {
//                                        url = feed_object.getString("full_picture");
//                                    }
//                                    catch (JSONException e){
//                                        url="https://scontent.xx.fbcdn.net/v/t1.0-1/p50x50/12038462_1069954399683381_3955124865656062195_n.jpg?oh=f770e4c0dec423944ecc700c5b96fe01&oe=5A201292";
//                                    }
//
//                                    String created_time = feed_object.getString("created_time");
//                                    String id = feed_object.getString("id");
//
//                                    FeedClass mystory = new FeedClass(story, created_time, id,url);
//
//                                    EventBus.getDefault().post(mystory);
//                                }
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//                        }
//                    }
//                }
//        ).executeAsync();
    }


}
