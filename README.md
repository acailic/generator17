# generator17
Metodologije brzog razvoj softvera-MBRS17 

# Podesavanje okruzenja

1. Instalirati MagicDraw trial verziju 18.1
--- Ukoliko imate problema sa sajtom predstavite se preko vpn kao da ste amerikanac ili slično.
2. Otvoriti projekat PluginDevelopment u Eclipse okruzenju
3. Podesiti projekat po uputstvu iz trecih vezbi (MagicDraw development in Eclipse)
-- možete da ubacite i vežbe koje dolaze direktno sa magic draw, ukoliko imate problema sa Magic Draw Main klasom.
4. Promeniti putanju do instalacionog foldera MagicDraw u build.properties
-- samo regularni karakteri u putanji
5. Promeniti putanju do foldera za generisanje koda u resources/projectoptions.properties
6. Pokrenuti target deploy preko Ant-a u build.xml
7. Pokrenuti projekat po uputstvu iz trecih vezbi
8. Kada se otvori MagicDraw, ucitati model i kliknuti na dugme Generate
--- dodatno postaviti konfiguracione fajlove za spring ( u njemu konekcija ka bazi)
--- pom.xml
--- Abstract-ne klase za Spring,  DTO, Service
--- JS fajlove za angular, main, controllers, services. CSS

# Kod je izgenerisan u zadatom folderu

# Podesavanje test projekta 

1. Instalirati MySql Server
2. Instalirati SpringToolSuite (STS)
3. Pokrenuti STS i za workspace staviti folder koji sadrzi root folder za generisanje koda (root folder za generisanje koda je ujedno i root folder projekta koji pokrecemo u STS)
4. Kopirati demo projekat u zadati workspace i otvoriti ga u STS okruzenju
5. Pokrenuti projekat kao SpringBoot aplikaciju

# Kada ponovo izgenerisemo kod, osvezimo test projekat i vidimo izmene
