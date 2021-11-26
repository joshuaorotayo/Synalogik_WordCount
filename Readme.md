Joshua Orotayo - 26/11/2021

Word Counter

Assumptions: 

- All Files being read would already be downloaded locally and stored in a location where the local user can read them
- Multiple files will not be attempted at once
- Directories will not be attempted to be read at once
- Files being read are normal text files, the behav

Instructions for use:
- Ensuring that Java is installed on the local machine and the Java home variable is set
- Open a command line/powershell terminal in the location of the main source code: (src/main/java/"
- then execute command to compile the api:
    javac .\WordCounter.java
- then to begin counting a file execute
    java WordCounter [filename]
- Text files used for testing are stored in /src/test/java/data
