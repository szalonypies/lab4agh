@echo off
for /f %%f in ('dir /b *.svg') do ("c:\Program Files\Inkscape\inkscape.exe" -D -z --file=%%f --export-area-page --export-pdf=%%~nf.pdf & echo %%f done)