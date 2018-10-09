# PAUL Parser

Hello, I am PAUL Parser,  
a parser for PAUL extended participants list.  
Find the list at: PAUL > My
Courses > Term administration > Course Overview > Event > Participants >
Extended list.  
Use me to create CSV files.

## Download and run

- Get the latest [release](https://github.com/dice-group/PaulParser/releases)
- Start a terminal and type  
  `java -jar paul-parser.jar`

## Options

```
usage: java -jar paul-parser.jar
 -i,--input <file>       Input text file, mandatory
 -d,--demiliter <char>   Character between entries, default: ','
 -o,--output <file>      Output CSV file, default: print to terminal
 -t,--types <chars>      Type of entries to include, default: all
types:
 i  IMT university id
 f  first name
 s  surname
 m  matriculation number
 e  email address
```

## Example

### Input file

```
No.	Matric. no.	Name	First name	Adviser	Class	Module reference	Obligation level	Uni-mail	Subjects	Aimed degree	Subject semesters	
Accepted
1	7654321	Smith	Adam			M.007.4211 Project Group	Voluntary	adamsmith@mail.uni-paderborn.de		Master of Science	1	
2	6754321	Doe	John			M.007.4211 Project Group	Voluntary	johndoe@mail.uni-paderborn.de		Master of Science	42
```

### Command

```
java -jar paul-parser.jar -i i.txt -o o.csv -d ";" -t fs
```

### Output file

```
John;Doe
Adam;Smith
```

## Credits

DICE - Data Science Group  
Paderborn University  
[dice.cs.uni-paderborn.de](http://dice.cs.uni-paderborn.de/)