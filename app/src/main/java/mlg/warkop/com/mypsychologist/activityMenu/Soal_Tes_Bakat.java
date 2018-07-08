package mlg.warkop.com.mypsychologist.activityMenu;


public class Soal_Tes_Bakat {
    //membuat array untuk pertanyaan
    public String pertanyaan[] = {
            "Tes Kemampuan Angka",
            "Tes Kemampuan Angka",
            "Tes Kemampuan Angka",
            "Tes Kemampuan Angka",
            "Tes Kemampuan Angka",
            "Tes Kemampuan Angka",
            "Tes Kemampuan Angka",
            "Tes Kemampuan Angka",
            "Tes Kemampuan Angka",
            "Tes Kemampuan Angka",
            "Tes Kemampuan Angka",
            "Tes Kemampuan Angka",
            "Tes Kemampuan Angka",
            "Tes Kemampuan Angka",
            "Tes Kemampuan Angka",
            "Tes Kemampuan Angka",
            "Tes Kemampuan Angka",
            "Tes Kemampuan Angka",
            "Tes Kemampuan Angka",
            "Tes Kemampuan Angka",

            "Tes Penalaran Verbal",
            "Tes Penalaran Verbal",
            "Tes Penalaran Verbal",
            "Tes Penalaran Verbal",
            "Tes Penalaran Verbal",
            "Tes Penalaran Verbal",
            "Tes Penalaran Verbal",
            "Tes Penalaran Verbal",
            "Tes Penalaran Verbal",
            "Tes Penalaran Verbal",
            "Tes Penalaran Verbal",
            "Tes Penalaran Verbal",
            "Tes Penalaran Verbal",
            "Tes Penalaran Verbal",
            "Tes Penalaran Verbal",
            "Tes Penalaran Verbal",
            "Tes Penalaran Verbal",
            "Tes Penalaran Verbal",
            "Tes Penalaran Verbal",
            "Tes Penalaran Verbal",

            "Tes Relasi Ruang",
            "Tes Relasi Ruang",
            "Tes Relasi Ruang",
            "Tes Relasi Ruang",
            "Tes Relasi Ruang",
            "Tes Relasi Ruang",
            "Tes Relasi Ruang",
            "Tes Relasi Ruang",
            "Tes Relasi Ruang",
            "Tes Relasi Ruang",
            "Tes Relasi Ruang",
            "Tes Relasi Ruang",
            "Tes Relasi Ruang",
            "Tes Relasi Ruang",
            "Tes Relasi Ruang",
            "Tes Relasi Ruang",
            "Tes Relasi Ruang",
            "Tes Relasi Ruang",
            "Tes Relasi Ruang",
            "Tes Relasi Ruang",

    };

    //membuat array untuk set gambar (dari String diubah jadi ImageView)
    //pastikan yang tertulis disini sama dengan nama di drawable
    //lihat gambar penjelasan1
    private String image[] = {
            "numerik1",
            "numerik2",
            "numerik3",
            "numerik4",
            "numerik5",
            "numerik6",
            "numerik7",
            "numerik8",
            "numerik9",
            "numerik10",
            "numerik11",
            "numerik12",
            "numerik13",
            "numerik14",
            "numerik15",
            "numerik16",
            "numerik17",
            "numerik18",
            "numerik19",
            "numerik20",

            "penalaran1",
            "penalaran2",
            "penalaran3",
            "penalaran4",
            "penalaran5",
            "penalaran6",
            "penalaran7",
            "penalaran8",
            "penalaran9",
            "penalaran10",
            "penalaran11",
            "penalaran12",
            "penalaran13",
            "penalaran14",
            "penalaran15",
            "penalaran16",
            "penalaran17",
            "penalaran18",
            "penalaran19",
            "penalaran20",

            "relasi1",
            "relasi2",
            "relasi3",
            "relasi4",
            "relasi5",
            "relasi6",
            "relasi7",
            "relasi8",
            "relasi9",
            "relasi10",
            "relasi11",
            "relasi12",
            "relasi13",
            "relasi14",
            "relasi15",
            "relasi16",
            "relasi17",
            "relasi18",
            "relasi19",
            "relasi20",

    };

