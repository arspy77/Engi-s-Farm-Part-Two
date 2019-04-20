package engisfarm;

/** Point adalah suatu struktur data yang menyimpan posisi di bidang 2 dimensi */
public class Point{
    /** Absis dari poin */
    public int x;
    /** Ordinat dari poin */
    public int y;
    /** Mengembalikan Manhattan Distance dari P1 dan P2 */
    public static int manhattanDist(Point P1, Point P2) {
        return (Math.abs(P1.x - P2.x) + Math.abs(P1.y - P2.y));
    }
}