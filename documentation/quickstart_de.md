## Einleitung

Dieses Dokument liefert einen Einstieg in das PM-Dungeon. Es erläutert die Installation des Frameworks und die ersten Schritte, um eigene Inhalte zum Dungeon hinzuzufügen. Es dient als Grundlage für alle weiteren Praktika. Lesen Sie das Dokument daher aufmerksam durch und versuchen Sie sich zusätzlich selbst mit dem Aufbau vertraut zu machen.

## Installation

Um das PM-Dungeon-Framework zu nutzen haben Sie zwei Möglichkeiten.

1. Erstellen Sie sich ein Fork des [`desktop`-Repository](https://github.com/PM-Dungeon/desktop) und ziehen Sie sich einen lokalen Klon auf Ihr Gerät. (Empfohlen)
2. Erstellen Sie eigenständig ein neues Projekt und binden Sie [`core`](https://repo1.maven.org/maven2/io/github/pm-dungeon/core/) in Ihr Projekt ein. Beachten Sie dabei, dass Sie damit jede glich das "Backend" des Frameworks implementieren. Um das "Frontend" nutzen zu können, benötigen Sie einen libGDX-Launcher und die entsprechenden Abhängigkeiten. Sie können die Nötigen Abhängigkeiten sowie die Implementierung eines libGDX-Launcher im [`desktop`-Repository](https://github.com/PM-Dungeon/desktop) nachschauen. 

## Arbeiten mit dem Framework

Zu Beginn einige grundlegende Prinzipien, die Sie verstanden haben sollten, bevor Sie mit dem Dungeon arbeiten.

- Das PM-Dungeon wird mithilfe des Cross-Plattform Java-Frameworks [`libGDX`](https://libgdx.com) umgesetzt. Dieses ist im `core`- und `desktop`-Projekt bereits integriert, Sie müssen dieses nicht extra installieren. Die Ihnen zur Verfügung gestellten Vorgaben sind so umgesetzt, dass Sie kein tieferes Verständnis für das Framework benötigen, um die Aufgaben zu lösen. Sollten Sie allerdings einmal auf Probleme stoßen, kann es unter Umständen helfen, einen Blick in die Dokumentation von `libGDX` zu werfen.
- Das Framework ist in ein Frontend (`desktop`) und ein Backend (`core`) aufgeteilt. 
- Das Frontend setzt die Parameter, erzeugt ein Fenster und startet die Anwendung.
- Das Backend liefert die Schnittstellen mit denen Sie arbeiten. 
- Sie selbst schreiben die Logik des Spiels. Sie können Ihren Code entweder in ein eigenes Projekt/Submodul (Empfohlen) oder in einem Package im `desktop` 
- Bis auf seltene (dokumentierte) Ausnahmen werden Sie nicht gezwungen sein an den Vorgaben Änderungen durchzuführen. 
- Sie werden im laufe der Praktika verschiedene Assets benötigen. Diese müssen Standartmäßig im `asset` Verzeichnis liegen. Sie können das Standartverzeichnis in der `build.gradle` anpassen.
  - Standartpfad für Texturen: **tbd**
  - Standartpfad für Level: **tbd**

## Grundlagen 

Bevor wir mit der eigentlichen Implementierung des Spiels anfangen, eine kurze Erklärung über den Aufbau des Frameworks.

- Das Framework verwendet sogenannte `Controller` um die einzelnen Aspekte des Spiels zu managen und Ihnen das Leben einfacher zu machen. 

  - `EntityController`: Dieser verwaltet alle "aktiven" Elemente wie Helden, Monster, Items etc. 
  - `LevelAPI`: Kümmert sich darum, dass neue Level erzeugt und geladen werden. 
  - `HUDController`: Verwaltet alle Bildschirmanzeigen die Sie implementieren.  
  - `MainController` Verwaltet die anderen `Controller` und beinhaltet die Game-Loop. Ihre Implementierung wird Teil des `MainController` 
- Game-Loop: Die Game-Loop ist die wichtigste Komponente des Spieles. Sie ist eine Endlosschleife, welche einmal pro [Frame](https://de.wikipedia.org/wiki/Bildfrequenz) aufgerufen wird. Das Spiel läuft in 30-FPS (also 30 *frames per seconds*, zu Deutsch 30 Bildern pro Sekunde), die Game-Loop wird also 30mal in der Sekunde aufgerufen. Alle Aktionen, die wiederholt ausgeführt werden müssen, wie zum Beispiel das Bewegen und Zeichnen von Figuren, müssen innerhalb der Game-Loop stattfinden. Das Framework ermöglicht es Ihnen, eigene Aktionen in die Game-Loop zu integrieren. Wie genau das geht, erfahren Sie im Laufe dieser Anleitung. *Hinweis: Die Game-Loop wird automatisch ausgeführt, Sie müssen sie nicht aktiv aufrufen.*
- Zusätzlich existieren noch eine Vielzahl an weiteren Helferklassen mit dem sie mal mehr oder mal weniger Kontakt haben werden. 
  - `Painter`: Kümmert sich darum, dass die Inhalte grafisch dargestellt werden. 
  - `DungeonCamera`: Ihr Auge in das Dungeon. 
  - Unterschiedliche Interfaces, welche Sie im Verlauf dieses Dokumentes kennen lernen werden. 

## Erster Start

In diesen Abschnitt werden alle Schritte erläutert, die zum ersten Start der Anwendung führen.

- Legen Sie sich eine neue Klasse an. Der Einfachheit halber wird diese Klasse im weiteren Verlauf `MyGame` genannt. Sie können die Klasse aber nennen wie Sie wollen.

- `MyGame` muss von `MainController` erben.

- Implementieren Sie alle notwendigen Methoden. *Hinweis: Weitere Informationen zu diesen Methoden erfolgen im Laufe der Dokumentation.*

  - `setup()`
  - `beginFrame()`
  - `enFrame()`
  - `onLevelLoad()`
  
- Fügen Sie die `main`-Methode hinzu

  ```
  public static void main(String[] args) {
      DesktopLauncher.run(new MyGame());
  }
  ```

Das Spiel sollte nun starten und Sie sollten einen Ausschnitt des Level sehen können. 

Bevor wir nun unseren Helden implementieren sollten wir verstehen, was genau der `MainController` eigentlich ist. Wie der Name schon vermuten lässt, ist dies die Haupt-Steuerung des Spiels. Er bereitet alles für den Start des Spieles vor, verwaltet die anderen Controller und enthält die Game-Loop. Wir nutzen `MyGame` um selbst in die Game-Loop einzugreifen und unsere eigenen Objekte wie Helden und Monster zu verwalten. Der `MainController` ist der Punkt, an dem alle Fäden des Dungeons zusammenlaufen. Im Folgenden wird Ihnen erklärt, wie Sie erfolgreich mit dem `MainController` arbeiten.

## Eigener Held

## Der bewegte (animierte) Held

## Wo bin ich?

## Sie werden platziert

## WASD oder die Steuerung des Helden über die Tastatur

## Ein letzter Überblick

## Head-up-Display (HUD)

## Abschlussworte

## Zusätzliche Funktionen

### Sound

### Text