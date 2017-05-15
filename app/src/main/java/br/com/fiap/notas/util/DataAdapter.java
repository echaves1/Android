package br.com.fiap.notas.util;

import android.support.v4.view.LayoutInflaterFactory;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.fiap.notas.R;
import br.com.fiap.notas.entity.Row;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList<Row> rows;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_row, parent, false);
        return new ViewHolder(view);
    }

    //CONSTRUTOR QUE RECEBE UMA LISTA DE ROW QUE VEM BO BLUEMIX
    public DataAdapter(ArrayList<Row> rows){
        this.rows = rows;
    }

    //POPULA OS DADOS DAS VIEWS DO CARD DINAMICAMENTE COM OS DADOS  VINDOS DO CLOUDANT
    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {

        holder.tvId.setText(rows.get(position).getDoc().get_id());
        holder.tvTitulo.setText(rows.get(position).getDoc().get_id());
        holder.tvAssunto.setText(rows.get(position).getDoc().get_id());
        holder.tvConteudo.setText(rows.get(position).getDoc().get_id());


    }

    //RETORNA O TAMANHO DO DATASET DO CLOUDANT
    @Override
    public int getItemCount() {
        return rows.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTitulo, tvId, tvAssunto, tvConteudo;

        public ViewHolder(View itemView) {
            super(itemView);
            tvId = (TextView) itemView.findViewById(R.id.tvId);
            tvTitulo = (TextView) itemView.findViewById(R.id.tvTitulo);
            tvAssunto = (TextView) itemView.findViewById(R.id.tvAssunto);
            tvConteudo = (TextView) itemView.findViewById(R.id.tvConteudo);
        }
    }
}
