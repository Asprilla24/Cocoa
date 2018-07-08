package mlg.warkop.com.mypsychologist.activityMenu;


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.ArrayList;

import mlg.warkop.com.mypsychologist.R;
import mlg.warkop.com.mypsychologist.activity.HomeActivity;

public class Tes extends AppCompatActivity {

    private static int noSoal, jwbBenar, jwbSalah, jwbKosong;
    private ArrayList<Question> listSoal;

    private ViewFlipper vfQuest;
    private TextView tvTimer, tvSoal1, tvSoal2;
    private RadioButton rbBenar1, rbSalah1, rbBenar2, rbSalah2;
    private Menu menuNoSoal;
    private CountDownTimer timer;


   @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tes);

       prepareData();
       //getSupportActionBar().setTitle(test.getName());

        //replace tittle to icon
        ActionBar menu = getSupportActionBar();
        menu.setDisplayShowHomeEnabled(true);
        menu.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onStart() {
        super.onStart();

        fetchLayout();
        prepareTimer();
    }

    @Override
    public void onBackPressed(){
       super.onBackPressed();
       timer.cancel();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      getMenuInflater().inflate(R.menu.option_menu_test_quest, menu);
       menuNoSoal = menu;
      // prepareData();
       showNextQuestion();
       return true;
    }

    private void fetchLayout() {
       vfQuest = (ViewFlipper) findViewById(R.id.test_quest_vf_parent_quest);
       vfQuest.setAutoStart(false);
       vfQuest.setInAnimation(Tes.this, R.anim.in_from_bottom);
       vfQuest.setOutAnimation(Tes.this, R.anim.out_to_top);

       tvTimer = (TextView) findViewById(R.id.test_quest_tv_timer);
       tvSoal1 = (TextView) findViewById(R.id.item_quest1_tv_soal);
       tvSoal2 = (TextView) findViewById(R.id.item_quest2_tv_soal);

       rbBenar1 = (RadioButton) findViewById(R.id.item_quest1_rb_benar);
       rbBenar1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if (isChecked) {
                   checkAnswer("Suka");
                   noSoal++;
                   showNextQuestion();
               }
           }
       });
       rbSalah1 = (RadioButton) findViewById(R.id.item_quest1_rb_salah);
       rbSalah1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if (isChecked) {
                   checkAnswer("Tidak Suka");
                   noSoal++;
                   showNextQuestion();
               }
           }
       });

        rbBenar2 = (RadioButton) findViewById(R.id.item_quest2_rb_benar);
        rbBenar2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkAnswer("Suka");
                    noSoal++;
                    showNextQuestion();
                }
            }
        });

        rbSalah2 = (RadioButton) findViewById(R.id.item_quest2_rb_salah);
        rbSalah2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkAnswer("Tidak Suka");
                    noSoal++;
                    showNextQuestion();
                }
            }
        });
    }

    private void prepareTimer() {
        long durasiPerSoal = 10000; //60 detik * 1000
        timer = new CountDownTimer(durasiPerSoal,1000) {
            @Override
            public void onTick(long l) {
                tvTimer.setText(String.valueOf(l / 1000));
            }

            @Override
            public void onFinish() {
                checkAnswer("kosong");
                noSoal++;
                showNextQuestion();
            }
        };
    }

    private void checkAnswer(String answer) {
        if (answer.equalsIgnoreCase("kosong")) jwbKosong++;
        else {
            String key = listSoal.get(noSoal).getKunci();

            if (answer.equalsIgnoreCase(key)) jwbBenar++;
            else jwbSalah++;
        }
    }

    private void showNextQuestion() {
        if (noSoal >= listSoal.size()) onTestFinished();
        else if (timer != null){
            timer.cancel();

            Question curQuestion = listSoal.get(noSoal);
            if (noSoal%2 == 0) {
                // soal ganjil
                rbBenar1.setChecked(false);
                rbSalah1.setChecked(false);
                tvSoal1.setText(curQuestion.getSoal());
            } else {
                // soal genap
                rbBenar2.setChecked(false);
                rbSalah2.setChecked(false);
                tvSoal2.setText(curQuestion.getSoal());
            }

            menuNoSoal.findItem(R.id.op_menu_no_test).setTitle(String.format(
                    getString(R.string.placeholder_menu_question),
                    noSoal+1, listSoal.size()));
            if (noSoal != 0) vfQuest.showNext();
            timer.start();
        }
    }


    private void onTestFinished() {
        timer.cancel();

        Intent intent = new Intent(Tes.this, HomeActivity.class);
        //intent.putExtra("test", test);
        intent.putExtra("Suka", jwbBenar);
        intent.putExtra("Tidak Suka", jwbSalah);
        intent.putExtra("kosong", jwbKosong);

        startActivity(intent); finish();
    }

    private void prepareData() {
       listSoal = new ArrayList<>();

       Question quest = new Question("Memodifikasi alat elektronik","Suka");
       listSoal.add(quest);
       quest = new Question("Memperbaiki kendaraan/motor yang rusak","Suka");
       listSoal.add(quest);
       quest = new Question("Merakit barang elektronik","Suka");
       listSoal.add(quest);
       quest = new Question("Memodifikasi motor/ kendaraan lainnya","Suka");
       listSoal.add(quest);
       quest = new Question("Memperbaiki barang elektronik yang rusak","Suka");
       listSoal.add(quest);
       quest = new Question("Mengubah pengaturan di hp/laptop","Suka");
       listSoal.add(quest);
       quest = new Question("Mengubah tatanan rumah/ruangan","Suka");
       listSoal.add(quest);
       quest = new Question("Melakukan latihan fisik","Suka");
       listSoal.add(quest);
       quest = new Question("Mengerjakan tugas/praktek lapangan","Suka");
       listSoal.add(quest);
       quest = new Question("Ternak hewan","Suka");
       listSoal.add(quest);
       quest = new Question("Mengunjungi perkebunan/ladang","Suka");
       listSoal.add(quest);
       quest = new Question("Mengolah pupuk tanaman","Suka");
       listSoal.add(quest);
       quest = new Question("Bercocok tanam","Suka");
       listSoal.add(quest);
       quest = new Question("Menyusun lego/blok","Suka");
       listSoal.add(quest);
       quest = new Question("Melihat pameran/video/film robotik","Suka");
       listSoal.add(quest);
       quest = new Question("Membuat aplikasi software","Suka");
       listSoal.add(quest);
       quest = new Question("Mengedit foto/video","Suka");
       listSoal.add(quest);
       quest = new Question("Mendesain bangunan","Suka");
       listSoal.add(quest);
       quest = new Question("Bekerja di luar ruangan","Suka");
       listSoal.add(quest);
       quest = new Question("Menyelesaikan tugas secara cepat","Suka");
       listSoal.add(quest);


        quest = new Question("Melakukan praktikum sains di laboraturium ","Suka");
        listSoal.add(quest);
        quest = new Question("Mencari tahu kandungan kimia pada makanan/benda disekitar","Suka");
        listSoal.add(quest);
        quest = new Question("Memecahkan soal matematika","Suka");
        listSoal.add(quest);
        quest = new Question("Menyelesaikan teka-teki silang","Suka");
        listSoal.add(quest);
        quest = new Question("Mempelajari jenis-jenis makhluk hidup","Suka");
        listSoal.add(quest);
        quest = new Question("Mempelajari zat-zat kimia","Suka");
        listSoal.add(quest);
        quest = new Question("Menggunakan komputer untuk mempermudah pekerjaan","Suka");
        listSoal.add(quest);
        quest = new Question("Membaca buku-buku tentang alam","Suka");
        listSoal.add(quest);
        quest = new Question("Mengunjungi museum fosil","Suka");
        listSoal.add(quest);
        quest = new Question("Menghafal rumus-rumus fisika","Suka");
        listSoal.add(quest);
        quest = new Question("Mengerjakan tugas secara individu","Suka");
        listSoal.add(quest);
        quest = new Question("Mempelajari jenis-jenis penyakit dan obatnya","Suka");
        listSoal.add(quest);
        quest = new Question("Melihat film/video tentang tata surya","Suka");
        listSoal.add(quest);
        quest = new Question("Praktek menggunakan mikroskop","Suka");
        listSoal.add(quest);
        quest = new Question("Melihat bintang dengan teleskop","Suka");
        listSoal.add(quest);
        quest = new Question("Memecahkan soal tes logika","Suka");
        listSoal.add(quest);
        quest = new Question("Bermain catur","Suka");
        listSoal.add(quest);
        quest = new Question("Menyelesaikan rubik","Suka");
        listSoal.add(quest);
        quest = new Question("Mencari tahu penyebab suatu permasalahan","Suka");
        listSoal.add(quest);
        quest = new Question("Mencari tahu asal-usul suatu fenomena","Suka");
        listSoal.add(quest);


        quest = new Question("Menulis cerita (cerpen, novel, komik)","Suka");
        listSoal.add(quest);
        quest = new Question("Menyusun lirik lagu","Suka");
        listSoal.add(quest);
        quest = new Question("Mengcover lagu","Suka");
        listSoal.add(quest);
        quest = new Question("Menulis skenario drama","Suka");
        listSoal.add(quest);
        quest = new Question("Melihat pertunjukkan drama","Suka");
        listSoal.add(quest);
        quest = new Question("Melihat pameran seni","Suka");
        listSoal.add(quest);
        quest = new Question("Mendesain pakaian","Suka");
        listSoal.add(quest);
        quest = new Question("Mendengarkan musik dan bernyanyi","Suka");
        listSoal.add(quest);
        quest = new Question("Membuat sketsa, menggambar, mewarnai, melukis","Suka");
        listSoal.add(quest);
        quest = new Question("Mengukir, memahat suatu objek","Suka");
        listSoal.add(quest);
        quest = new Question("Potografer/hunting","Suka");
        listSoal.add(quest);
        quest = new Question("Bermain alat musik","Suka");
        listSoal.add(quest);
        quest = new Question("Menulis puisi","Suka");
        listSoal.add(quest);
        quest = new Question("Membuat film pendek/video","Suka");
        listSoal.add(quest);
        quest = new Question("Membuat konsep dekor dalam suatu acara","Suka");
        listSoal.add(quest);
        quest = new Question("Menjahit, menyulam, merajut","Suka");
        listSoal.add(quest);
        quest = new Question("Plating masakan/ menghias makanan","Suka");
        listSoal.add(quest);
        quest = new Question("Belajar tata rias","Suka");
        listSoal.add(quest);
        quest = new Question("Berlatih bela diri","Suka");
        listSoal.add(quest);
        quest = new Question("Merangkai bunga","Suka");
        listSoal.add(quest);


        quest = new Question("Membaca buku-buku sosial ","Suka");
        listSoal.add(quest);
        quest = new Question("Berkenalan dengan orang lain","Suka");
        listSoal.add(quest);
        quest = new Question("Melakukan kegiatan amal","Suka");
        listSoal.add(quest);
        quest = new Question("Memiliki teman yang banyak","Suka");
        listSoal.add(quest);
        quest = new Question("Mengajari teman yang kesulitan memahami materi pelajaran","Suka");
        listSoal.add(quest);
        quest = new Question("Menjadi relawan korban bencana","Suka");
        listSoal.add(quest);
        quest = new Question("Ramah/menyapa orang lain","Suka");
        listSoal.add(quest);
        quest = new Question("Mendengarkan keluh kesah teman","Suka");
        listSoal.add(quest);
        quest = new Question("Membantu orang lain yang membutuhkan","Suka");
        listSoal.add(quest);
        quest = new Question("Mengikuti organisasi/komunitas sosial","Suka");
        listSoal.add(quest);
        quest = new Question("Berbagi pengalaman positif/menghibur pada orang lain","Suka");
        listSoal.add(quest);
        quest = new Question("Menabung/menyimpan barang untuk diberikan ke orang lain","Suka");
        listSoal.add(quest);
        quest = new Question("Melihat video/acara tv tentang menolong orang yang kurang mampu","Suka");
        listSoal.add(quest);
        quest = new Question("Mengikuti kegiatan bakti sosial","Suka");
        listSoal.add(quest);
        quest = new Question("Makan bersama teman","Suka");
        listSoal.add(quest);
        quest = new Question("Mengajarkan pelajaran sekolah ke anak-anak","Suka");
        listSoal.add(quest);
        quest = new Question("Mengunjungi panti jompo/panti asuhan","Suka");
        listSoal.add(quest);
        quest = new Question("Membantu menyelesaikan pekerjaan rumah","Suka");
        listSoal.add(quest);
        quest = new Question("Memberikan sosialisasi kepada masyarakat","Suka");
        listSoal.add(quest);
        quest = new Question("Bekerja untuk kepentingan orang lain","Suka");
        listSoal.add(quest);


        quest = new Question("Membuat usaha/bisnis","Suka");
        listSoal.add(quest);
        quest = new Question("Menawarkan barang dagangan di media sosial","Suka");
        listSoal.add(quest);
        quest = new Question("Membuat promo pada barang yang dijual","Suka");
        listSoal.add(quest);
        quest = new Question("Menjual barang-barang yang sedang booming","Suka");
        listSoal.add(quest);
        quest = new Question("Merayu teman untuk membeli barang yang sedang dijual","Suka");
        listSoal.add(quest);
        quest = new Question("Menyisihkan uang saku untuk ditabung","Suka");
        listSoal.add(quest);
        quest = new Question("Bernegosiasi dengan orang lain","Suka");
        listSoal.add(quest);
        quest = new Question("Membuat seseorang melakukan pekerjaan sesuai yang saya harapkan","Suka");
        listSoal.add(quest);
        quest = new Question("Memberikan hadiah/pujian pada orang yang berhasil melakukan tugas yang saya harapkan","Suka");
        listSoal.add(quest);
        quest = new Question("Membandingkan organisasi saya dengan organisasi orang lain","Suka");
        listSoal.add(quest);
        quest = new Question("Membandingkan barang yang saya jual dengan barang di toko/penjual lain","Suka");
        listSoal.add(quest);
        quest = new Question("Merancang kegiatan-kegiatan di organisasi","Suka");
        listSoal.add(quest);
        quest = new Question("Mengatur pekerjaan anggota","Suka");
        listSoal.add(quest);
        quest = new Question("Mengajak kerjasama orang lain","Suka");
        listSoal.add(quest);
        quest = new Question("Meminta dukungan orang lain","Suka");
        listSoal.add(quest);
        quest = new Question("Memberi perintah ke orang lain","Suka");
        listSoal.add(quest);
        quest = new Question("Membagi tugas ketika bekerja sama dalam kelompok","Suka");
        listSoal.add(quest);
        quest = new Question("Bertindak tegas pada anggota/teman yang sulit diajak kerjasama","Suka");
        listSoal.add(quest);
        quest = new Question("Menjadi pengurus inti organisasi","Suka");
        listSoal.add(quest);
        quest = new Question("Menciptakan produk secara kreatif","Suka");
        listSoal.add(quest);


        quest = new Question("Menulis tugas sekolah dengan jelas dan detail","Suka");
        listSoal.add(quest);
        quest = new Question("Membuat peraturan-peraturan","Suka");
        listSoal.add(quest);
        quest = new Question("Mengecek ulang pekerjaan","Suka");
        listSoal.add(quest);
        quest = new Question("Membayar iuran tepat waktu","Suka");
        listSoal.add(quest);
        quest = new Question("Bekerja didalam ruangan","Suka");
        listSoal.add(quest);
        quest = new Question("Memastikan ruangan pribadi selalu rapi dan bersih","Suka");
        listSoal.add(quest);
        quest = new Question("Berpakaian rapi dan bersih","Suka");
        listSoal.add(quest);
        quest = new Question("Mengerjakan tugas sekolah dengan baik dan tepat waktu","Suka");
        listSoal.add(quest);
        quest = new Question("Melakukan segala pekerjaan secara detail dan terstruktur","Suka");
        listSoal.add(quest);
        quest = new Question("Mengelola uang saku dengan membuat catatan laporan keuangan ","Suka");
        listSoal.add(quest);
        quest = new Question("Melakukan pekerjaan dengan hati-hati dan teratur","Suka");
        listSoal.add(quest);
        quest = new Question("Patuh terhadap tata tertib","Suka");
        listSoal.add(quest);
        quest = new Question("Membuat jadwal kegiatan yang terstruktur","Suka");
        listSoal.add(quest);
        quest = new Question("Merinci barang-barang yang akan dibeli","Suka");
        listSoal.add(quest);
        quest = new Question("Memikirkan kemungkinan-kemungkinan buruk yang akan terjadi dan solusinya","Suka");
        listSoal.add(quest);
        quest = new Question("Menjadi bendahara kelas/organisasi","Suka");
        listSoal.add(quest);
        quest = new Question("Menyelesaikan soal akuntansi","Suka");
        listSoal.add(quest);
        quest = new Question("Menata barang-barang sesuai dengan tempatnya","Suka");
        listSoal.add(quest);
        quest = new Question("Mengerjakan tugas secara teratur","Suka");
        listSoal.add(quest);
        quest = new Question("Memecahkan permasalahan secara runtut","Suka");
        listSoal.add(quest);

       noSoal = 0;
       jwbBenar = 0;
       jwbSalah = 0;
       jwbKosong = 0;

    }

}
