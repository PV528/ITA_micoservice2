# Sistem za upravljanje najemov avtomobilov
## Opis Projekta
Projekt predstavlja sistem za upravljanje najemov avtomobilov. Zgrajen je s programskim jezikom Java s pomočjo Quarkusa. Omogoča protokol Grpc za upravljanje podatkov o najemih avtomobilov. Omogoča pridobivanje, vstavljanje, posodabljanje in brisanje najemov avtomobilov iz sistema. Za shranjevanje podatkov se uporablja MongoDB baza. Sistem je zasnovan kot mikrostoritev, kar omogoča njegovo integracijo v večji sistem upravljanja vozil.
## Lastnosti
+ Povezava in trajno shranjevanje podatkov v MongoDB podatkovno bazo.
+ Izpisovanje dnevnikov delovanja (logov) za lažji nadzor delovanja.
+ Izvedba Unit testov za testiranje pravilnega delovanja končnih točk in repozitorija
+ kontejnerizacija za Docker (Dockerfile in docker-compose).
## Osnovi ukazi
+ mvn clean pakage (čiščenje in izradnja projekta).
+ ./mvnw compile quarkus:dev  (zagon aplikacije v Quarkusovem razvojnem okolju (devUI).
+ docker-compose up (kontejnerizacija za docker in pogon aplikacije in baze v docker okolju).
