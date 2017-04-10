set output "../plot.svg"
set terminal svg dynamic fname 'arial' lw .08
set ylabel "Evaluación"
set xlabel "Solución aceptada" 
plot "exec.txt" every 200 w d

