ETAPA 2

Player
  - insert (cand un player nou isi face cont)
  - read (cand un player existent se conecteaza)
  - update (la finalul jocului, la scor)
  - delete (user-ul isi sterge contul)
Scoreboard
  - delete (se sterge scoreboard-ul anterior)
  - insert (se insereaza datele noi)
  - read (se afiseaza)
Runda
  - insert (cand incepe prima runda)
  - update (cand se schimba tipul rundei)
  - read (la fiecare runda se afiseaza numele)
  - delete (la finalul jocului este sters din baza de date)
Optiuni
  - insert (cand alege o optiune din meniu)
  - delete (cand isi sterge contul, se sterg si optiunile inregistrate)



OBIECTE FOLOSITE
  1. Clasa Carte
  2. Clasa Player
  3. Clasa PlayerHuman
  4. Clasa Joc
  5. Clasa Runda
  6. Clasa RundaLevate
  7. Clasa RundaCupe
  8. Clasa RundaDame
  9. Clasa RundaPopa
  10. Clasa RundaTotale
  11. Clasa RundaWhist
  12. Clasa Optiuni
  13. Clasa Meniu
  14. Clasa ClasamentJoc
  15. Clasa ScoreBoard
  16. Clasa Tabla

ACIUNI IN SISTEM
  1. Userul se poate autentifica. In cazul in care acesta are cont, sistemul ii cere parola. In caz contrar, parola introdusa este folosita pentru a crea un cont nou.
  2. Userul conectat poate citi din meniu regulamentul jocului
  3. Userul conectat poate vizualiza din meniu clasamentul jucatorilor in functie de scor
  4. Pentru fiecare joc,, sistemul creeaza 3 boti care stiu sa joace, pentru a juca alaturi de user
  5. Pentru fiecare runda, sistemul amesteca cartile si le imparte celor 4 jucatori (player + 3 boti)
  6. Pentru fiecare runda, sistemul alege aleatoriul primul jucator. După, oridinea este in sensul acelor de ceasornic
  7. O rundă este alcătuit din 13 „mâini”. Pentru fiecare mână, sistemul desemnează jucătorul de start, ca fiind cel care a luat mâna anterioară.
  8. Sistemul gestionează automat ordinea în care se joacă fiecare rundă.
  9. Pentru fiecare mână, sistemul oferă o vizualizare grafică a cărților care se joacă
  10. Pentru userul autentificat, sistemul ii arată jucătorului numai cărțile pe care le poate juca, pentru a evita greșelile
  11. După fiecare rundă, sistemul afișează scorurile parțiale pentru fiecare jucător.
  12. La finalul jocului, este afișat clasamentul final
  13. In functie de pozitiia obtinuta, scorul general, diferit de cel pe care il are in timpul jocului, este actualizat
