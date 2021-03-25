#!/bin/bash
cd "$(dirname "$0")"
echo 'Running Unit Tests...'
javac aunit.java
javac analysers/TestLexicalAnalyser1.java 
java analysers/TestLexicalAnalyser1 >> tests.txt
javac analysers/TestLexicalAnalyser2.java 
java analysers/TestLexicalAnalyser2 >> tests.txt
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
javac generators/TestListing1.java 
java generators/TestListing1 >> tests.txt
javac generators/TestListing2.java 
java generators/TestListing2 >> tests.txt
javac generators/TestListing3.java 
java generators/TestListing3 >> tests.txt
javac generators/TestListing4.java 
java generators/TestListing4 >> tests.txt
javac analysers/TestSyntaxAnalyser1.java 
java analysers/TestSyntaxAnalyser1 >> tests.txt
javac analysers/TestSyntaxAnalyser2.java 
java analysers/TestSyntaxAnalyser2 >> tests.txt
javac tests/TestRange.java 
java tests/TestRange >> tests.txt


java aunit tests.txt
echo 'Unit tests completed'

