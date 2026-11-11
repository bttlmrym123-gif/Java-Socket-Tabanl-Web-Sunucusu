import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class WebSunucu {
    // İstenen 1989 portu
    private static final int PORT = 1989;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Web sunucusu baslatildi. Tarayicidan http://localhost:" + PORT + " adresine gidebilirsiniz.");
            
            // Sunucu sürekli açık kalıp istekleri dinlemelidir (Sonsuz döngü)
            while (true) {
                Socket clientSocket = serverSocket.accept();
                // Her yeni bağlantıyı ayrı bir thread'de işlemek, SOLID ve performans açısından önemlidir
                new Thread(new Istemciİslemcisi(clientSocket)).start();
            }
        } catch (IOException e) {
            System.err.println("Sunucu baslatilamadi: " + e.getMessage());
        }
    }
}

// Tek Sorumluluk (SRP): Bu sınıfın tek amacı gelen HTTP isteğine uygun HTML cevabı üretmektir.
class Istemciİslemcisi implements Runnable {
    private Socket clientSocket;

    public Istemciİslemcisi(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream())
        ) {
            // Tarayıcıdan gelen HTTP request başlıklarını okuyup geçiyoruz
            String line;
            while ((line = in.readLine()) != null && !line.isEmpty()) {
                // Request okuma işlemi (Bu basit projede içeriğe bakmamıza gerek yok)
            }

            // Gerekli HTTP Response Başlıkları (Tarayıcının HTML okuyabilmesi için zorunludur)
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: text/html; charset=UTF-8");
            out.println(""); // Başlıklar ile içerik arasındaki boş satır (Zorunlu)

            // İstenen HTML İçeriği (H1, H2, Renk, Font biçimlendirmesi)
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head><title>Ödev - Web Sunucusu</title></head>");
            out.println("<body style='font-family: \"Segoe UI\", Tahoma, Geneva, Verdana, sans-serif; background-color: #f8f9fa; color: #333; padding: 40px;'>");
            out.println("<div style='max-width: 600px; margin: 0 auto; background: white; padding: 20px; border-radius: 10px; box-shadow: 0 4px 8px rgba(0,0,0,0.1);'>");
            
            // Kendi bilgilerini buraya girmelisin
            out.println("<h1 style='color: #9b2305; border-bottom: 2px solid #3498db; padding-bottom: 10px;'>Meryem Battal</h1>");
            out.println("<h2 style='color: #9b2305;'>Öğrenci No: 1250505007</h2>");
            
            out.println("<h3 style='color: #9b2305; margin-top: 20px;'>Kısa Biyografi</h3>");
            out.println("<p style='font-size: 16px; line-height: 1.6; text-align: justify;'>");
            out.println("Merhaba, ben Bilgisayar ve Yazılım Mühendisliği alanında eğitim gören, teknolojiye ve yazılım geliştirmeye tutkulu bir öğrenciyim. ");
            out.println("Java ve C gibi dillerde nesneye yönelik programlama, veri yapıları ve algoritma geliştirme üzerine çalışmalar yürütüyorum. ");
            out.println("Boş zamanlarımda dizi izlemeyi, arkadaşlarımla vakit geçirmeyi ve yeni yazılım teknolojilerini araştırmayı seviyorum. ");
            out.println("Gelecekte sağlam mimarilere sahip, kullanıcı dostu yazılım sistemleri geliştiren bir mühendis olmayı hedefliyorum.");
            out.println("</p>");
            
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
            out.flush(); // Tampon belleği boşaltıp veriyi tarayıcıya gönderir

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
