### Параметры запуска JVM
-Xms2048m<br>
-Xmx2048m<br>
-verbose:gc<br>
-XX:+HeapDumpOnOutOfMemoryError<br>
-XX:HeapDumpPath=./logs/dump<br>
## Serial Collector
[0.011s][info][gc] Using Serial<br>
[1.812s][info][gc] GC(0) Pause Young (Allocation Failure) 546M->1M(1979M) 3.052ms<br>
[2.255s][info][gc] GC(1) Pause Young (Allocation Failure) 547M->1M(1979M) 2.664ms<br>
[2.692s][info][gc] GC(2) Pause Young (Allocation Failure) 547M->1M(1979M) 2.114ms<br>
[3.141s][info][gc] GC(3) Pause Young (Allocation Failure) 547M->1M(1979M) 1.989ms<br>
[3.580s][info][gc] GC(4) Pause Young (Allocation Failure) 547M->1M(1979M) 1.990ms<br>
[4.019s][info][gc] GC(5) Pause Young (Allocation Failure) 547M->1M(1979M) 1.997ms<br>
[4.458s][info][gc] GC(6) Pause Young (Allocation Failure) 547M->1M(1979M) 2.092ms<br>
[4.895s][info][gc] GC(7) Pause Young (Allocation Failure) 547M->1M(1979M) 1.987ms<br>
[5.339s][info][gc] GC(8) Pause Young (Allocation Failure) 547M->1M(1979M) 2.036ms<br>
[5.779s][info][gc] GC(9) Pause Young (Allocation Failure) 547M->1M(1979M) 2.032ms<br>
[6.225s][info][gc] GC(10) Pause Young (Allocation Failure) 547M->1M(1979M) 2.054ms
## Parallel Collector
[0.011s][info][gc] Using Parallel<br>
[1.812s][info][gc] GC(0) Pause Young (Allocation Failure) 512M->1M(1963M) 1.960ms<br>
[2.208s][info][gc] GC(1) Pause Young (Allocation Failure) 513M->1M(1963M) 1.072ms<br>
[2.619s][info][gc] GC(2) Pause Young (Allocation Failure) 513M->1M(1963M) 0.961ms<br>
[3.009s][info][gc] GC(3) Pause Young (Allocation Failure) 514M->1M(1963M) 0.958ms<br>
[3.400s][info][gc] GC(4) Pause Young (Allocation Failure) 513M->1M(1963M) 0.936ms<br>
[3.817s][info][gc] GC(5) Pause Young (Allocation Failure) 513M->1M(2046M) 1.089ms<br>
[4.419s][info][gc] GC(6) Pause Young (Allocation Failure) 679M->1M(2046M) 1.619ms<br>
[4.934s][info][gc] GC(7) Pause Young (Allocation Failure) 679M->1M(2046M) 0.344ms<br>
[5.454s][info][gc] GC(8) Pause Young (Allocation Failure) 679M->1M(2044M) 0.304ms<br>
[5.971s][info][gc] GC(9) Pause Young (Allocation Failure) 679M->1M(2045M) 0.356ms<br>
[6.483s][info][gc] GC(10) Pause Young (Allocation Failure) 678M->1M(2045M) 0.324ms
##### Тратит меньше времени на очистку памяти
## CMS Collector
[0.013s][info][gc] Using Concurrent Mark Sweep<br>
[1.515s][info][gc] GC(0) Pause Young (Allocation Failure) 266M->1M(2014M) 38.786ms<br>
[1.743s][info][gc] GC(1) Pause Young (Allocation Failure) 267M->2M(2014M) 3.713ms<br>
[1.946s][info][gc] GC(2) Pause Young (Allocation Failure) 268M->2M(2014M) 3.482ms<br>
[2.148s][info][gc] GC(3) Pause Young (Allocation Failure) 268M->2M(2014M) 3.679ms<br>
[2.356s][info][gc] GC(4) Pause Young (Allocation Failure) 268M->1M(2014M) 3.898ms<br>
[2.557s][info][gc] GC(5) Pause Young (Allocation Failure) 268M->2M(2014M) 3.483ms<br>
[2.768s][info][gc] GC(6) Pause Young (Allocation Failure) 268M->2M(2014M) 7.095ms<br>
[2.977s][info][gc] GC(7) Pause Young (Allocation Failure) 268M->1M(2014M) 2.777ms<br>
[3.187s][info][gc] GC(8) Pause Young (Allocation Failure) 268M->1M(2014M) 3.072ms<br>
[3.398s][info][gc] GC(9) Pause Young (Allocation Failure) 267M->1M(2014M) 3.106ms<br>
[3.609s][info][gc] GC(10) Pause Young (Allocation Failure) 267M->1M(2014M) 2.782ms
##### Заметно чаще остальный освобождает память, то есть если в программе используется много молодых объектов, то такой сборщик мусора подойдет больше
## G1 Collector
[0.026s][info][gc] Using G1<br>
[0.041s][info][gc] Periodic GC disabled<br>
[1.325s][info][gc] GC(0) Pause Young (Normal) (G1 Evacuation Pause) 102M->1M(2048M) 2.448ms<br>
[2.011s][info][gc] GC(1) Pause Young (Normal) (G1 Evacuation Pause) 599M->1M(2048M) 1.906ms<br>
[2.556s][info][gc] GC(2) Pause Young (Normal) (G1 Evacuation Pause) 608M->1M(2048M) 1.942ms<br>
[3.114s][info][gc] GC(3) Pause Young (Normal) (G1 Evacuation Pause) 625M->1M(2048M) 2.442ms<br>
[3.715s][info][gc] GC(4) Pause Young (Normal) (G1 Evacuation Pause) 650M->1M(2048M) 2.375ms<br>
[4.325s][info][gc] GC(5) Pause Young (Normal) (G1 Evacuation Pause) 684M->1M(2048M) 2.141ms<br>
[5.004s][info][gc] GC(6) Pause Young (Normal) (G1 Evacuation Pause) 726M->1M(2048M) 2.162ms<br>
[5.742s][info][gc] GC(7) Pause Young (Normal) (G1 Evacuation Pause) 780M->1M(2048M) 2.100ms<br>
[6.539s][info][gc] GC(8) Pause Young (Normal) (G1 Evacuation Pause) 851M->1M(2048M) 2.113ms<br>
[7.394s][info][gc] GC(9) Pause Young (Normal) (G1 Evacuation Pause) 939M->1M(2048M) 2.363ms<br>
[8.343s][info][gc] GC(10) Pause Young (Normal) (G1 Evacuation Pause) 1034M->1M(2048M) 2.226ms<br>
[9.398s][info][gc] GC(11) Pause Young (Normal) (G1 Evacuation Pause) 1157M->1M(2048M) 2.327ms<br>
[10.498s][info][gc] GC(12) Pause Young (Normal) (G1 Evacuation Pause) 1227M->1M(2048M) 2.250ms<br>
[11.596s][info][gc] GC(13) Pause Young (Normal) (G1 Evacuation Pause) 1227M->1M(2048M) 2.031ms<br>
[12.659s][info][gc] GC(14) Pause Young (Normal) (G1 Evacuation Pause) 1227M->1M(2048M) 2.007ms<br>
[13.711s][info][gc] GC(15) Pause Young (Normal) (G1 Evacuation Pause) 1227M->1M(2048M) 2.284ms<br>
[14.830s][info][gc] GC(16) Pause Young (Normal) (G1 Evacuation Pause) 1228M->1M(2048M) 1.120ms<br>
[15.949s][info][gc] GC(17) Pause Young (Normal) (G1 Evacuation Pause) 1228M->1M(2048M) 1.056ms<br>
[17.031s][info][gc] GC(18) Pause Young (Normal) (G1 Evacuation Pause) 1228M->1M(2048M) 1.126ms<br>
[18.063s][info][gc] GC(19) Pause Young (Normal) (G1 Evacuation Pause) 1228M->1M(2048M) 1.192ms<br>
[19.090s][info][gc] GC(20) Pause Young (Normal) (G1 Evacuation Pause) 1228M->1M(2048M) 1.150ms<br>
[20.121s][info][gc] GC(21) Pause Young (Normal) (G1 Evacuation Pause) 1228M->1M(2048M) 1.125ms
##### Заметно реже остальных освобождает память, значит приложение будет дольше работать не прерываясь