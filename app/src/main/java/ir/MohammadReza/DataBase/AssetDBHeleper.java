package ir.MohammadReza.DataBase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import ir.MohammadReza.applications.application;

public class AssetDBHeleper {
    //in class miyad dar soorat lozom oun file db ro estekhraj mikone


    private String dbName = "db_city";


    public  AssetDBHeleper(String dbName) {
        this.dbName = dbName;

    }


    public AssetDBHeleper() {
        //yani agar to constrctor behesh db name nadadim khodesh ino seda bezane

        this("db_city");

    }


    public void chechDb() {
        //Miayad check mikone ke data base ijad shode ya nashode
        //in file oun data base ro be ma mide agar esmo behesh bedim to file hay root gooshi

        File dbfile = application.getContext().getDatabasePath(dbName);
        if (!dbfile.exists()) {
            //migim agar data base ijad nashode mirim ouno ijad mikonim
            try {
                copyDataBaseFile(dbfile);
            } catch (Exception e) {
                throw new RuntimeException("Erro creating Source DataBase . ", e);

                //yani khataee dashtim dar zaman ejra va natuneste kari bkne
            }
        }
    }

    private void copyDataBaseFile(File dbfile) throws Exception {
        //in mige in fili ke man copy mikonm vojod nadare bayad biyad az to assset ha bardare va bad copy kne

        //az koja gharare bekhore be koja gharare berize. input/output stream


        InputStream im = application.getContext().getAssets().open(dbName);
        //dar soorati ke file parent vojod dasht besaz
        dbfile.getParentFile().mkdirs();
        //tamam poshe hay mad nazaresho besaze agar poshee vojod nadasht khodesh baray khodesh besazar


        OutputStream outputStream = new FileOutputStream(dbfile);
        int len = 0;
        //tool etelaat ke gharare az input bekhunim va be out put berizim

        byte[] buffer = new byte[1024];
        //masalan agar file ma 1030 ta ja dashte bashe dafehy aval 1024 ta mikhune
        //baghiyash dafehy bad


        while ((len=im.read(buffer))>0){
            //migim agar mosbat bud yani tunesti ye seri byte ha bekhuni az input stream

        outputStream.write(buffer,0,len);
        //migim az buffer az sefresh bekhun ta len

        }

        outputStream.flush();
        outputStream.close();
        im.close();


    }


}
