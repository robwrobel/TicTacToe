#!/bin/sh
for inputFileName in `ls ./input`;
do 
java -DIn=file -DOut=file -DInFileName=./input/$inputFileName -DOutFileName=./output/$inputFileName.out -jar  ./target/tictactoe-1.0-SNAPSHOT.jar 
done
