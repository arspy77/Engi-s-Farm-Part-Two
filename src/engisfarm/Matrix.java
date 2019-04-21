package engisfarm;

import java.util.Vector;

/** Kelas generic yang merepresentasikan matriks dua dimensi */
public class Matrix<Type> {
    /** Constructor dari Matrix */
    public Matrix(int nRow, int nColl, Type init) {
        data = new Vector<Vector<Type>>();
        for (int i = 0; i < nRow; i++) {
            data.add(new Vector<Type>());
        }
        for (int i = 0; i < nRow; i++) {
            for (int j = 0; j < nColl; j++) {
                data.get(i).add(init);
            }
        }
    }

    /** Return element at index (i, j) */
    public Type get(int i, int j) {
        return data.get(i).get(j);
    }

    /** Mengganti element pada index (i, j) dengan elmt */
    public void set(int i, int j, Type elmt) {
        data.get(i).set(j, elmt);
    }

    /** Container dari data matriks */
    private Vector<Vector<Type>> data;
} 