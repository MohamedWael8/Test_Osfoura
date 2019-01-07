package com.example.hawkt.test_osfoura

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.hawkt.test_osfoura.R.string.CONSUMER_KEY
import com.example.hawkt.test_osfoura.R.string.CONSUMER_SECRET
import com.twitter.sdk.android.core.*
import com.twitter.sdk.android.core.internal.network.OAuth1aInterceptor
import com.twitter.sdk.android.core.models.Tweet
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.twitter.sdk.android.core.models.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import com.twitter.sdk.android.core.TwitterException
import com.twitter.sdk.android.core.services.StatusesService
import com.twitter.sdk.android.core.TwitterCore
import com.twitter.sdk.android.core.TwitterApiClient
import com.twitter.sdk.android.core.internal.network.OkHttpClientHelper
import com.twitter.sdk.android.core.models.UserBuilder
import com.twitter.sdk.android.core.models.UserValue
import com.twitter.sdk.android.tweetui.TimelineResult
import com.twitter.sdk.android.tweetui.UserTimeline
import okhttp3.Credentials
import okhttp3.Interceptor
import retrofit2.http.FormUrlEncoded
import java.io.IOException


class MainActivity : AppCompatActivity()
{


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        val config = TwitterConfig.Builder(this)
            .logger(DefaultLogger(Log.DEBUG))
            .twitterAuthConfig(
                TwitterAuthConfig(
                    getString(R.string.CONSUMER_KEY),
                    getString(R.string.CONSUMER_SECRET)
                )
            )
            .debug(true)
            .build()
        Twitter.initialize(config)
        //var credentials = Credentials.basic(getString(CONSUMER_KEY),getString(CONSUMER_SECRET))
        //var token: OAuthToken? = null
        Log.d( "Keys" ,TwitterCore.getInstance().authConfig.consumerKey)
        //Log.d( "TestSession" ,"H")
        setContentView(R.layout.activity_main)

        /*val retrofit =  Retrofit.Builder()
            .baseUrl("https://api.twitter.com/1.1/")
            .addConverterFactory(GsonConverterFactory.create())
            // Twitter interceptor
            .client(
                OkHttpClient.Builder()
                .addInterceptor(OAuth1aInterceptor(TwitterCore.getInstance().getSessionManager().getActiveSession(), TwitterCore.getInstance().getAuthConfig()))
            .build())
        .build()*/

        /*val okHttpClient = OkHttpClient.Builder().addInterceptor(object : Interceptor
        {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): okhttp3.Response
            {
                val originalRequest = chain.request()
                var cred : String

                if(token != null)
                {
                    cred = token!!.authorization
                }
                else
                {
                    cred = credentials
                }
                val builder = originalRequest.newBuilder().header(
                    "Authorization",cred

                )

                val newRequest = builder.build()
                return chain.proceed(newRequest)
            }
        }).build()*/


    //

       val userServices = UserServices
       /* val call = userServices.create().search("mwael8" , 1 , 5 , false)
        call.enqueue(
            object : Callback<List<User>>() {
                override fun success(result: Result<List<User>>) {
                    print(result)

                    Log.d("UserTest", result.data[0].id.toString())
                }

                override fun failure(exception: TwitterException) {
                    print(exception)
                    Log.d("UserTest", "Error" + exception.toString())
                }
            }
        )*/
        val call2 = userServices.create().show(null,395571736,1,false)
        call2.enqueue(
            object : Callback<User>() {
                override fun success(result: Result<User>) {
                    print(result)

                    Log.d("UserTest2", result.data.name)
                }

                override fun failure(exception: TwitterException) {
                    print(exception)
                    Log.d("UserTest2", "E" + exception.toString())
                }
            }
        )




        //Return userTimeLine
        /*
        val userTimeline = UserTimeline.Builder().screenName("mwael8").maxItemsPerRequest(2).build()
        userTimeline.next(null , object : Callback<TimelineResult<Tweet>>()
        {
            override fun success(result: Result<TimelineResult<Tweet>>)
            {
                Log.d("TimeLineTest" , result.data.items[0].text)
            }

            override fun failure(exception: TwitterException)
            {
                print(exception)
                Log.d("TimeLineTest" , "Error" + exception.toString())
            }
          })
         */

        //Returns Tweet with specific id

        /*val twitterApiClient = TwitterCore.getInstance().apiClient
        val statusesService = twitterApiClient.statusesService
        val call = statusesService.show(524971209851543553L, null, null, null)
        call.enqueue(object : Callback<Tweet>()
        {
            override fun success(result: Result<Tweet>)
            {
                print(result.data.text)

                Log.d("TweetTest" , "ActualResult" + result.data.text)
            }

            override fun failure(exception: TwitterException)
            {
                print(exception)
                Log.d("TweetTest" , "Error" + exception.toString())
            }
        })*/
    }
}

