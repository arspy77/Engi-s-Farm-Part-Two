package engisfarm.cell;

/** Land merupakan kelas turunan dari Cell yang merepresentasikan petak-petak yang bisa ditempati oleh LivingThing */
public abstract class Land extends Cell { 
        /** Return true bila Land adalah sebuah facility */
        public boolean isFacility()
        {
            return facility;
        }

        /** Membuat existGrass menjadi true */        
        public void growGrass()
        {
            if(!existGrass)
            {
                existGrass = true;
            }
        }

        /** Membuat existGrass menjadi false */
        public void removeGrass()
        {
            if(existGrass)
            {
                existGrass = false;
            }
        }

        /** Mengembalikan keberadaan grass */
        public boolean isGrassExist()
        {
            return existGrass;
        }

        /** Flag yang menandakan apakah terdapat rumput diatas suatu cell atau tidak */
        private boolean existGrass;

        /** Menandakan bahwa land bukan facility */
        static final boolean facility = false;
}