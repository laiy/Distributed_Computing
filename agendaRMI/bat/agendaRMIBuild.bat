cd ..\src
javac -d ..\bin *.java
cd ..\bin
rmic agenda.service.AgendaServiceImplement
pause