package com.example.recyclerviewej;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.recyclerviewej.databinding.FragmentFirstBinding;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment implements WordAdapter.InterfacePasarElemento {
private FragmentFirstBinding mBinding;



private List<String> dataList = new ArrayList<>();

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
            ) {
        mBinding = FragmentFirstBinding.inflate(inflater,container,false);
        // Inflate the layout for this fragment
        return mBinding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Se instancia el adapoter y se le posa los datos con el metodo set data
        WordAdapter adapter = new WordAdapter(setData(), this);
        // Se le pasa el adapter al recycler view
        mBinding.recyclerView.setAdapter(adapter);
        //Le indicamos al RV como mostrar elementos , podria ser Grid layout o Stagger latoyut
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mBinding.fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataList.add("PALABRA   :" + dataList.size());
                //Notificar al adaptador que se insertaron datos
                mBinding.recyclerView.getAdapter().notifyItemInserted(dataList.size());
                //Scroll al final de la lista
                mBinding.recyclerView.smoothScrollToPosition(dataList.size());


            }
        });
    }
    private List<String> setData(){
        for (int i = 1; i < 101; i++) {
            dataList.add("PALABRA :" + i);
        }
        return dataList;
    }

    @Override
    public void passElemento(String item) {
        Bundle bundle = new Bundle();
        bundle.putString("item", item);
        Navigation.findNavController(mBinding.getRoot())
                .navigate(R.id.action_FirstFragment_to_SecondFragment,bundle);
    }
}