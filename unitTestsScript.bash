#!/bin/bash
cd "$(dirname "$0")"
echo 'Running Unit Tests...'
javac aunit.java
javac tests/TestSyntaxAnalyser.java
java tests/TestSyntaxAnalyser >> tests.txt
javac tests/TestSymbolTable.java
java tests/TestSymbolTable >> tests.txt
javac tests/TestPosition.java
java tests/TestPosition >> tests.txt
javac tests/TestMnemonic.java
java tests/TestMnemonic >> tests.txt
javac tests/TestLineStatement.java
java tests/TestLineStatement >> tests.txt
javac tests/TestInstruction.java
java tests/TestInstruction >> tests.txt
javac tests/TestIR1.java
java tests/TestIR1 >> tests.txt
javac tests/TestIR2.java
java tests/TestIR2 >> tests.txt
javac tests/TestFileReader.java
java tests/TestFileReader  >> tests.txt
java aunit tests.txt
echo 'Unit tests completed'