# 🌐 Java Socket Tabanlı Basit Web Sunucusu

Bu proje, Ağ (Network) Programlama konseptlerini anlamak amacıyla geliştirilmiş, harici bir kütüphane (Spring, Tomcat, Nginx vb.) **kullanılmadan** tamamen Java'nın çekirdek (core) kütüphaneleriyle sıfırdan yazılmış bir HTTP web sunucusudur.

## 📌 Proje Amacı ve Kapsamı
Uygulama, temel İstemci-Sunucu (Client-Server) mimarisinde çalışır. Dışarıdan gelen web tarayıcısı bağlantılarını dinler ve bu isteklere standartlara uygun bir HTTP yanıtı (HTML formatında) döndürür.

### ✨ Temel Özellikler
- **Özel Port Dinleme:** Sunucu, bağlantıları spesifik olarak `1989` numaralı port üzerinden kabul eder.
- **Eşzamanlılık (Concurrency):** `Thread` (İş parçacığı) altyapısı sayesinde aynı anda birden fazla tarayıcıdan gelen istekleri tıkanmadan eşzamanlı olarak işleyebilir.
- **Dinamik HTML Yanıtı:** Bağlantı sağlandığında istemciye inline-CSS ile tasarlanmış, renk ve font ayarlamaları yapılmış bir web sayfası sunar. Sayfa içeriğinde H1, H2 etiketleri ve kişisel biyografi yer almaktadır.
- **SOLID Prensiplerine Uyumluluk:** Proje kodlanırken *Tek Sorumluluk Prensibi (Single Responsibility Principle - SRP)* gözetilmiştir. Sunucuyu ayağa kaldıran sınıf ile HTML içeriğini üreten sınıf birbirinden tamamen ayrılmıştır.

## 🛠️ Kullanılan Teknolojiler
- **Java (JDK 8+)**: Temel programlama dili.
- **java.net.ServerSocket & Socket**: TCP bağlantılarının ve ağ geçitlerinin (port) yönetimi için.
- **java.io (BufferedReader, PrintWriter)**: HTTP Request (İstek) başlıklarını okumak ve HTTP Response (Yanıt) verilerini ağ üzerinden istemciye iletmek için.

## 📂 Proje Yapısı
Proje, görev ayrımını sağlamak adına tek bir dosya (`WebSunucu.java`) içerisinde iki temel sınıftan oluşmaktadır:
1. `WebSunucu`: Ana sınıftır. Belirtilen portu rezerve eder, sonsuz bir döngüde dışarıdan gelen bağlantıları dinler ve kabul edilen her bağlantıyı yeni bir Thread'e atar.
2. `Istemciİslemcisi`: Gelen isteği işleyen sınıftır. Bağlantı sağlandığında gerekli HTTP başlıklarını (`200 OK`, `text/html`) yazar ve tasarlanmış HTML kodlarını tarayıcıya göndererek bağlantıyı güvenli bir şekilde kapatır.

## 🚀 Kurulum ve Çalıştırma

Projeyi yerel ortamınızda test etmek için aşağıdaki adımları sırasıyla izleyin:

**1. Projeyi Klonlayın veya İndirin**
```bash
git clone <github-repo-linkiniz>
cd <proje-klasör-dizini>
