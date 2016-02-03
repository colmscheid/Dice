package com.christian.dice;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import database.DiceDatabaseController;
import model.DiceCollection;
import model.DiceCollectionFactory;
import view.DiceCollectionView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DiceDatabaseController controller = new DiceDatabaseController(this);

        controller.open();

        controller.resetDatabase();

        DiceCollection coll1 = DiceCollectionFactory.createOriginalDiceCollection(1);
        coll1.setName("Test1");
        controller.addDiceCollection(coll1, -1);

        DiceCollection coll2 = DiceCollectionFactory.createOriginalDiceCollection(1);
        coll1.setName("Test2");
        controller.addDiceCollection(coll1, -1);

        ArrayList<DiceCollection> list = controller.getAllDiceCollections();

        LinearLayout lLayout = (LinearLayout) findViewById(R.id.info);

        lLayout.setOrientation(LinearLayout.VERTICAL);
        //-1(LayoutParams.MATCH_PARENT) is fill_parent or match_parent since API level 8
        //-2(LayoutParams.WRAP_CONTENT) is wrap_content
        lLayout.setLayoutParams(new RelativeLayout.LayoutParams(
                GridLayout.LayoutParams.MATCH_PARENT,
                GridLayout.LayoutParams.MATCH_PARENT));

        for (DiceCollection coll : list) {

            DiceCollectionView tView = new DiceCollectionView(this, coll);
            lLayout.addView(tView);
            //setContentView(lLayout);
        }

        for (DiceCollection coll : list) {
            System.out.println(coll);
        }

        controller.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}