# N11 Bootcamp Final Project

Proje kullanıcı servisi, restorant servisi ve log servisi olmak üzere üç servisten oluşmaktadır. 

## Özellikler
Kullanıcı servisi kullanıcı işlemlerini ve kullanıcıların restorantlar hakkında yaptığı değerlendirme işlemlerini yapabilmenizi sağlar. Ayrıca kullanıcıya restorant puanı ve yakınlık bilgisine göre restorant önerisi sunar. 

Restorant servisi ise restorant bilgilerinin tutulduğu ve listelendiği servistir.

Log servisi ise diğer servislerden gelen bilgi veya hata loglarının tutulmasını sağlar.

## Teknolojiler
* Tüm servislerde `Spring Boot` teknolojisi kullanılmıştır.
* Restorant servisinde `Java 17`, kullanıcı ve log servisinde ise `Java 21` kullanılmıştır.
* Log servisinde log işlemleri için `Apache Kafka` kullanılmıştır.
* Kullanıcı ve log servisinde veriler `Postgre` veri tabanında, Restorant servisinde ise veriler `Apache Solr` üzerinde tutulmaktadır.

## Kurulum
1. Projeyi çalıştırmak için klonlayınız.
 - git clone https://github.com/merveyilmas/N11BootcampFinalProject.git
2. Docker üzerinde postgre veri tabanını çalıştırmak için terminal ekranında aşağıdaki komutları çalıştırınız.
 - docker pull postgres
 - docker run -p 5432:5432 -e POSTGRES_PASSWORD=12345 postgres:latest
3. Docker üzerinde apache solr'ı çalıştırmak için klonladığınız projede bulunan restorant servisininin altındaki docker-compose.yml dosyasını bulunuz. Terminal ekranında ilgili dizine giderek aşağıdaki komut ile çalıştırınız.
 - docker-compose up --build
4. Docker üzerinde apache kafka'yı çalıştırmak için klonladığınız projede bulunan log servisininin altındaki docker-compose.yml dosyasını bulunuz. Terminal ekranında ilgili dizine giderek aşağıdaki komut ile çalıştırınız. Ve Kafka'nın Docker dışındaki uygulamalar tarafından erişilebilmesi için bilgisayarınızın IP adresini bilgisayarınızda bulunan hosts.txt (/etc/hosts) dosyasına "192.168.1.3 kafka" şeklinde tanımlayınız.
 - docker-compose up --build
5. Son olarak ise ilgili ide üzerinde tüm servisleri ayağa kaldırınız. Proje kullanıma hazırdır.

## Kullanım
Oluşturduğum "swagger docs" klasöründe servislerin apilerine ulaşabilirsiniz. 
[Bu linke](https://drive.google.com/file/d/1l8ojWyygxOSZ9cgfM5i6eNv9YEiBwUcZ/view?usp=sharing) tıklayarak proje kullanım dökümanına ulaşabilirsiniz.

## Lisans
Bu proje MIT lisansı altında lisanslanmıştır. Daha fazla bilgi için LICENSE dosyasını inceleyin.