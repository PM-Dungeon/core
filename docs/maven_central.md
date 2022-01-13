## Veröffentlichung auf MavenCentral

Um etwas auf MavenCentral/Sonatype zu veröffentlichen, müssen verschiedene Vorgaben von MavenCentral eingehalten werden:

- Das Buildfile darf keine Abhängigkeiten enthalten, die gefährlich sein könnten
- Das Buildfile sollte die sources und die javadocs generieren
- Das Buildfile muss für alles Signaturen erstellen, dafür müssen alle Files mit GPG signiert werden
- Das Buildfile muss eine `groupId`, `artifactId` und Versionsnummer enthalten
- Das Buildfile muss verschiedene Zusatzinformationen bereitstellen, dazu zählen:
  - der Projektname,
  - die Projektbeschreibung,
  - die Projekt-URL,
  - die Lizenzinformationen,
  - die SCM-URL,
  - und die beteiligten Entwickler:
    - ID,
    - Name,
    - E-Mail.

Überprüfung:

- Nachdem ein Release erstellt und hochgeladen wurde, überprüft MavenCentral/Sonatype, ob alle Vorgaben eingehalten wurden.
- Wenn alle Vorgaben eingehalten wurden, wird das Release "gestaged" und steht innerhalb von 30 Minuten auf Central zur Verfügung.

Weitere Informationen finden Sie [hier](https://central.sonatype.org/publish/publish-guide/).
