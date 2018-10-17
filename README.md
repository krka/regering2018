## Bygg

Kräver Java 8 eller senare.

Kompilera med `javac Gov.java`

Kör med `java Gov`

## Analys

Filteringen av giltiga regeringsalternativ baseras på bedömning om hur villiga
olika partier är att samregera.

S, V, MP, C, och L är väldigt tydliga med att de inte vill samarbeta med SD.
(M och KD verkar mer flexibla där men vågar inte spräcka alliansen.)

Det verkar även orimligt att V eller S samarbetar med M eller KD

Det som kvarstår är alltså:
```
179: S C V L 
175: S C V MP 
195: S C V L MP
```

Här är `195: S C V L MP` onödigt stort. Det räcker att ta med L eller MP men
det finns ingen poäng med att ta med båda.

Antingen blir det då en klassisk S-V-MP med stöd av C eller S-V C-L.

Dock är det tveksamt att C eller L skulle acceptera att samarbeta med V.


