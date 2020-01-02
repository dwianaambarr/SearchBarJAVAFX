package sampel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Search{
    private StringProperty namastore = null;
    private StringProperty namabarang = null;
    private int harga = 0;

    public Search(String newStoreName, String newNama, int newharga){
        this.namastore = new SimpleStringProperty(newStoreName);
        this.namabarang = new SimpleStringProperty(newNama);
        this.harga = newharga;
    }

    public final StringProperty namastoreProperty() {
        return namastore;
    }

    public final String getnamastore() {
        return namastoreProperty().get();
    }

    public final StringProperty namabarangProperty() {
        return namabarang;
    }

    public final String getnamabarang() {
        return namabarangProperty().get();
    }

    public int getHarga() {
        return harga;
    }

    public final void setNamaStore(String nama1) {
        namastore.set(nama1);
    }

    public final void setNama(String nama2) {
        namabarang.set(nama2);
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }
}
