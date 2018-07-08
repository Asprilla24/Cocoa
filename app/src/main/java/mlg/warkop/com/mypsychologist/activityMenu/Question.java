package mlg.warkop.com.mypsychologist.activityMenu;

import android.os.Parcel;
import android.os.Parcelable;

public class Question implements Parcelable {

    private String soal;
    private String kunci;

    public Question(String soal, String kunci) {
        this.soal = soal;
        this.kunci = kunci;
    }

    public String getSoal() {
        return soal;
    }

    public void setSoal(String soal) {
        this.soal = soal;
    }

    public String getKunci() {
        return kunci;
    }

    public void setKunci(String kunci) {
        this.kunci = kunci;
    }

    protected Question(Parcel in) {
        soal = in.readString();
        kunci = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(soal);
        dest.writeString(kunci);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Question> CREATOR = new Parcelable.Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };
}