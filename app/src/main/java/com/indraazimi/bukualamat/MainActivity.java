package com.indraazimi.bukualamat;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.indraazimi.bukualamat.Adapters.AdapterData;
import com.indraazimi.bukualamat.data.AlamatDao;
import com.indraazimi.bukualamat.data.DataAlamat;
import com.indraazimi.bukualamat.data.Database;
import com.indraazimi.bukualamat.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AlamatDao dao;
    private AdapterData adapterData;
    private List<DataAlamat> listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.indraazimi.bukualamat.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.teal_700)));

        Database database = Database.getDatabase(this);
        dao = database.dao();

        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listData = new ArrayList<>();
        adapterData = new AdapterData(this, listData);
        recyclerView.setAdapter(adapterData);

        binding.fab.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddEditActivity.class);
            startActivity(intent);
        });

        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(decoration);

        adapterData.setOnItemClickListener(position -> {
            DataAlamat data = listData.get(position);
            Intent intent = new Intent(MainActivity.this, AddEditActivity.class);
            intent.putExtra("editMode", true); // Set editMode ke true
            intent.putExtra("dataId", data.getId()); // Kirim ID data yang ingin diedit
            startActivity(intent);
        });

        adapterData.setOnItemLongClickListener(position -> {
            DataAlamat data = listData.get(position);
            showDeleteConfirmationDialog(data);
        });

        updateList();
    }

    private void showDeleteConfirmationDialog(final DataAlamat data) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Konfirmasi Hapus")
                .setMessage("Apakah Anda yakin ingin menghapus item ini?")
                .setPositiveButton("Hapus", (dialog, which) -> deleteData(data))
                .setNegativeButton("Batal", null)
                .show();
    }

    private void deleteData(DataAlamat data) {
        dao.delete(data);
        updateList();
    }

    private void updateList() {
        List<DataAlamat> data = dao.getData();
        adapterData.setData(data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateList();
    }

}
