set output "mejor.png"
set terminal png font 'times' 
set ylabel "Evaluación"
set xlabel "Solución aceptada" 
plot "best.txt" every 150 w d

