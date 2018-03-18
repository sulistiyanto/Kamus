package com.tubandev.kamus.UI.Kamus;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.tubandev.kamus.Adapter.AdapterKamus;
import com.tubandev.kamus.Database.KamusHelper;
import com.tubandev.kamus.Model.KamusModel;
import com.tubandev.kamus.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class KamusInEnFragment extends Fragment {

    private KamusHelper kamusHelper;
    private ArrayList<KamusModel> kamusModels = new ArrayList<>();
    private AdapterKamus adapterKamus;

    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.txtResult)
    TextView txtResult;

    public KamusInEnFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_kamus, container, false);
        ButterKnife.bind(this, rootView);
        kamusHelper = new KamusHelper(getContext());
        kamusHelper.open();

        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                txtResult.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                kamusModels = kamusHelper.getListDataByName(s.toString(), "in");
                adapterKamus = new AdapterKamus(kamusModels, new AdapterKamus.OnItemClickListener() {
                    @Override
                    public void onClick(KamusModel kamusModel) {
                        txtResult.setText(kamusModel.getDescription());
                        txtResult.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                    }
                });
                recyclerView.setAdapter(adapterKamus);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return rootView;
    }

}
