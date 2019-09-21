import java.io.* ;

public class readFile
{
    public static void main(String[] args)
    {
        FileInputStream is = null ;
	try {
	    is = new FileInputStream("config/config.json");
            int b = is.read() ;
            while (b != -1) {
                String digits = Integer.toHexString(b) ;
                if (b < 16) digits = "0" + digits ;
                System.out.println(" 0x" + digits) ;
                b = is.read() ;
            }
	} catch (Exception e) {
	    e.printStackTrace() ;
	} finally {
            try { is.close() ; } catch (Exception e) {}
        }
    }
}