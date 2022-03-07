
# Huffman 

Το πρόγραμμα Encode υπολογίζει έναν πίνακα συχνοτήτων για τα γράμματα με ASCII από 0 έως και 127,
παράγει ένα δέντρο Huffman (tree.dat) και κωδικοποιεί το κάθε γράμμα. Τέλος διαβάζει ένα αρχείο 
που να περιέχει ASCII χαρακτήρες και αντικαταστεί κάθε χαρακτήρα με την κωδικοποιημένη μορφή του.
Το πρόγραμμα Decode διαβάζει το αρχείο tree.dat, ένα κωδικοποιημένο αρχείο και το αποκωδικοποιεί
σε μορφή ASCII.


# Authors

it21906 Διογένης Αντωνόπουλος

it21996 Άρτεμις Στεφανίδου

it219118 Βασίλης Χύσκαϊ

## Usage

Compile using 

```
mvn compile
```

Create a jar using 

```
mvn package
```

Run main using 

```
java -cp target/ergasia-1.0-SNAPSHOT.jar org.hua.ergasia.Encode "input filename" "output filename"
```
Προσοχή: Πρέπει το αρχείο input_filename να υπάρχει και να περιέχει χαρακτήρες ASCII.

```
java -cp target/ergasia-1.0-SNAPSHOT.jar org.hua.ergasia.Decode "input filename" "output filename"
```
Προσοχή: Πρέπει το αρχείο input_filename να υπάρχει και να περιέχει το κωδικοποιημένο κείμενο Huffman.
