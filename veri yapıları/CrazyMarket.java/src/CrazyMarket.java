/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package crazymarket.java;

/**
 *
 * @author HP Omen
 */
import java.util.Iterator;
import java.util.Random;

public class CrazyMarket implements MyQueue<Customer> {

    /**
     * default tekerleme
	 *
     */
    int arrivalTime = 0;//musterilerin kasaya göre geliş zamanı için
    int removalTime = 0;//musterilerin gidiş zamanı için
    Node root;//kuyrugun ilk degeri
    Node root1;//tekerleme ile saymada seçilen ogeyi basa alarak yeni bir kuyruk olusturur.
    int lenght = 0;//kuyrugun uzunlugu
    int sayac = 0;//verilen tekerlemelerdeki sesli harf sayisini bulmak için
    int islemSuresi = 0;//Kac saniye islem goruyor.(1-3)
    int gelisSaniyesi;//kac saniyede ekleniyor(0-2)
    int toplam = 0;//müsteriler eklenirken işlem süresi ile eklenen müşterilerin saniyesini karşılaştırmak için
    int a;//musterinin ismi farklı olsun diye
    int beklemeSuresi;//musterilerin bekleme suresi

    CrazyMarket() {
        root = null;
        root1 = null;
    }

    class Node {

        Customer data;
        Node next;

        Node(Customer data) {
            this.data = data;
            next = null;
        }
    }
    String tekerleme = "O piti piti karamela sepeti "
            + "\nTerazi lastik jimnastik "
            + "\nBiz size geldik bitlendik Hamama gittik temizlendik.";

    /**
     * numberOfCustumer ile verilen sayida musteri hizmet gorene kadar calismaya
     * devam eder
     */
    public CrazyMarket(int numberOfCustomer) {
        a = numberOfCustomer;
        String sesliHarfler = "aeıiuüoö";//tekerlemedeki sesli harfleri bulmak için 
        for (int i = 0; i <= tekerleme.length() - 1; i++) {
            for (int j = 0; j <= 7; j++) {
                if (tekerleme.charAt(i) == sesliHarfler.charAt(j)) {
                    sayac++;
                }
            }
        }
        System.out.println("sesli harf sayisi:" + sayac);
        for (int i = 0; i < numberOfCustomer; i++) {//verilen numberOfCustomer degeri kadar musteri için kasa çalışsın.
            Customer b = new Customer("musteri", i);
            enqueue(b);
            chooseCustomer();

        }
    }

    /**
     * numberOfCustumer ile verilen sayida musteri hizmet gorene kadar calismaya
     * devam eder, calisirken verilen tekerlemeyi kullanir
     */
    public CrazyMarket(int numberOfCustomer, String tekerleme) {
        a = numberOfCustomer;
        this.tekerleme = tekerleme;
        String sesliHarfler = "aeıiuüoö";//tekerlemedeki sesli harfleri bulmak için
        for (int i = 0; i <= tekerleme.length() - 1; i++) {
            for (int j = 0; j <= 7; j++) {
                if (tekerleme.charAt(i) == sesliHarfler.charAt(j)) {
                    sayac++;
                }
            }
        }
        System.out.println("sesli harf sayisi:" + sayac);
        for (int i = 0; i < numberOfCustomer; i++) {//verilen numberOfCustomer degeri kadar musteri için kasa çalışsın.
            Customer b = new Customer("musteri:", i);
            enqueue(b);
            chooseCustomer();

        }

    }

    /**
     * kuyrugun basindaki musteriyi yada tekerleme ile buldugu musteriyi return eder
     */
    public Customer chooseCustomer() {
        Customer secilenMusteri;
        if (beklemeSuresi > 10) {//bekleme suresi > 10 ise direk o elemanı çıkartsın
            secilenMusteri = dequeuNext();
        } else {//eger buyuk değilse tekerleme ile secilen musteriyi çıkartsı
            secilenMusteri = dequeuWithCounting(tekerleme);
        }
        System.out.println("secilen musteri:" + secilenMusteri);
        return secilenMusteri;//tekerleme ile seçilen veya bekleme suresine göre ilk elemanı döndürür.

    }

