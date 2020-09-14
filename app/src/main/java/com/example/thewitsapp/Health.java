package com.example.thewitsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class Health extends AppCompatActivity {

    //This where the student will come in.
    LinearLayout RestHolder, RecipeHolder, TipsHolder,FoodsHolder;

    RecyclerView recyclerView;
    ArrayList<ModelFood> foodsList;
    //
    LinearLayout RestRecHolder, RecipeRecHolder, FoodRecHolder, TipsRecHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);

        //initializing the Horizontal scrollViews:
        /*RestHolder = findViewById(R.id.restauratsHorizontalHolder);
        RecipeHolder = findViewById(R.id.RecipesHorizontalHolder);
        FoodsHolder = findViewById(R.id.FoodsHorizontalHolder);
        TipsHolder = findViewById(R.id.HealthHorizontalHolder);


        //initializing the recommendation linearLayouts:
        RestRecHolder = findViewById(R.id.ourRestRecommendations);
        RecipeRecHolder = findViewById(R.id.ourRecipRecommendations);
        FoodRecHolder = findViewById(R.id.ourFoodsRecommendations);
        TipsRecHolder = findViewById(R.id.ourHealthTipsRecommendations);
        */

        //For adding new Post

        /*FloatingActionButton addHealthPost = findViewById(R.id.add_health_post);
        addHealthPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Health.this, PostOnHealth.class));
                //Toast.makeText(Health.this,"I have been clicked",Toast.LENGTH_SHORT).show();
            }
        });*/

        foodsList = new ArrayList<>();

        //this is where you add the dishes from the server
        String recipe = "Just add water then you're good to go, or simply add anything that you want to add then cook it for 112 minutes tops";

        foodsList.add(new ModelFood(R.drawable.chow_mein, "Chow Mein", "Kara Nichas", "R25", "Ingredients: Noodles, chicken strips, vegetable mix",recipe));
        foodsList.add(new ModelFood(R.drawable.mogodu, "Mogodu", "Khutso's Restaurant", "R20", "Ingredients: Beef tripe, potatoes, onions, beef broth",recipe));
        foodsList.add(new ModelFood(R.drawable.sandwich, "Healthy Sandwich", "Sizwe's Restaurant", "R15", "Ingredients: Brown bread, cheese, 3 eggs, bacon",recipe));
        foodsList.add(new ModelFood(R.drawable.spicy_chicken, "Spicy Chicken", "Xolani's Restaurant", "R35", "Ingredients: chicken breasts, carrots, potatoes, lentils",recipe));
        foodsList.add(new ModelFood(R.drawable.steak_caper_stroganoff, "Steak stroganoff", "Percy's place", "R27", "Ingredients: Italian noodles, mayo, chicken strips",recipe));

        recyclerView = findViewById(R.id.rView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.LayoutManager rvLayManager = layoutManager;
        recyclerView.setLayoutManager(rvLayManager);

        HealthAdapter healthAdapter = new HealthAdapter(this, foodsList);
        recyclerView.setAdapter(healthAdapter);
    }

/*
    //We will work in here
    @Override
    protected void onResume() {
        super.onResume();

        //for horizontal shandis
        getCategoryPosts(Health.this,"Food",FoodsHolder);
        getCategoryPosts(Health.this,"Recipes",RecipeHolder);
        getCategoryPosts(Health.this,"Tips",TipsHolder);
        getCategoryPosts(Health.this,"Rest",RestHolder);

        //Recommendations
        getOurRecs(Health.this,"REC_REST",RestRecHolder);
        getOurRecs(Health.this,"REC_RECIPES",RecipeRecHolder);
        getOurRecs(Health.this,"REC_FOOD",FoodRecHolder);
        getOurRecs(Health.this,"REC_TIPS",TipsRecHolder);



    }

    @SuppressLint("StaticFieldLeak")
    public void getCategoryPosts(final Context context, final String category, final LinearLayout linearLayout){

        ContentValues params = new ContentValues();

        linearLayout.removeAllViews();

        new ServerCommunicator("https://lamp.ms.wits.ac.za/~s1872817/getHealthPosts.php",params) {
            @Override
            protected void onPostExecute(String output) {

                if(output.equals("0")){

                    Toast.makeText(Health.this,"An error has occurred",Toast.LENGTH_SHORT).show();
                }

                else{

                    try {
                        JSONArray jsonArray = new JSONArray(output);

                        for(int i=0;i<jsonArray.length();i++){

                            View view = View.inflate(context,R.layout.health_post_card,null);

                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            if(jsonObject.getString("CAT").equals(category)){

                                final String nameOfPost = jsonObject.getString("POST_NAME");
                                final String stdNumber = jsonObject.getString("STUDENT_NUM");
                                String imgURL = "https://lamp.ms.wits.ac.za/~s1872817/HealthPostImgs/"+stdNumber+nameOfPost+".JPG";

                                ImageView postImg = view.findViewById(R.id.healthPostImage);
                                TextView postName = view.findViewById(R.id.healthPostName);

                                Glide.with(view)
                                        .load(imgURL)
                                        .into(postImg);
                                postName.setText("");

                                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                params.setMargins(10,10,10,10);

                                linearLayout.addView(view,params);

                                view.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        Intent toHealthDetails = new Intent(Health.this,viewHealthPostDetails.class);
                                        toHealthDetails.putExtra("NameOfPost",nameOfPost);
                                        toHealthDetails.putExtra("STUDENT_NUM",stdNumber);
                                        startActivity(toHealthDetails);

                                    }
                                });



                            }

                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.execute();

    }

    //Really hope you are working..
    @SuppressLint("StaticFieldLeak")
    public void getOurRecs(final  Context context, final String category, final LinearLayout linearLayout){

        ContentValues params = new ContentValues();

        linearLayout.removeAllViews();

        new ServerCommunicator("https://lamp.ms.wits.ac.za/~s1872817/getHealthPosts.php",params) {
            @Override
            protected void onPostExecute(String output) {

                if(output.equals("0")){

                    Toast.makeText(Health.this,"An error has occurred",Toast.LENGTH_SHORT).show();
                }

                else{

                    try {
                        JSONArray jsonArray = new JSONArray(output);

                        ArrayList<JSONObject> withCatObjs = new ArrayList<>();

                        View view = View.inflate(context,R.layout.health_rec_post,null);

                        for(int i=0;i<jsonArray.length();i++){

                            if(jsonArray.getJSONObject(i).getString("CAT").equals(category)){

                                withCatObjs.add(jsonArray.getJSONObject(i));
                            }
                        }

                        //last one or only one
                        JSONObject jsonObject = withCatObjs.get(withCatObjs.size()-1);


                            final String nameOfPost = jsonObject.getString("POST_NAME");
                            final String stdNumber = jsonObject.getString("STUDENT_NUM");
                            String imgURL = "https://lamp.ms.wits.ac.za/~s1872817/HealthPostImgs/"+stdNumber+nameOfPost+".JPG";

                            ImageView postImg = view.findViewById(R.id.healthRecPostImage);
                            TextView postName = view.findViewById(R.id.healthRecPostName);

                            Glide.with(view)
                                    .load(imgURL)
                                    .into(postImg);
                            postName.setText("");

                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                            params.setMargins(10,10,10,10);

                            linearLayout.addView(view,params);

                            view.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    Intent toHealthDetails = new Intent(Health.this,viewHealthPostDetails.class);
                                    toHealthDetails.putExtra("NameOfPost",nameOfPost);
                                    toHealthDetails.putExtra("STUDENT_NUM",stdNumber);
                                    startActivity(toHealthDetails);

                                }
                            });



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.execute();

    }*/
}
