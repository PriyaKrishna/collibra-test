Download the zip file and extract it to some folder on your workstation
Go to the folder <extractedFolder>\tcp-server on the command prompt
Do a mvn clean install
Change directory to target
Run java -jar tcp-server-1.0-SNAPSHOT.jar, this starts the server and it will be waiting to receive connections from the client
Open another command prompt and navigate to the folder where testclient.jar is copied
Launch the test client by running java -jar testclient.jar
You should see an output similar to this:
C:\Users\D-YW44CN\misc\interview\collibra>java -jar testclient.jar
Running Phase 1 ---------
Phase 1: ←[31mFAIL
        I sent you an unknown command, so I expected the error for it, but I got
: SORRY, I DIDN'T UNDERSTAND THAT
Running Phase 2 ---
Phase 2: PASS
Running Phase 3 ---------
Phase 3: PASS
Running Phase 4 ---------
Phase 4: PASS
Running Phase 5 ---------
Phase 5: PASS
Running Phase 6 ---------
Phase 6: PASS

C:\Users\D-YW44CN\misc\interview\collibra>