    @Override
    public int size() {
        return lenght;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    public boolean isEmpty1() {//kopyalayacağımız kuyruk için
        return root1 == null;
    }

    @Override
    public boolean enqueue(Customer item) {
        if (islemSuresi == 0) {//eger kimse islem gormuyorsa eklenecek musterinin saniyesi belirnensin
            Random r = new Random();
            gelisSaniyesi = r.nextInt(3);

        }

        if (gelisSaniyesi < 3) {
            if (isEmpty()) {//eğer kuyruğumuz boş ise gelen değeri kuyrugun başındaki değere ata
                root = new Node(item);

            } else {
                Node tmp = root;
                while (tmp.next != null) {//tmp.next boş olana kadar tmp değerimizi öteliyoruz
                    tmp = tmp.next;
                }
                tmp.next = new Node(item);

            }
        }
        arrivalTime += gelisSaniyesi;

        System.out.println("gelisSaniyesi:  " + gelisSaniyesi + "   arrivalTime: " + arrivalTime + "    " + item);
        lenght++;

        return false;
    }

    @Override
    public Customer dequeuNext() {
        Customer secilenKisi = root.data;//ilk değeri çıkartacağımız için ilk değeri bir değiskene atadık
        if (!isEmpty()) {

            Random r = new Random();
            islemSuresi = r.nextInt(3) + 1;//islem suresini random olarak atadık
            gelisSaniyesi = r.nextInt(3);//burda da sıradaki değerin eklenip eklenmeyeceğini kontrol etmek için geliş zamanını belirledik.
            toplam = toplam + gelisSaniyesi;
            while (toplam <= islemSuresi) {//eger işlem devam ederken gelen musterilerin gelişSaniyelerinin toplamı işlem süresinden küçük ise müsteri eklensin.
                Customer b = new Customer("musteri", a);
                enqueue(b);
                gelisSaniyesi = r.nextInt(3);//yine gelecek kişi için geliş saniyesini belirttik
                toplam = toplam + gelisSaniyesi;
                a++;//musterinin ismi değişsin diye
            }
            removalTime += islemSuresi;
            Node tmp = root;
            root = root.next;
            lenght--;
            System.out.println("islemSuresi:" + islemSuresi + " " + "  removalTime:" + removalTime);

            toplam = 0;
        }
        beklemeSuresi = removalTime - arrivalTime;
        return secilenKisi;
    }

    @Override
    public Customer dequeuWithCounting(String tekerleme) {//tekerleme ile çıkacak kişiyi seçip çıkardık ve seçılen kişiyi başa alıp kuyruğu kopyaladık.
        int i = 1;//dongü için
        int j = 0;
        Customer secilenKisi = new Customer();
        if (lenght != 0) {
            j = sayac % lenght;//j tekerleme ile secilen kişi bulmak için 

            Node tmp = root;//tmp tekerlemede seçilen kişi
            if (j == 1) {//eger seçilen kişi ilk kişi ise direkt o kişiyi işleme aldık
                dequeuNext();
            } else {//eger ilk deger değilse once o degeri bulup sonra kurugun basına alıcaz

                while (i < j) {//tekerlemede secilen kişi bulmak için dongü yaptık
                    tmp = tmp.next;
                    i++;
                }
                secilenKisi = tmp.data;
                Node tmp2 = root;
                int a = 0;//dongunun bitmesi için bir a tanımladık

                while (a < 2) {//dongumuz iki defa dönsün.bir kuyruk bosken bir de kuyruk bos değilken.
                    if (isEmpty1()) {
                        root1 = new Node(tmp.data);//tekerlemede seçilen müşteriyi başa aldık.
                    } else {
                        Node tmp1 = root1;

                        while (tmp2 != null) {//root kuyruğundaki her degere bakar ta ki bakacak deger kalmayana kadar.

                            if (tmp2 == tmp) {//eger tmp2 degerimiz tekerlemede seçilen müşteri ile aynı ise tmp2 diğer müşeriyi göstersin
                                tmp2 = tmp2.next;
                            } else {//eger tmp2 egerimiz tekerlemede seçilen müşteri ile aynı değilse root kuruğundaki değeri root1 kuruğuna kopyalasın.
                                tmp1.next = new Node(tmp2.data);
                                tmp1 = tmp1.next;
                                tmp2 = tmp2.next;
                            }

                        }
                    }
                    a++;//döngümüzün sonlanması için
                }
            }
            root = root1;//kopyaladığımız kuyruğu root kuyruğuna atadık.
            dequeuNext();
        }

        return secilenKisi;
    }

    @Override
    public Iterator<Customer> iterator() {

        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Customer> {

        private Node tmp = root;

        @Override
        public boolean hasNext() {
            return tmp != null;
        }

        @Override
        public Customer next() {
            Customer data = tmp.data;
            tmp = tmp.next;
            return data;
        }

    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        CrazyMarket c = new CrazyMarket(8, "portakali soydum basi cuma koydum ben bir");
        System.out.println("bos mu:" + c.isEmpty());
        Iterator itr = c.iterator();
        System.out.println("kalan musteriler:");
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }

        System.out.println("Tek parametreli constructor:");
        CrazyMarket d = new CrazyMarket(8);
        System.out.println("bos mu:" + d.isEmpty());
        Iterator iterator = d.iterator();
        System.out.println("kalan musteriler:");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }
}
