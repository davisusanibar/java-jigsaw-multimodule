Java Module System
==================

MAVEN MODULES
-------------

````shell
[INFO] totestmultimodule .................................. SUCCESS [  0.260 s]
[INFO] module-child-parent-b .............................. SUCCESS [  0.014 s]
[INFO] child-b-2 .......................................... SUCCESS [  0.979 s]
[INFO] module-child-parent-a .............................. SUCCESS [  0.004 s]
[INFO] child-a-1 .......................................... SUCCESS [  0.116 s]
[INFO] child-a-2 .......................................... SUCCESS [  0.120 s]
````

MAVEN MODULE: MODULE-CHILD-PARENT-B -> CHILD-B-2
------------------------------------------------

````shell
# Compile your code as usual through maven (maven are able to detect if module-info.java is created)
$ mvn clean install

# Test java jar as usual through classpath
$ java -cp target/child-b-2-1.0-SNAPSHOT.jar childb2.Main
Hi child-b-2!

# Test java jar as new way through module system
$ java --module-path target/child-b-2-1.0-SNAPSHOT.jar --module child.b/childb2.Main
Hi child-b-2!
````

MAVEN MODULE: MODULE-CHILD-PARENT-A -> CHILD-A-1 -> (needs) -> MODULE-CHILD-PARENT-B -> CHILD-B-2
-------------------------------------------------------------------------------------------------

````shell
# Compile your code as usual through maven (maven are able to detect if module-info.java is created)
$ mvn clean install
# Classpath mode:
$ java -cp target/child-a-1-1.0-SNAPSHOT.jar:../../module-child-parent-b/childb2/target/child-b-2-1.0-SNAPSHOT.jar childa1.Main 
no-core-child-a-1 -> consume -> core-child-b-2
# Module mode
$ java --module-path target/child-a-1-1.0-SNAPSHOT.jar:../../module-child-parent-b/childb2/target/child-b-2-1.0-SNAPSHOT.jar -m childa/childa1.Main
no-core-child-a-1 -> consume -> core-child-b-2
````

MAVEN MODULE: MODULE-CHILD-PARENT-A -> CHILD-A-2 -> (needs) -> MODULE-CHILD-PARENT-A -> CHILD-A-1 -> (needs) -> MODULE-CHILD-PARENT-B -> CHILD-B-2
--------------------------------------------------------------------------------------------------------------------------------------------------

````shell
# Compile your code as usual through maven (maven are able to detect if module-info.java is created)
$ mvn clean install
# Classpath mode:
$ java -cp  target/child-a-2-1.0-SNAPSHOT.jar:../child-a-1/target/child-a-1-1.0-SNAPSHOT.jar:../../module-child-parent-b/childb2/target/child-b-2-1.0-SNAPSHOT.jar childa2.Main
no-core-child-a-2 -> consume -> no-core-child-a-1 -> consume -> core-child-b-2
# Module mode
$ java --module-path target/child-a-2-1.0-SNAPSHOT.jar:../child-a-1/target/child-a-1-1.0-SNAPSHOT.jar:../../module-child-parent-b/childb2/target/child-b-2-1.0-SNAPSHOT.jar -m child.a/childa2.Main
no-core-child-a-2 -> consume -> no-core-child-a-1 -> consume -> core-child-b-2
````

