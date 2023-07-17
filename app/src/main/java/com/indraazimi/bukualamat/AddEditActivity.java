package com.indraazimi.bukualamat;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Toast;

import com.indraazimi.bukualamat.data.AlamatDao;
import com.indraazimi.bukualamat.data.DataAlamat;
import com.indraazimi.bukualamat.data.Database;
import com.indraazimi.bukualamat.databinding.ActivityAddEditBinding;

public class AddEditActivity extends AppCompatActivity {

    private ActivityAddEditBinding binding;
    private AlamatDao dao;
    private boolean editMode = false;
    private long dataId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.teal_700)));

        dao = Database.getDatabase(this).dao();

        if (getIntent().hasExtra("editMode")) {
            editMode = getIntent().getBooleanExtra("editMode", false);
            if (editMode) {
                dataId = getIntent().getLongExtra("dataId", 0);
                loadData();
            }
        }

        binding.submitButton.setOnClickListener(v -> saveData());

        binding.cancelButton.setOnClickListener(v -> finish());
    }

    private void loadData() {
        DataAlamat data = dao.getDataById(dataId);
        if (data != null) {
            binding.nameEditText.setText(data.getName());
            binding.addressEditText.setText(data.getAddress());
        }
    }

    private void saveData() {
        String name = binding.nameEditText.getText().toString();
        String address = binding.addressEditText.getText().toString();
        if (name.isEmpty() || address.isEmpty()) {
            Toast.makeText(this, "Name dan Address harus diisi.", Toast.LENGTH_LONG).show();
            return;
        }

        if (editMode) {
            DataAlamat data = dao.getDataById(dataId);
            if (data != null) {
                data.setName(name);
                data.setAddress(address);
                dao.update(data);
                Toast.makeText(this, "Berhasil mengupdate data", Toast.LENGTH_LONG).show();
            }
        } else {
            dao.insert(new DataAlamat(0, name, address));
            Toast.makeText(this, "Berhasil menambahkan data", Toast.LENGTH_LONG).show();
        }

        finish();
    }
}