    //membuat array untuk pilihan jawaban
    private String pilihanJawaban[][] = {
            {"62","78","72","82","80"},
            {"15","18","21","24","27"},
            {"0","1","2","3","4"},
            {"172","216","388","308","316"},
            {"51","53","54","60","64"},
            {"94","108","128","284","306"},
            {"23","14","25","26","27"},
            {"0,1","4,1","3","3,6","1,2"},
            {"18","19","20","21","22"},
            {"6,5","7","7,75","10,5","14"},
            {"7","8","9","10","11"},
            {"30","31","32","33","34"},
            {"15","16","17","18","19"},
            {"3","4","5","6","7"},
            {"208","210","216","302","308"},
            {"32","38","42","46","52"},
            {"25","17","29","31","33"},
            {"15","18","21","24","27"},
            {"115","127","275","305","410"},

            {"a","b","c","d","e"},
            {"a","b","c","d","e"},
            {"a","b","c","d","e"},
            {"a","b","c","d","e"},
            {"a","b","c","d","e"},
            {"a","b","c","d","e"},
            {"a","b","c","d","e"},
            {"a","b","c","d","e"},
            {"a","b","c","d","e"},
            {"a","b","c","d","e"},
            {"a","b","c","d","e"},
            {"a","b","c","d","e"},
            {"a","b","c","d","e"},
            {"a","b","c","d","e"},
            {"a","b","c","d","e"},
            {"a","b","c","d","e"},
            {"a","b","c","d","e"},
            {"a","b","c","d","e"},
            {"a","b","c","d","e"},
            {"a","b","c","d","e"},

            {"a","b","c","d","e"},
            {"a","b","c","d","e"},
            {"a","b","c","d","e"},
            {"a","b","c","d","e"},
            {"a","b","c","d","e"},
            {"a","b","c","d","e"},
            {"a","b","c","d","e"},
            {"a","b","c","d","e"},
            {"a","b","c","d","e"},
            {"a","b","c","d","e"},
            {"a","b","c","d","e"},
            {"a","b","c","d","e"},
            {"a","b","c","d","e"},
            {"a","b","c","d","e"},
            {"a","b","c","d","e"},
            {"a","b","c","d","e"},
            {"a","b","c","d","e"},
            {"a","b","c","d","e"},
            {"a","b","c","d","e"},
            {"a","b","c","d","e"},

    };

    //membuat array jawaban benar
    private String jawabanBenar[] = {
            "72",
            "18",
            "2",
            "216",
            "64",
            "128",
            "23",
            "4,1",
            "19",
            "7,75",
            "9",
            "32",
            "18",
            "7",
            "216",
            "32",
            "29",
            "15",
            "127",
            "52",

            "d",
            "d",
            "c",
            "d",
            "d",
            "a",
            "b",
            "a",
            "c",
            "e",
            "d",
            "c",
            "d",
            "a",
            "b",
            "b",
            "d",
            "c",
            "a",
            "a",

            "a",
            "b",
            "d",
            "c",
            "a",
            "a",
            "b",
            "a",
            "b",
            "d",
            "c",
            "d",
            "a",
            "c",
            "d",
            "a",
            "b",
            "a",
            "b",
            "a"

    };

    //membuat getter untuk mengambil pertanyaan
    public String getPertanyaan(int x){
        String soal = pertanyaan[x];
        return soal;
    }

    //membuat getter untuk mengambil jawaban benar
    public String getStringGambar(int x){
        String gambar = image[x];
        return gambar;
    }

    //membuat getter untuk mengambil pilihan jawaban 1
    public String getPilihanJawaban1(int x){
        String jawaban1 = pilihanJawaban[x][0];
        return jawaban1;
    }

    //membuat getter untuk mengambil pilihan jawaban 2
    public String getPilihanJawaban2(int x){
        String jawaban2 = pilihanJawaban[x][1];
        return jawaban2;
    }

    //membuat getter untuk mengambil pilihan jawaban 3
    public String getPilihanJawaban3(int x){
        String jawaban3 = pilihanJawaban[x][2];
        return jawaban3;
    }

    //membuat getter untuk mengambil pilihan jawaban 3
    public String getPilihanJawaban4(int x){
        String jawaban4 = pilihanJawaban[x][3];
        return jawaban4;
    }

    //membuat getter untuk mengambil pilihan jawaban 3
    public String getPilihanJawaban5(int x){
        String jawaban5 = pilihanJawaban[x][4];
        return jawaban5;
    }


    //membuat getter untuk mengambil jawaban benar
    public String getJawabanBenar(int x){
        String jawaban = jawabanBenar[x];
        return jawaban;
    }
}

