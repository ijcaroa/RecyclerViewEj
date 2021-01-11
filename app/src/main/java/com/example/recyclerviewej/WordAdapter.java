package com.example.recyclerviewej;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewej.databinding.WordItemListBinding;

import java.util.List;
//3. Se heredó (extends) el recicler.adapter, pasar el ViewHolder creado
//4. Se implementwron los metodos.
public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {
    //1. Añadir una repr>esentación de los datos
    private List<String> mWordList;

    //Referencia a interface que pasa el objeto
    private InterfacePasarElemento pasarElemento;

    //8. No olvidar crear el constructor para pasar el listado de datos al instanciar el adapter
    public WordAdapter(List<String> mWordList, InterfacePasarElemento pasarElemento) {
        this.mWordList = mWordList;
        this.pasarElemento = pasarElemento;
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //6. Se instació ViewBinding para retornar el View Holder con esa dependencia
        WordItemListBinding mBinding = WordItemListBinding
                .inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new WordViewHolder(mBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
     //7. Se crea un variable de referencia element del clontenido del listado
        // y se asigna al emento visual que viene en el holder
        // Toma los datos y los mete en el objeto que se muestra varias veces.
        String element = mWordList.get(position);
        holder.textView.setText(element);

    }

    @Override
    public int getItemCount() {
        //5. Indica la cantidad de datos a mostrar
        return mWordList.size();
    }

    //2. Crear clase interna view holder. Representación del objeto que se repite y se muestra
    //Puede esta dentro o fuera de la clase Adapter
    public class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

        private TextView textView;

        public WordViewHolder(@NonNull WordItemListBinding mBinding) {
            super(mBinding.getRoot());
            textView = mBinding.textView;
            //No olvidar este paso para que funcione el click
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //Obtener la posición del elemento
            int position = getLayoutPosition();
            String seleccionado = mWordList.get(position);
            mWordList.set(position, seleccionado + " CLICK");
            notifyDataSetChanged();
            pasarElemento.passElemento(seleccionado);
        }
    }
    //INTERFACE CON UN METODO QUE RECIBE EL ELEMENTO Y LO PASA DONDE ESTE IMPLEMENTADA LA INTERFACE
    //Interface solo declaran metodos no recibe paramentros
    public interface InterfacePasarElemento{
        //ESte metdodo pasa el objeto seleccionado
        void passElemento(String item);

    }
}
