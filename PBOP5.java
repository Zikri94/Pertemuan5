import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Scanner;
 
public class PBOP5 {
 
    public static void main(String[] args) {
        ArrayList<Pesan> p = new ArrayList();
        Scanner sc = new Scanner(System.in);
        Integer pilihan = 0;
 
       do{
           /*
           jika pilih 1, maka input data, 
           jika 2, maka tampilkan data
           jika 3 maka keluar sistem
           */
 
            System.out.println("----------------------------");
            System.out.println("--- BINTANG BUCK COFFEE ----");
            System.out.println("----------------------------");
            System.out.println("  1. Pembelian");
            System.out.println("  2. Bayar");
            System.out.println("  3. Keluar sistem");
            System.out.println("----------------------------");
            System.out.print("  Pilihanmu: ");
            pilihan = sc.nextInt();
 
           if(pilihan == 1 ){
               p = beli( p );
           }else if(pilihan == 2){
                checkout( p );
           }
       }while (pilihan != 3);
 
    }
 
    private static ArrayList<Pesan> beli( ArrayList<Pesan> p ){ 
        Scanner sc = new Scanner(System.in);
        String nama, tipe, gula;
        Integer harga, qty;

        boolean ok = false;
        do{  
        System.out.print("Nama [Americano/latte/arabika]: ");
        nama = sc.nextLine();
        if ( nama.equalsIgnoreCase("Americano") ) {
            ok = true;
        }else if ( nama.equalsIgnoreCase("latte") ) {
            ok = true;
        }else if ( nama.equalsIgnoreCase("arabika") ) {
            ok = true;
        }else{
            System.out.println("[ Kopi tidak tersedia ]");
            ok = false;
        }
    } while ( ok == false );
 
        System.out.print("Tipe: ");
        tipe = sc.nextLine();

        System.out.print("Gula: ");
        gula = sc.nextLine();
 
        System.out.print("Harga: ");
        harga = sc.nextInt();

        ok = false;
        do{ 
        System.out.print("Qty[Min 1]: ");
        qty = sc.nextInt();
        if ( qty >= 1 ){ 
            ok = true;
        }else{
            System.out.println( "[ Minimal Beli 1 ]");
            ok = false;
        }
    } while ( ok == false );

        p.add( new Pesan( nama, tipe, gula, harga, qty ) );
 
        return p;
    }
 
    private static void checkout( ArrayList<Pesan> p ){
        Scanner sc = new Scanner(System.in);
        int idx = 0, jumlah = 0, bayar = 0;
        boolean correct;
 
        //tampilkan data 
        System.out.println( "[ Type "+ ( p.size() + 1 ) +" to check out ]" );
        System.out.println("--------------------------------------------------------------------");
        System.out.printf("| %-3s | %-10s | %-10s | %-5s | %-7s | %-3s  | %-7s |", 
                "No",
                "Nama",
                "Tipe",
                "Gula",
                "Harga",
                "Qty",
                "Jumlah");
        System.out.println();
        System.out.println("--------------------------------------------------------------------");
 
        for(int i = 0; i < p.size(); i++ ){
            System.out.printf("| %-3s | %-10s | %-10s | %-5s | %-7s | %-3s  | %-7s |", 
                i + 1,
                p.get(i).getNama(),
                p.get(i).getTipe(),
                p.get(i).getGula(),
                p.get(i).getHarga(),
                p.get(i).getQty(),
                (p.get(i).getHarga() * p.get(i).getQty()));
            System.out.println();
            System.out.println("--------------------------------------------------------------------");
 
        }

        correct = true;
        do{ 
            System.out.print( "Choose Coffee index [ 1 - "+ p.size() +" ]: " );
            idx = sc.nextInt();
            
            if ( idx > 0 && idx <= p.size() ){ 
                jumlah += p.get( idx - 1 ).getHarga();
                System.out.println( p.get( idx - 1 ).getNama() + " " + (p.get( idx - 1 ).getHarga() * p.get(idx - 1).getQty() ));
                correct = true;
            }else{
                correct = false;
            }
        } while ( correct == true );
        
        //payment
        System.out.println( "Total: "+ jumlah*idx );
        
        correct = true;
        do{ 
            System.out.print( "Amount: ");
            bayar = sc.nextInt();
            
            if ( bayar < jumlah ){ 
                System.out.println( "[ less than Total ]");
                correct = false;
            }else{
                correct = true;
            }
        } while ( correct == false );
        
        System.out.println( "Change: "+ abs( bayar - jumlah*idx ) );
        System.out.println( "[ Succesfully add new order! ]. Press enter to continue.." );
        sc.nextLine();
        sc.nextLine();
    }
}