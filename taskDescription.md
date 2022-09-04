1.3 Java
Es soll ein Programm für die Überwachung von Ampeln in ihrer Stadt entwickelt
werden. Dieses Programm soll den Status aller Ampeln in einem festgelegten
Rhythmus abfragen. Zudem ist das Programm für den Start und das Beenden
der Ampeln zuständig.

Jeder Ampel bekommt eine Nummer und soll als eigenständiger Thread
programmiert werden und kann den Status Rot, Gelb oder Grün annehmen. Für
das Beispiel ist es nicht wichtig, dass die Ampel die Lichtzeichen in der richtigen
Reihenfolge oder Kombination einnimmt. Es soll zufällig eine der Lampen
eingeschaltet werden. Der Wechsel zwischen den Lichtzeichen erfolgt innerhalb
einer Ampel regelmäßig, d.h. beim Start der Ampel soll ein Wert zwischen 2
und 10 Sekunden per Zufall gewählt werden.

Die Anzahl der zu überwachenden Ampeln soll beim Start des Programms als
Parameter mitgegeben werden. Jede gestartete Ampel gibt aus, mit welcher
Wechselfrequenz sie eingerichtet wurde, z.B. „Ampel 1: 3 Sekunden
Schaltdauer“

Das Überwachungsprogramm, ein eigener Thread, fragt im Abstand von 3
Sekunden über die gesamte Laufzeit den Zustand aller Ampeln ab und gibt
diesen auf der Konsole summiert in jeweils einer Zeile aus, d.h. „3 grüne
Ampeln, 2 gelbe Ampeln, 4 rote Ampeln“

Nach 30 Sekunden gibt das Überwachungsprogramm das Signal an alle Ampeln
den Betrieb einzustellen. Jede Ampel, die den Betrieb einstellt, gibt den Status
„beendet“ auf der Konsole aus, z.B. „Ampel 1: Beendet“. Das
Überwachungsprogramm wartet bis alle Ampeln sich beendet haben, bevor es
sich selbst mit der Ausgabe „Überwachung beendet“ beendet.
Die Implementierung der Aufgabe erfolgt in Java. Alle Ausgaben erfolgen auf
der Konsole, es wird keine UI-Anwendung benötigt.

In Russian

Необходимо разработать программу мониторинга светофоров в их городе. Эта программа должна запрашивать состояние всех светофоров в фиксированном ритме. Программа также отвечает за запуск и остановку светофоров.

Каждому светофору присваивается номер, и он должен быть запрограммирован как независимый поток и может иметь статус красного, желтого или зеленого цвета. Например, не важно, чтобы светофор воспринимал световые сигналы в правильном порядке или комбинации. Одна из ламп должна быть включена случайным образом. Изменение между световыми сигналами происходит регулярно в пределах светофора, т. е. при включении светофора значение между 2 и 10 секундами должно быть выбрано случайным образом.

Количество светофоров, за которыми нужно следить, должно быть задано в качестве параметра при запуске программы. Каждый запущенный светофор указывает частоту смены, с которой он был настроен, например, «Светофор 1: время переключения 3 секунды».

Программа мониторинга отдельным потоком запрашивает состояние всех светофоров с интервалом в 3 секунды за все время выполнения и выводит это суммировано на консоль в каждой строке, т.е. "3 зеленых светофора, 2 желтых светофора, 4 красный сигнал светофора».

Через 30 секунд программа мониторинга дает сигнал всем светофорам прекратить работу. Каждый светофор, который перестает работать, будет отображать на консоли статус «завершен», например, «Светофор 1: завершен». Программа мониторинга ждет, пока не закончатся все светофоры, прежде чем завершиться с выводом «Контроль завершен».

Задача реализована на Java. Все выходные данные находятся на консоли, приложение пользовательского интерфейса не требуется.
