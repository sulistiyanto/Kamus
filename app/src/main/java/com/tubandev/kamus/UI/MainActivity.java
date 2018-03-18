package com.tubandev.kamus.UI;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.tubandev.kamus.Database.KamusHelper;
import com.tubandev.kamus.Model.KamusModel;
import com.tubandev.kamus.R;
import com.tubandev.kamus.UI.Kamus.KamusInEnFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    KamusHelper kamusHelper;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //kamusHelper = new KamusHelper(this);
        //kamusHelper.open();

        // Ambil semua data mahasiswa di database
        //ArrayList<KamusModel> kamusModels = kamusHelper.getListDataByName("b", "in");
        //Log.d("asdfg", kamusModels.size() + "");

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, new KamusInEnFragment());
        fragmentTransaction.commitAllowingStateLoss();
        getSupportActionBar().setTitle("Kamus Indonesia - English");

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_in_en) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container, new KamusInEnFragment());
            fragmentTransaction.commitAllowingStateLoss();
            getSupportActionBar().setTitle("Kamus Indonesia - English");
        } else if (id == R.id.nav_en_in) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container, new KamusInEnFragment());
            fragmentTransaction.commitAllowingStateLoss();
            getSupportActionBar().setTitle("Kamus English - Indonesia");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
