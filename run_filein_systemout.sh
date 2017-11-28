#!/bin/sh
for inputFileName in `ls ./input`;
do
java -DIn=file -DOut=system -DInFileName=./input/$inputFileName -jar ./target/tictactoe-1.0-SNAPSHOT.jar
done
